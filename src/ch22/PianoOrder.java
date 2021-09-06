package ch22;
import javax.swing.JOptionPane;

public class PianoOrder extends Instrument implements Order {
	private int downPayment;
	private int loanDuration;
	private double interestRate;

	public PianoOrder(String brand, String manufacturedYear, String model, int downPayment, int loanDuration) {
		super(brand, manufacturedYear, model);
		this.downPayment = downPayment;
		this.loanDuration = loanDuration;

		if (downPayment < 30) {
			System.out.println("The order cannot be generated.");
			JOptionPane.showMessageDialog(null, "Down payment must be >= 30% ");
		} else if (downPayment >= 70) {
			if (loanDuration == 12) {
				this.interestRate = 3;
			} else if (loanDuration == 24) {
				this.interestRate = 3;
			}
		} else if (downPayment >= 50) {
			if (loanDuration == 12) {
				this.interestRate = 3.5;
			} else if (loanDuration == 24) {
				this.interestRate = 3;
			}
		} else if (downPayment >= 30) {
			if (loanDuration == 12) {
				this.interestRate = 4;
			} else if (loanDuration == 24) {
				this.interestRate = 3.5;
			}
		}
	}

	public double[] paymentSchedule(double price) {
		double[] monthlyPayment = new double[loanDuration];
		double principlePaid = price / loanDuration;
		
		for(int i = 0; i < monthlyPayment.length; i++) {
			monthlyPayment[i] = principlePaid + price * ((interestRate/100))/loanDuration;
			price -= principlePaid;
		}
		

		return monthlyPayment;

	}

	@Override
	public void saleTax() {
		// TODO Auto-generated method stub

	}

	@Override
	public void totalPrice() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Play() {
		// TODO Auto-generated method stub

	}

}
