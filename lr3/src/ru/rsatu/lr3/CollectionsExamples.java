package ru.rsatu.lr3;
import java.util.ArrayList; //работа с List
import java.util.HashSet; //работа с Set
import java.util.Set;

import ru.rsatu.lr3.sources.Animal;

import java.util.Map;//работа с Map
import java.util.HashMap;

public class CollectionsExamples {
	public void Collections() {
		//Работа с List
		ArrayList<String> SomePersons = new ArrayList<String>(); //создание экземпляра списка
        SomePersons.add("Nikolay");		//добавление элементов в список
		SomePersons.add("Masha");
		SomePersons.add("Max");
		SomePersons.add("Sasha");
		SomePersons.add("Sasha"); //элементы списка могут быть дублированы, но иметь разные индексы
		SomePersons.add(1, "Slava");	//добавляется элемент по индексу 1 (второй элемент списка), сдвиг списка
		System.out.println("Collections: List.");
		for(String person : SomePersons){ //вывод элементов списка
	            System.out.println(person);
	    }
		        		
        SomePersons.set(0, "Alexey"); // элемент 0 заменяется новым
        System.out.println("\nNew element 0: "+SomePersons.get(0));// вывод элемента под индексом 0
          
        System.out.printf("\nArrayList has %d elements: \n", SomePersons.size()); //вывод количества элементов списка
        
        for(String person : SomePersons){ //вывод элементов списка
            System.out.println(person);
        }
       
        // проверяем наличие элемента
        if(SomePersons.contains("Nikolay")){          
            System.out.println("\nArrayList contains Nikolay");
        } else {
        	System.out.println("\nNikolay is missing");
        	if(SomePersons.contains("Alexey")) {
        		System.out.println("Alexey is here\n");	
        	}
        }
          
        // удаление объектов по содержанию
        SomePersons.remove("Alexey");
        // удаление по индексу
        SomePersons.remove(1);
        
        Object[] SomePersonsArray = SomePersons.toArray(); //создание массива объектов
        for(Object person : SomePersonsArray){
          System.out.println(person);
        }
                
        //Работа с Set
        System.out.println("\nCollections: Set. \nAnimals:");
        //хеш-таблица объектов
        HashSet<Animal> someSet = new HashSet<Animal>();
        someSet.add(new Animal("Cow"));
        someSet.add(new Animal("Dog"));
        someSet.add(new Animal("Bird"));
        someSet.add(new Animal("Dog")); //поскольку создан новый экземпляр, это считается за иной объект
        for(Animal anim : someSet){          
            System.out.println(anim.getName());
        }
        
        HashSet<String> cities = new HashSet<String>(); //создание нового списка уникальных элементов
        cities.add("Moscow");
        cities.add("Petersburg");
        cities.add("Perm");
        cities.add("Moscow"); //элементы коллекции Set уникальны, поэтому данный элемент не будет добавлен
        System.out.println("\nCities:");
        for(String a : cities){          
            System.out.println(a);
        }
        
        //Работа с Map
        System.out.println("\nCollections: Map.");
        Map<String, Animal> animalMap = new HashMap<String, Animal>();
        animalMap.put("756", new Animal("Rabbit")); //создание экземпляров с уникальным ключом
        animalMap.put("234", new Animal("Goat"));
        animalMap.put("987", new Animal("Ferret"));
          
        for(Map.Entry<String, Animal> item : animalMap.entrySet()){
          
            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue().getName());
        }
        
        Animal ferret = animalMap.get("987");	//получение имени элемента по ключу
        System.out.println("\n"+ferret.getName());
        
        Set<String> keys = animalMap.keySet();	//получение ключей элементов
        System.out.println("\n"+keys);
        
        
	}	
}