package ru.rsatu.lr1;

public class TextClass {
	public static void main(String[] args)
	{
		System.out.println("There are main class"); //Вывод текстовой строки на экран
		secondclass.StaticText(); //Вызов статического метода из второго класса
		secondclass nonstat = new secondclass(); //Создание экземпляра второго класса
		nonstat.nonStaticText(); //Вызов нестатического метода из второго класса
	}
}