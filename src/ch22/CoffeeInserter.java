package ch22;

import java.util.Scanner;
import java.sql.*;

public class CoffeeInserter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String description;
		String prodNum;
		double price;
		
		final String DB_URL = "jdbc:derby:CoffeeDB";
		
		Scanner keyboard = new Scanner(System.in);
		
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			System.out.print("Enter the coffee description: ");
			description = keyboard.nextLine();
			System.out.print("Enter the product number: ");
			prodNum = keyboard.nextLine();
			System.out.print("Enter the price: ");
			price = keyboard.nextDouble();
			
			Statement stmt = conn.createStatement();
			String sqlStatement = "INSERT INTO Coffee (ProdNum, Price, Description) VALUES ('" + prodNum + "', " + price +", '" + description + "')";
			int rows = stmt.executeUpdate(sqlStatement);
			
			System.out.println( rows + " row(s) added to the table.");
			
			conn.close();			
			
		} catch (Exception e) {System.out.println(e.getMessage());}
		
	}

}
