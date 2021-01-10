package canteenServer;

public class Address {
	public String citta;
	public String provincia;
	public int CAP;
	public String via;
	public int nCivico;
	public Mensa nomeMensa;

	public Address(String citta, String provincia, int cAP, String via, int nCivico, Mensa nomeMensa) {
		super();
		this.citta = citta;
		this.provincia = provincia;
		CAP = cAP;
		this.via = via;
		this.nCivico = nCivico;
		this.nomeMensa = nomeMensa;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getCAP() {
		return CAP;
	}

	public void setCAP(int cAP) {
		CAP = cAP;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public int getnCivico() {
		return nCivico;
	}

	public void setnCivico(int nCivico) {
		this.nCivico = nCivico;
	}

	public Mensa getNomeMensa() {
		return nomeMensa;
	}

	public void setNomeMensa(Mensa nomeMensa) {
		this.nomeMensa = nomeMensa;
	}

}
