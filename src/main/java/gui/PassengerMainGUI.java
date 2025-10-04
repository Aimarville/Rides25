package gui;

import javax.swing.*;

import businessLogic.BLFacade;

import domain.*;

import java.awt.*;

import java.awt.event.*;

import java.util.*;

public class PassengerMainGUI extends JFrame {

	private Passenger passenger;
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JPanel jOptionPane = null;
	private JButton jButtonBookRide;
	private JButton jButtonAddMoney;
	private JButton jButtonMovements;
	private JButton jButtonLookBookings;
	private JButton jButtonCreateAlert;
	private JButton jButtonLookAlert;
	private JButton jButtonDeleteUser;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * This is the default constructor
	 */
	public PassengerMainGUI(Passenger p) {
		super();

		passenger=p;
		
		
		// this.setSize(271, 295);
		this.setSize(495, 290);
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("MainGUI.SelectOption"));
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		
		jButtonBookRide = new JButton();
		jButtonBookRide.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("PassengerMainGUI.QueryRides"));
		jButtonBookRide.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new FindAndBookGUI(passenger);
				a.setVisible(true);
			}
		});
		
		jButtonAddMoney = new JButton();
		jButtonAddMoney.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("PassengerMainGUI.Saldo"));
		jButtonAddMoney.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new DepositMoneyGUI(passenger);
				a.setVisible(true);
				
			}
		});
		
		jButtonMovements = new JButton();
		jButtonMovements.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("MainGUI.Movements"));
		jButtonMovements.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Passenger p = new Passenger(passenger.getEmail(),passenger.getPassword());
				JFrame a = new LookMovementsGUI(p);
				a.setVisible(true);
			}
		});
		
		jButtonLookBookings = new JButton();
		jButtonLookBookings.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("PassengerMainGUI.LookBookings"));
		jButtonLookBookings.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new LookBookingsGUI(passenger);
				a.setVisible(true);
			}
		});
		jButtonCreateAlert = new JButton();
		jButtonCreateAlert.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("CreateRideGUI.CreateAlert"));
		jButtonCreateAlert.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new CreateAlertGUI(passenger);
				a.setVisible(true);
			}
		});
		jButtonLookAlert = new JButton();
		jButtonLookAlert.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("LookAlertsGUI.LookAlerts"));
		jButtonLookAlert.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new LookAlertsGUI(passenger);
				a.setVisible(true);
			}
		});
		
		jButtonDeleteUser = new JButton();
		jButtonDeleteUser.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("DeleteUserGUI.Delete"));
		jButtonDeleteUser.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new DeleteUserGUI(passenger);
				a.setVisible(true);
			}
		});
		
		jContentPane = new JPanel();
		jContentPane.setLayout(new BorderLayout());
		jContentPane.add(jLabelSelectOption, BorderLayout.NORTH);
		
		jOptionPane = new JPanel();
		jOptionPane.setLayout(new GridLayout(7, 1, 0, 0));
		
		jOptionPane.add(jButtonBookRide);
		jOptionPane.add(jButtonAddMoney);
		jOptionPane.add(jButtonMovements);
		jOptionPane.add(jButtonLookBookings);
		jOptionPane.add(jButtonCreateAlert);
		jOptionPane.add(jButtonLookAlert);
		jOptionPane.add(jButtonDeleteUser);
		jContentPane.add(jOptionPane, BorderLayout.CENTER);
		
		
		setContentPane(jContentPane);
		setTitle(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("PassengerMainGUI.PassMainTitle"));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}
	
	private void paintAgain() {
		jLabelSelectOption.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("MainGUI.SelectOption"));
		jButtonAddMoney.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("HomeGUI.Register"));
		jButtonBookRide.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("HomeGUI.Login"));
		jButtonMovements.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("HomeGUI.NotRegistered"));
	}
	
}
