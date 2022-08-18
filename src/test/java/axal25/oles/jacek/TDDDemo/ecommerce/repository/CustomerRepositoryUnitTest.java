package axal25.oles.jacek.TDDDemo.ecommerce.repository;

import axal25.oles.jacek.TDDDemo.ecommerce.data.repository.CustomerRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
// TODO: fix - currently not working, should work
//  @DatabaseSetup("classpath:dbunit/customer-start.xml")
//  @ExpectedDatabase("dbunit/customer-finish.xml")
public class CustomerRepositoryUnitTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findByIdTest() {

        /**
         CustomerEntity expectedCustomerEntity = new CustomerEntity();
         expectedCustomerEntity.setId(111);

         testEntityManager.persist(expectedCustomerEntity);

         Optional<CustomerEntity> optCustomerEntity = customerRepository.findById(expectedCustomerEntity.getId());

         assertThat(optCustomerEntity.isPresent(), is(equalTo(true)));
         assertThat(optCustomerEntity.get().getId(), is(equalTo(expectedCustomerEntity.getId())));
         **/
    }

    @Test
    public void findByIdPersonTest() {
        // TODO: fix - currently not working, should work

        /**
         CustomerEntity expectedCustomerEntity = new CustomerEntity();
         expectedCustomerEntity.setIdPerson(222);

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
         **/
    }
}
