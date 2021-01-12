package CampusDataManagement;

public class Time {
	private String ore;
	private String minuti;
	private String secondi;

	public Time(String ore, String minuti, String secondi) {
		super();
		this.ore = ore;
		this.minuti = minuti;
		this.secondi = secondi;
	}

	public String getOre() {
		return ore;
	}

	public void setOre(String ore) {
		this.ore = ore;
	}

	public String getMinuti() {
		return minuti;
	}

	public void setMinuti(String minuti) {
		this.minuti = minuti;
	}

	public String getSecondi() {
		return secondi;
	}

	public void setSecondi(String secondi) {
		this.secondi = secondi;
	}

}
