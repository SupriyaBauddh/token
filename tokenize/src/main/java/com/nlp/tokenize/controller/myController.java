package com.nlp.tokenize.controller;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.nlp.tokenize.model.Type;

import org.hibernate.annotations.Entity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.hamcrest.CoreMatchers.is;

import org.springframework.web.client.RestTemplate;

@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RequestMapping("/nlp")
public class myController {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	static
	String userinfo;
	String task;
	
	final Type type = Type.valueOf("Person");
	@Autowired
	private StanfordCoreNLP stanfordcorenlp;
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/info")
	public List<Object> information(@RequestBody final String input){
		CoreDocument coreDocument = new CoreDocument(input);
		stanfordcorenlp.annotate(coreDocument);
		List<CoreLabel> coreLabels = coreDocument.tokens();
		

		for(CoreLabel coreLabel : coreLabels) {
			//printing the all the tokens
			String pos = coreLabel.getString(CoreAnnotations.PartOfSpeechAnnotation.class);

			switch(pos) {
				case "appointment":
					task = "appointment";
					break;
				case "data":
					task = "users";
					break;
				case "my":
					userinfo = "abcd";
					break;
			}
		}
		List<String> info = name(coreLabels, type);
		userinfo = info.get(0);
		assertFalse(info.isEmpty());
		Object[] objects = restTemplate.getForObject("http://localhost:5000"+ task + userinfo, Object[].class);
		return Arrays.asList(objects);
	}
	@Test
	@Autowired 
	private List<String> name(List<CoreLabel> coreLabels, final Type type)
	{
		List<String> names=coreLabels.stream().filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
				.map(CoreLabel::originalText).collect(Collectors.toList());
		assertFalse(names.isEmpty());
		return names;
	}
	
	public static boolean getUser() {
		// TODO Auto-generated method stub
		return true;
	}
}
