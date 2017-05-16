package global.transactions.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.transactions.dao.CustomerRepository;
import global.transactions.domain.Customer;

@Service
public class CustomerService {

	private Logger logger = LogManager.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;
	
	public Boolean isCustomerEmail(String email) {
		List<Customer> customers = customerRepository.findByEmail(email);
		return customers.size()>0;
	}



}
