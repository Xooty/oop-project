package de.hwrberlin.autovermietung.cars;

public enum CarBrand {

	AUDI("Audi"),
	BMW("BMW"),
	DACIA("Dacia"),
	HYUNDAI("Hyundai"),
	MERCEDES_BENZ("Mercedes-Benz"),
	PORSCHE("Porsche"),
	RENAULT("Renault"),
	SEAT("Seat"),
	SKODA("Škoda"),
	SMART("smart"),
	VOLKSWAGEN("VW");

	private String name;
	
	private CarBrand(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
