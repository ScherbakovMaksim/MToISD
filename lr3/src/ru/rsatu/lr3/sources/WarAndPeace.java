package ru.rsatu.lr3.sources;

public class WarAndPeace extends BigBook { 	//наследование абстрактного класса
	private String catRead = "Cat reading ";	//приватные переменные
	private String whatRead = "War and Peace."; 
	public void bookName() {
		System.out.println(catRead + whatRead);
	}
	public int pages(int Pages) {
		return Pages;
	}
}