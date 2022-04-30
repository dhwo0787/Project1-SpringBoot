package com.example.demo.model;
import java.util.UUID;

import org.springframework.lang.NonNull;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	
	public Person(@JsonProperty("email")String email,@JsonProperty("password")String password) {
		this.email = email;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public void setPassword(String password) {this.password = password;}
	public void setEmail(String email) {
		this.email = email;
	}
}
