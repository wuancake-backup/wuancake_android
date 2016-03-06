package com.god530.yes;

public class ObjectDemo {

	public static void main(String[] args) {
		Dog dog = new Dog();
		
		
//		Dog dog = new Dog();
//		Dog dog1 = new Dog("flannery");
		
		Puppy puppy = new Puppy("Flannery");
		puppy.setAge(2);
		int age = puppy.getAge();
		System.out.println(age);
	}
	
}


