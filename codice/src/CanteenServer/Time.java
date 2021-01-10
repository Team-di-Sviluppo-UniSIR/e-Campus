package CanteenServer;

public class Time {
	public short ore;
	public short minuti;
	public short secondi;

	public Time(short ore, short minuti, short secondi) {
		super();
		this.ore = ore;
		this.minuti = minuti;
		this.secondi = secondi;
	}

	public short getOre() {
		return ore;
	}

	public void setOre(short ore) {
		this.ore = ore;
	}

	public short getMinuti() {
		return minuti;
	}

	public void setMinuti(short minuti) {
		this.minuti = minuti;
	}

	public short getSecondi() {
		return secondi;
	}

	public void setSecondi(short secondi) {
		this.secondi = secondi;
	}

}
