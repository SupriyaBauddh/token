package com.nlp.tokenize.user;

public class user {
	static String userinfo; //to store the info related to user
	static String action; //to store the info related to actions to be performed using lemma
	static String memberID;// to store the id of the user retrived from data base
	static String lemma;
	public void setId(String string) {
		// TODO Auto-generated method stub
		memberID=string;
		
	}
	public void setUserInfo(String string) {
		// TODO Auto-generated method stub
		userinfo=string;
	}
	public void lemma(String string) {
		// TODO Auto-generated method stub
		lemma=string;
	}
	public void action(String string) {
		// TODO Auto-generated method stub
		action=string;
	}
}
