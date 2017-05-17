package global.transactions.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import global.transactions.service.CustomerService;

@RestController
public class CustomerAPI {
		
	@Autowired
	private CustomerService customerService;

	@CrossOrigin
	@RequestMapping(value = "IsCustomerEmail/{email}/", method = { RequestMethod.GET })
	public Boolean isCustomerEmail(@PathVariable String email) {
		Boolean isCustomerEmail = customerService.isCustomerEmail(email);
		return isCustomerEmail;
	}
	
	@CrossOrigin
	@RequestMapping(value = "IsCustomerPhoneNumber/{phoneNumber}/", method = { RequestMethod.GET })
	public Boolean isCustomerMobile(@PathVariable String phoneNumber) {
		Boolean isCustomerPhoneNumber = customerService.isCustomerPhoneNumber(phoneNumber);
		return isCustomerPhoneNumber;
	}
}