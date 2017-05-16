package global.transactions.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId", nullable = false)
	private Customer customer;
	private BigDecimal balance;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "from")
	private List<Transaction> outTransactions;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "to")
	private List<Transaction> inTransactions;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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
