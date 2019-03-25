package com.demien.sprdata.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQuery(name = "UserGroup.namedQueryByName", query = "SELECT g FROM UserGroup g WHERE g.name = :name ")
@Getter
@Setter
public class UserGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;

	// @OneToMany(mappedBy = "group")
	// private List<User> users;

	public UserGroup() {

	}

}
