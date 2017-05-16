package global.transactions.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import global.transactions.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{

	List<Customer> findByEmail(String email);
}
