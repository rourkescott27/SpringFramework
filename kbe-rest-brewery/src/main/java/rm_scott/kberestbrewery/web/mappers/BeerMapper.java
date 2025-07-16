package rm_scott.kberestbrewery.web.mappers;

import org.mapstruct.Mapper;

import rm_scott.kberestbrewery.domain.Beer;
import rm_scott.kberestbrewery.web.model.BeerDto;

/**
 * Created by jt on 2019-05-25.
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto ( Beer beer );

    BeerDto beerToBeerDtoWithInventory ( Beer beer );

    Beer beerDtoToBeer ( BeerDto dto );
}