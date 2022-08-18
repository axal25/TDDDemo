package axal25.oles.jacek.TDDDemo.ecommerce.data.repository;

import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    // not necessary
    @Query(value = "SELECT * FROM customers customerEntity WHERE customerEntity.id_person = :id_person",
            nativeQuery = true)
    public Iterable<CustomerEntity> findByIdPerson(@Param("id_person") Integer idPerson);

    @Query("SELECT customerEntity FROM CustomerEntity customerEntity WHERE customerEntity.idOrganization = :idOrganization")
    public Iterable<CustomerEntity> findByIdOrganization(Integer idOrganization);
}
