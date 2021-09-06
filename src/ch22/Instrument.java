package ch22;

public abstract class Instrument {
	private String brand;
	private String manufacturedYear;
	private String model;

	public Instrument(String brand, String manufacturedYear, String model) {
		this.brand = brand;
		this.manufacturedYear = manufacturedYear;
		this.model = model;
	}

	public abstract void Play();

	public String getBrand() {
		return brand;
	}

	public String getManufacturedYear() {
		return manufacturedYear;
	}

	public String getModel() {
		return model;
	}

}
