package com.itwillbs.spring4;

public class PersonImpl implements Person{

	private String name;
	private int age;
	
	public PersonImpl() {	}
	public PersonImpl(String name) { 
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public void showMyInfo() {
		System.out.println(" 이름 : "+name);
		System.out.println(" 나이 : "+age);
	}
	
	
	@Override
	public void showMyInfo(String name,int age) {
		System.out.println(" 이름 : "+name);
		System.out.println(" 나이 : "+age);
	}

}
