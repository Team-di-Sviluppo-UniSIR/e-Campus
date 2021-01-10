package CanteenServer;

public class Menu {
	public int idMenu;
	public String nomeMenu;
	public String tipoMenu;
	public Apertura apertura;

	public Menu(int idMenu, String nomeMenu, String tipoMenu, Apertura apertura) {
		super();
		this.idMenu = idMenu;
		this.nomeMenu = nomeMenu;
		this.tipoMenu = tipoMenu;
		this.apertura = apertura;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getNomeMenu() {
		return nomeMenu;
	}

	public void setNomeMenu(String nomeMenu) {
		this.nomeMenu = nomeMenu;
	}

	public String getTipoMenu() {
		return tipoMenu;
	}

	public void setTipoMenu(String tipoMenu) {
		this.tipoMenu = tipoMenu;
	}

	public Apertura getApertura() {
		return apertura;
	}

	public void setApertura(Apertura apertura) {
		this.apertura = apertura;
	}

}
