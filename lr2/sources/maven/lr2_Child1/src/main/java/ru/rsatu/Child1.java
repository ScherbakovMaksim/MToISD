package ru.rsatu;

import ru.rsatu.ParentClass;
import ru.rsatu.Child2;

public class Child1 extends ParentClass {
   public static void MessChild1(){
	   System.out.println(Mess); //демонстрация наследования
	   Child2.MessChild2(); //построение зависимости от второго класса-потомка 
    }
}
