package rm_scott.kberestbrewery.services;

import rm_scott.kberestbrewery.web.model.CustomerDto;

import java.util.UUID;

/**
 * Created by jt on 2019-04-21.
 */
public interface CustomerService {
    CustomerDto getCustomerById ( UUID customerId );

    CustomerDto saveNewCustomer ( CustomerDto customerDto );

    void updateCustomer ( UUID customerId, CustomerDto customerDto );

    void deleteById ( UUID customerId );
}