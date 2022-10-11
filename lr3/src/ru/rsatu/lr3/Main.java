package ru.rsatu.lr3;

import java.lang.reflect.Field;  //работа с рефлексией - поля
import java.lang.reflect.Method; //работа с рефлексией - методы
import java.util.Arrays;
import java.util.List;

import ru.rsatu.lr3.sources.BigBook;
import ru.rsatu.lr3.sources.Cat;
import ru.rsatu.lr3.sources.Pet;
import ru.rsatu.lr3.sources.WarAndPeace;

import java.lang.reflect.InvocationTargetException; //работа с рефлексией - исключения

public class Main {

	public static void main(String[] args) {
		
		//Работа с интерфейсом и абстрактными классами
		System.out.println("	Work with interface and abstract classes:");
		Pet cat = new Cat();			//создание экземпляров
		BigBook book = new WarAndPeace();	//
		cat.voice();					// вызов методов
		book.bookName();				//
		
		//Работа с рефлексией, выгрузка полей и методов
		System.out.println("\n	Work with Reflection:\nAll declared Fields of class WarAndPeace:");
		Field[] readingFields = book.getClass().getDeclaredFields(); //получение массива private-полей класса WarAndPeace
		for (Field allfield : readingFields) {	//построчный вывод полученного массива
		    System.out.println(allfield);
		}
		System.out.println("\nAll Methods of class WarAndPeace:");
		Method[] readingMethods = book.getClass().getDeclaredMethods(); //получение массива методов класса WarAndPeace
		for (Method method : readingMethods) {	//построчный вывод полученного массива
		    System.out.println(method);
		}
		
		//Вызов методов с помощью рефлексии
		System.out.println("\nCalling methods with reflection:");
		 try {
			 Method callVoice = cat.getClass().getDeclaredMethod("voice"); 	//получение метода класса Cat
			 callVoice.setAccessible(true);									//получение доступа к методу
			 callVoice.invoke(cat);											//вызов метода
			 
			 Method callLives = cat.getClass().getDeclaredMethod("lives");
			 callLives.setAccessible(true);
			 callLives.invoke(cat);
		 } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) { //обработка исключений
			 e.printStackTrace();
		 }	

		 //Вывод предков класса
		 System.out.println ("\nAll Parents of class Cat:");
		 Class<?>   clazz = Cat.class; 
		 Class<?>[] parents = clazz.getInterfaces(); //определение массива предков
		 for(Class<?> parentsList : parents) { 
			 System.out.println (parentsList.getName()); 
		 }
		 
		 //Работа с коллекциями
		 System.out.println ("\n	Collection's examples");
		 CollectionsExamples coll = new CollectionsExamples();
		 coll.Collections();
		 
		 //Работа с Generics
		 System.out.println ("\n	Generics Class");
		 GenericClass<String> generic = new GenericClass<>();
		 List<String> list = Arrays.asList("First","Second");
		 generic.GenericMethod(list);

	}
}