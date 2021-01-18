package mvcDashboard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;

public class ClientDashboardView {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientDashboardView window = new ClientDashboardView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientDashboardView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    JFrame f;    
	    f=new JFrame();    
	    String data[][]={ {"101","Amit","670000"},    
	                          {"102","Jai","780000"},    
	                          {"101","Sachin","700000"}};    
	    String column[]={"ID","NAME","SALARY"};
	    f.setSize(300,400);    
	    f.setVisible(true);    
	}

}
