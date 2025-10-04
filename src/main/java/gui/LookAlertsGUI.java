package gui;

import java.util.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import domain.*;

public class LookAlertsGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Passenger passenger;
	
	
	private JPanel contentPane;
	private JComboBox<Ride> comboBoxAlerts;
	private DefaultComboBoxModel<Ride> Rides = new DefaultComboBoxModel<Ride>();
	
	public LookAlertsGUI(Passenger p) {
		
		super();

		passenger=p;
		
		BLFacade facade = HomeGUI.getBusinessLogic();

		
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("LookAlertsGUI.LookAlerts"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		comboBoxAlerts = new JComboBox<Ride>();
		comboBoxAlerts.setBounds(10, 65, 414, 22);
		contentPane.add(comboBoxAlerts);
		comboBoxAlerts.setModel(Rides);
		
		int i = 0;
		List<Ride> s = facade.getAlertRides(passenger.getEmail());
		while(i<s.size()) {
			if(s.get(i)!=null) {
			Rides.addElement(s.get(i));
			}
			i++;
		}
		
		JLabel lblNewLabelAlerts = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LookAlertsGUI.Alerts")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabelAlerts.setBounds(37, 26, 351, 14);
		contentPane.add(lblNewLabelAlerts);
		
		
	}
}
