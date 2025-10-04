package gui;

import java.util.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import domain.*;

import java.awt.event.*;

public class LookMovementsGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	
	private JPanel contentPane;
	private JTextArea textAreaResultado;
	private JComboBox<Mugimenduak> comboBoxRequests;
	private DefaultComboBoxModel<Mugimenduak> bookedRides = new DefaultComboBoxModel<Mugimenduak>();
	
	public LookMovementsGUI(User u) {
		
		super();

		user=u;
		
		
		
		
		
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Movements"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		textAreaResultado = new JTextArea();
		textAreaResultado.setBounds(49, 96, 338, 32);
		contentPane.add(textAreaResultado);
		
		comboBoxRequests = new JComboBox<Mugimenduak>();
		comboBoxRequests.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Mugimenduak r = (Mugimenduak) comboBoxRequests.getSelectedItem();
				textAreaResultado.setText(r.toString());
			}
		});
		comboBoxRequests.setBounds(78, 34, 266, 22);
		contentPane.add(comboBoxRequests);
		comboBoxRequests.setModel(bookedRides);
		BLFacade facade = HomeGUI.getBusinessLogic();
		int i = 0;
		List<Mugimenduak> s = facade.getMugimenduak(user.getEmail());
		while(i<s.size()) {
			bookedRides.addElement(s.get(i));
			i++;
		}
		
	}
}
