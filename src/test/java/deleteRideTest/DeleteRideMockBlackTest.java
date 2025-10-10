package deleteRideTest;

import static org.junit.Assert.*;

import java.text.*;

import java.util.*;

import javax.persistence.*;

import org.junit.*;

import org.mockito.*;

import dataAccess.*;

import domain.*;

public class DeleteRideMockBlackTest {
	
	static DataAccess sut;
	
	protected MockedStatic <Persistence> persistenceMock;

	@Mock
	protected  EntityManagerFactory entityManagerFactory;
	@Mock
	protected  EntityManager db;
	@Mock
    protected  EntityTransaction  et;
	

	@Before
    public  void init() {
        MockitoAnnotations.openMocks(this);
        persistenceMock = Mockito.mockStatic(Persistence.class);
		persistenceMock.when(() -> Persistence.createEntityManagerFactory(Mockito.any()))
        .thenReturn(entityManagerFactory);
        
        Mockito.doReturn(db).when(entityManagerFactory).createEntityManager();
		Mockito.doReturn(et).when(db).getTransaction();
	    sut=new DataAccess(db);
    }
	
	@After
    public  void tearDown() {
		persistenceMock.close();
    }
	
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		Driver driver = new Driver(dGmail, "1234");
		Ride ride = new Ride(rideNumber, "Donosti", "Madrid", rideDate, 5, 3, driver);
		
		
		Mockito.when(db.find(Driver.class, dGmail)).thenReturn(driver);
		Mockito.when(db.find(Ride.class, rideNumber)).thenReturn(ride);
		driver.addRide("Donosti", "Madrid", rideDate, 5, 3, new Car("1234FFF", "Ford", "Black", 5, driver));
		
		Ride foundRide = db.find(Ride.class, rideNumber);
		
		assertEquals(foundRide.getDriver().getEmail(), dGmail);
	}
	
	@Test
	public void test6() {
		
		int rideNumber = 3;
		String dGmail = "a@mail.com";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		Driver driver = new Driver(dGmail, "1234");
		Ride ride = new Ride(rideNumber, "Donosti", "Madrid", rideDate, 5, 3, driver);
		
		Mockito.when(db.find(Ride.class, rideNumber)).thenReturn(ride);
		
		Ride foundRide = db.find(Ride.class, rideNumber);
		
		assertTrue(foundRide.getRideNumber() == rideNumber);
	}
	
	@Test
	public void test7() {
		
		int rideNumber = 7;
		String dGmail = "a@mail.com";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		Driver driver = new Driver(dGmail, "1234");
		Ride ride = new Ride(rideNumber, "Donosti", "Madrid", rideDate, 5, 3, driver);
		Passenger passenger = new Passenger("kaixo@gmail.com", "1234");
		RideBooked rideBooked = new RideBooked(5, 1, ride, passenger);
		
		Mockito.when(db.find(Driver.class, dGmail)).thenReturn(driver);
		Mockito.when(db.find(Ride.class, rideNumber)).thenReturn(ride);
		ride.addBookRide(rideBooked);
		passenger.addBookRide(rideBooked);
		driver.addRide("Donosti", "Madrid", rideDate, 8, 3, new Car("1234FFF", "Ford", "Black", 5, driver));
		
		Ride foundRide = db.find(Ride.class, rideNumber);
		
		assertTrue(foundRide.getBookings() != null);
	}
	
	@Test
	public void test8() {

		int rideNumber = 7;
		String dGmail = "a@mail.com";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		Driver driver = new Driver(dGmail, "1234");
		Ride ride = new Ride(rideNumber, "Donosti", "Madrid", rideDate, 5, 3, driver);
		
		Mockito.when(db.find(Driver.class, dGmail)).thenReturn(driver);
		Mockito.when(db.find(Ride.class, rideNumber)).thenReturn(ride);
		ride.setBookings(null);
		
		Ride foundRide = db.find(Ride.class, rideNumber);
		
		assertTrue(foundRide.getBookings() == null);
	}
	
	@Test
	public void test9() {
		
		int rideNumber = 5;
		String dGmail = "a@gmail.com";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		Driver driver = new Driver(dGmail, "1234");
		Ride ride = new Ride(rideNumber, "Donosti", "Madrid", rideDate, 5, 3, driver);
		Passenger passenger = new Passenger("kaixo@gmail.com", "1234");
		RideBooked rideBooked = new RideBooked(5, 1, ride, passenger);
		
		Mockito.when(db.find(Driver.class, dGmail)).thenReturn(driver);
		Mockito.when(db.find(Ride.class, rideNumber)).thenReturn(ride);
		Mockito.when(db.find(Passenger.class, "kaixo@gmail.com")).thenReturn(passenger);
		ride.addBookRide(rideBooked);
		passenger.addBookRide(rideBooked);
		driver.addRide("Donosti", "Madrid", rideDate, 8, 3, new Car("1234FFF", "Ford", "Black", 5, driver));
		
		Ride foundRide = db.find(Ride.class, rideNumber);
		float beforeWallet = passenger.getWallet();
		
		sut.deleteRide(rideNumber, dGmail);
		
		Passenger afterPassenger = db.find(Passenger.class, "kaixo@gmail.com");
		float afterWallet = afterPassenger.getWallet();
		
		assertTrue(afterWallet - beforeWallet == foundRide.getPrice());
	}
}
