package axal25.oles.jacek.TDDDemo.ecommerce.controller;

import axal25.oles.jacek.TDDDemo.ecommerce.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {

    @Autowired
    private CustomerController customerController;

    @Test
    public void testAddPositive() {
        ResponseEntity<String> expectedResult = new ResponseEntity<>("success", HttpStatus.CREATED);

        CustomerEntity inputCustomerEntity = new CustomerEntity();
        inputCustomerEntity.setId(1);
        inputCustomerEntity.setIdPerson(2);
        inputCustomerEntity.setIdOrganization(3);

        ResponseEntity<String> actualResult = customerController.add(inputCustomerEntity);

        assertEquals(actualResult, expectedResult);
        assertThat(actualResult, is(equalTo(expectedResult)));
        assertEquals(actualResult.getStatusCode(), expectedResult.getStatusCode());
        assertThat(actualResult.getStatusCode(), is(equalTo(expectedResult.getStatusCode())));
        assertEquals(actualResult.getBody(), expectedResult.getBody());
        assertThat(actualResult.getBody(), is(equalTo(expectedResult.getBody())));
    }

    @Test
    public void testAddNegative1() {
        ResponseEntity<String> expectedResult = new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);

        ResponseEntity<String> actualResult = customerController.add(null);

        assertEquals(actualResult, expectedResult);
        assertThat(actualResult, is(equalTo(expectedResult)));
        assertEquals(actualResult.getStatusCode(), expectedResult.getStatusCode());
        assertThat(actualResult.getStatusCode(), is(equalTo(expectedResult.getStatusCode())));
        assertEquals(actualResult.getBody(), expectedResult.getBody());
        assertThat(actualResult.getBody(), is(equalTo(expectedResult.getBody())));
    }

    @Test
    public void testAddNegative2() {
        ResponseEntity<String> expectedResult = new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);

        ResponseEntity<String> actualResult = customerController.add(new CustomerEntity());

        assertEquals(actualResult, expectedResult);
        assertThat(actualResult, is(equalTo(expectedResult)));
        assertEquals(actualResult.getStatusCode(), expectedResult.getStatusCode());
        assertThat(actualResult.getStatusCode(), is(equalTo(expectedResult.getStatusCode())));
        assertEquals(actualResult.getBody(), expectedResult.getBody());
        assertThat(actualResult.getBody(), is(equalTo(expectedResult.getBody())));
    }
}
