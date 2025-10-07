package testOperations;

import java.util.*;

import javax.persistence.*;

import configuration.*;

import domain.*;


public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("TestDataAccess created");

		//open();
		
	}

	
	public void open(){
		

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		System.out.println("TestDataAccess opened");

		
	}
	public void close(){
		db.close();
		System.out.println("TestDataAccess closed");
	}

	public boolean removeDriver(String driverEmail) {
		System.out.println(">> TestDataAccess: removeDriver");
		Driver d = db.find(Driver.class, driverEmail);
		if (d!=null) {
			db.getTransaction().begin();
			db.remove(d);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
	public Driver createDriver(String email, String name) {
		System.out.println(">> TestDataAccess: addDriver");
		Driver driver=null;
			db.getTransaction().begin();
			try {
			    driver=new Driver(name,email);
				db.persist(driver);
				db.getTransaction().commit();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return driver;
    }
	public boolean existDriver(String email) {
		 return  db.find(Driver.class, email)!=null;
		 

	}
		
	public Driver addDriverWithRide(String email, String name, String from, String to,  Date date, int nPlaces, float price, Car car) {
		System.out.println(">> TestDataAccess: addDriverWithRide");
			Driver driver=null;
			db.getTransaction().begin();
			try {
				 driver = db.find(Driver.class, email);
				if (driver==null)
					driver=new Driver(email,name);
			    driver.addRide(from, to, date, nPlaces, price, car);
			    db.persist(driver);
				db.getTransaction().commit();
				return driver;
			}
			catch (Exception e){
				e.printStackTrace();
			}
		return null;
	}
	
	public Driver addDriverWithRideAndBookings(Integer rideNumber, String email, String name, String from, String to,  Date date, int nPlaces, float price, Car car, List<RideBooked> bookings) {
		System.out.println(">> TestDataAccess: addDriverWithRideAndBookings");
			Driver driver=null;
			db.getTransaction().begin();
			try {
				 driver = db.find(Driver.class, email);
				 if (driver==null)
						driver=new Driver(email,name);
			    driver.addRide(from, to, date, nPlaces, price, car);
			    for (Ride r : driver.getRides()) {
			    	if (r.getRideNumber() == rideNumber)
			    		r.setBookings(bookings);
			    }
			    db.persist(driver);
				db.getTransaction().commit();
				return driver;
			}
			catch (Exception e){
				e.printStackTrace();
			}
		return null;
	}
	
	public Driver addDriverWithRideList(String email, String pass, List<Ride> rideList) {
		System.out.println(">> TestDataAccess: addDriverWithRideList");
			Driver driver=null;
			db.getTransaction().begin();
			try {
				 driver = db.find(Driver.class, email);
				if (driver==null)
					driver=new Driver(email,pass);
			    driver.setRides(rideList);
			    
				db.getTransaction().commit();
				return driver;
			}
			catch (Exception e){
				e.printStackTrace();
			}
		return null;
	}
	
		public boolean existRide(String email, String from, String to, Date date) {
			System.out.println(">> TestDataAccess: existRide");
			Driver d = db.find(Driver.class, email);
			if (d!=null) {
				return d.doesRideExists(from, to, date);
			} else 
			return false;
		}
		
		public Ride findRide(int rideNumber) {
			System.out.println(">> TestDataAccess: findRide");
			Ride r = db.find(Ride.class, rideNumber);
			if (r != null)
				return r;
			else
				return null;
		}
		
		public Ride removeRide(String email, String from, String to, Date date ) {
			System.out.println(">> TestDataAccess: removeRide");
			Driver d = db.find(Driver.class, email);
			if (d!=null) {
				db.getTransaction().begin();
				Ride r= d.removeRide(from, to, date);
				db.getTransaction().commit();
				return r;

			} else 
			return null;

		}


		
}


