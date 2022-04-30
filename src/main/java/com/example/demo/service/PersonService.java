package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;

@Service
public class PersonService {
	private final PersonDao personDao;
	
	@Autowired
	public PersonService(@Qualifier("postgres") PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public int addPerson(Person person) {
		return personDao.insertPerson(person);
	}
	
	public List<Person> getAllPeople() {
		return personDao.selectAllPeople();
	}
	public Optional<Person> getPersonByEmail(String email) {
		return personDao.selectPersonByEmail(email);
	}
	public int deletePerson(String email) { return personDao.deletePersonByEmail(email);}
	public int updatePerson(String email, Person newPerson) {
		return personDao.updatePersonByEmail(email, newPerson);
	}
	public int checkPerson(Person person){ return personDao.checkPerson(person);}
}
