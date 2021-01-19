package mvcDashboard;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DashboardGUI extends JFrame {
	private JTable table;

	public DashboardGUI() {
		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// etichetta di titolo tabella
		JLabel lblNewLabel = new JLabel("DASHBOARD MENSE");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel);

		// spazio orizzontale
		panel.add(Box.createRigidArea(new Dimension(0, 5)));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		// tabella
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}) {
			Class[] columnTypes = new Class[] { String.class, Integer.class, Integer.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public void refreshDashboard() {

		DashboardBusinessLogic dataManager = new DashboardBusinessLogic();

		// nomi delle colonne (tabella)
		String[] colNames = new String[] { "Nome mensa", "Capacit\u00E0", "Posti disponibili", "Stato" };

		List<String> nomiMense = dataManager.getNomiMense();
		List<Integer> capacitaMense = dataManager.getCapacitaMense();
		List<Integer> availableSeatsMense = dataManager.getAvailableSeatsMense();

		final int numRighe = nomiMense.size();
		final int numCol = colNames.length;

		// oggetto che contiene i dati con cui aggiornare la tabella
		Object[][] tableData = new Object[numRighe][numCol];
		
		// update "nomiMense"
		// nomiMense = prima colonna (0) --> tableData[i][0] --> fixedIndex = 0
		tableData = updateCampo(tableData, 0, nomiMense);
		// update "capacitaMense"
		// --> tableData[i][1] --> fixedIndex = 1
		tableData = updateCampo(tableData, 1, capacitaMense);
		// update "availableSeatsMense"
		// --> tableData[i][2] --> fixedIndex = 2
		tableData = updateCampo(tableData, 2, availableSeatsMense);

		// creo nuova tabella che fa da modello per aggiornare la tabella precedente
		DefaultTableModel model = new DefaultTableModel(tableData, colNames);
		// aggiorno tabella precedente
		table.setModel(model);
		
		
	}

	/**
	 * Aggiorna le righe della tabella della GUI nella colonna specificata e
	 * utilizzando i dati contenuti nella lista
	 * 
	 * @param fixedIndex colonna da aggiornare
	 * @param list       dati con cui aggiornare le righe
	 *
	 */
	private <T> Object[][] updateCampo(Object[][] tableData, final int fixedIndex, List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			tableData[i][fixedIndex] = list.get(i);
		}
		return tableData;
	}

}
