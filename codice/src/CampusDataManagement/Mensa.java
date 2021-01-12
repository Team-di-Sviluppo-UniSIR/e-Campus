package CampusDataManagement;

public class Mensa {
	private int idMensa;
	private String nome;
	private int capacita;
	private Ristoratore proprietario;

	public Mensa(int idMensa, String nome, int capacita, Ristoratore proprietario) {
		super();
		this.idMensa = idMensa;
		this.nome = nome;
		this.capacita = capacita;
		this.proprietario = proprietario;
	}

	public int getIdMensa() {
		return idMensa;
	}

	public void setIdMensa(int idMensa) {
		this.idMensa = idMensa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCapacita() {
		return capacita;
	}

	public void setCapacita(int capacita) {
		this.capacita = capacita;
	}

	public Ristoratore getProprietario() {
		return proprietario;
	}

	public void setProprietario(Ristoratore proprietario) {
		this.proprietario = proprietario;
	}

}
