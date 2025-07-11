package sia.tacos.data;

import java.util.Optional;
import sia.tacos.TacoOrder;

public interface OrderRepository {

    TacoOrder save ( TacoOrder order );

    Optional<TacoOrder> findById ( Long id );

}