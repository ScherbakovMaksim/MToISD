package ru.rsatu;

import ru.rsatu.ParentClass;

public class Child2 extends ParentClass{
	
	public static void MessChild2() {
		System.out.println(Mess2); //демонстрация наследования
		System.out.println("This message is declared in the method of the second child class, which is called in the first child class.");
	}
}