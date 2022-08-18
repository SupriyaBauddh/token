package com.nlp.tokenize.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;


@RestController
@RequestMapping("/nlp")
public class myController {
	
	protected static String userinfo; //to store the info related to user
	static String action; //to store the info related to actions to be performed using lemma
	static String memberID;// to store the id of the user retrived from data base
	static String lemma;
	protected String task;
	protected List<CoreLabel> coreLabels;
	Type type;
	
	@Autowired
	private StanfordCoreNLP stanfordcorenlp;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@PostMapping("/nlp/tokenize")
	public List<Object> str(@RequestBody final String input){
		CoreDocument coreDocument = new CoreDocument(input);
		stanfordcorenlp.annotate(coreDocument);
		List<CoreLabel> coreLabels = coreDocument.tokens();
		
		for(CoreLabel coreLabel : coreLabels) {
			//printing the all the tokens
			String pos = coreLabel.getString(CoreAnnotations.PartOfSpeechAnnotation.class);
			String lemma = coreLabel.lemma();
			System.out.println(coreLabel.originalText());
			if(pos=="NNP")//NNP = proper noun 
			{
				userinfo = coreLabel.originalText();
			}
			if(lemma == "my") 
			{	
				userinfo="supriya";
			}
			if(lemma == "appointment") 
			{
				
				action = lemma;
			}
				
		}
		
		memberID = userinfo;
		List<String> info = getAllUser(coreLabels, type);
		userinfo = info.get(0);
		Object[] objects = restTemplate.getForObject("http://localhost:8080"+ task + userinfo, Object[].class);
		return Arrays.asList(objects);
	
	}
	
	public static List<String> getAllUser(List<CoreLabel> coreLabels, final Type type){
		return coreLabels.stream().filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
				.map(CoreLabel::originalText).collect(Collectors.toList());
	}
	

}
