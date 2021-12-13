package axal25.oles.jacek.TDDDemo.ecommerce.service;

import axal25.oles.jacek.TDDDemo.ecommerce.entity.CustomerEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity add(CustomerEntity customerToAdd) {
        return customerToAdd != null && customerToAdd.getIdPerson() != null && customerToAdd.getIdOrganization() != null
                ? customerRepository.save(customerToAdd)
                : null;
    }
}
