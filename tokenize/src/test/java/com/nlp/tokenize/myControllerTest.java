package com.nlp.tokenize;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nlp.tokenize.controller.Type;
import com.nlp.tokenize.controller.myController;

import edu.stanford.nlp.ling.CoreLabel;


@RunWith(SpringRunner.class)
@WebMvcTest(myController.class)
public class myControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private myController myc;
	
	static String inputInJson;
	 myController obj;
	 Type type;
	@Test
	public void testPostInput() throws Exception
	{
	    obj=new myController();
		obj.setId("1");
		obj.setUserInfo("Sup");
		obj.lemma("Appoinment");
		obj.action("Show");
		 inputInJson=this.mapTOJson(obj);
		String URI="nlp/tokenize/create";
		Mockito.when(myController.createInfo(Mockito.any(myController.class))).thenReturn(obj);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response=result.getResponse();
		
		String outputInJson=response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(),response.getStatus());
	}

	

	 private String mapTOJson(Object obj) throws JsonProcessingException{
		// TODO Auto-generated method stub
		ObjectMapper objMapper=new ObjectMapper();
		try {
			return objMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	 @Test
	 public void testGetAllUser() throws Exception
	 {
		 myController obj1=new myController();
		 obj1.setId("1");
		 obj1.setUserInfo("supriya");
		 obj1.lemma("Appoinment");
		 obj1.action("show appoinment");
		 
		 myController obj2=new myController();
		 obj2.setId("2");
		 obj2.setUserInfo("shipra");
		 obj2.lemma("Appoinment");
		 obj2.action("show appoinment");
		 
		 List<myController> list=new ArrayList<>();
		 list.add((myController) obj1);
		 list.add((myController) obj2);
		 
		 Mockito.when(myController.getAllUserName(list,type)).thenReturn(list);
		 
		 String URI="nlp/tokenize/alluser";
		 RequestBuilder requestBuilder=MockMvcRequestBuilders
					.get(URI)
					.accept(MediaType.APPLICATION_JSON);
		 
		 MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		 
		 String expectedJson=this.mapTOJson(list);
		 String outputInJson=result.getResponse().getContentAsString();
		 assertThat(outputInJson).isEqualTo(expectedJson);
		 
	 }
}
