package factories;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

public class LocalBLFactory implements BLFactory {
	
	@Override
	public BLFacade createBLFacade() {
		DataAccess da = new DataAccess();
		return new BLFacadeImplementation(da);
	}
}
