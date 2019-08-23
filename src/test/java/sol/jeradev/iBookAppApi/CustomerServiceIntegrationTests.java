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
import sol.jeradev.iBookAppApi.transfer.UpdateCustomerRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;

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
        request.setPrice(649.99);

        Customer createdCustomer = customerService.createCustomer(request);

        assertThat(createdCustomer, notNullValue());
        assertThat(createdCustomer.getId(), greaterThan(0L));
        assertThat(createdCustomer.getId(), is(createdCustomer.getId()));
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

    @Test
    public void testUpdateProduct_whenValidRequest_thenReturnUpdatedProduct() throws ResourceNotFoundException {
        Customer createdCustomer = createdCustomer();
        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.setFirstName(createdCustomer.getFirstName());
        request.setLastName(createdCustomer.getLastName());
        request.setPhone(createdCustomer.getPhone());
        request.setPeriod(createdCustomer.getPeriod());
        request.setPrice(createdCustomer.getPrice());

        Customer updatedCustomer = customerService.updateCustomer(createdCustomer.getId(), request);
        assertThat(updatedCustomer, notNullValue());
        assertThat(updatedCustomer.getId(), is(createdCustomer.getId()));

        assertThat(updatedCustomer.getFirstName(), not(is(createdCustomer.getFirstName())));
        assertThat(updatedCustomer.getFirstName(), is(request.getFirstName()));

        assertThat(updatedCustomer.getLastName(), not(is(createdCustomer.getLastName())));
        assertThat(updatedCustomer.getLastName(), is(request.getLastName()));

        assertThat(updatedCustomer.getPhone(), not(is(createdCustomer.getPhone())));
        assertThat(updatedCustomer.getPhone(), is(request.getPhone()));

        assertThat(updatedCustomer.getPeriod(), not(is(createdCustomer.getPeriod())));
        assertThat(updatedCustomer.getPeriod(), is(request.getPeriod()));
    }

}
