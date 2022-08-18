package axal25.oles.jacek.TDDDemo.ecommerce.data.repository;

import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
}
