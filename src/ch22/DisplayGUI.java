package ch22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;

import javax.swing.JComboBox;

public class DisplayGUI extends JFrame implements ActionListener {
//	Connection conn = DriverManager.getConnection("jdbc:derby:PianoInventory");
	JLabel downP = new JLabel("Down Payment(%)");
	JTextField downPValue = new JTextField(2);
	JLabel loanD = new JLabel("Select Loan Duration");
	String[] choice = { "12 Month", "24 Month" };
	JComboBox<String> monthChoice = new JComboBox<String>(choice);
	JTextArea pianoList = new JTextArea(10, 60);

	JButton order = new JButton("Place Order");

	public DisplayGUI() throws HeadlessException, SQLException {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		add(downP);
		add(downPValue);
		add(loanD);
		add(monthChoice);
		monthChoice.addActionListener(this);
		add(pianoList);
		ArrayList<String> piano = UsedPiano.getPianoList();
		String pianoListText = piano.get(0) + "\n" + piano.get(1) + "\n" + piano.get(2) + "\n" + piano.get(3) + "\n"
				+ piano.get(4);
		pianoList.setText(pianoListText);
		add(order);
		order.addActionListener(this);
	
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws HeadlessException, SQLException {
		// TODO Auto-generated method stub

		DisplayGUI aFrame = new DisplayGUI();
		final int WIDTH = 700;
		final int HEIGHT = 300;
		aFrame.setSize(WIDTH, HEIGHT);
		aFrame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int loanD = 0, downP = 0;
		if (e.getSource() == order) {
			downP = Integer.parseInt(downPValue.getText());

		} else if (e.getSource() == monthChoice) {
			String text = (String) monthChoice.getSelectedItem();
			if (text.equals("12 Month"))
				loanD = 12;
			else if (text.equals("24 Month"))
				loanD = 24;

		}
		try {
			Path file = Paths.get(
					"C:\\Users\\unite\\Desktop\\Douglas College\\2. 2021 Summer\\CSIS2175 Adv Integrated Software Dev (java)\\Final\\order.txt");
			OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
			ArrayList<String> piano = UsedPiano.getPianoList();
			PianoOrder newOrder = new PianoOrder(piano.get(1), piano.get(4), piano.get(2), downP, loanD);
			double price = Double.parseDouble(piano.get(3));
			double[] paymentSchedule = newOrder.paymentSchedule(price);
			String resultText = piano.get(0) + "\n" + piano.get(1) + "\n" + piano.get(2) + "\n" + piano.get(3) + "\n"
					+ piano.get(4);
			double totalPrice = 0;
			for (int i = 0; i < paymentSchedule.length; i++) {
				totalPrice += paymentSchedule[i];
			}
			resultText += "\nThe total price is " + totalPrice;
			double downpayment = totalPrice * downP / 100;
			resultText += "\nThe downpayment is " + downpayment + "\nYour loan duration will be " + loanD
					+ " months and the payment scehdule is : ";
			for (int i = 0; i < paymentSchedule.length; i++) {
				resultText += "\n month " + (i + 1) + " payment is 	" + paymentSchedule[i];
			}
			byte[] data = resultText.getBytes();
			output.write(data);
			output.flush();
			output.close();
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}

	}

}
