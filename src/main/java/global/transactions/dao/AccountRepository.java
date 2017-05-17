package global.transactions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import global.transactions.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

	//@Query(value = "SELECT FROM Account acc WHERE acc.customer.email = :email")
	Account findByCustomerEmail(@Param(value = "email") String email);
	
	//@Query(value = "SELECT FROM Account acc WHERE acc.customer.phoneNumber = :phoneNumber")
	Account findByCustomerPhoneNumber(String phoneNumber);

}
