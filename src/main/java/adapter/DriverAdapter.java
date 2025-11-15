package adapter;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.*;

public class DriverAdapter extends AbstractTableModel{
	
	private Driver driver;
	private String[] columnNames = new String[] {"from", "to", "date", "places", "price"};
	
	public DriverAdapter(Driver d) {
		this.driver = d;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int i) {
		return columnNames[i];
	}
	
	@Override
	public int getRowCount() {
		return driver.getRides().size();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		List<Ride> rideList = driver.getRides();
		Ride r = rideList.get(row);
		
		Object itzul = null;
		switch (col) {
			case 0:
				itzul = r.getFrom();
				break;
			case 1:
				itzul = r.getTo();
				break;
			case 2:
				itzul = r.getDate();
				break;
			case 3:
				itzul = r.getnPlaces();
				break;
			case 4:
				itzul = r.getPrice();
				break;
			default:
				itzul = null;
		}
		return itzul;
	}
}
