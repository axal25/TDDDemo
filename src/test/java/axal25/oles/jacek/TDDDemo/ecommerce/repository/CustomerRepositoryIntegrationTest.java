package axal25.oles.jacek.TDDDemo.ecommerce.repository;


import axal25.oles.jacek.TDDDemo.ecommerce.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findByIdTest() {
        CustomerEntity expectedCustomerEntity = new CustomerEntity();
        expectedCustomerEntity.setId(1);
        expectedCustomerEntity.setIdPerson(12345);

        testEntityManager.persist(expectedCustomerEntity);

        CustomerEntity actualCustomerEntity = customerRepository.findById(expectedCustomerEntity.getId())
                .orElse(new CustomerEntity());

        assertThat(actualCustomerEntity.getIdPerson(), is(equalTo(expectedCustomerEntity.getIdPerson())));
    }

    @Test
    public void findByIdPersonTest() {
        CustomerEntity expectedCustomerEntity = new CustomerEntity();
        expectedCustomerEntity.setId(54321);
        expectedCustomerEntity.setIdPerson(12345);

        testEntityManager.persist(expectedCustomerEntity);

        CustomerEntity actualCustomerEntity = customerRepository.findByIdPerson(expectedCustomerEntity.getIdPerson());

        assertThat(actualCustomerEntity.getIdPerson(), is(equalTo(expectedCustomerEntity.getIdPerson())));
    }
}
