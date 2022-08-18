package com.nlp.tokenize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.nlp.tokenize.controller.Type;
import com.nlp.tokenize.controller.myController;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(myController.class)
public class myControllerTest extends myController{

	@MockBean
	private myController myc;
	
	static String inputInJson;
	 myController obj;
	 Type type;
	@Test
	 public List<Object> strTest(final String input)
	 {
		 
		 List<String> expected = Arrays.asList("show","my","appoinment");
		 List<String>  actual= null;
		 
		 String inputInJson[]=input.split(" ");
	        for(String y:inputInJson){
	            try{
	                actual.add(y);
	            }catch(Exception e){}
	        }
	        assertFalse(actual.isEmpty());
	        assertThat(actual, containsInAnyOrder(
	                hasProperty("actual", is("show")),
	                hasProperty("actual", is("my")),
	                hasProperty("actual", is("appoinment"))
	        ));
	        assertEquals(actual,IsIterableContainingInOrder.contains(expected.toArray()));
	        List<String> info = getAllUser(coreLabels, type);
			userinfo = info.get(0);
			Object[] objects = restTemplate.getForObject("http://localhost:8080"+ task + userinfo, Object[].class);
			return (List<Object>) Arrays.asList(objects); 	 
	 }
	
}
