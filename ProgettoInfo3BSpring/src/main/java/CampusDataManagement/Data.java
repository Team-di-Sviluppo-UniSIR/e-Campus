package CampusDataManagement;

public class Data {
	private String giorno;
	private String mese;
	private String anno;

	public Data(String giorno, String mese, String anno) {
		super();
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}

	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	public String getMese() {
		return mese;
	}

	public void setMese(String mese) {
		this.mese = mese;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.giorno + "/" + this.mese + "/" + this.anno;
	}

}
