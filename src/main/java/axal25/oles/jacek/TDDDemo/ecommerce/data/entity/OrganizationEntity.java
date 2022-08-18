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
@Table(schema = "ECOMMERCE", name = "organizations")
public class OrganizationEntity implements EntityInterface<OrganizationEntity> {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_sector")
    private Integer idSector;

    @Override
    public int compareTo(OrganizationEntity other) {
        return other == null
                ? 1
                : !Objects.equals(this.id, other.id)
                ? Integer.compare(this.id, other.id)
                : !Objects.equals(this.name, other.name)
                ? StringUtils.compare(this.name, other.name)
                : !Objects.equals(this.idSector, other.idSector)
                ? Integer.compare(this.idSector, other.idSector)
                : 0;
    }
}
