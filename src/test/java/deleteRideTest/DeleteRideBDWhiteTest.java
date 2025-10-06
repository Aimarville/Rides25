package deleteRideTest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import javax.persistence.*;

import org.junit.*;

import dataAccess.DataAccess;

import domain.*;

public class DeleteRideBDWhiteTest {

	static DataAccess sut;
	
	EntityManager db;
	
	@Before
	public void init() {
		sut = new DataAccess(db);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void test1() {
		
		int rideNumber = 99;
		String dGmail = "a@mail.com";
		
		Ride ride = db.find(Ride.class, rideNumber);
		
		assertTrue(ride == null);
	}
}
