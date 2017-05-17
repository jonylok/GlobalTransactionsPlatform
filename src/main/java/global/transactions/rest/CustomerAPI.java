package global.transactions.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import global.transactions.dto.EmailDTO;
import global.transactions.dto.PhoneNumberDTO;
import global.transactions.service.CustomerService;

@RestController
public class CustomerAPI {
		
	@Autowired
	private CustomerService customerService;

	@CrossOrigin
	@RequestMapping(value = "IsCustomerEmail/{email}/", method = { RequestMethod.GET })
	public EmailDTO isCustomerEmail(@PathVariable String email) {
		Boolean isCustomerEmail = customerService.isCustomerEmail(email);
		EmailDTO activeEmail = new EmailDTO();
		activeEmail.setActive(isCustomerEmail);
		activeEmail.setEmail(email);
		return activeEmail;
	}
	
	@CrossOrigin
	@RequestMapping(value = "IsCustomerPhoneNumber/{phoneNumber}/", method = { RequestMethod.GET })
	public PhoneNumberDTO isCustomerMobile(@PathVariable String phoneNumber) {
		Boolean isCustomerPhoneNumber = customerService.isCustomerPhoneNumber(phoneNumber);
		PhoneNumberDTO activePhoneNumber = new PhoneNumberDTO();
		activePhoneNumber.setActive(isCustomerPhoneNumber);
		activePhoneNumber.setPhoneNumber(phoneNumber);
		return activePhoneNumber;
	}
}