package deleteRideTest;

import static org.junit.Assert.*;

import java.text.*;

import java.util.*;

import org.junit.*;

import dataAccess.*;

import domain.*;

import exceptions.*;

import testOperations.*;

public class DeleteRideBDBlackTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();
	 
	 @Test
	 public void test1() {
		 Object rideNumber = 4;
			
			if (rideNumber != null)
				assertTrue(true);
			else
				fail();
	 }
	 
		@Test
		public void test2() {
			
			Object dGmail= "a@mail.com";
			
			if (dGmail != null)
				assertTrue(true);
			else
				fail();
		}
		
		@Test
		public void test3() {
			
			Object rideNumber = 4;
			
			if (rideNumber instanceof Integer)
				assertTrue(true);
			else
				fail();
		}
		
		@Test
		public void test4() {
			
			int rideNumber = 4;
			
			if (rideNumber > 0)
				assertTrue(true);
			else
				fail();
		}
		
		@Test
		public void test5() {
			int rideNumber = 10;
			String dGmail = "a@mail.com";
			String from = "Donosti";
			String to = "Madrid";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=null;
			try {
				rideDate = sdf.parse("05/10/2025");
			} catch (ParseException e) {

				e.printStackTrace();
			}
			
			boolean existDriver = false;
			boolean existRide = false;
			Ride foundRide = null;
			Driver foundDriver = null;
			
			try {
				testDA.open();
				existDriver = testDA.existDriver(dGmail);
				existRide = testDA.existRide(dGmail, from, to, rideDate);
				if (existRide) {
					foundRide = testDA.findRide(rideNumber);
					testDA.removeRide(dGmail, from, to, rideDate);
				}
				if (existDriver) {
					foundDriver = testDA.findDriver(dGmail);
				}
				testDA.addDriverWithRide(dGmail, "1234", from, to, rideDate, 5, 3, null);
				testDA.close();
				
				sut.open();
				Ride ema = sut.getRideByEmail(dGmail, from, to, rideDate);
				sut.close();
				
				assertEquals(ema.getEmail(), dGmail);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				testDA.open();
				testDA.removeDriver(dGmail);
				testDA.removeRide(dGmail, from, to, rideDate);
				if (existRide && existDriver) {
					testDA.addDriverWithRideAndBookings(foundRide.getRideNumber(), foundDriver.getEmail(), foundDriver.getPassword(), from, to, rideDate, foundRide.getnPlaces(), foundRide.getPrice(), foundRide.getCar(), foundRide.getBookings());
				}
				testDA.close();
			}
		}
		
		@Test
		public void test6() {
			int rideNumber = 10;
			String dGmail = "a@mail.com";
			String from = "Donosti";
			String to = "Madrid";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=null;
			try {
				rideDate = sdf.parse("05/10/2025");
			} catch (ParseException e) {

				e.printStackTrace();
			}
			
			boolean existDriver = false;
			boolean existRide = false;
			Ride foundRide = null;
			Driver foundDriver = null;
			
			try {
				testDA.open();
				existDriver = testDA.existDriver(dGmail);
				existRide = testDA.existRide(dGmail, from, to, rideDate);
				if (existRide) {
					foundRide = testDA.findRide(rideNumber);
					testDA.removeRide(dGmail, from, to, rideDate);
				}
				if (existDriver) {
					foundDriver = testDA.findDriver(dGmail);
				}
				testDA.addDriverWithRide(dGmail, "1234", from, to, rideDate, 5, 3, null);
				testDA.close();
				
				sut.open();
				Ride ema = sut.getRideByEmail(dGmail, from, to, rideDate);
				sut.close();
				
				assertTrue(ema.getRideNumber() == rideNumber);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				testDA.open();
				testDA.removeDriver(dGmail);
				testDA.removeRide(dGmail, from, to, rideDate);
				if (existRide && existDriver) {
					testDA.addDriverWithRideAndBookings(foundRide.getRideNumber(), foundDriver.getEmail(), foundDriver.getPassword(), from, to, rideDate, foundRide.getnPlaces(), foundRide.getPrice(), foundRide.getCar(), foundRide.getBookings());
				}
				testDA.close();
			}
		}
		
		@Test
		public void test7() {
			int rideNumber = 10;
			String dGmail = "a@mail.com";
			String from = "Donosti";
			String to = "Madrid";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=null;
			try {
				rideDate = sdf.parse("05/10/2025");
			} catch (ParseException e) {

				e.printStackTrace();
			}
			
			boolean existDriver = false;
			boolean existRide = false;
			Ride foundRide = null;
			Driver foundDriver = null;
			
			try {
				testDA.open();
				existDriver = testDA.existDriver(dGmail);
				existRide = testDA.existRide(dGmail, from, to, rideDate);
				if (existRide) {
					foundRide = testDA.findRide(rideNumber);
					testDA.removeRide(dGmail, from, to, rideDate);
				}
				if (existDriver) {
					foundDriver = testDA.findDriver(dGmail);
				}
				ArrayList<RideBooked> bookings = new ArrayList<>();
				bookings.add(new RideBooked(5, 1, new Ride(rideNumber, from, to, rideDate, 5, 3, new Driver(dGmail, "1234")), null));
				testDA.addDriverWithRideAndBookings(rideNumber, dGmail, "1234", from, to, rideDate, 5, 3, null, bookings);
				testDA.close();
				
				sut.open();
				Ride ema = sut.getRideByEmail(dGmail, from, to, rideDate);
				sut.close();
				
				assertNotNull(ema.getBookings());
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				testDA.open();
				testDA.removeDriver(dGmail);
				testDA.removeRide(dGmail, from, to, rideDate);
				if (existRide && existDriver) {
					testDA.addDriverWithRideAndBookings(foundRide.getRideNumber(), foundDriver.getEmail(), foundDriver.getPassword(), from, to, rideDate, foundRide.getnPlaces(), foundRide.getPrice(), foundRide.getCar(), foundRide.getBookings());
				}
				testDA.close();
			}
		}
		
		@Test
		public void test8() {
			int rideNumber = 10;
			String dGmail = "a@mail.com";
			String from = "Donosti";
			String to = "Madrid";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=null;
			try {
				rideDate = sdf.parse("05/10/2025");
			} catch (ParseException e) {

				e.printStackTrace();
			}
			
			boolean existDriver = false;
			boolean existRide = false;
			Ride foundRide = null;
			Driver foundDriver = null;
			
			try {
				testDA.open();
				existDriver = testDA.existDriver(dGmail);
				existRide = testDA.existRide(dGmail, from, to, rideDate);
				if (existRide) {
					foundRide = testDA.findRide(rideNumber);
					testDA.removeRide(dGmail, from, to, rideDate);
				}
				if (existDriver) {
					foundDriver = testDA.findDriver(dGmail);
				}
				ArrayList<RideBooked> bookings = new ArrayList<>();
				bookings.add(new RideBooked(5, 1, new Ride(rideNumber, from, to, rideDate, 5, 3, new Driver(dGmail, "1234")), null));
				testDA.addDriverWithRideAndBookings(rideNumber, dGmail, "1234", from, to, rideDate, 5, 3, null, bookings);
				testDA.close();
				
				sut.open();
				Ride ema = sut.getRideByEmail(dGmail, from, to, rideDate);
				sut.close();
				
				assertNull(ema.getBookings());
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				testDA.open();
				testDA.removeDriver(dGmail);
				testDA.removeRide(dGmail, from, to, rideDate);
				if (existRide && existDriver) {
					testDA.addDriverWithRideAndBookings(foundRide.getRideNumber(), foundDriver.getEmail(), foundDriver.getPassword(), from, to, rideDate, foundRide.getnPlaces(), foundRide.getPrice(), foundRide.getCar(), foundRide.getBookings());
				}
				testDA.close();
			}
		}
		
		@Test
		public void test9() {
			int rideNumber = 10;
			String dGmail = "a@mail.com";
			String from = "Donosti";
			String to = "Madrid";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=null;
			try {
				rideDate = sdf.parse("05/10/2025");
			} catch (ParseException e) {

				e.printStackTrace();
			}
			
			boolean existDriver = false;
			boolean existRide = false;
			boolean existPassenger = false;
			Ride foundRide = null;
			Driver foundDriver = null;
			Passenger foundPassenger = null;
			
			
			try {
				testDA.open();
				existDriver = testDA.existDriver(dGmail);
				existRide = testDA.existRide(dGmail, from, to, rideDate);
				existPassenger = testDA.existPassenger("e@mail.com");
				if (existRide) {
					foundRide = testDA.findRide(rideNumber);
					testDA.removeRide(dGmail, from, to, rideDate);
				}
				if (existDriver) {
					foundDriver = testDA.findDriver(dGmail);
				}
				if (existPassenger) {
					foundPassenger = testDA.findPassenger("e@mail.com");
					testDA.removePassenger("e@mail.com");
				}
				ArrayList<RideBooked> bookings = new ArrayList<>();
				bookings.add(new RideBooked(5, 1, new Ride(rideNumber, from, to, rideDate, 5, 3, new Driver(dGmail, "1234")), new Passenger("e@mail.com", "1234")));
				testDA.addDriverWithRideAndBookings(rideNumber, dGmail, "1234", from, to, rideDate, 5, 3, null, bookings);
				testDA.createPassenger("e@mail.com", "1234");
				float walletBefore = testDA.findPassenger("e@mail.com").getWallet();
				testDA.close();
				
				sut.open();
				sut.deleteRide(rideNumber, dGmail);
				sut.close();
				
				testDA.open();
				float walletAfter = testDA.findPassenger("e@mail.com").getWallet();
				Ride ride = testDA.findRide(rideNumber);
				
				assertTrue(walletAfter - walletBefore == ride.getPrice());
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				testDA.open();
				testDA.removeDriver(dGmail);
				testDA.removePassenger("e@mail.com");
				testDA.removeRide(dGmail, from, to, rideDate);
				if (existRide && existDriver) {
					testDA.addDriverWithRideAndBookings(foundRide.getRideNumber(), foundDriver.getEmail(), foundDriver.getPassword(), from, to, rideDate, foundRide.getnPlaces(), foundRide.getPrice(), foundRide.getCar(), foundRide.getBookings());
				}
				if (existPassenger) {
					testDA.createPassenger("e@mail.com", foundPassenger.getPassword());
				}
				testDA.close();
			}
		}
}
