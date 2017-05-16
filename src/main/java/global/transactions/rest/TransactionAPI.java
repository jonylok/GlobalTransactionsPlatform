package global.transactions.rest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import global.transactions.service.TransactionService;

@RestController
public class TransactionAPI {
		
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "Transaction/{from}/{to}/{amount}/", method = { RequestMethod.POST })
	public ResponseEntity<String> Transaction(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal amount) {
		Boolean transactionExecuted = transactionService.executeTransaction(from, to, amount);
		return transactionExecuted==true?new ResponseEntity<>(HttpStatus.CREATED):new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}