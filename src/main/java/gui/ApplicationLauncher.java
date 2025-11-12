package gui;

import java.awt.Color;

import java.net.URL;

import java.util.Locale;

import javax.swing.UIManager;

import javax.xml.namespace.QName;

import javax.xml.ws.Service;

import configuration.ConfigXML;

import dataAccess.DataAccess;

import domain.Driver;
import factories.*;
import businessLogic.*;

public class ApplicationLauncher { 
	
	public static final String ET = "Etiquetas";
	
	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();
	
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		
	    Driver driver=new Driver("driver3@gmail.com","Test Driver");
		
		HomeGUI a=new HomeGUI();
		a.setVisible(true);
		
		BLFactory factory;

		try {
			
			BLFacade appFacadeInterface;
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			if (c.isBusinessLogicLocal()) {
				factory = new LocalBLFactory();
			}else { //If remote
				factory = new RemoteBLFactory();
			} 
			
			appFacadeInterface = factory.createBLFacade();
			
			HomeGUI.setBussinessLogic(appFacadeInterface);
			
		}catch (Exception e) {
			a.jLabelSelectOption.setText("Error: "+e.toString());
			a.jLabelSelectOption.setForeground(Color.RED);	
			
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}

	}

}
