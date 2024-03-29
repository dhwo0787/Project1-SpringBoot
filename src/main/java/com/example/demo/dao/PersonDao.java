package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Person;

public interface PersonDao {
	
	// int insertPerson(UUID id, Person person);
	
	int insertPerson(Person person);
	
	List<Person> selectAllPeople();
	
	Optional<Person> selectPersonByEmail(String email);
	
	int deletePersonByEmail(String email);
	
	int updatePersonByEmail(String email,Person update);

	int checkPerson(Person person);
}
