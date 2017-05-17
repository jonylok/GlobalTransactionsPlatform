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

	@RequestMapping(value = "Transaction/{id}/{code}/{from}/{to}/{amount}/", method = { RequestMethod.PUT })
	public ResponseEntity<String> transactionExecute(@PathVariable String id, @PathVariable String code, @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal amount) {
		Boolean transactionExecuted = transactionService.executeTransaction(id, code, from, to, amount);
		return transactionExecuted==true?new ResponseEntity<>(HttpStatus.CREATED):new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "Transaction/{from}/", method = { RequestMethod.POST })
	public ResponseEntity<String> transactionCreate(@PathVariable String from) {
		String transactionId = transactionService.createTransaction(from);
		ResponseEntity<String> response = new ResponseEntity<String>(transactionId, HttpStatus.CREATED);
		return response;
	}
}