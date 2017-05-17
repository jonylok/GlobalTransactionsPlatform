package global.transactions.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import global.transactions.dao.AccountRepository;
import global.transactions.dao.TransactionRepository;
import global.transactions.domain.Account;
import global.transactions.domain.Transaction;
import global.transactions.util.EmailValidator;

@Service
public class TransactionService {

	private Logger logger = LogManager.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountRepository accountRepository;
	

	public Boolean executeTransaction(String from, String to, BigDecimal amount) {
<<<<<<< HEAD
		//List<Customer> customers = transactionRepositoryfindByEmail(email);
		return true;
	}
}
=======
		Boolean transactionComplete = false;
		try {
			Account fromAccount = EmailValidator.validate(from) ? accountRepository.findByCustomerEmail(from)
					: accountRepository.findByCustomerPhoneNumber(from);
			Account toAccount = EmailValidator.validate(to) ? accountRepository.findByCustomerEmail(to)
					: accountRepository.findByCustomerPhoneNumber(to);
			fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
			toAccount.setBalance(toAccount.getBalance().add(amount));
			accountRepository.save(fromAccount);
			accountRepository.save(toAccount);
			Transaction newTransaction = new Transaction();
			newTransaction.setFrom(fromAccount);
			newTransaction.setTo(toAccount);
			newTransaction.setAmount(amount);
			newTransaction.setDate(new Date());
			transactionRepository.save(newTransaction);
			transactionComplete = true;

		} catch (Exception ex) {
			logger.error("Error in transaction from " + from + " to " + to + " amount " + amount, ex);
		}

		return transactionComplete;
	}
	
	public BigDecimal getAccountBalanceByEmail(String email){
		Account account = accountRepository.findByCustomerEmail(email);
		return account.getBalance();
	}
	
	public long getTransactionCount(){
		return transactionRepository.count();
	}

}
>>>>>>> 0f554f0c4bf80e8cf4c6711fd96b74fda3f98482
