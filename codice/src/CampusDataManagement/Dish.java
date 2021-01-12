package CampusDataManagement;

public class Dish {
	private int idPiatto;
	private String nomePiatto;
	private String tipoPiatto;
	private double prezzo;
	private int initialAvailability;
	private int currentAvailability;
	private Menu menu;

	public Dish(int idPiatto, String nomePiatto, String tipoPiatto, double prezzo, int initialAvailability,
			int currentAvailability, Menu menu) {
		super();
		this.idPiatto = idPiatto;
		this.nomePiatto = nomePiatto;
		this.tipoPiatto = tipoPiatto;
		this.prezzo = prezzo;
		this.initialAvailability = initialAvailability;
		this.currentAvailability = currentAvailability;
		this.menu = menu;
	}

	public int getIdPiatto() {
		return idPiatto;
	}

	public void setIdPiatto(int idPiatto) {
		this.idPiatto = idPiatto;
	}

	public String getNomePiatto() {
		return nomePiatto;
	}

	public void setNomePiatto(String nomePiatto) {
		this.nomePiatto = nomePiatto;
	}

	public String getTipoPiatto() {
		return tipoPiatto;
	}

	public void setTipoPiatto(String tipoPiatto) {
		this.tipoPiatto = tipoPiatto;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getInitialAvailability() {
		return initialAvailability;
	}

	public void setInitialAvailability(int initialAvailability) {
		this.initialAvailability = initialAvailability;
	}

	public int getCurrentAvailability() {
		return currentAvailability;
	}

	public void setCurrentAvailability(int currentAvailability) {
		this.currentAvailability = currentAvailability;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
