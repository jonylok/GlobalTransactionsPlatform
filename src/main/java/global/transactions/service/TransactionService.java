package global.transactions.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import global.transactions.dao.TransactionRepository;
import global.transactions.domain.Customer;

@Service
public class TransactionService {

	private Logger logger = LogManager.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Transactional
	public Boolean executeTransaction(String from, String to, BigDecimal amount) {
		//List<Customer> customers = transactionRepositoryfindByEmail(email);
		return true;
	}
}