package axal25.oles.jacek.TDDDemo.ecommerce.service;

import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void addTestPositive() {
        CustomerEntity inputCustomerEntity = new CustomerEntity();
        inputCustomerEntity.setId(1);
        inputCustomerEntity.setIdPerson(3);
        inputCustomerEntity.setIdOrganization(2);

        CustomerEntity returnedCustomerEntity = customerService.add(inputCustomerEntity);

        assertNotNull(returnedCustomerEntity);
        assertNotNull(returnedCustomerEntity.getId());
        assertEquals(inputCustomerEntity.getId(), returnedCustomerEntity.getId());
        assertEquals(inputCustomerEntity.getIdPerson(), returnedCustomerEntity.getIdPerson());
        assertEquals(inputCustomerEntity.getIdOrganization(), returnedCustomerEntity.getIdOrganization());
    }
}
