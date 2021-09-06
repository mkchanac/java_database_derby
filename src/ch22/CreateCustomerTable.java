package ch22;
import java.sql.*;

public class CreateCustomerTable {

	public static void main(String[] args) {
		final String DB_URL = "jdbc:derby:CoffeeDB";
		
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE Customer2 ( CustomerNumber CHAR(10) NOT NULL PRIMARY KEY, "
					+ " Name CHAR(25), " 
					+ " Address CHAR(25), "
					+ " City CHAR(12), "
					+ " State CHAR(2), "
					+ " Zip CHAR(5) )";
			stmt.execute(sql);
			
			sql = "INSERT INTO Customer2 VALUES" + 
					"('101','Downtown Cafe', '17 N. Main Street', 'Asheville', 'NC', '55515')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO Customer2 VALUES" + 
					"('102','Main Street Grocery', '110 E. Main Street', 'Canton', 'NC', '55555')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO Customer2 VALUES" + 
					"('103',' The Coffee Place', '101 Center Plaza', 'Waynesville', 'NC', '55516')";
			stmt.executeUpdate(sql);
			
//			String sqlStatement = "SELECT * FROM Customer2";
//			
//			ResultSet result = stmt.executeQuery(sqlStatement);
//
//			
//			while(result.next()) {
//				System.out.println(result.getString("CustomerNumber") + "\t" + result.getString("Name") + "\t" + result.getString("Address") + "\t" + result.getString("City") + "\t" +  result.getString("State") + "\t" + result.getString("Zip"));
//			}
//			
			
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
