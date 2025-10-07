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
			    driver=new Driver(email,name);
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
	
	public Driver findDriver(String email) {
		return db.find(Driver.class, email);
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
			    db.persist(driver);
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
	
		public boolean existPassenger(String email) {
			return db.find(Passenger.class, email) != null;
		}
		
		public Passenger findPassenger(String email) {
			return db.find(Passenger.class, email);
		}
		
		public boolean removePassenger(String email) {
			System.out.println(">> TestDataAccess: removePassenger");
			Passenger p = db.find(Passenger.class, email);
			if (p!=null) {
				db.getTransaction().begin();
				db.remove(p);
				db.getTransaction().commit();
				return true;
			} else 
			return false;

		}
		
		public Passenger createPassenger(String email, String pass) {
			System.out.println(">> TestDataAccess: addPassenger");
			Passenger passenger=null;
				db.getTransaction().begin();
				try {
				    passenger=new Passenger(email,pass);
					db.persist(passenger);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return passenger;
	    }
		
		public RideBooked addDriverWithRideAndBook(Integer rideNumber, String email, String pass, String from, String to, Date date, int nPlaces, float price, Car car, RideBooked book) {
			System.out.println(">> TestDataAccess: addDriverWithRideAndBook");
			Driver driver=null;
			db.getTransaction().begin();
			try {
				 driver = db.find(Driver.class, email);
				if (driver==null)
					driver=new Driver(email,pass);
			    Ride addedRide = driver.addRide(from, to, date, nPlaces, price, car);
			    System.out.println(addedRide.toString() + " y " + addedRide.getBookings());
			    addedRide.addBookRide(book);
			    System.out.println(addedRide.toString() + " y " + addedRide.getBookings());
			    book.setRide(addedRide);
			    System.out.println(addedRide.toString() + " y " + addedRide.getBookings());
			    db.persist(driver);
				db.getTransaction().commit();
				return book;
			}
			catch (Exception e){
				e.printStackTrace();
			}
		return null;
		}
		
		public RideBooked removeBook(Integer rideNumber, int bookNumber) {
			System.out.println(">> TestDataAccess: removeBook");
			Ride r = db.find(Ride.class, rideNumber);
			if (r!=null) {
				db.getTransaction().begin();
				System.out.println(r.getBookings());
				System.out.println(db.find(RideBooked.class, 1));
				RideBooked rb= r.removeBookRide(bookNumber);
				db.remove(rb);
				System.out.println(r.getBookings());
				System.out.println(db.find(RideBooked.class, 1));
				db.getTransaction().commit();
				return rb;
			} else 
			return null;
		}
		
		public void removeBookNoRide(int bookNumber) {
			RideBooked rb = db.find(RideBooked.class, bookNumber);
			db.getTransaction().begin();
			db.remove(rb);
			db.getTransaction().commit();
		}
		
		public RideBooked findBook(int bookNumber) {
			return db.find(RideBooked.class, bookNumber);
		}
}


