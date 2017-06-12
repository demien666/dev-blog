package com.demien.richdomain.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "USER")
public class User extends AbstractModel<User, Integer> implements Serializable {

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
        super(User.class);
	}
	
	public User(String login, String password) {
        this();
		this.setLogin(login);
		this.setPassword(password);
	}
	
	public User(Integer id, String login, String password) {
        this();
		this.setId(id);
		this.setLogin(login);
		this.setPassword(password);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object object) {
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
