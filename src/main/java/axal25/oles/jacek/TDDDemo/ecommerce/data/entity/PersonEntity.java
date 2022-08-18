package axal25.oles.jacek.TDDDemo.ecommerce.data.entity;

import axal25.oles.jacek.TDDDemo.utils.StringUtils;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table(schema = "ECOMMERCE", name = "persons")
public class PersonEntity implements EntityInterface<PersonEntity> {

    @Id
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "surname")
    private String surname;

    @Override
    public int compareTo(PersonEntity other) {
        return other == null
                ? 1
                : !Objects.equals(this.id, other.id)
                ? Integer.compare(this.id, other.id)
                : !Objects.equals(this.firstName, other.firstName)
                ? StringUtils.compare(this.firstName, other.firstName)
                : !Objects.equals(this.secondName, other.secondName)
                ? StringUtils.compare(this.secondName, other.secondName)
                : !Objects.equals(this.surname, other.surname)
                ? StringUtils.compare(this.surname, other.surname)
                : 0;
    }
}
