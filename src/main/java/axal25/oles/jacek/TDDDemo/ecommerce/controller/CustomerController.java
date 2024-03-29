package axal25.oles.jacek.TDDDemo.ecommerce.controller;

import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.CustomerEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/customers")
class CustomerController {

    @Autowired
    private CustomerService customerService;

    // web browser http://localhost:8080/customers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<Iterable<CustomerEntity>> getAll() {
        return new ResponseEntity<>(
                customerService.getAll(),
                HttpStatus.OK
        );
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    ResponseEntity<String> add(@RequestBody CustomerEntity customerEntity) {
        return customerService.add(customerEntity) != null
                ? new ResponseEntity<>("success", HttpStatus.CREATED)
                : new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
}
