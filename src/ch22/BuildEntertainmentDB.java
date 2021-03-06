package ch22;
import java.sql.*;

public class BuildEntertainmentDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String DB_URL = "jdbc:derby:EntertainmentDB;create=true";
		
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			
			System.out.println("Creating the Dvd table...");
			stmt.execute("CREATE TABLE Dvd (" + 
						"Title CHAR(25), " +
						"Minutes INTEGER, " +
						"Price DOUBLE)");
			
			stmt.close();
			conn.close();
			System.out.println("Done");
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
