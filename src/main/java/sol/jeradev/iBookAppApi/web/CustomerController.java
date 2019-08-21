package sol.jeradev.iBookAppApi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sol.jeradev.iBookAppApi.domain.Customer;
import sol.jeradev.iBookAppApi.exception.ResourceNotFoundException;
import sol.jeradev.iBookAppApi.service.CustomerService;
import sol.jeradev.iBookAppApi.transfer.CreateCustomerRequest;
import sol.jeradev.iBookAppApi.transfer.GetCustomerRequest;
import sol.jeradev.iBookAppApi.transfer.UpdateCustomerRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }
    //endpoint
    @GetMapping
    public ResponseEntity<Page<Customer>> getCustomer(GetCustomerRequest request, Pageable pageable) {
        Page<Customer> response = customerService.getCustomer(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        Customer response = customerService.createCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateCustomerRequest request) throws ResourceNotFoundException {
        customerService.updateCustomer(id, request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
