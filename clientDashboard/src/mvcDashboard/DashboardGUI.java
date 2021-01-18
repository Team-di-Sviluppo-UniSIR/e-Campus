package mvcDashboard;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DashboardGUI extends JFrame {
	   private JTable table;
	   private DashboardBusinessLogic dataManager;
	   
	   public DashboardGUI() {
		   JPanel panel = new JPanel();
		   dataManager = new DashboardBusinessLogic();
	    	
   		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
   		
   		//etichetta di titolo tabella
   		JLabel lblNewLabel = new JLabel("DASHBOARD MENSE");
   		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 17));
   		lblNewLabel.setForeground(Color.RED);
   		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
   		panel.add(lblNewLabel);
   		
   		//spazio orizzontale
   		panel.add(Box.createRigidArea(new Dimension(0,5)));
   		
   		JScrollPane scrollPane = new JScrollPane();
   		panel.add(scrollPane);
   		
   		//tabella
   		table = new JTable();
   		scrollPane.setViewportView(table);
   		table.setModel(new DefaultTableModel(
   			new Object[][] {
   				{dataManager.getNomiMense().get(0), dataManager.getCapacitaMense().get(0), dataManager.getAvailableSeatsMense().get(0), null},
   				{dataManager.getNomiMense().get(1), dataManager.getCapacitaMense().get(1), dataManager.getAvailableSeatsMense().get(1), null},
   				{dataManager.getNomiMense().get(2), dataManager.getCapacitaMense().get(2), dataManager.getAvailableSeatsMense().get(2), null},
   				{dataManager.getNomiMense().get(3), dataManager.getCapacitaMense().get(3), dataManager.getAvailableSeatsMense().get(3), null},
   			},
   			new String[] {
   				"Nome mensa", "Capacit\u00E0", "Posti disponibili", "Stato"
   			}
   		) {
   			Class[] columnTypes = new Class[] {
   				String.class, Integer.class, Integer.class, Object.class
   			};
   			public Class getColumnClass(int columnIndex) {
   				return columnTypes[columnIndex];
   			}
   		});
   		
   		this.getContentPane().add(panel);
   		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		this.pack();
   		this.setVisible(true);
	}
}
