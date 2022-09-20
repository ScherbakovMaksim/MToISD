package ru.rsatu.src;

public class secondclass {	
	static String text = "Static method of a second class";
		public static void StaticText() { //Статический метод второго класса
		System.out.println(text);
	}
		public void nonStaticText() { //Нестатический метод второго класса
		System.out.println("Not static method of a second class");
	}	
}