package mvcDashboard;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.DefaultTableModel;

// View dell'applicazione. Implementiamo "Observer" per fare in modo di
// controllare le modifiche sul model e venir notificati ad ogni modifica
// del model
class NumberCheckerView extends JFrame implements Observer {
    // Campi della view
    private JTextField m_userInputTf = new JTextField(5);
    private JTextField m_esitoTx     = new JTextField(20);
    private JButton    m_checkNumBtn = new JButton("Check N°");
    
    // Riferimento a model
    private NumberCheckerModel m_model;
    private JTable table;
    
    // Costruttore
    NumberCheckerView(NumberCheckerModel model) {
    	// Alloco il riferimento passato relativo al modello
    	m_model = model;
    	// Il model implementa Observable, aggiungo al modello un Observer 
    	// (la view stessa)
    	m_model.addObserver(this);
    	
    	JFrame frame = new JFrame("BoxLayout trial");
    	JPanel panel = new JPanel();
    	
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
    				{null, null, null, null},
    				{null, null, null, null},
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
    		
    		
    		frame.getContentPane().add(panel);
    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		frame.pack();
    		frame.setVisible(true);
    		
    
        
        // Creo il contenitore...
      //  this.setContentPane(content);
       // this.pack();
        // Imposto il titolo alla view
        this.setTitle("Number Checker");
        // Imposto il meccanismo di chiusura sulla finestra
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
    /*
     * I metodi seguenti servono a chi detiene un riferimento alla view 
     * (il controller)
     * Se non ci fossero, il controller dovrebbe avere un riferimento 
     * esplicito a tutti gli elementi della view per poter svolgere 
     * operazioni. In questo modo, invece, � sufficiente avere il 
     * riferimento all'intera classe CalcView.
     */
    // Getter per rendere disponibile all'esterno il valore del campo 
    // testo del textField
    String getUserInput() {
        return m_userInputTf.getText();
    }    
    
    // Rende disponibile all'esterno l'eventuale testo del messaggio di errore 
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
    
    // Permette di impostare dall'esterno il listener del bottone moltiplica
    void addCheckListener(ActionListener mal) {
        m_checkNumBtn.addActionListener(mal);
    }

	// Ereditato da Observer, chiama il metodo update definito localmente 
    // quando l'osservato (il modello) effettua una notifica
	@Override
	public void update(Observable o, Object arg) {
		update();		
	}
	
    // Permette di fare l'update dall'esterno.
    // In questo caso è l'azione compiuta dalla GUI quando il model 
	// (che è stato impostato come Observable) effettua una notifica
	private void update() {
		// Estraggo il valore corrente della "memoria" del modello dal
		// riferimento al modello e aggiorno il textField.
		System.out.println("[VIEW] Notified by the model");
		m_esitoTx.setText(m_model.getValue());
	}


}
