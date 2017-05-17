package global.transactions.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
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
		String code = "1234";
		
		ResponseEntity<String> transactionPostResponse = restTemplate.postForEntity("/Transaction/" + testEmail1 + "/", null, String.class);
		String id = transactionPostResponse.getBody();
		assertThat(transactionPostResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		ResponseEntity<String> transactionPutResponse = restTemplate.exchange("/Transaction/" + id + "/" + code + "/" + testEmail1 + "/" + testEmail2 + "/100.00/", HttpMethod.PUT, null, String.class);
		assertThat(transactionPutResponse.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

	}
}
