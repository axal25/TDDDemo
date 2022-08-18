package axal25.oles.jacek.TDDDemo.ecommerce.controller;

import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.PersonEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/persons")
class PersonController {

    @Autowired
    private PersonService personService;

    // web browser http://localhost:8080/customers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<Iterable<PersonEntity>> getAll() {
        return new ResponseEntity<>(
                personService.getAll(),
                HttpStatus.OK
        );
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    ResponseEntity<String> add(@RequestBody PersonEntity personEntity) {
        return personService.add(personEntity) != null
                ? new ResponseEntity<>("success", HttpStatus.CREATED)
                : new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
}
