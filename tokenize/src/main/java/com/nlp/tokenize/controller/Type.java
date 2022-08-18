package com.nlp.tokenize.controller;

public enum Type {
    PERSON("PERSON");

    private String type;
    Type(String type){
        this.type = type;
    }
    public String getName(){
        return type;
    }
}
