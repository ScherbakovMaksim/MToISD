package ru.rsatu.jdbc.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Conn {
	public static Connection conn;
	public static Statement statmt;
	public static String url;
	public static ResultSet resSet;	
	
	// Подключение к БД
	public static void conn() throws ClassNotFoundException, SQLException 
	   {
		   @SuppressWarnings("resource")
		   Scanner scanner = new Scanner(System.in);
		   
		   System.out.print("Enter the path to the database: ");
		   url = scanner.nextLine();
		   url = "jdbc:sqlite:"+url+"\\TestDB.s3db";	
		     
		   Class.forName("org.sqlite.JDBC");
		   
		   conn = DriverManager.getConnection(url);
		   
		   System.out.println("Connected succesful");		  
		   
	   }
	
	// Создание таблицы
	public static void CreateDB() throws ClassNotFoundException, SQLException
	   {
		statmt = conn.createStatement();
		
		statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");
		
		System.out.println("Table is already exist");
	   }
	
	//Заполнение таблицы в пределах одной транзакции
	public static void WriteDB() throws SQLException
	{
		try (Connection conn = DriverManager.getConnection(url)){ 
			
			conn.setAutoCommit(false); //отключение автотранзакций
			   	
			PreparedStatement prepstatmt = conn.prepareStatement("INSERT INTO 'users' (name, phone) VALUES (?, ?)");
			
			prepstatmt.setString(1, "Nikolay");		
			prepstatmt.setInt(2, 765423);
			prepstatmt.executeUpdate();
			prepstatmt.setString(1, "Sergey");
			prepstatmt.setInt(2, 852336);
			prepstatmt.executeUpdate();
			
			System.out.println("The table has been written.");
			conn.commit();
			conn.setAutoCommit(true);			
			}

			catch(Exception ex){
				System.out.println("Connection failed...");//откат транзакции с закрытием соединения
				System.out.println(ex);
				conn.close();
			}
	}
		  
	
	
	//Вывод таблицы 
	public static void ReadDB() throws ClassNotFoundException, SQLException
	   {
			resSet = statmt.executeQuery("SELECT * FROM users");
		while(resSet.next())
		{
			int id = resSet.getInt("id");
			String  name = resSet.getString("name");
			String  phone = resSet.getString("phone");
	         System.out.println( "ID = " + id );
	         System.out.println( "name = " + name );
	         System.out.println( "phone = " + phone );
	         System.out.println();
		}	
		
		System.out.println("The table is displayed.");
	    }
	
		//Закрытие соединения с БД
		public static void CloseDB() throws ClassNotFoundException, SQLException
		   {
			conn.close();
			statmt.close();
			resSet.close();
			
			System.out.println("Connection closed");
		   }
		
		//Удаление таблицы
		public static void DeleteDB() throws ClassNotFoundException, SQLException
		   {
			statmt = conn.createStatement();
			statmt.execute("DELETE FROM users");		
			conn.close();
			System.out.println("Database deleted. Connection closed.");
		   }

}