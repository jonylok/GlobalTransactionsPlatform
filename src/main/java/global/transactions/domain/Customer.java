package global.transactions.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	private List<Account> accounts;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "from")
	private List<Transaction> outTransactions;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "to")
	private List<Transaction> inTransactions;
		
	public Customer() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Transaction> getOutTransactions() {
		return outTransactions;
	}

	public void setOutTransactions(List<Transaction> outTransactions) {
		this.outTransactions = outTransactions;
	}

	public List<Transaction> getInTransactions() {
		return inTransactions;
	}

	public void setInTransactions(List<Transaction> inTransactions) {
		this.inTransactions = inTransactions;
	}
		
}