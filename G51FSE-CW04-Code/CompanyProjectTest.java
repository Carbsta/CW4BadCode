import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompanyProjectTest {
	//Tests by Chris, Audrey and Jacob
	
	
	// ------  Test setup after fixing CompanyEmailSystem. Written by Tom.
	private CompanyEmailSystem testCES;
	private ByteArrayOutputStream outContent; 
	
	@Before
	public void setupCES() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String input = "X";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		testCES = new CompanyEmailSystem();
		System.setOut(null);
		System.setIn(null);
	}
	
	@After
	 public void cleanUpStreams() {
		System.setOut(null);
		System.setIn(null);
	 }
	// -----
	

	// ---- Audrey's tests start -----
	
	@Test
	public void testdef() {
		CompanyProject one = new CompanyProject(testCES);
		CompanyProject first = new CompanyProject(testCES);
		ArrayList<String> arr = new ArrayList<String>();
		
		int defID = one.getPID();
		assertEquals(first.getPID(), ++defID );
		assertEquals("New Project", one.getPTitle());
		assertEquals(arr, one.getProjectContacts());
		assertEquals(1, one.getPhaseByID());
		assertEquals(arr, one.getEmailsForPhase());
	}

	@Test
	public void testGetPID() {
		CompanyProject three = new CompanyProject(testCES);
		CompanyProject third = new CompanyProject(testCES);
		
		int testID = three.getPID();
		assertEquals(third.getPID(), ++testID);
	}
	
	@Test
	public void testGetPtitle() {
		CompanyProject four = new CompanyProject(testCES);
		assertEquals("New Project", four.getPTitle());
	}
	
	@Test
	public void testSetPtitle() {//
		CompanyProject five = new CompanyProject(testCES);
		five.setPTitle("abcd");
		assertEquals("New Project", five.getPTitle());
	}
	
	@Test
	public void testSetPtitle2() {
		CompanyProject five1 = new CompanyProject(testCES);
		five1.setPTitle("NewProjectName");
		assertEquals("NewProjectName", five1.getPTitle());
		
	}


	//----- Audrey's tests end --------
	
	
//	Chris test start
		@Test
		public void testIsContact() {
			CompanyProject test6 = new CompanyProject(testCES);
			String example = "123@gmail.com";
			boolean result = test6.isContact("123@gmail.com");
			assertNotNull(example, result);
		}



		@Test
		public void testAddContact() {
			CompanyProject test7 = new CompanyProject(testCES);
			String example = "1234@gmail.com";
			//boolean result = test7.addContact(example);
			//assertNotNull(example,result);
		}


		@Test
		public void testAddEmail() {
			CompanyProject test8 = new CompanyProject(testCES);
			CompanyEmail ce = new CompanyEmail("test@gmail.com", "test@gmail.com", "this is a test subject for email", "this is a test message for email ");
			test8.addEmail(ce);
//			assertNull(example,result);
			
			assertEquals(test8.isContact("test@gmail.com"), true);
		}

		@Test
		public void testGetEmailsForPhase() {
		CompanyProject test9 = new CompanyProject(testCES);
		ArrayList<CompanyEmail> A1 = test9.getEmailsForPhase();
		assertNotNull(A1);
		}

		@Test
		public void testGetEmailsForPhaseInt() {
		CompanyProject test10 = new CompanyProject(testCES);
		ArrayList<CompanyEmail> B2 = test10.getEmailsForPhase();
		assertNotNull(B2);
		}
	//Chris test end
	
	
	
	// Jacob's Tests
	//===== Next Phase Function tests ======
		
	
	//===== Constructor tests ======
			//2.1.1
			
			
	
	    //2.11.1 Returns true when Project Phase correctly incremented and doesn't 
		
		@Test
		public void testNPTrue() {
			CompanyProject cp = new CompanyProject(testCES);
			if(CompanyEmailSystem.ProjectPhases.length > 1){
				assertTrue(cp.nextPhase());
			}
			else {
				fail("There is only one project phase, no next phase to change to.");
			}
				
		}
		
		//2.11.2 Returns false when the project phase 
		@Test
		public void testNPFalse() {
			int i = 1;
			CompanyProject cp = new CompanyProject(testCES);
			
			while (i++ < CompanyEmailSystem.ProjectPhases.length) {
				cp.nextPhase();
				
			}
			
			assertFalse(cp.nextPhase());
		
		}
		
		//===== Get Phase Name Function tests ======
		//2.12.1 
		
			
		@Test
		public void testPhaseNames() {
			CompanyProject cp = new CompanyProject(testCES);
			
			String[] projectNames = new String[]{"Feasibility","Design","Implementation","Testing","Deployment","Completed"};
			
			int i = 0;
			String pn;
			
			while(i < projectNames.length) {
				pn = projectNames[i++];
				assertEquals(cp.getPhaseByName(), pn);
				cp.nextPhase();
			}
			
			
			
		}
		
		//===== Get Phase ID Function tests ======
		//2.13.1

		@Test
		public void testPhaseIDs() {
			CompanyProject cp = new CompanyProject(testCES);
			int i = 1;
			
			while(i < CompanyEmailSystem.ProjectPhases.length)
			{
				assertEquals(cp.getPhaseByID(), i++);
				cp.nextPhase();
			}
			
		}
		
		//===== Get Project Contacts Function tests ======
		//2.14.1
		
		@Test
		public void testEmptyContacts() {
			CompanyProject cp = new CompanyProject(testCES);
			ArrayList<String> empty = new ArrayList<String>();
			
			assertEquals(cp.getProjectContacts(), empty);
			
		}
		
		//2.14.2
		@Test
		public void testSomeContacts() {
			CompanyProject cp = new CompanyProject(testCES);
			ArrayList<String> some = new ArrayList<String>();
			
			some.add("jeed@gmail.com");
			cp.addContact("jeed@gmail.com");
			some.add("gota@gmail.com");
			cp.addContact("gota@gmail.com");
			
			assertEquals(cp.getProjectContacts(), some);
			
			
		}
		
		//===== Project toString Function tests ======
		//2.15.1
				
		@Test
		public void testNewTitleS() {
			CompanyProject cp = new CompanyProject(testCES);
			String tstr = "New Project [Feasibility]";
			assertEquals(cp.toString(), tstr);
			
			
			
		}
		
		//2.15.2
		@Test
		public void testNamedTitleS() {
			int i = 0;
			String[] tstr = new String[]{"Named Project [Feasibility]","Renamed Project [Feasibility]","Renamed Project [Design]"};
			CompanyProject cp = new CompanyProject(testCES,"Named Project");
			
			assertEquals(cp.toString(), tstr[i++]);
			
			cp.setPTitle("Renamed Project");
			
			assertEquals(cp.toString(), tstr[i++]);
		
			cp.nextPhase();
			
			assertEquals(cp.toString(), tstr[i++]);
			
			
			
		}
		
		//===== Project Construction tests ======
		//2.1.1
		@Test
		public void testProjectConPID() {
			CompanyProject cp = new CompanyProject(testCES);
			CompanyProject nextcp = new CompanyProject(testCES);
			
			ArrayList<String> empty = new ArrayList<String>();
			
			int tpid = cp.getPID();
			assertEquals(nextcp.getPID(), ++tpid);
			assertEquals(cp.getPTitle(), "New Project");
			assertEquals(cp.getProjectContacts(), empty);
			assertEquals(cp.getPhaseByID(), 1);
			assertEquals(cp.getEmailsForPhase(), empty);
		} 
		
		
		//2.2.1
		public void testProjectConSetTit() {
			CompanyProject cp = new CompanyProject(testCES,"This Is A New Project");
			assertEquals(cp.getPhaseByName(), "This Is A New Project");
			}
		
}