package rm_scott.kberestbrewery.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import rm_scott.kberestbrewery.domain.Beer;
import rm_scott.kberestbrewery.repositories.BeerRepository;
import rm_scott.kberestbrewery.web.controller.NotFoundException;
import rm_scott.kberestbrewery.web.mappers.BeerMapper;
import rm_scott.kberestbrewery.web.model.BeerDto;
import rm_scott.kberestbrewery.web.model.BeerPagedList;
import rm_scott.kberestbrewery.web.model.BeerStyleEnum;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jt on 2019-04-20.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ")
    @Override
    public BeerPagedList listBeers ( String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand ) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if (showInventoryOnHand) {
            beerPagedList = new BeerPagedList(beerPage
                                                      .getContent()
                                                      .stream()
                                                      .map(beerMapper::beerToBeerDtoWithInventory)
                                                      .collect(Collectors.toList()),
                                              PageRequest
                                                      .of(beerPage.getPageable().getPageNumber(),
                                                          beerPage.getPageable().getPageSize()),
                                              beerPage.getTotalElements());
        } else {
            beerPagedList = new BeerPagedList(beerPage
                                                      .getContent()
                                                      .stream()
                                                      .map(beerMapper::beerToBeerDto)
                                                      .collect(Collectors.toList()),
                                              PageRequest
                                                      .of(beerPage.getPageable().getPageNumber(),
                                                          beerPage.getPageable().getPageSize()),
                                              beerPage.getTotalElements());
        }

        return beerPagedList;
    }

    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
    @Override
    public BeerDto getById ( UUID beerId, Boolean showInventoryOnHand ) {
        if (showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(
                    beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
            );
        } else {
            return beerMapper.beerToBeerDto(
                    beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
            );
        }
    }

    @Override
    public BeerDto saveNewBeer ( BeerDto beerDto ) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer ( UUID beerId, BeerDto beerDto ) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(BeerStyleEnum.PILSNER.valueOf(beerDto.getBeerStyle()));
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Cacheable(cacheNames = "beerUpcCache")
    @Override
    public BeerDto getByUpc ( String upc ) {
        return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
    }

    @Override
    public void deleteBeerById ( UUID beerId ) {
        beerRepository.deleteById(beerId);
    }
}
