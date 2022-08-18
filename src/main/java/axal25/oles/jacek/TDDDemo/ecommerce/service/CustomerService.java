package axal25.oles.jacek.TDDDemo.ecommerce.service;

import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.CustomerEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.data.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<CustomerEntity> getAll() {
        return customerRepository.findAll();
    }

    public CustomerEntity add(CustomerEntity customerToAdd) {
        String operation = String.format("%s#add(%s %s)",
                this.getClass().getName(),
                CustomerService.class.getSimpleName(),
                CustomerService.class.getSimpleName().toLowerCase()
        );

        if (customerToAdd == null) {
            logger.error(String.format("Error during %s. %s is null.",
                    operation, CustomerEntity.class.getSimpleName()));
            return null;
        }
        if (customerToAdd.getId() == null) {
            logger.error(String.format("Error during %s. %s.id is null.",
                    operation, CustomerEntity.class.getSimpleName()));
            return null;
        }
        if (customerToAdd.getIdPerson() == null) {
            logger.error(String.format("Error during %s. %s.idPerson is null.",
                    operation, CustomerEntity.class.getSimpleName()));
            return null;
        }
        if (customerToAdd.getIdOrganization() == null) {
            logger.error(String.format("Error during %s. %s.idOrganization is null.",
                    operation, CustomerEntity.class.getSimpleName()));
            return null;
        }

        try {
            return customerRepository.save(customerToAdd);
        } catch (RuntimeException e) {
            logger.error(String.format("Error during %s. \n" +
                            "%s: \n%s \n" +
                            "%s: \n%s",
                    operation,
                    CustomerEntity.class.getSimpleName(), customerToAdd,
                    RuntimeException.class.getSimpleName(), e.getMessage()));
            return null;
        }
    }
}
