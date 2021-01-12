package dbConnectionTest;

public class Frutto {
	
	private String frutto;
	private String size;
	private String color;
	
	public Frutto(String frutto, String size, String color) {
		this.frutto=frutto;
		this.size=size;
		this.color=color;
	}
	
	@Override
	public String toString() {
		return frutto +" " + size + " " + color;
	}	

}
