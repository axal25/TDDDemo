package axal25.oles.jacek.TDDDemo.ecommerce.controller;

import axal25.oles.jacek.TDDDemo.ecommerce.entity.CustomerEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPositive() {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(1);
        customerEntity.setIdPerson(2);
        customerEntity.setIdOrganization(3);

        when(customerService.add(any(CustomerEntity.class))).thenReturn(customerEntity);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String jsonCustomerEntity = null;
        try {
            jsonCustomerEntity = objectWriter.writeValueAsString(customerEntity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/customers")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(jsonCustomerEntity))
                    .andExpectAll(
                            status().isCreated(),
                            content().encoding(Charset.defaultCharset()),
                            content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE),
                            content().string("success")
                    ).andReturn();
            // works for
            // String add(@ModelAttribute CustomerEntity customerEntity) {
            // instead of
            // ResponseEntity<String> add(@RequestBody CustomerEntity customerEntity)
            // mockMvc.perform(post("/customers", customerEntity))
            //         .andExpect(status().isOk())
            //         .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddNegative1() {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(1);
        customerEntity.setIdPerson(2);
        customerEntity.setIdOrganization(3);

        when(customerService.add(any(CustomerEntity.class))).thenReturn(customerEntity);

        try {
            mockMvc.perform(post("/customers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content((String) null))
                    .andExpectAll(
                            status().isBadRequest(),
                            content().encoding(Charset.defaultCharset()),
                            content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE),
                            content().string("failure")
                    ).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddNegative2() {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(1);
        customerEntity.setIdPerson(2);
        customerEntity.setIdOrganization(3);

        when(customerService.add(any(CustomerEntity.class))).thenReturn(customerEntity);

        String jsonCustomerEntity = "{<>}";

        try {
            mockMvc.perform(post("/customers")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(jsonCustomerEntity))
                    .andExpectAll(
                            status().isBadRequest(),
                            content().encoding(StandardCharsets.ISO_8859_1),
                            content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE),
                            content().string("")
                    ).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddNegative3() {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(1);
        customerEntity.setIdPerson(2);
        customerEntity.setIdOrganization(3);

        when(customerService.add(any(CustomerEntity.class))).thenReturn(customerEntity);

        String jsonCustomerEntity = "{<>}";

        try {
            mockMvc.perform(post("/customers")
                            .contentType(MediaType.TEXT_PLAIN_VALUE)
                            .content(jsonCustomerEntity))
                    .andExpectAll(
                            status().isUnsupportedMediaType(),
                            content().encoding(StandardCharsets.ISO_8859_1),
                            content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE),
                            content().string("")
                    ).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
