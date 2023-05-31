package com.pp2;

public class Persona {
	protected int age;
	protected String name;
	
	public Persona() {
		
	}

	public Persona(int age, String name) {
		this.age = age;
		this.name= name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		 this.age=age;
	}
	public int getName(int name) {
		return name;
	}
	public void setName(String name) {
		 this.name=name;
	}
	public void showAge() {
		System.out.println("Age is" + age);
	}
}	
