package ru.rsatu.lr3.sources;

public class Cat implements Pet { 	//класс, имплементирующий интерфейс	
	public void voice() {
		System.out.println("Cat says Meow.");
	}
	public void lives() {
		System.out.println("The cat has nine lives.");
	}
}