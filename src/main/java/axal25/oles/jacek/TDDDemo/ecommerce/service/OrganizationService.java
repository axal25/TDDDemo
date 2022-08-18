package axal25.oles.jacek.TDDDemo.ecommerce.service;

import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.OrganizationEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.data.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public Iterable<OrganizationEntity> getAll() {
        return organizationRepository.findAll();
    }

    public OrganizationEntity add(OrganizationEntity organizationEntity) {
        return organizationEntity != null
                && organizationEntity.getId() != null
                && organizationEntity.getName() != null
                && organizationEntity.getIdSector() != null
                ? organizationRepository.save(organizationEntity)
                : null;
    }
}
