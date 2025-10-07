package getBookingsDriverTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			testDA.open();
			testDA.removeDriver(email);
			testDA.close();
		}
	}
	
	@Test
	public void test2() {
		String email = "asier@mail.com";
		List<Ride> rideList = new ArrayList<Ride>();
		boolean existDriver = false;
		
		testDA.open();
		testDA.addDriverWithRideList(email, "1234", rideList);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		sut.open();
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		sut.close();
		assertTrue(ema.isEmpty());
		
		if(existDriver) {
			testDA.open();
			testDA.removeDriver(email);
			testDA.close();
		}
	}
	
	@Test
	public void test3() {
		String email = "asier@mail.com";
		boolean existDriver = false;
		List<RideBooked> bookList = new ArrayList<RideBooked>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		testDA.open();
		testDA.addDriverWithRideAndBookings(1, email, "1234", "Donostia", "Bilbo", rideDate, 4, 15, null, bookList);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		sut.open();
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		sut.close();
		assertTrue(ema.isEmpty());
		
		testDA.open();
		if (existDriver) {
			testDA.removeRide(email, "Donostia", "Bilbo", rideDate);
			testDA.removeDriver(email);
		}else {
			testDA.removeDriver(email);
		}
		testDA.close();	
	}
	
	@Test
	public void test4() {
		String email = "asier@mail.com";
		boolean existDriver = false;
		Passenger pass = new Passenger("pass@mail.com", "5678");
		RideBooked book = new RideBooked(1, 1, null, pass);
		book.setAproved(true);
		List<RideBooked> bookList = new ArrayList<RideBooked>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		testDA.open();
		RideBooked retBook = testDA.addDriverWithRideAndBook(1, email, "1234", "Donostia", "Bilbo", rideDate, 4, 15, null, book);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		sut.open();
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		sut.close();
		
		assertTrue(ema.isEmpty() && retBook.isAproved());

		testDA.open();
		if (existDriver) {
			testDA.removeBook(retBook.getRideNumber(), retBook.getBookNumber());
			testDA.removeRide(email, "Donostia", "Bilbo", rideDate);
			testDA.removeDriver(email);
		}else {
			testDA.removeDriver(email);
		}
		testDA.close();
	}
	
	@Test
	public void test5() {
		String email = "asier@mail.com";
		boolean existDriver = false;
		Passenger pass = new Passenger("pass@mail.com", "5678");
		RideBooked book = new RideBooked(1, 1, null, pass);
		book.setAproved(false);
		List<RideBooked> bookList = new ArrayList<RideBooked>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		testDA.open();
		RideBooked retBook = testDA.addDriverWithRideAndBook(1, email, "1234", "Donostia", "Bilbo", rideDate, 4, 15, null, book);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		sut.open();
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		sut.close();
		
		assertTrue(!ema.isEmpty() && !retBook.isAproved());

		testDA.open();
		if (existDriver) {
			testDA.removeBook(retBook.getRideNumber(), retBook.getBookNumber());
			testDA.removeRide(email, "Donostia", "Bilbo", rideDate);
			testDA.removeDriver(email);
		}else {
			testDA.removeDriver(email);
		}
		testDA.close();
	}	
}
