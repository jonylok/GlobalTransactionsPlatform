package global.transactions.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
		ResponseEntity<String> transactionPostResponse = restTemplate.postForEntity("/Transaction/" + testEmail1 + "/" + testEmail2 + "/100.00/", null, String.class);
		assertThat(transactionPostResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

	}
	
	@Test
	public void testTransaction(){
		String testEmailFrom = "schulerob@gmail.com";
		String testEmailTo = "28@sebanken.nu";
		BigDecimal amount = new BigDecimal("100.00");
		long transactionCountBefore = transactionService.getTransactionCount();
		BigDecimal balanceTargetBefore = transactionService.getAccountBalanceByEmail(testEmailTo);
		BigDecimal balanceSourceBefore = transactionService.getAccountBalanceByEmail(testEmailFrom);
		boolean transactionComplete = transactionService.executeTransaction(testEmailFrom, testEmailTo, amount);
		BigDecimal balanceTargetAfter = transactionService.getAccountBalanceByEmail(testEmailTo);
		BigDecimal balanceSourceAfter = transactionService.getAccountBalanceByEmail(testEmailFrom);
		long transactionCountAfter = transactionService.getTransactionCount();
		assertThat(balanceTargetBefore.add(amount)).isEqualTo(balanceTargetAfter);
		assertThat(balanceSourceBefore.subtract(amount)).isEqualTo(balanceSourceAfter);
		assertThat(transactionCountBefore).isEqualTo(transactionCountAfter-1);
		assertThat(transactionComplete).isEqualTo(true);

	}
}
