package mainProgram;

import canteenServer.CanteenStatusOutputIF;
import canteenServer.CanteenStatusOutputIMPL;
import canteenServer.Mensa;

public class MainClass {

	public static void main(String[] args) {
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		CanteenStatusOutputIF canteenOutput = new CanteenStatusOutputIMPL();

		System.out.println("Capacità mensa \""  + m1.nome + "\": "  + canteenOutput.getCanteenCapacity(m1));

	}

}
