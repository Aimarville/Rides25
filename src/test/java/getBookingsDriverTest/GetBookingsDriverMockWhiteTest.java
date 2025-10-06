package getBookingsDriverTest;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

import org.junit.*;

import domain.*;

import org.mockito.*;

import dataAccess.*;

public class GetBookingsDriverMockWhiteTest {
	
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
		String email = "b@mail.com";
		
		Mockito.when(db.find(Driver.class, email)).thenReturn(null);
		
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		
		assertTrue(ema.isEmpty());
	}
	
	@Test
	public void test2() {
		String email = "asier@mail.com";
		Driver driver = new Driver(email, "1234");
		ArrayList<Ride> rideList = new ArrayList<Ride>();
		driver.setRides(rideList);
		
		Mockito.when(db.find(Driver.class, email)).thenReturn(driver);
		
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		
		assertTrue(ema.isEmpty());
	}
	
	@Test
	public void test3() {
		String email = "asier@mail.com";
		Driver driver = new Driver(email, "1234");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("06/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		Ride ride = new Ride(1, "Donostia", "Bilbo", rideDate, 4, 15, driver);
		ArrayList<Ride> rideList = new ArrayList<Ride>();
		rideList.add(ride);
		driver.setRides(rideList);
		
		ArrayList<RideBooked> bookList = new ArrayList<RideBooked>();
		ride.setBookings(bookList);
		
		Mockito.when(db.find(Driver.class, email)).thenReturn(driver);
		
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		
		assertTrue(ema.isEmpty());		
	}
	
	@Test
	public void test4() {
		String email = "asier@mail.com";
		Driver driver = new Driver(email, "1234");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("06/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		Ride ride = new Ride(1, "Donostia", "Bilbo", rideDate, 4, 15, driver);
		ArrayList<Ride> rideList = new ArrayList<Ride>();
		rideList.add(ride);
		driver.setRides(rideList);
		
		String pEmail = "pass@mail.com";
		Passenger pass = new Passenger(pEmail, "5678");
		RideBooked book = new RideBooked(1, 2, ride, pass);
		book.setAproved(true);
		ArrayList<RideBooked> bookList = new ArrayList<RideBooked>();
		bookList.add(book);
		ride.setBookings(bookList);
		
		Mockito.when(db.find(Driver.class, email)).thenReturn(driver);
		
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		
		assertTrue(ema.isEmpty());		
	}
	
	@Test
	public void test5() {
		String email = "asier@mail.com";
		Driver driver = new Driver(email, "1234");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("06/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		Ride ride = new Ride(1, "Donostia", "Bilbo", rideDate, 4, 15, driver);
		ArrayList<Ride> rideList = new ArrayList<Ride>();
		rideList.add(ride);
		driver.setRides(rideList);
		
		String pEmail = "pass@mail.com";
		Passenger pass = new Passenger(pEmail, "5678");
		RideBooked book = new RideBooked(1, 2, ride, pass);
		book.setAproved(false);
		ArrayList<RideBooked> bookList = new ArrayList<RideBooked>();
		bookList.add(book);
		ride.setBookings(bookList);
		
		Mockito.when(db.find(Driver.class, email)).thenReturn(driver);
		
		ArrayList<RideBooked> ema = sut.getBookingsDriver(email);
		
		assertTrue(!ema.isEmpty());		
	}
}
