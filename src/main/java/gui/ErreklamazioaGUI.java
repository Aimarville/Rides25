package gui;

import java.util.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import domain.*;

import java.awt.event.*;

public class ErreklamazioaGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Passenger passenger;
	
	
	private JPanel contentPane;
	private JComboBox<RideBooked> comboBoxRequests;
	private DefaultComboBoxModel<RideBooked> bookedRides = new DefaultComboBoxModel<RideBooked>();
	private JTextField textFieldErreklamazioa;
	
	public ErreklamazioaGUI(Passenger p) {
		
		super();

		passenger=p;
		

		setTitle(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("ErreklamazioGUI.Erreklamazio"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JTextArea textAreaReslutado = new JTextArea();
		textAreaReslutado.setBounds(71, 169, 279, 22);
		contentPane.add(textAreaReslutado);
		textAreaReslutado.setEditable(false);
		
		comboBoxRequests = new JComboBox<RideBooked>();
		comboBoxRequests.setBounds(10, 23, 414, 22);
		contentPane.add(comboBoxRequests);
		comboBoxRequests.setModel(bookedRides);
		BLFacade facade = HomeGUI.getBusinessLogic();
		int i = 0;
		List<RideBooked> s = facade.getBookingsPass(passenger.getEmail(), passenger.getPassword());
		while(i<s.size()) {
			if(s.get(i).getRide().getFrom()!=null) {
			bookedRides.addElement(s.get(i));
			}
			i++;
		}	
		JButton btnNewButtonBukatuta = new JButton(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("ErreklamazioaGUI.Egin")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButtonBukatuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String zerg = textFieldErreklamazioa.getText();
			RideBooked rb = (RideBooked) comboBoxRequests.getSelectedItem();
			
			facade.erreklamazioa(zerg, p.getEmail(), rb.getBookNumber());			
				
			textAreaReslutado.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("ErreklamazioaGUI.Eginda"));
			}
		});
		btnNewButtonBukatuta.setBounds(153, 200, 98, 31);
		contentPane.add(btnNewButtonBukatuta);
		
		textFieldErreklamazioa = new JTextField();
		textFieldErreklamazioa.setBounds(10, 56, 414, 102);
		contentPane.add(textFieldErreklamazioa);
		textFieldErreklamazioa.setColumns(10);
		
		
		
	}
}
