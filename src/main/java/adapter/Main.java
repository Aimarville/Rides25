package adapter;

import businessLogic.*;
import domain.Driver;
import factories.*;

public class Main {

	public static void main(String[]args)	{
		BLFacade	blFacade =	new LocalBLFactory().createBLFacade();
		Driver	d= blFacade.getDriver("asier");
		DriverTable	dt=new	DriverTable(d);
		dt.setVisible(true);
	}

}