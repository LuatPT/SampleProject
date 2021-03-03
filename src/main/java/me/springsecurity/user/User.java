package me.springsecurity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

	@Id
	@GeneratedValue
	@Column(name="id", unique = true)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	private String password;
	
}
