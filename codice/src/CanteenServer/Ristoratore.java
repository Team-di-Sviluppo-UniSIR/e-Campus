package CanteenServer;

public class Ristoratore {
	public int idRistoratore;
	public String nome;
	public String cognome;
	public String nomeSocieta;
	public String numTelefono;

	public Ristoratore(int idRistoratore, String nome, String cognome, String nomeSocieta, String numTelefono) {
		super();
		this.idRistoratore = idRistoratore;
		this.nome = nome;
		this.cognome = cognome;
		this.nomeSocieta = nomeSocieta;
		this.numTelefono = numTelefono;
	}

	public int getIdRistoratore() {
		return idRistoratore;
	}

	public void setIdRistoratore(int idRistoratore) {
		this.idRistoratore = idRistoratore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNomeSocieta() {
		return nomeSocieta;
	}

	public void setNomeSocieta(String nomeSocieta) {
		this.nomeSocieta = nomeSocieta;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

}
