package sol.jeradev.iBookAppApi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;
import sol.jeradev.iBookAppApi.domain.Customer;
import sol.jeradev.iBookAppApi.exception.ResourceNotFoundException;
import sol.jeradev.iBookAppApi.service.CustomerService;
import sol.jeradev.iBookAppApi.transfer.CreateCustomerRequest;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTests {
    @Autowired
    private CustomerService customerService;

    @Test
    public void testCreateCustomer_whenValidRequest_thenReturnCreatedCustomer() {
        createdCustomer();
    }

    private Customer createdCustomer() {
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setFirstName("Ionut");
        request.setLastName("Popa");
        request.setPhone("0745856965");
        request.setPeriod("1-5 Iulie");
        request.setPrice("600");

        Customer createdCustomer = customerService.createCustomer(request);

        assertThat(createdCustomer, notNullValue());
        assertThat(createdCustomer.getFirstName(), is(request.getFirstName()));
        assertThat(createdCustomer.getLastName(), is(request.getLastName()));
        assertThat(createdCustomer.getPhone(), is(request.getPhone()));
        assertThat(createdCustomer.getPeriod(), is(request.getPeriod()));
        assertThat(createdCustomer.getPrice(), is(request.getPrice()));
        return createdCustomer;
    }

    @Test(expected = TransactionSystemException.class)
    public void testCreateCustomer_whenMissingMandatoryProperties_thenThrowException() {
        CreateCustomerRequest request = new CreateCustomerRequest();
        customerService.createCustomer(request);
    }

    @Test
    public void testGetCustomer_whenExistingId_thenReturnCustomer() throws ResourceNotFoundException {
        Customer createdCustomer = createdCustomer();
        Customer customer = customerService.getCustomer(createdCustomer.getId());

        assertThat(customer, notNullValue());
        assertThat(customer.getId(), is(createdCustomer.getId()));

    }
    @Test(expected = ResourceNotFoundException.class)
    public void testGetCustomer_whenNonExistingId_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        customerService.getCustomer(9999L);
    }
}
