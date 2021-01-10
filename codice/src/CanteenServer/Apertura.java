package CanteenServer;

public class Apertura {
	public int idApertura;
	public Data data;
	public int availableSeats;
	public Mensa mensa;

	public Apertura(int idApertura, Data data, int availableSeats, Mensa mensa) {
		super();
		this.idApertura = idApertura;
		this.data = data;
		this.availableSeats = availableSeats;
		this.mensa = mensa;
	}

	public int getIdApertura() {
		return idApertura;
	}

	public void setIdApertura(int idApertura) {
		this.idApertura = idApertura;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Mensa getMensa() {
		return mensa;
	}

	public void setMensa(Mensa mensa) {
		this.mensa = mensa;
	}

}
