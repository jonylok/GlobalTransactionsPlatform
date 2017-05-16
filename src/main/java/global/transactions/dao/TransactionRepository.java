package global.transactions.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import global.transactions.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String>{

}
