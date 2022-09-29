package ru.rsatu;

import org.joda.time.LocalTime; //внешняя библиотека

import ru.rsatu.Child1; 

public class ParentClass {
	public static String Mess = "This message is declared with a variable in the parent class and is displayed by the first child class.";
	public static String Mess2 = "This message is declared with a variable in the parent class and is displayed by the second child class.";
	public static void main(String[] args) {
		LocalTime req_date = new LocalTime();
	    System.out.println("The parent class outputs the date and time: " + req_date);	
	    Child1.MessChild1();
	}
}
