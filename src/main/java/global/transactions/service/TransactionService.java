package global.transactions.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	

	public String createTransaction(String from) {
		int code = UtilService.generatePasscode();
		Account fromAccount = EmailValidator.validate(from) ? accountRepository.findByCustomerEmail(from)
				: accountRepository.findByCustomerPhoneNumber(from);
		Transaction transaction = new Transaction(fromAccount, code);
		transaction = transactionRepository.saveAndFlush(transaction);
		return transaction.getId();
	}
	
	public Boolean executeTransaction(String id, String code, String from, String to, BigDecimal amount) {

		Boolean transactionComplete = false;
		try {
			Transaction transaction = transactionRepository.findById(id);
			if(code.contentEquals(transaction.getCode())){
				Account fromAccount = EmailValidator.validate(from) ? accountRepository.findByCustomerEmail(from)
						: accountRepository.findByCustomerPhoneNumber(from);
				Account toAccount = EmailValidator.validate(to) ? accountRepository.findByCustomerEmail(to)
						: accountRepository.findByCustomerPhoneNumber(to);
				fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
				toAccount.setBalance(toAccount.getBalance().add(amount));
				accountRepository.save(fromAccount);
				accountRepository.save(toAccount);			
				transaction.setFrom(fromAccount);
				transaction.setTo(toAccount);
				transaction.setAmount(amount);
				transaction.setDate(new Date());
				transactionRepository.save(transaction);
				transactionComplete = true;
			}

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

