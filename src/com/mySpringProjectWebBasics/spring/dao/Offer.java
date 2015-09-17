package com.mySpringProjectWebBasics.spring.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Offer {
	private int id;
	
	@Size(min=5, max=100) /*Hibernate dependencies is used for the bean validation*/
	private String name;
	
	@Size(min=5, max=100, message="Text shoud contain min of 20 - 250 characters")
	private String text;
	
	@NotNull
	@Pattern(regexp=".*\\@.*\\..*", message="Should be a valid Email ID.")
	private String email;
	
	public Offer() {
	}
	public Offer(String name, String text, String email) {
		super();
		this.name = name;
		this.text = text;
		this.email = email;
	}
	public Offer(int id, String name, String text, String email) {
		super();
		this.id = id;
		this.name = name;
		this.text = text;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", text=" + text + ", email=" + email + "]";
	}
	
}
