package com.example.demo.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {
	
	private final PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	// 데이터베이스 내 회원 정보 확인
	@PostMapping
	public void checkPerson(@Valid @NonNull @RequestBody Person person) { personService.checkPerson(person);}
	// 모든 회원 정보 출력
	@GetMapping
	public List<Person> getAllPeople() {
		return personService.getAllPeople();
	}
	// 특정 이메일을 가진 회원 정보 출력
	@GetMapping(path = "/{email}")
	public Person getPersonByEmail(@PathVariable("email") String email) {
		return personService.getPersonByEmail(email).orElse(null);
	}
	// 회원 삭제
	@DeleteMapping(path = "/{email}")
	public void deletePersonByEmail(@PathVariable("email") String email) {
		personService.deletePerson(email);
	}
	// 회원 가입
	@PutMapping
	public void addPerson(@Valid @NonNull @RequestBody Person person) {
		personService.addPerson(person);
	}
	// 회원 정보 변경
	@PutMapping(path = "/{email}")
	public void updatePerson(@PathVariable("email") String email ,@Valid @NonNull @RequestBody Person personToUpdate) {
		personService.updatePerson(email, personToUpdate);
	}
}
