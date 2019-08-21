package sol.jeradev.iBookAppApi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sol.jeradev.iBookAppApi.domain.Customer;

//Long is wrapper for primitive type long
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //queries derived from method names
    Page<Customer> findByFirstNameContainingAndPhone(String partialName, String Phone, Pageable pageable);

    @Query(value = "SELECT * FROM customer WHERE name LIKE '%?1'", nativeQuery = true)
    Page<Customer>findByPartialAndFirstNameAndLastNameAndPhone (String partialFirstName, String partialLastName, String phone, Pageable pageable);


}
