package com.demien.sprdata.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NamedQuery(name = "User.namedQueryByName", query = "SELECT u FROM User u WHERE u.name = :name ")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupId")
	private UserGroup group;
	private String name;
	private Integer salary;

	public User() {
	}

}
