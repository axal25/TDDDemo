package axal25.oles.jacek.TDDDemo.ecommerce.service;

import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.PersonEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.data.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Iterable<PersonEntity> getAll() {
        return personRepository.findAll();
    }

    public PersonEntity add(PersonEntity personEntity) {
        return personEntity != null
                && personEntity.getId() != null
                && personEntity.getFirstName() != null
                && personEntity.getSurname() != null
                ? personRepository.save(personEntity)
                : null;
    }
}
