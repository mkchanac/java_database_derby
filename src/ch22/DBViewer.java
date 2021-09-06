package ch22;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.sql.*;


public class DBViewer extends Application {

	final String DB_URL = "jdbc:derby:CoffeeDB";
	TextArea queryTextArea;
	TextArea resultsTextArea;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		final int COL_SIZE = 50;
		final int ROW_SIZE = 10;
		final double SPACING = 10.0;
		
		queryTextArea = new TextArea();
		queryTextArea.setPrefColumnCount(COL_SIZE);
		queryTextArea.setPrefRowCount(ROW_SIZE);
		
		resultsTextArea = new TextArea();
		resultsTextArea.setPrefColumnCount(COL_SIZE);
		resultsTextArea.setPrefRowCount(ROW_SIZE);
		
		Button submitButton = new Button("Submit");
		submitButton.setOnAction(new SubmitButtonHandler());
		
		VBox vbox = new VBox(SPACING, queryTextArea, submitButton, resultsTextArea);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(SPACING));
		primaryStage.setTitle("Enter a SELECT Query");
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	
	class SubmitButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			try {
				resultsTextArea.setText("");
				Connection conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(queryTextArea.getText());
				ResultSetMetaData meta = resultSet.getMetaData();
				String output = "";
				
				while(resultSet.next()) {
					for(int i = 1; i <= meta.getColumnCount(); i++) {
						output += resultSet.getString(i);
						output += '\t';
					}
					output +='\n';
					
				}
				
				resultsTextArea.setText(output);
				stmt.close();
				conn.close();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			
		}
		
	}

	
	
}
