package ru.rsatu.lr4.minterface;
public interface ModuleInterface {
	public static final int EXIT_SUCCESS = 0; //загрузка успешна
	public static final int EXIT_FAILURE = 1; //ошибка загрузки
	  
	public void load();	//загрузка модуля
	public int run(); //исполнение модуля и возвращение результата исполнения
	public void unload(); //выгрузка модуля

}