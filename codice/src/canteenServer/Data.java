package canteenServer;

public class Data {
	public short giorno;
	public short mese;
	public int anno;

	public Data(short giorno, short mese, int anno) {
		super();
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}

	public short getGiorno() {
		return giorno;
	}

	public void setGiorno(short giorno) {
		this.giorno = giorno;
	}

	public short getMese() {
		return mese;
	}

	public void setMese(short mese) {
		this.mese = mese;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

}
