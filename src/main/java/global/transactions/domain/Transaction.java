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
	private String code;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fromAccount", nullable = true)
	private Account from;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "toAccount", nullable = true)
	private Account to;
	private BigDecimal amount;
	private Date date;
	
	public Transaction() {
	}
	public Transaction(Account fromAccount, int code) {
		this.from = fromAccount;
		this.code = String.valueOf(code);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Account getFrom() {
		return from;
	}
	public void setFrom(Account from) {
		this.from = from;
	}
	public Account getTo() {
		return to;
	}
	public void setTo(Account to) {
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
