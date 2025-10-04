package gui;

import java.util.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import domain.*;

import java.awt.event.*;

public class IkusiErreklamazioaGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Driver driver;
	
	
	private JPanel contentPane;
	private JComboBox<Erreklamazioa> comboBoxRequests;
	private DefaultComboBoxModel<Erreklamazioa> bookedRides = new DefaultComboBoxModel<Erreklamazioa>();
	private JTextField textFieldErreklamazioa;
	
	public IkusiErreklamazioaGUI(Driver d) {
		
		super();

		driver=d;
		

		setTitle(ResourceBundle.getBundle("Etiquetas").getString("IkusiErreklamazioGUI.Ikusi"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		

		textFieldErreklamazioa = new JTextField();
		textFieldErreklamazioa.setBounds(10, 56, 414, 194);
		contentPane.add(textFieldErreklamazioa);
		textFieldErreklamazioa.setColumns(10);
		
		comboBoxRequests = new JComboBox<Erreklamazioa>();
		comboBoxRequests.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Erreklamazioa er = (Erreklamazioa) comboBoxRequests.getSelectedItem();
				textFieldErreklamazioa.setText(er.getZergaitia());
			}
		});
		comboBoxRequests.setBounds(10, 23, 414, 22);
		contentPane.add(comboBoxRequests);
		comboBoxRequests.setModel(bookedRides);
		BLFacade facade = HomeGUI.getBusinessLogic();
		int i = 0;
		List<Erreklamazioa> s = facade.geterreklamazio(d.getEmail());
		while(i<s.size()) {
			if(s.get(i)!=null) {
			bookedRides.addElement(s.get(i));
			}
			i++;
		}
		
	}
}
