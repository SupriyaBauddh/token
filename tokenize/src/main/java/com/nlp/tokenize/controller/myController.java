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
	
	static String userinfo; //to store the info related to user
	static String action; //to store the info related to actions to be performed using lemma
	static String memberID;// to store the id of the user retrived from data base
	static String lemma;
	String task;
	
	Type type;
	
	@Autowired
	private StanfordCoreNLP stanfordcorenlp;
	
	@Autowired
	private RestTemplate restTemplate;
	
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
			if(lemma == "show") 
			{
				
				action="appoinments";
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
	public static Object createInfo(myController any) {
		userinfo=any.userinfo;
		action=any.action;
		memberID=any.memberID;
		lemma=any.lemma;
		return null;
	}
	public static List<String> getAllUser(List<CoreLabel> coreLabels, final Type type){
		return coreLabels.stream().filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
				.map(CoreLabel::originalText).collect(Collectors.toList());
	}
	public void setId(String i) {
		memberID=i;
		
	}

	public void setUserInfo(String name) {
		// TODO Auto-generated method stub
		userinfo=name;
	}

	public void lemma(String lemm) {
		// TODO Auto-generated method stub
		lemma=lemm;
	}

	public void action(String ac) {
		// TODO Auto-generated method stub
		action=ac;
	}
	public static Object getAllUserName(List<myController> list, Type type2) {
		// TODO Auto-generated method stub
		return list;
	}

	
	

}
