package mvcDashboard;

public class DashboardMain {

	public static void main(String[] args) {
		
		//creo e setto la finestra visible
		DashboardGUI view = new DashboardGUI();
		view.refreshDashboard();
		view.setVisible(true);
		
		DashboardBusinessLogic dataManager = new DashboardBusinessLogic();
		
		//refresh della dashboard ogni 10s
		while(true) {
		try {
		Thread.sleep(10000);
		view.refreshDashboard();
		}catch (Exception e) { }
		
		}
		
	}

}
