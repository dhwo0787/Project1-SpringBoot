package com.example.demo.model;
import java.util.UUID;

import org.springframework.lang.NonNull;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	private UUID id;
	
	@NotBlank
	private String name;
	
	public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
	}
	public UUID getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public void setString(String name) {
		this.name = name;
	}
}
