package com.nlp.tokenize;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;

import com.nlp.tokenize.controller.RepositoryImpl;
import com.nlp.tokenize.controller.myController;
import com.nlp.tokenize.controller.user;

import junit.framework.Assert;

public class myControllerTest {

	@InjectMocks
	myController mycontro;
	
	user User;
	@Mock
	RepositoryImpl repo;
	
	void setValue() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		User=new user();
		user.setId(012);
		user.setUserName("sup");
		user.setPhone("8177052089");
	}
	@Test
	void testGetUser()
	{
	when(user.getId(anyInt())).thenReturn(User);	
	boolean User=myController.getUser();
	assertNotNull(User);
	}
	@Test
	void testList(List<user> User)
	{
		Assert.assertEquals(List.of(), User);

	}
	
}
