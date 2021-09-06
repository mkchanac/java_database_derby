package ch22;

import java.util.Scanner;
import java.sql.*;

public class CoffeeDeleter {
	public static void main(String[] args) {
		String prodNum;
		String sure;

		final String DB_URL = "jdbc:derby:CoffeeDB";
		Scanner keyboard = new Scanner(System.in);

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			System.out.print("Enter the product number: ");
			prodNum = keyboard.nextLine();

			if (findAndDisplayProduct(stmt, prodNum)) {
				System.out.print("Are you sure you want to delete this item (y/n): ");
				sure = keyboard.nextLine();

				if (Character.toUpperCase(sure.charAt(0)) == 'Y') {
					deleteCoffee(stmt, prodNum);
				} else {
					System.out.println("The item was not deleted.");
				}

			} else {
				System.out.println("That product was not found");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static boolean findAndDisplayProduct(Statement stmt, String prodNum) throws SQLException {
		boolean productFound;
		String sqlStatement = "SELECT * FROM Coffee WHERE ProdNum = '" + prodNum + "'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		if (result.next()) {
			System.out.println("Description: " + result.getString("Description"));
			System.out.println("Product Number: " + result.getString("ProdNum"));
			System.out.println("Price: $" + result.getDouble("Price"));
			productFound = true;
		} else {
			productFound = false;
		}

		return productFound;

	}

	public static void deleteCoffee(Statement stmt, String prodNum) throws SQLException {
		String sqlStatement = "DELETE FROM Coffee WHERE ProdNum = '" + prodNum + "'";
		int rows = stmt.executeUpdate(sqlStatement);
		System.out.println(rows + " row(s) deleted.");
		
	}
}
