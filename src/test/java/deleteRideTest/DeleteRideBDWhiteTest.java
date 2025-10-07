package deleteRideTest;

import static org.junit.Assert.*;

import java.text.*;

import java.util.*;

import org.junit.*;

import dataAccess.*;

import domain.*;

import exceptions.*;

import testOperations.*;

public class DeleteRideBDWhiteTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	private Driver driver; 

	@Test
	//sut.createRide:  The Driver("iker driver", "driver1@gmail.com") HAS one ride "from" "to" in that "date". 
	public void test1() {
		String driverEmail="driver1@gmail.com";
		String driverName="Aitor Fernandez";

		String rideFrom="Donostia";
		String rideTo="Zarautz";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		boolean existRide = false;
		Ride foundRide = null;
		boolean existDriver = false;
			
			//define parameters
			int rideNumber = 9;
			
			//configure the state of the system (create object in the database)
			testDA.open();
			existDriver=testDA.existDriver(driverEmail);
			existRide=testDA.existRide(driverEmail, rideFrom, rideTo, rideDate);
			if (existRide) {
				foundRide = testDA.findRide(rideNumber);
				testDA.removeRide(driverEmail, rideFrom, rideTo, rideDate);
			}
			testDA.createDriver(driverName, driverEmail);
			testDA.close();			
			
			//invoke System Under Test (sut)  
			sut.open();
		    sut.deleteRide(rideNumber, driverEmail);
			sut.close();
			
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			Ride after = testDA.findRide(rideNumber);
			testDA.removeDriver(driverEmail);
			if (existRide && existDriver) {
				testDA.addDriverWithRide(driverName, driverEmail, rideFrom, rideTo, rideDate, foundRide.getnPlaces(), foundRide.getPrice(), foundRide.getCar());
			}
		    testDA.close();
		    
		    assertNull(after);
	}

	@Test
	//sut.createRide:  The Driver("Aitor Fernandez", "driver1@gmail.com") HAS NOT one ride "from" "to" in that "date". 
	public void test2() {
		String driverEmail="driver1@gmail.com";
		String driverName="Aitor Fernandez";

		String rideFrom="Donostia";
		String rideTo="Zarautz";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		boolean existRide = false;
		Ride foundRide = null;
		boolean existDriver = false;
			
			//define parameters
			int rideNumber = 9;
			
			//configure the state of the system (create object in the database)
			testDA.open();
			existDriver=testDA.existDriver(driverEmail);
			existRide=testDA.existRide(driverEmail, rideFrom, rideTo, rideDate);
			if (existRide) {
				foundRide = testDA.findRide(rideNumber);
				testDA.removeRide(driverEmail, rideFrom, rideTo, rideDate);
			}
			
			testDA.createDriver(driverEmail, driverName);
			testDA.close();			
			
			//invoke System Under Test (sut)  
			sut.open();
			Ride before = sut.getRideByEmail(driverEmail, rideFrom, rideTo, rideDate);
		    sut.deleteRide(rideNumber, driverEmail);
		    Ride after = sut.getRideByEmail(driverEmail, rideFrom, rideTo, rideDate);
			sut.close();
			
			if (before != null)
				assertNull(after);
			else
				fail();
			
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			testDA.removeDriver(driverEmail);
			if (existRide && existDriver) {
				testDA.addDriverWithRideAndBookings(foundRide.getRideNumber(), driverEmail, driverName, rideFrom, rideTo, rideDate, foundRide.getnPlaces(), foundRide.getPrice(), foundRide.getCar(), foundRide.getBookings());
			}
		    testDA.close();
	}
/*
	@Test
	//sut.createRide:  The Driver is null. The test must return null. If  an Exception is returned the createRide method is not well implemented.
		public void test3() {
			try {
				
				//define parameters
				driver=null;

				String rideFrom="Donostia";
				String rideTo="Zarautz";
				
				String driverEmail=null;

				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date rideDate=null;;
				try {
					rideDate = sdf.parse("05/10/2025");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				
				
				//invoke System Under Test (sut)  
				sut.open();
				Ride ride=sut.createRide(rideFrom, rideTo, rideDate, 0, 0, null, driverEmail);
				System.out.println("ride "+ride);

				//verify the results
				assertNull(ride);
				
				
			   } catch (RideAlreadyExistException e) {
				// TODO Auto-generated catch block
				// if the program goes to this point fail  
				fail();
				} catch (RideMustBeLaterThanTodayException e) {
				// TODO Auto-generated catch block
					fail();
				} catch (Exception e) {
				// TODO Auto-generated catch block
					fail();
					
				} finally {
					sut.close();
				}
			
			   } 
	@Test
	//sut.createRide:  The ride "from" is null. The test must return null. If  an Exception is returned the createRide method is not well implemented.

	//This method detects a fail in createRide method because the method does not check if the parameters are null, and the ride is created.
	
	public void test4() {
		String driverEmail="driver1@gmail.com";
		String rideFrom=null;
		String rideTo="Zarautz";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Ride ride=null;
		try {
			//invoke System Under Test (sut)  
			sut.open();
			 ride=sut.createRide(rideFrom, rideTo, rideDate, 0, 0, null, driverEmail);
			sut.close();			
			
			//verify the results
			assertNull(ride);
			
			//q datubasean dago
			testDA.open();
			boolean exist=testDA.existRide(driverEmail,rideFrom, rideTo, rideDate);
				
			assertTrue(!exist);
			testDA.close();
			
		   } catch (RideAlreadyExistException e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} catch (RideMustBeLaterThanTodayException e) {

			// TODO Auto-generated catch block
			fail();
			}  catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
			}
		
		
		finally {   

			testDA.open();
			if (testDA.existRide(driverEmail,rideFrom, rideTo, rideDate))
				testDA.removeRide(driverEmail, rideFrom, rideTo, rideDate);
			testDA.close();
			
		        }
		   }
*/
}