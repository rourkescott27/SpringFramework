package rm_scott.kberestbrewery.web.mappers;

import org.mapstruct.Mapper;

import rm_scott.kberestbrewery.domain.Customer;
import rm_scott.kberestbrewery.web.model.CustomerDto;

/**
 * Created by jt on 2019-05-25.
 */
@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer ( CustomerDto dto );

    CustomerDto customerToCustomerDto ( Customer customer );
}
