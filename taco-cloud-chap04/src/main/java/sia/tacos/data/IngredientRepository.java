package sia.tacos.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    Iterable<Ingredient> findAll ();

    Optional<Ingredient> findById ( String id );

    Ingredient save ( Ingredient ingredient );

}
