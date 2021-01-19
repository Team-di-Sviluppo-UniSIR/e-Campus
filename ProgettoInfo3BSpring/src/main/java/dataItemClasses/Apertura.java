package dataItemClasses;

public class Apertura {
	private int idApertura;
	private Data data;
	private int availableSeats;
	private Mensa mensa;
	private DettaglioApertura dettaglioApertura;

	public Apertura(int idApertura, Data data, int availableSeats, Mensa mensa, DettaglioApertura dettaglioApertura) {
		super();
		this.idApertura = idApertura;
		this.data = data;
		this.availableSeats = availableSeats;
		this.mensa = mensa;
		this.dettaglioApertura = dettaglioApertura;
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

	public DettaglioApertura getDettaglioApertura() {
		return dettaglioApertura;
	}

	public void setDettaglioApertura(DettaglioApertura dettaglioApertura) {
		this.dettaglioApertura = dettaglioApertura;
	}

}
