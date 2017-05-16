package global.transactions.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("sys")
public class CustomerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testIsCustomerEmail() {
		
		String testEmail1 = "aaaa@bbb.se";
		String testEmail2 = "28@sebanken.nu";
		
		Boolean isCustomerEmail = restTemplate.getForObject("/IsCustomerEmail/" + testEmail1 + "/",
				Boolean.class);
		assertThat(isCustomerEmail.booleanValue()).isEqualTo(false);
		
		isCustomerEmail = restTemplate.getForObject("/IsCustomerEmail/" + testEmail2 + "/",
				Boolean.class);
		assertThat(isCustomerEmail.booleanValue()).isEqualTo(true);

	}
}
