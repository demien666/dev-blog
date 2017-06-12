package com.demien.testloan.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "USER")
public class User implements Serializable, IPersistable {

	private static final long serialVersionUID = -7022793569839517729L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "PASSWORD")
	private String password;
	

	public User() {		
	}
	
	public User(final String login, final String password) {		
		this.setLogin(login);
		this.setPassword(password);
	}
	
	public User(final Integer id, final String login, final String password) {		
		this.setId(id);
		this.setLogin(login);
		this.setPassword(password);
	}

	@Override
    public Integer getId() {
		return id;
	}

	@Override
    public void setId(final Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof User)) {
			return false;
		} else {
			User user = (User) object;
			if (user.getId().equals(this.getId())
					&& user.getLogin().equals(this.getLogin())
					&& user.getPassword().equals(this.getPassword())) {
				return true;
			}
			return false;
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ "]";
	}
	

	
	

}
