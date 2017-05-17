package global.transactions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import global.transactions.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

	Account findByCustomerEmail(@Param(value = "email") String email);
	
	Account findByCustomerPhoneNumber(String phoneNumber);

}
