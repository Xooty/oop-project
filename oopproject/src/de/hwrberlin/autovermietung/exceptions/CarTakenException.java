package de.hwrberlin.autovermietung.exceptions;

public class CarTakenException extends Exception {

	private static final long serialVersionUID = 6555898960768567438L;

	public CarTakenException() {
		super("Das Fahrzeug wurde bereits reserviert.");
	}
	
	public CarTakenException(String message) {
		super(message);
	}
}
