package businessLogic;

import java.util.*;

import javax.jws.*;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.*;
import exceptions.*;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		dbManager=new DataAccess();
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    
    /**
     * {@inheritDoc}
     */
    @WebMethod public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(from);		

		dbManager.close();
		
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, Car car, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	   
		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, car, driverEmail);		
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

    
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
   /* @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    */
    @WebMethod	
	 public boolean register(String email, String password, String type){
    	boolean res;
    	dbManager.open();
		res = dbManager.register(email, password, type);
		dbManager.close();
		return res;
	}
   
   @WebMethod	
	 public boolean login(String email, String password, String type){
   	boolean res;
   	dbManager.open();
		res = dbManager.login(email, password, type);
		dbManager.close();
		return res;
	}

   @WebMethod	
   public boolean withdrawMoney(String email, int amount){
 	boolean res;
 	dbManager.open();
		res = dbManager.withdrawMoney(email, amount);
		dbManager.close();
		return res;
	}
   
   @WebMethod	
   public boolean addMoney(String email, int amount){
 	boolean res;
 	dbManager.open();
		res = dbManager.addMoney(email, amount);
		dbManager.close();
		return res;
	}
   
   @WebMethod	
   public ArrayList<RideBooked> getBookingsDriver(String email,String password){
 	ArrayList<RideBooked> res;
 	dbManager.open();
		res = dbManager.getBookingsDriver(email, password);
		dbManager.close();
		return res;
	}
   
   public boolean bookRide(Passenger passenger, String driveremail, int seatsBooked, int rideID){
	   Boolean res;
	   dbManager.open();
		res = dbManager.bookRide(passenger, driveremail, seatsBooked, rideID);
		dbManager.close();
	   return res;
   }
   
   public boolean accept(int bookNumber) {
	   Boolean res;
	   dbManager.open();
		res = dbManager.accept(bookNumber);
		dbManager.close();
	   return res;
   }
   
   public boolean decline(int bookNumber) {
	   Boolean res;
	   dbManager.open();
		res = dbManager.decline(bookNumber);
		dbManager.close();
	   return res;
	   
   }
   @WebMethod	
   public List<RideBooked> getBookingsPass(String email,String password){
 	List<RideBooked> res;
 	dbManager.open();
		res = dbManager.getBookingsPass(email, password);
		dbManager.close();
		return res;
	}
   @WebMethod
     public List<Mugimenduak> getMugimenduak(String uGmail){
	 	List<Mugimenduak> res;
	 	dbManager.open();
			res = dbManager.getMugimenduak(uGmail);
			dbManager.close();
			return res;
	   
   }
   @WebMethod
   public boolean bidaiaEginda(int bookNumber, String pGmail, int b) {
	   boolean res = false;
	   dbManager.open();
		res = dbManager.bidaiaEginda(bookNumber, pGmail,b);
		dbManager.close();
		return res;
   }
   @WebMethod
   public List<Ride> getRidesDriver(String dGmail){
	   List<Ride> res;
	 	dbManager.open();
			res = dbManager.getRidesDriver(dGmail);
			dbManager.close();
			return res;
   }
   @WebMethod
   public void deleteRide(int RideNumber, String dGmail) {
	   dbManager.open();
		dbManager.deleteRide(RideNumber, dGmail);
		dbManager.close();
   }
   @WebMethod
   public float getDriversWallet(String dGmail) {
	 	dbManager.open();
			float res = dbManager.getDriversWallet(dGmail);
			dbManager.close();
			return res;
   }
   @WebMethod
   public float getPassengersWallet(String pGmail) {
	 	dbManager.open();
			float res = dbManager.getPassengersWallet(pGmail);
			dbManager.close();
			return res;
   }
   @WebMethod
   public boolean addCar(String license, String brand, String color, int seats,  String dGmail) {
	   dbManager.open();
		boolean res = dbManager.addCar(license, brand, color, seats, dGmail);
		dbManager.close();
		return res;
   }
   @WebMethod
   public List<Car> getCars(String dGmail){
	   dbManager.open();
		List<Car> res = dbManager.getCars(dGmail);
		dbManager.close();
		return res;
   }
   
   @WebMethod
   public Ride getRideByEmail(String email, String from, String to, Date date) {
	   dbManager.open();
	   Ride res = dbManager.getRideByEmail(email, from, to, date);
	   dbManager.close();
	   return res;
   }
   
   @WebMethod
   public void erreklamazioa(String zerg, String pGmail, int bookNumber) {
	   dbManager.open();
	   dbManager.erreklamazioa(zerg, pGmail, bookNumber);
	   dbManager.close();
   }
   
   @WebMethod
   public List<Erreklamazioa> geterreklamazio(String dGmail){
	   dbManager.open();
		List<Erreklamazioa> res = dbManager.geterreklamazio(dGmail);
		dbManager.close();
		return res;
   }
   
   @WebMethod
   public List<Driver> getAllDrivers(){
	   dbManager.open();
		List<Driver> res = dbManager.getAllDrivers();
		dbManager.close();
		return res;
   }
   
   @WebMethod
   public void ezabatuErreklamazioa(int eID) {
	   dbManager.open();
	   dbManager.ezabatuErreklamazioa(eID);
	   dbManager.close();
   }
   
   @WebMethod
   public void baieztatuErreklamazioa(int eID) {
	   dbManager.open();
	   dbManager.baieztatuErreklamazioa(eID);
	   dbManager.close();
   }
   
   @WebMethod
   public boolean deleteUser(String uGmail) {
	   dbManager.open();
	   boolean res = dbManager.deleteUser(uGmail);
	   dbManager.close();
	   return res;
   }
   @WebMethod 
   public void createAlert(String origin, String destination, Date date, String pGmail) {
	   dbManager.open();
	   dbManager.createAlert(origin, destination, date, pGmail);
	   dbManager.close();
   }
   @WebMethod 
   public List<Ride> getAlertRides(String pGmail){
	   dbManager.open();
	   List<Ride> res = dbManager.getAlertRides(pGmail);
	   dbManager.close();
	   return res;
   }
}

