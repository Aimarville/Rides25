package getBookingsDriverTest;

import static org.junit.Assert.*;

import java.text.*;

import java.util.*;

import javax.persistence.*;

import org.junit.*;

import domain.*;

import org.mockito.*;

import dataAccess.*;

public class GetBookingsDriverMockBlackTest {
	
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
		String email = "asier@mail.com";
		
		if (email != null) {
			assertTrue(true);
		}else {
			fail();
		}
	}
	
	@Test
	public void test2() {
		String email = "asier@mail.com";
		Driver driver = new Driver(email, "1234");
		
		Mockito.when(db.find(Driver.class, driver)).thenReturn(driver);
		
		Driver foundDriver = db.find(Driver.class, email);
		
		if (foundDriver != null) {
			assertTrue(true);
		}else {
			fail();
		}
	}
}