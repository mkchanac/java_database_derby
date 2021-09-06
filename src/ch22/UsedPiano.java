package ch22;

import java.sql.*;
import java.util.ArrayList;

public class UsedPiano {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String DB_URL = "jdbc:derby:PianoInventory;create=true";
		try {
			// Create a connection to the database.
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE  TABLE PIANO(\r\n" + "ProdID	Int	NOT NULL,\r\n" + "Brand	Char(25)	NOT NULL,\r\n"
					+ "Model	Char(25),  \r\n" + "Price	Numeric(15,2)     	NOT NULL,\r\n"
					+ "ManufacturedYear	Char(25)	\r\n" + "    )");
			stmt.execute("INSERT INTO PIANO VALUES ( 101, " + "'Yamaha', " + "'YU10S', " + "10900, " + "'2014' )");
			stmt.execute("INSERT INTO PIANO VALUES ( 102, " + "'Yamaha', " + "'YU30S', " + "13900, " + "'2012' )");
			stmt.execute("INSERT INTO PIANO VALUES ( 103, " + "'Kawai', " + "'KUC1', " + "7900, " + "'2017' )");
			stmt.execute("INSERT INTO PIANO VALUES ( 104, " + "'Yamaha', " + "'CX2L', " + "37900, " + "'2015' )");
			stmt.execute("INSERT INTO PIANO VALUES ( 105, " + "'Stainway', " + "'D217', " + "47900, " + "'2000' )");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static ArrayList<String> getPianoList() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:PianoInventory");
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM PIANO");

		resultSet.last();
		int numRows = resultSet.getRow();
		resultSet.first();

		ArrayList<String> listData = new ArrayList<>();

		for (int index = 0; index < numRows; index++) {
			listData.add(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t"
					+ resultSet.getString(4) + "\t" + resultSet.getString(5));
			resultSet.next();
		}

		conn.close();
		stmt.close();
		return listData;
	}

}
