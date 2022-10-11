package ru.rsatu.lr3;

import java.util.Collection;

class GenericClass<T> {	//Типизированный класс
	public <E> void GenericMethod(Collection<E> collection) {	//Типизированный метод
		for (E element : collection) {
			System.out.println(element);
		}
	}
}