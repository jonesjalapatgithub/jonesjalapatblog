package com.jones.izo809;

public class Myutil {

	public static void main(String[] args) throws ClassNotFoundException {
		AB ab = new AB();
 
		Animal animal = new Dog();
		Food food = new Flesh();
		animal.eat(food);
	}
}
class AB
{
	}
class Food {
	public String toString() {
		return "Normal Food";
	}
}

class Flesh extends Food {
	public String toString() {
		return "Flesh Food";
	}
}

class Animal {
	

	
	public void eat(Food food) {
		System.out.println("Animal eats " + food);
	}
}

class Dog extends Animal {
	


	public void eat(Flesh flesh) {
		
		System.out.println("Dog eats " + flesh);
	}
}