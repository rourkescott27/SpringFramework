package rm_scott.kberestbrewery.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import rm_scott.kberestbrewery.domain.Beer;
import rm_scott.kberestbrewery.web.model.BeerStyleEnum;

import java.util.UUID;


public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByBeerName ( String beerName, Pageable pageable );

    Page<Beer> findAllByBeerStyle ( BeerStyleEnum beerStyle, Pageable pageable );

    Page<Beer> findAllByBeerNameAndBeerStyle ( String beerName, BeerStyleEnum beerStyle, Pageable pageable );

    Beer findByUpc ( String upc );
}
