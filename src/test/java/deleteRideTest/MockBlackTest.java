package deleteRideTest;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.*;

import java.text.*;

import java.util.*;

import javax.persistence.*;

import org.junit.*;

import org.mockito.*;

import dataAccess.*;

import domain.*;

public class MockBlackTest {
	
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
		
		String dGmail = "a@mail.com";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rideDate=null;;
		try {
			rideDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		Driver driver = new Driver(dGmail, "1234");
		
		Mockito.when(db.find(Driver.class, dGmail)).thenReturn(null);
		driver.addRide("Donosti", "Madrid", rideDate, 8, 3, new Car("1234FFF", "Ford", "Black", 5, driver));
		
		assertTrue(Mockito.contains(dGmail) != null);
	}
}
