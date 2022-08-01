package com.nlp.tokenize.controller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class user {
	@Id
	@GeneratedValue
	private static int id;
	private static String username;
	private static String phoneno;
	public static void save(myController user) {
		// TODO Auto-generated method stub
		
	}
	public static void setId(int i) {
		// TODO Auto-generated method stub\
		id=i;
		
	}
	public static void setUserName(String string) {
		// TODO Auto-generated method stub
		username=string;
	}
	public static void setPhone(String i) {
		// TODO Auto-generated method stub
		phoneno=i;
	}
	public static Object getId(int anyInt) {
		// TODO Auto-generated method stub
		return id;
	}
}
