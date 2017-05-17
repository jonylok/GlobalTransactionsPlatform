package global.transactions.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import global.transactions.domain.Transaction;
import global.transactions.dto.TransactionDTO;
import global.transactions.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("sys")
public class TransactionTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private TransactionService transactionService;
	
	@Test
	public void testTransactionAPI() {
		
		String testEmail1 = "schulerob@gmail.com";
		String testEmail2 = "28@sebanken.nu";
		String code = "1234";
		
		ResponseEntity<String> transactionPostResponse = restTemplate.postForEntity("/Transaction/" + testEmail1 + "/", null, String.class);
		String id = transactionPostResponse.getBody();
		assertThat(transactionPostResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		TransactionDTO transaction= new TransactionDTO();
		transaction.setId(id);
		transaction.setCode(code);
		transaction.setFrom(testEmail1);
		transaction.setTo(testEmail2);
		transaction.setAmount(new BigDecimal("100.0"));
		
		HttpEntity<TransactionDTO> putEntity = new HttpEntity<TransactionDTO>(transaction);
		
		ResponseEntity<String> transactionPutResponse = restTemplate.exchange("/Transaction/", HttpMethod.PUT, putEntity, String.class);
		assertThat(transactionPutResponse.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

	}
	
	@Test
	public void testTransaction(){
		String testEmailFrom = "schulerob@gmail.com";
		String testEmailTo = "28@sebanken.nu";
		BigDecimal amount = new BigDecimal("100.00");
		long transactionCountBefore = transactionService.getTransactionCount();
		BigDecimal balanceTargetBefore = transactionService.getAccountBalanceByEmail(testEmailTo);
		BigDecimal balanceSourceBefore = transactionService.getAccountBalanceByEmail(testEmailFrom);
		String id = transactionService.createTransaction(testEmailFrom);
		Transaction transaction = transactionService.getTransactionById(id);
		boolean transactionComplete = transactionService.executeTransaction(id, transaction.getCode(), testEmailFrom, testEmailTo, amount);
		BigDecimal balanceTargetAfter = transactionService.getAccountBalanceByEmail(testEmailTo);
		BigDecimal balanceSourceAfter = transactionService.getAccountBalanceByEmail(testEmailFrom);
		long transactionCountAfter = transactionService.getTransactionCount();
		assertThat(balanceTargetBefore.add(amount)).isEqualTo(balanceTargetAfter);
		assertThat(balanceSourceBefore.subtract(amount)).isEqualTo(balanceSourceAfter);
		assertThat(transactionCountBefore).isEqualTo(transactionCountAfter-1);
		assertThat(transactionComplete).isEqualTo(true);

	}
}
