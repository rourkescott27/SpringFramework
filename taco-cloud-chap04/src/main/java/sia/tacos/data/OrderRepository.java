package sia.tacos.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacos.TacoOrder;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {

    TacoOrder save ( TacoOrder order );

    Optional<TacoOrder> findById ( Long id );

}