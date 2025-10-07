package getBookingsDriverTest;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Driver;
import domain.Passenger;
import domain.Ride;
import domain.RideBooked;
import testOperations.TestDataAccess;

public class GetBookingsDriverDBBlackTest {
	static DataAccess sut=new DataAccess();
	  
	static TestDataAccess testDA=new TestDataAccess();
	
	
	@Test
	public void test1() {
		String email = "asier@mail.com";
		
		assertTrue(email!=null);
	}
	
	@Test
	public void test2() {
		String email = "asier@mail.com";
		boolean existDriver = false;
		
		testDA.open();
		testDA.createDriver(email, "1234");
		Driver foundDriver = testDA.findDriver(email);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		assertTrue(foundDriver!=null);
		
		if (existDriver) {
			testDA.open();
			testDA.removeDriver(email);
			testDA.close();
		}
	}
	
	
	@Test
	public void test3() {
		String email = "asier@mail.com";
		boolean existDriver = false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		testDA.open();
		Driver foundDriver = testDA.addDriverWithRide(email, "1234", "Donostia", "Bilbo", rideDate, 4, 15, null);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		assertTrue(!foundDriver.getRides().isEmpty());
		
		testDA.open();
		System.out.println(testDA.findDriver(email));
		System.out.println(testDA.findDriver(email).getRides());
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
		List<Ride> rideList = new ArrayList<>();
		boolean existDriver = false;
		
		testDA.open();
		Driver foundDriver = testDA.addDriverWithRideList(email, "1234", rideList);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		assertTrue(foundDriver.getRides().isEmpty());
		
		if (existDriver) {
			testDA.open();
			testDA.removeDriver(email);
			testDA.close();
		}
	}
	
	@Test
	public void test5() {
		String email = "asier@mail.com";
		boolean existDriver = false;
		Passenger pass = new Passenger("pass@mail.com", "5678");
		RideBooked book = new RideBooked(1, 1, null, pass);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		testDA.open();
		RideBooked retBook = testDA.addDriverWithRideAndBook(1, email, "1234", "Donostia", "Bilbo", rideDate, 4, 15, null, book);
		Ride foundRide = testDA.findRide(1);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		assertTrue(!foundRide.getBookings().isEmpty());
		
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
	public void test6() {
		String email = "asier@mail.com";
		boolean existDriver = false;
		List<RideBooked> bookList = new ArrayList<RideBooked>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		testDA.open();
		testDA.addDriverWithRideAndBookings(1, email, "1234", "Donostia", "Bilbo", rideDate, 4, 15, null, bookList);
		Ride foundRide = testDA.findRide(1);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		assertTrue(foundRide.getBookings().isEmpty());
		
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
	public void test7() {
		String email = "asier@mail.com";
		boolean existDriver = false;
		Passenger pass = new Passenger("pass@mail.com", "5678");
		RideBooked book = new RideBooked(1, 1, null, pass);
		book.setAproved(false);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		testDA.open();
		RideBooked retBook = testDA.addDriverWithRideAndBook(1, email, "1234", "Donostia", "Bilbo", rideDate, 4, 15, null, book);
		Ride foundRide = testDA.findRide(1);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		assertTrue(!foundRide.getBookings().isEmpty() && !retBook.isAproved());
		
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
	public void test8() {
		String email = "asier@mail.com";
		boolean existDriver = false;
		Passenger pass = new Passenger("pass@mail.com", "5678");
		RideBooked book = new RideBooked(1, 1, null, pass);
		book.setAproved(true);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		testDA.open();
		RideBooked retBook = testDA.addDriverWithRideAndBook(1, email, "1234", "Donostia", "Bilbo", rideDate, 4, 15, null, book);
		Ride foundRide = testDA.findRide(1);
		existDriver = testDA.existDriver(email);
		testDA.close();
		
		assertTrue(!foundRide.getBookings().isEmpty() && retBook.isAproved());
		
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
	
	
	/*
	@Test
	public void check() {
		testDA.open();
		//System.out.println(testDA.findDriver("asier@mail.com").toString());
		//System.out.println(testDA.findRide(1).toString());
		System.out.println(testDA.findBook(1).toString());
		testDA.close();
	}
	*/
	
	/*
	@Test
	public void Delete() {
		testDA.open();
		testDA.removeDriver("asier@mail.com");
		testDA.removeBookNoRide(1);
		testDA.close();
	}
	*/
}
