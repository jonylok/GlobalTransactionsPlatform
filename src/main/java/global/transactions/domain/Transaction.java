package global.transactions.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fromCustomer", nullable = false)
	private Customer from;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "toCustomer", nullable = false)
	private Customer to;
	private BigDecimal amount;
	private Date date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Customer getFrom() {
		return from;
	}
	public void setFrom(Customer from) {
		this.from = from;
	}
	public Customer getTo() {
		return to;
	}
	public void setTo(Customer to) {
		this.to = to;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
