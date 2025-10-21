package deleteRideTest;

import static org.mockito.ArgumentMatchers.*;

import java.text.*;

import java.util.*;

import javax.persistence.*;

import org.junit.*;

import org.mockito.*;

import dataAccess.*;

import domain.*;

public class DeleteRideMockWhiteTest {
	
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
        
		int rideNumber = 99;
		String dGmail = "a@mail.com";
		
		Mockito.when(db.find(Ride.class, rideNumber)).thenReturn(null);
		
		sut.deleteRide(rideNumber, dGmail);
		
		Mockito.verify(db, Mockito.times(2)).getTransaction();
		Mockito.verify(db, Mockito.never()).remove(any());
	}
	
	@Test
	public void test2() {
		
		int rideNumber = 9;
		String dGmail = "a@mail.com";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		Driver driver = new Driver(dGmail, "1234");
		Ride ride = new Ride(rideNumber, "Donosti", "Madrid", rideDate, 8, 3, driver);
		
		Mockito.when(db.find(Ride.class, rideNumber)).thenReturn(ride);
		Mockito.when(db.find(Driver.class, dGmail)).thenReturn(driver);
		ride.setBookings(null);
		
		sut.deleteRide(rideNumber, dGmail);
		
		Mockito.verify(db, Mockito.times(1)).remove(ride);
	}

	@Test
	public void test3() {
		int rideNumber = 8;
		String dGmail = "a@mail.com";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		Driver driver = new Driver(dGmail, "1234");
		Ride ride = new Ride(rideNumber, "Donosti", "Madrid", rideDate, 8, 3, driver);
		
		Mockito.when(db.find(Ride.class, rideNumber)).thenReturn(ride);
		Mockito.when(db.find(Driver.class, dGmail)).thenReturn(driver);
		ride.setBookings(new ArrayList<>());
		
		sut.deleteRide(rideNumber, dGmail);
		
		Mockito.verify(db, Mockito.times(1)).remove(ride);
	}
/*
	@Test
	public void test4() {
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
		Ride ride = new Ride(rideNumber, "Donosti", "Madrid", rideDate, 8, 3, driver);
		Passenger passenger = new Passenger("kaixo@gmail.com", "1234");
		RideBooked rideBooked = new RideBooked(5, 1, ride, passenger);
		
		Mockito.when(db.find(Ride.class, rideNumber)).thenReturn(ride);
		Mockito.when(db.find(Driver.class, dGmail)).thenReturn(driver);
		ride.addBookRide(rideBooked);
		passenger.addBookRide(rideBooked);
		driver.addRide("Donosti", "Madrid", rideDate, 8, 3, new Car("1234FFF", "Ford", "Black", 5, driver));
		
		sut.deleteRide(rideNumber, dGmail);
		
		Mockito.verify(db, Mockito.atLeastOnce()).persist(driver);
		Mockito.verify(db, Mockito.times(1)).remove(ride);
   }
   */
}
