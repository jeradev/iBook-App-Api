package sol.jeradev.iBookAppApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sol.jeradev.iBookAppApi.domain.Customer;
import sol.jeradev.iBookAppApi.exception.ResourceNotFoundException;
import sol.jeradev.iBookAppApi.repository.CustomerRepository;
import sol.jeradev.iBookAppApi.transfer.CreateCustomerRequest;

@Service
public class CustomerService {

    //IoC (inversion of control) and dependency injection
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    public Customer createCustomer(CreateCustomerRequest request) {
        Customer customer = objectMapper.convertValue(request, Customer.class);

        //This is the same method like before but more efficient - same result
       /* Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhone());
        customer.setPrice(request.getPrice());
        customer.setPeriod(request.getPeriod());*/
        return customerRepository.save(customer);
    }

    public Customer getCustomer(long id) throws ResourceNotFoundException {
        //using Optional with orElseThrow
        return customerRepository.findById(id)
                //using Lambda expressions
                .orElseThrow(() -> new ResourceNotFoundException("Customer" + id + "does not exist."));

    }
}
