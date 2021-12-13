package axal25.oles.jacek.TDDDemo.ecommerce.service;

import axal25.oles.jacek.TDDDemo.ecommerce.entity.CustomerEntity;
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
        CustomerEntity inputContact = new CustomerEntity();
        inputContact.setId(1);
        inputContact.setIdPerson(2);
        inputContact.setIdOrganization(3);

        CustomerEntity returnedContact = customerService.add(inputContact);

        assertNotNull(returnedContact);
        assertNotNull(returnedContact.getId());
        assertEquals(inputContact.getId(), returnedContact.getId());
        assertEquals(inputContact.getIdPerson(), returnedContact.getIdPerson());
        assertEquals(inputContact.getIdOrganization(), returnedContact.getIdOrganization());
    }
}
