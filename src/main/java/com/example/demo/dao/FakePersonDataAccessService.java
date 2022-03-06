package com.example.demo.dao;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Person;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
	
	private static List<Person> DB = new ArrayList<>();

	@Override
	public int insertPerson(UUID id, Person person) {
		DB.add(new Person(id,person.getName()));
		return 1;
	}
	
	@Override
	public List<Person> selectAllPeople() {
		return DB;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		return DB.stream()
				.filter(person-> person.getId().equals(id))
				.findFirst();
	}

	@Override
	public int deletePersonById(UUID id) {
		
		return 0;
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		
		return 0;
	}

}
