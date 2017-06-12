package com.demien.testloan.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

@Entity(name = "LOAN")
public class Loan implements Serializable, IPersistable {

	public static enum STATE {
		OPEN, CLOSED, EXTENDED
	}

	private static final long serialVersionUID = 7566184847780781908L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "IP_ADDR")
	private String ipAddr;

	@Column(name = "AMOUNT")
	private Float amount;

	@Column(name = "RATE")
	private Float rate;

	@Column(name = "STATE")
	private String state;

	@Column(name = "DEADLINE")
	private Date deadLine;

	@Column(name = "PARENT_ID")
	private Integer parentId;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CHANGE_DATE")
	private Date changeDate;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "USER_ID")
	private User user;

	public Loan() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Loan clone() {
		Loan loan = new Loan();
		loan.setAmount(new Float(this.getAmount()));
		loan.setDeadLine(new Date(this.getDeadLine().getTime()));
		loan.setIpAddr(new String(this.getIpAddr()));
		if (this.getParentId() != null) {
			loan.setParentId(new Integer(this.getParentId()));
		}
		loan.setRate(new Float(this.getRate()));
		loan.setState(new String(this.getState()));
		loan.setCreateDate(new Date());
		return loan;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Loan)) {
			return false;
		} else {
			Loan loan = (Loan) object;
			if (loan.getId().equals(this.getId())
					&& loan.getIpAddr().equals(this.getIpAddr())
					&& loan.getCreateDate().equals(this.getCreateDate())) {
				return true;
			}
			return false;
		}
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", ipAddr=" + ipAddr + ", amount=" + amount
				+ ", rate=" + rate + ", state=" + state + ", deadLine="
				+ deadLine + ", parentId=" + parentId + ", createDate="
				+ createDate + ", changeDate=" + changeDate + "]";
	}
	
	@Override
	public int hashCode() {
		return getId();
	}
	
	

}
