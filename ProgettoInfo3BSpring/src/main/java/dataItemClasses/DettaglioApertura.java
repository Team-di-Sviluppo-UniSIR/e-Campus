package dataItemClasses;

public class DettaglioApertura {
	private int idDettaglio;
	private String giornoSettimana;
	private String tipoPasto;
	private Time orarioApertura;
	private Time orarioChiusura;

	public DettaglioApertura(int idDettaglio, String giornoSettimana, String tipoPasto, Time orarioApertura,
			Time orarioChiusura) {
		super();
		this.idDettaglio = idDettaglio;
		this.giornoSettimana = giornoSettimana;
		this.tipoPasto = tipoPasto;
		this.orarioApertura = orarioApertura;
		this.orarioChiusura = orarioChiusura;
	}

	public int getIdDettaglio() {
		return idDettaglio;
	}

	public void setIdDettaglio(int idDettaglio) {
		this.idDettaglio = idDettaglio;
	}

	public String getGiornoSettimana() {
		return giornoSettimana;
	}

	public void setGiornoSettimana(String giornoSettimana) {
		this.giornoSettimana = giornoSettimana;
	}

	public String getTipoPasto() {
		return tipoPasto;
	}

	public void setTipoPasto(String tipoPasto) {
		this.tipoPasto = tipoPasto;
	}

	public Time getOrarioApertura() {
		return orarioApertura;
	}

	public void setOrarioApertura(Time orarioApertura) {
		this.orarioApertura = orarioApertura;
	}

	public Time getOrarioChiusura() {
		return orarioChiusura;
	}

	public void setOrarioChiusura(Time orarioChiusura) {
		this.orarioChiusura = orarioChiusura;
	}

}
