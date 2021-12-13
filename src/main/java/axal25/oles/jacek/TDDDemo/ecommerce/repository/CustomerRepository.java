package axal25.oles.jacek.TDDDemo.ecommerce.repository;

import axal25.oles.jacek.TDDDemo.ecommerce.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    public CustomerEntity findByIdPerson(Integer idPerson);
}
