package axal25.oles.jacek.TDDDemo.ecommerce.repository;


import axal25.oles.jacek.TDDDemo.config.TestEntityManagerUtilsBeanProvider;
import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.CustomerEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.OrganizationEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.PersonEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.data.repository.CustomerRepository;
import axal25.oles.jacek.TDDDemo.ecommerce.entity.TestEntityManagerUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestEntityManagerUtilsBeanProvider.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManagerUtils testEntityManagerUtils;

    @Test
    public void findById() {
        CustomerEntity expectedCustomerEntity = new CustomerEntity();
        expectedCustomerEntity.setId(testEntityManagerUtils.getLowestAvailableIntegerId(CustomerEntity.class));

        testEntityManager.persist(expectedCustomerEntity);

        Optional<CustomerEntity> optCustomerEntity = customerRepository.findById(expectedCustomerEntity.getId());

        assertThat(optCustomerEntity.isPresent(), is(equalTo(true)));
        assertThat(optCustomerEntity.get().getId(), is(equalTo(expectedCustomerEntity.getId())));
    }

    @Test
    public void findByIdPerson() {
        CustomerEntity expectedCustomerEntity = new CustomerEntity();

        expectedCustomerEntity.setId(testEntityManagerUtils.getLowestAvailableIntegerId(CustomerEntity.class));
        expectedCustomerEntity.setIdPerson(testEntityManagerUtils.getLowestAvailableIntegerId(PersonEntity.class) - 1);
        expectedCustomerEntity.setIdOrganization(testEntityManagerUtils.getLowestAvailableIntegerId(OrganizationEntity.class) - 1);

        testEntityManager.persist(expectedCustomerEntity);

        Iterable<CustomerEntity> entities = customerRepository.findByIdPerson(expectedCustomerEntity.getIdPerson());

        assertThat(entities, is(notNullValue()));
        Iterator<CustomerEntity> iterator = entities.iterator();
        assertThat(iterator, is(notNullValue()));
        boolean hasNext = iterator.hasNext();
        assertThat(hasNext, is(equalTo(true)));
        assertThat(iterator.next().getIdPerson(), is(equalTo(expectedCustomerEntity.getIdPerson())));
        while (iterator.hasNext()) {
            assertThat(iterator.next().getIdPerson(), is(equalTo(expectedCustomerEntity.getIdPerson())));
        }
    }

    @Test
    public void findByIdOrganization() {
        CustomerEntity expectedCustomerEntity = new CustomerEntity();
        expectedCustomerEntity.setId(testEntityManagerUtils.getLowestAvailableIntegerId(CustomerEntity.class));
        expectedCustomerEntity.setIdPerson(testEntityManagerUtils.getLowestAvailableIntegerId(PersonEntity.class) - 1);
        expectedCustomerEntity.setIdOrganization(testEntityManagerUtils.getLowestAvailableIntegerId(OrganizationEntity.class) - 1);

        testEntityManager.persist(expectedCustomerEntity);

        Iterable<CustomerEntity> entities = customerRepository.findByIdOrganization(expectedCustomerEntity.getIdOrganization());

        assertThat(entities, is(notNullValue()));
        Iterator<CustomerEntity> iterator = entities.iterator();
        assertThat(iterator, is(notNullValue()));
        boolean hasNext = iterator.hasNext();
        assertThat(hasNext, is(equalTo(true)));
        assertThat(iterator.next().getIdOrganization(), is(equalTo(expectedCustomerEntity.getIdOrganization())));
        while (iterator.hasNext()) {
            assertThat(iterator.next().getIdOrganization(), is(equalTo(expectedCustomerEntity.getIdOrganization())));
        }
    }
}
