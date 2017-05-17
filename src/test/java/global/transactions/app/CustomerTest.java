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

import global.transactions.dto.EmailDTO;
import global.transactions.dto.PhoneNumberDTO;

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
		
		EmailDTO isCustomerEmail = restTemplate.getForObject("/IsCustomerEmail/" + testEmail1 + "/",
				EmailDTO.class);
		assertThat(isCustomerEmail.isActive()).isEqualTo(false);
		
		isCustomerEmail = restTemplate.getForObject("/IsCustomerEmail/" + testEmail2 + "/",
				EmailDTO.class);
		assertThat(isCustomerEmail.isActive()).isEqualTo(true);

	}
	
	@Test
	public void testIsCustomerPhoneNumber() {
		
		String testPhone1 = "1234567";
		String testPhone2 = "37061743092";
		
		PhoneNumberDTO isCustomerPhone = restTemplate.getForObject("/IsCustomerPhoneNumber/" + testPhone1 + "/",
				PhoneNumberDTO.class);
		assertThat(isCustomerPhone.isActive()).isEqualTo(false);
		
		isCustomerPhone = restTemplate.getForObject("/IsCustomerPhoneNumber/" + testPhone2 + "/",
				PhoneNumberDTO.class);
		assertThat(isCustomerPhone.isActive()).isEqualTo(true);

	}
}
