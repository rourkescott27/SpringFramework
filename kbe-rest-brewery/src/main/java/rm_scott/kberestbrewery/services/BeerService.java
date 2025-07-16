package rm_scott.kberestbrewery.services;

import org.springframework.data.domain.PageRequest;

import rm_scott.kberestbrewery.web.model.BeerDto;
import rm_scott.kberestbrewery.web.model.BeerPagedList;
import rm_scott.kberestbrewery.web.model.BeerStyleEnum;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
public interface BeerService {
    BeerPagedList listBeers ( String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand );

    BeerDto getById ( UUID beerId, Boolean showInventoryOnHand );

    BeerDto saveNewBeer ( BeerDto beerDto );

    BeerDto updateBeer ( UUID beerId, BeerDto beerDto );

    BeerDto getByUpc ( String upc );

    void deleteBeerById ( UUID beerId );
}
