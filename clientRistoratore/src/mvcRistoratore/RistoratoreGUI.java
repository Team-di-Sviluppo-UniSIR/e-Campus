package mvcRistoratore;

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
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

//import mvcDashboard.DashboardBusinessLogic;

public class RistoratoreGUI extends JFrame {

	private JTable table;
	private JTextField nomeMensa;
	private JTextField capacitaMensa;
	private JTextField N_mensa2;
	private JTextField G_settimana1;
	private JTextField TipoApertura;
	private JTextField Data;
	private JTextField NomeMenu;
	private JTextField TipoMenu;
	private JTextField nomemensa3;
	private JTextField giornosettimana3;
	private JTextField tipoapertura3;
	private JTextField data3;
	private JTextField nomepiatto3;
	private JTextField tipopiatto3;
	private JTextField prezzo3;
	private JTextField disponibilita3;
	// private RistoratoreBusinessLogic dataManager;

	RistoratoreBusinessLogic businessLogic = new RistoratoreBusinessLogic();

	public RistoratoreGUI() {
		this.setSize(1000, 400);
		JPanel panel = new JPanel();
		panel.setLayout(null);

		// etichetta di titolo tabella
		JLabel titolo = new JLabel("RISTORATORE VIEW");
		titolo.setBounds(414, 10, 216, 21);
		titolo.setFont(new Font("Calibri", Font.BOLD, 17));
		titolo.setForeground(Color.RED);
		titolo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(titolo);

		this.getContentPane().add(panel);

		nomeMensa = new JTextField();
		nomeMensa.setBounds(122, 58, 140, 19);
		panel.add(nomeMensa);
		nomeMensa.setColumns(10);

		JLabel esito1 = new JLabel("Esito:");
		esito1.setBounds(25, 158, 260, 13);
		panel.add(esito1);

		JButton N_mensa = new JButton("Inserisci");
		N_mensa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaNome = nomeMensa.getText();
				int mensaCapacita = Integer.parseInt(capacitaMensa.getText());
				nomeMensa.setText("");
				capacitaMensa.setText("");
				// DA METTERE SUL DATABASE MENSACAPACITA'
				esito1.setText("Esito:  " + businessLogic.insertCanteenCapacityFromAPI(mensaNome, mensaCapacita));

			}
		});
		N_mensa.setBounds(76, 127, 85, 21);
		panel.add(N_mensa);

		JLabel c_mensa = new JLabel("Capacit\u00E0 mensa");
		c_mensa.setForeground(Color.RED);
		c_mensa.setBounds(10, 90, 103, 13);
		panel.add(c_mensa);

		capacitaMensa = new JTextField();
		capacitaMensa.setBounds(122, 87, 140, 19);
		panel.add(capacitaMensa);
		capacitaMensa.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Inserisci capacit\u00E0 mensa");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setBounds(55, 35, 163, 13);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Nome mensa");
		lblNewLabel_3.setBounds(10, 61, 85, 13);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Inserimento nuovo menu");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(403, 35, 209, 13);
		panel.add(lblNewLabel);

		N_mensa2 = new JTextField();
		N_mensa2.setBounds(490, 58, 140, 19);
		panel.add(N_mensa2);
		N_mensa2.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome mensa");
		lblNewLabel_1.setBounds(356, 61, 103, 13);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_4 = new JLabel("Giorno della settimana");
		lblNewLabel_4.setBounds(356, 90, 118, 13);
		panel.add(lblNewLabel_4);

		G_settimana1 = new JTextField();
		G_settimana1.setBounds(490, 87, 140, 19);
		panel.add(G_settimana1);
		G_settimana1.setColumns(10);

		TipoApertura = new JTextField();
		TipoApertura.setBounds(490, 116, 140, 19);
		panel.add(TipoApertura);
		TipoApertura.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tipo apertura");
		lblNewLabel_5.setBounds(356, 119, 85, 13);
		panel.add(lblNewLabel_5);

		Data = new JTextField();
		Data.setBounds(490, 145, 140, 19);
		panel.add(Data);
		Data.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Data");
		lblNewLabel_6.setBounds(356, 148, 45, 13);
		panel.add(lblNewLabel_6);

		NomeMenu = new JTextField();
		NomeMenu.setBounds(490, 174, 140, 19);
		panel.add(NomeMenu);
		NomeMenu.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Nome menu");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setBounds(356, 177, 85, 13);
		panel.add(lblNewLabel_7);

		TipoMenu = new JTextField();
		TipoMenu.setBounds(490, 203, 140, 19);
		panel.add(TipoMenu);
		TipoMenu.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Tipo menu");
		lblNewLabel_8.setForeground(Color.RED);
		lblNewLabel_8.setBounds(356, 206, 85, 13);
		panel.add(lblNewLabel_8);

		JLabel esito2 = new JLabel("Esito:");
		esito2.setBounds(352, 268, 280, 13);
		panel.add(esito2);

		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeMensa = N_mensa2.getText();
				String giornoSettimana = G_settimana1.getText();
				String tipoApertura = TipoApertura.getText();
				String data = Data.getText();
				String nomeMenu = NomeMenu.getText();
				String tipoMenu = TipoMenu.getText();

				N_mensa2.setText("");
				G_settimana1.setText("");
				TipoApertura.setText("");
				Data.setText("");
				NomeMenu.setText("");
				TipoMenu.setText("");

				esito2.setText("Esito:  " + businessLogic.insertNewMenuFromAPI(nomeMensa, giornoSettimana, tipoApertura,
						data, nomeMenu, tipoMenu));
			}
		});
		btnNewButton.setBounds(434, 237, 85, 21);
		panel.add(btnNewButton);

		JLabel lblInserimentoNuovoPiatto = new JLabel("Inserimento nuovo piatto");
		lblInserimentoNuovoPiatto.setForeground(Color.BLUE);
		lblInserimentoNuovoPiatto.setBounds(767, 35, 209, 13);
		panel.add(lblInserimentoNuovoPiatto);

		JLabel lblNewLabel_1_1 = new JLabel("Nome mensa");
		lblNewLabel_1_1.setBounds(703, 61, 103, 13);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_4_1 = new JLabel("Giorno della settimana");
		lblNewLabel_4_1.setBounds(703, 90, 118, 13);
		panel.add(lblNewLabel_4_1);

		JLabel lblNewLabel_5_1 = new JLabel("Tipo apertura");
		lblNewLabel_5_1.setBounds(703, 119, 85, 13);
		panel.add(lblNewLabel_5_1);

		JLabel lblNewLabel_6_1 = new JLabel("Data");
		lblNewLabel_6_1.setBounds(703, 148, 45, 13);
		panel.add(lblNewLabel_6_1);

		JLabel lblNewLabel_7_1 = new JLabel("Nome piatto");
		lblNewLabel_7_1.setForeground(Color.RED);
		lblNewLabel_7_1.setBounds(703, 177, 85, 13);
		panel.add(lblNewLabel_7_1);

		JLabel lblNewLabel_8_1 = new JLabel("Tipo piatto");
		lblNewLabel_8_1.setForeground(Color.RED);
		lblNewLabel_8_1.setBounds(703, 206, 85, 13);
		panel.add(lblNewLabel_8_1);

		nomemensa3 = new JTextField();
		nomemensa3.setColumns(10);
		nomemensa3.setBounds(841, 58, 135, 19);
		panel.add(nomemensa3);

		giornosettimana3 = new JTextField();
		giornosettimana3.setColumns(10);
		giornosettimana3.setBounds(841, 87, 135, 19);
		panel.add(giornosettimana3);

		tipoapertura3 = new JTextField();
		tipoapertura3.setColumns(10);
		tipoapertura3.setBounds(841, 116, 135, 19);
		panel.add(tipoapertura3);

		data3 = new JTextField();
		data3.setColumns(10);
		data3.setBounds(841, 145, 135, 19);
		panel.add(data3);

		nomepiatto3 = new JTextField();
		nomepiatto3.setColumns(10);
		nomepiatto3.setBounds(841, 174, 135, 19);
		panel.add(nomepiatto3);

		tipopiatto3 = new JTextField();
		tipopiatto3.setColumns(10);
		tipopiatto3.setBounds(841, 203, 135, 19);
		panel.add(tipopiatto3);

		prezzo3 = new JTextField();
		prezzo3.setColumns(10);
		prezzo3.setBounds(841, 238, 135, 19);
		panel.add(prezzo3);

		JLabel lblNewLabel_8_1_1 = new JLabel("Prezzo");
		lblNewLabel_8_1_1.setForeground(Color.RED);
		lblNewLabel_8_1_1.setBounds(703, 241, 85, 13);
		panel.add(lblNewLabel_8_1_1);

		disponibilita3 = new JTextField();
		disponibilita3.setColumns(10);
		disponibilita3.setBounds(841, 267, 135, 19);
		panel.add(disponibilita3);

		JLabel lblNewLabel_8_1_1_1 = new JLabel("Disponibilit\u00E0 iniziale");
		lblNewLabel_8_1_1_1.setForeground(Color.RED);
		lblNewLabel_8_1_1_1.setBounds(703, 270, 118, 13);
		panel.add(lblNewLabel_8_1_1_1);

		JLabel esito3 = new JLabel("Esito:");
		esito3.setBounds(696, 327, 280, 13);
		panel.add(esito3);

		JButton btnNewButton_1 = new JButton("Inserisci");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeMensa = nomemensa3.getText();
				String giornoSettimana = giornosettimana3.getText();
				String tipoApertura = tipoapertura3.getText();
				String data = data3.getText();
				String nomePiatto = nomepiatto3.getText();
				String tipoPiatto = tipopiatto3.getText();
				double prezzo = Double.parseDouble(prezzo3.getText());
				int dispIniziale = Integer.parseInt(disponibilita3.getText());

				nomemensa3.setText("");
				giornosettimana3.setText("");
				tipoapertura3.setText("");
				data3.setText("");
				nomepiatto3.setText("");
				tipopiatto3.setText("");
				prezzo3.setText("");
				disponibilita3.setText("");

				// DA INSERIRE NUOVO PIATTO
				esito3.setText("Esito:  " + businessLogic.insertDishFromAPI(nomeMensa, giornoSettimana, tipoApertura,
						data, nomePiatto, tipoPiatto, prezzo, dispIniziale));

			}

		});
		btnNewButton_1.setBounds(767, 296, 85, 21);
		panel.add(btnNewButton_1);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.pack();
		this.setVisible(true);

	}
}
