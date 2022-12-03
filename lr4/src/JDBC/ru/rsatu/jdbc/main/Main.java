package ru.rsatu.jdbc.main;

import java.sql.SQLException;

import ru.rsatu.jdbc.connect.Conn;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Conn.conn(); //соединение
		Conn.CreateDB(); //создание таблицы
		Conn.WriteDB(); //заполнение таблицы (CRUD)
		Conn.ReadDB(); //чтение таблицы (CRUD)	
		Conn.CloseDB(); //закрытие соединения
		
   //	Conn.DeleteDB(); //удаление таблицы
	}
}