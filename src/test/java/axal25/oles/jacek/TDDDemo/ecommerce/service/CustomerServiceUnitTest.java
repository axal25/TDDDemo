package axal25.oles.jacek.TDDDemo.ecommerce.service;

import axal25.oles.jacek.TDDDemo.ecommerce.entity.CustomerEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerServiceUnitTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPositive() {
        CustomerEntity expectedCustomerEntity = new CustomerEntity();

        expectedCustomerEntity.setId(1);
        expectedCustomerEntity.setIdPerson(2);
        expectedCustomerEntity.setIdOrganization(3);

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(expectedCustomerEntity);

        CustomerEntity actualCustomerEntity = customerService.add(expectedCustomerEntity);

        assertNotNull(actualCustomerEntity);
        assertEquals(expectedCustomerEntity, actualCustomerEntity);
        assertEquals(expectedCustomerEntity.getId(), actualCustomerEntity.getId());
        assertEquals(expectedCustomerEntity.getIdPerson(), actualCustomerEntity.getIdPerson());
        assertEquals(expectedCustomerEntity.getIdOrganization(), actualCustomerEntity.getIdOrganization());
    }

    @Test
    public void testAddNegative() {
        assertNull(customerService.add(null));
        assertNull(customerService.add(new CustomerEntity()));
    }
}
