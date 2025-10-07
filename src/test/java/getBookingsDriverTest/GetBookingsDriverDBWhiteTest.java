package getBookingsDriverTest;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.*;
import testOperations.TestDataAccess;

public class GetBookingsDriverDBWhiteTest {
	
	static DataAccess sut=new DataAccess();
	 
	static TestDataAccess testDA=new TestDataAccess();
	
	@Test
	public void test1() {
		String email = "b@mail.com";
		boolean existDriver = false;
		testDA.open();
		existDriver = testDA.existDriver(email);
		testDA.close();
			
		sut.open();
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		sut.close();
		
		assertTrue(ema.isEmpty());
		
		if (existDriver) {
			testDA.removeDriver(email);
		}
	}
	
	@Test
	public void test2() {
		String email = "asier@mail.com";
		List<Ride> rideList = new ArrayList<Ride>();
		boolean existDriver = false;
		
		testDA.open();
		existDriver = testDA.existDriver(email);
		testDA.addDriverWithRideList(email, "1234", rideList);
		testDA.close();
		
		sut.open();
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		sut.close();
		
		assertTrue(ema.isEmpty());
		
		if(existDriver) {
			testDA.removeDriver(email);
		}
		
	}
}
