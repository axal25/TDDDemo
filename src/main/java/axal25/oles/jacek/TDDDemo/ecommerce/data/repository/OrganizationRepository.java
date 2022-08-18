package axal25.oles.jacek.TDDDemo.ecommerce.data.repository;

import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<OrganizationEntity, Integer> {
}
