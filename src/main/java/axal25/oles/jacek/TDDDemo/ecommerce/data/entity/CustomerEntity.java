package axal25.oles.jacek.TDDDemo.ecommerce.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
// catalog = null, but null is not constant

// Customer = {1, 2, 3}
// added in CustomerServiceIntegrationTest
// using @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@Table(schema = "ECOMMERCE", name = "ECOMMERCE.customers")

@Table(schema = "ECOMMERCE", name = "customers")
public class CustomerEntity implements EntityInterface<CustomerEntity> {

    @Id
    private Integer id;

    @Column(name = "id_person")
    private Integer idPerson;

    @Column(name = "id_organization")
    private Integer idOrganization;

    @Override
    public int compareTo(CustomerEntity other) {
        return other == null
                ? 1
                : !Objects.equals(this.id, other.id)
                ? Integer.compare(this.id, other.id)
                : !Objects.equals(this.idPerson, other.idPerson)
                ? Integer.compare(this.idPerson, other.idPerson)
                : !Objects.equals(this.idOrganization, other.idOrganization)
                ? Integer.compare(this.idOrganization, other.idOrganization)
                : 0;
    }
}