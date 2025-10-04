package gui;

import java.util.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import domain.*;

import java.awt.event.*;

public class LookBookingsGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Passenger passenger;
	
	
	private JPanel contentPane;
	private JTextArea textAreaShow;
	private JComboBox<RideBooked> comboBoxRequests;
	private DefaultComboBoxModel<RideBooked> bookedRides = new DefaultComboBoxModel<RideBooked>();
	private JTextField textFieldResultado;
	private JComboBox<Integer> comboBoxRating;
	private DefaultComboBoxModel<Integer> Ratings = new DefaultComboBoxModel<Integer>();
	private JButton btnNewButtonErreklamazioa;
	
	public LookBookingsGUI(Passenger p) {
		
		super();

		passenger=p;
		

		setTitle(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("LookBookingsGUI.Title"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		textAreaShow = new JTextArea();
		textAreaShow.setBounds(10, 67, 414, 31);
		contentPane.add(textAreaShow);
		
		comboBoxRequests = new JComboBox<RideBooked>();
		comboBoxRequests.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				RideBooked r = (RideBooked) comboBoxRequests.getSelectedItem();
				textAreaShow.setText(r.toString());
			}
		});
		comboBoxRequests.setBounds(78, 34, 266, 22);
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
		JButton btnNewButtonBukatuta = new JButton(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("LookBookingsGUI.Bukatuta")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButtonBukatuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BLFacade facade = HomeGUI.getBusinessLogic();
				RideBooked r = (RideBooked) comboBoxRequests.getSelectedItem();
				Integer ra = (Integer) comboBoxRating.getSelectedItem();
				if(facade.bidaiaEginda(r.getBookNumber(), passenger.getEmail(),ra)) {
					textFieldResultado.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("LookBookingsGUI.Res"));
				}else {
					textFieldResultado.setText(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("LookBookingsGUI.ERROR"));
				}
			}
		});
		btnNewButtonBukatuta.setBounds(64, 201, 131, 31);
		contentPane.add(btnNewButtonBukatuta);
		
		textFieldResultado = new JTextField();
		textFieldResultado.setBounds(78, 169, 266, 20);
		contentPane.add(textFieldResultado);
		textFieldResultado.setColumns(10);
		textFieldResultado.setEditable(false);
		
		JLabel lblNewLabelRating = new JLabel(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("LookBookingsGUI.Rating")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabelRating.setBounds(102, 130, 80, 14);
		contentPane.add(lblNewLabelRating);
		
		comboBoxRating = new JComboBox<Integer>();
		comboBoxRating.setBounds(192, 126, 59, 22);
		contentPane.add(comboBoxRating);
		comboBoxRating.setModel(Ratings);
		
		btnNewButtonErreklamazioa = new JButton(ResourceBundle.getBundle(ApplicationLauncher.ET).getString("ErreklamazioGUI.Erreklamazio")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButtonErreklamazioa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame a = new ErreklamazioaGUI(p);
				a.setVisible(true);
				
			}
		});
		btnNewButtonErreklamazioa.setBounds(235, 201, 123, 31);
		contentPane.add(btnNewButtonErreklamazioa);
		int j = 0;
		while(j<6) {
			Ratings.addElement(j);
			j++;
			}
	}
}
