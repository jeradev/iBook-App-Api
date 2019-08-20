package sol.jeradev.iBookAppApi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sol.jeradev.iBookAppApi.domain.Customer;

//Long is wrapper for primitive type long
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //queries derived from method names
    Page<Customer> findByFirstNameContainingAndPhone(String partialName, String Phone, Pageable pageable);

}
