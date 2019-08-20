package sol.jeradev.iBookAppApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sol.jeradev.iBookAppApi.domain.Customer;
import sol.jeradev.iBookAppApi.exception.ResourceNotFoundException;
import sol.jeradev.iBookAppApi.repository.CustomerRepository;
import sol.jeradev.iBookAppApi.transfer.CreateCustomerRequest;
import sol.jeradev.iBookAppApi.transfer.GetCustomerRequest;
import sol.jeradev.iBookAppApi.transfer.UpdateCustomerRequest;

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

    public Customer updateProduct(long id, UpdateCustomerRequest request) throws ResourceNotFoundException {
        Customer customer = getCustomer(id);

        BeanUtils.copyProperties(request, customer);

        return customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

    public Page<Customer> getCustomer(GetCustomerRequest request, Pageable pageable) {
        if (request.getPartialName() != null && request.getPhone() != null) {
            customerRepository.findByFirstNameContainingAndPhone(request.getPartialName(), request.getPhone(), pageable);
        } else if (request.getPartialName() != null) {
            return customerRepository.findByFirstNameContainingAndPhone(request.getPartialName(), request.getPhone(), pageable);
        }
        return customerRepository.findAll(pageable);
    }
}