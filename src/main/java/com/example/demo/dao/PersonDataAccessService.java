package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	// 회원가입
	@Override
	public int insertPerson(Person person) {
		// TODO Auto-generated method stub
		final String sql = "INSERT INTO person(email,password) VALUES (?,?)";
		jdbcTemplate.update(sql, new Object[]{person.getEmail(), person.getPassword()});
		return 0;
	}
	// 회원 목록 출력
	@Override
	public List<Person> selectAllPeople() {
		// TODO Auto-generated method stub
		final String sql = "SELECT email,password from person";
		return jdbcTemplate.query(sql,(resultSet,i) -> {
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			return new Person(email,password);
		});
	}
	// 회원 검색
	@Override
	public Optional<Person> selectPersonByEmail(String email) {
		// TODO Auto-generated method stub
		final String sql = "SELECT email,password from person WHERE email = ?";
		// 하나의 오브젝트를 위해선 QUERY 가 다르다
		Person person = jdbcTemplate.queryForObject(sql, new Object[]{email}, (resultSet, i) -> {
			String p_email = resultSet.getString("email");
			String password = resultSet.getString("password");
			return new Person(p_email,password);
		});
		return Optional.ofNullable(person);
	}
	// 회원 삭제
	@Override
	public int deletePersonByEmail(String email) {
		// TODO Auto-generated method stub
		final String sql = "DELETE FROM person WHERE email = ?";
		jdbcTemplate.update(sql,new Object[]{email});
		return 0;
	}
	// 비밀번호 업데이트
	@Override
	public int updatePersonByEmail(String email,Person update) {
		// TODO Auto-generated method stub
		final String sql = "UPDATE person SET password = ? WHERE email = ?";
		jdbcTemplate.update(sql,new Object[]{update.getPassword(),email});
		return 0;
	}
	// 회원 확인
	@Override
	public int checkPerson(Person person) {
		Optional<Person> n_person = selectPersonByEmail(person.getEmail());
		if(n_person.equals(person)) return 0;
		else return -1;
	}

}
