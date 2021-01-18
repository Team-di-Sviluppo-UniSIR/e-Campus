package mvcDashboard;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;

public class ClientDashboardView {
	JFrame frame = new JFrame("BoxLayout trial");
	JPanel panel = new JPanel();
	JButton buttonFirst = new JButton("First");
	JButton buttonSecond = new JButton("Second");
	
	public ClientDashboardView() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(buttonFirst);
		panel.add(buttonSecond);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	

}
