package com.nlp.tokenize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nlp.tokenize.controller.Repository;
import com.nlp.tokenize.controller.myController;
import com.nlp.tokenize.controller.user;

@DataJpaTest
public class RepositoryTest {

	private Repository repo;
	
	
	@Test
	@PostMapping("/userinfo")
	public void saveUser(@RequestBody myController User) {
		user.save(User);
		Assertions.assertTrue(myController.getUser());
	}
}
