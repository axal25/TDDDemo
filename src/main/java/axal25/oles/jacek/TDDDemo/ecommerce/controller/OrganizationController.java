package axal25.oles.jacek.TDDDemo.ecommerce.controller;

import axal25.oles.jacek.TDDDemo.ecommerce.data.entity.OrganizationEntity;
import axal25.oles.jacek.TDDDemo.ecommerce.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/organizations")
class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    // web browser http://localhost:8080/customers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<Iterable<OrganizationEntity>> getAll() {
        return new ResponseEntity<>(
                organizationService.getAll(),
                HttpStatus.OK
        );
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    ResponseEntity<String> add(@RequestBody OrganizationEntity organizationEntity) {
        return organizationService.add(organizationEntity) != null
                ? new ResponseEntity<>("success", HttpStatus.CREATED)
                : new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
}
