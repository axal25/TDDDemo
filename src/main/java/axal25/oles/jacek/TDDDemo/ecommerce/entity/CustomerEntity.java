package axal25.oles.jacek.TDDDemo.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class CustomerEntity implements Comparable<CustomerEntity> {

    @Id
    private Integer id;
    private Integer idPerson;
    private Integer idOrganization;

    public CustomerEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Integer getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Integer idOrganization) {
        this.idOrganization = idOrganization;
    }

    @Override
    public int compareTo(CustomerEntity other) {
        return other == null
                ? 1
                : !Objects.equals(this.id, other.id)
                ? Integer.compare(this.id, other.id)
                : !Objects.equals(this.idPerson, other.id)
                ? Integer.compare(this.idPerson, other.id)
                : !Objects.equals(this.idOrganization, other.idOrganization)
                ? Integer.compare(this.idOrganization, other.idOrganization) // NPE
                : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CustomerEntity)) return false;
        CustomerEntity other = (CustomerEntity) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(idPerson, other.idPerson)
                && Objects.equals(idOrganization, other.idOrganization);
    }

    @Override
    public int hashCode() {
//        int altHashCode =
//                (
//                        (
//                                (17 * 19) + Objects.hashCode(id)
//                        ) * 23 + Objects.hashCode(idPerson)
//                ) * 29 + Objects.hashCode(idOrganization);
        return Objects.hash(id, idPerson, idOrganization);
    }
}