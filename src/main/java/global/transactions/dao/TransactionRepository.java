package global.transactions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import global.transactions.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String>{

	Transaction findById(@Param(value = "id") String id);

}
