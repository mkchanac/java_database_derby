package ch22;
import java.sql.*;
import java.util.Scanner;

public class MetaDataDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String DB_URL = "jdbc:derby:CoffeeDB";
		try {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter a SELECT statement for the CoffeeDB database: ");
			String sql = keyboard.nextLine();
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			ResultSetMetaData meta = resultSet.getMetaData();
			
			System.out.println("The result set has " + meta.getColumnCount() + " column(s).");
			
			for(int i = 1; i <= meta.getColumnCount(); i++) {
				System.out.println(meta.getColumnName(i) + ", " + meta.getColumnTypeName(i));
			}
			
			System.out.println("\nHere are the result set rows: ");
			while (resultSet.next()) {
				for(int i =1; i <= meta.getColumnCount(); i++) {
					System.out.print(resultSet.getString(i));
				}
				System.out.println();
			}
			
			stmt.close();
			conn.close();
			
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
