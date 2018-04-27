import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CompanyProjectTest {
	//Tests by Chris, Audrey and Jacob

	@Test
	public void test3() {
		CompanyProject three = new CompanyProject();
		
		assertEquals(1,three.getPID());
		
		CompanyProject clone1 = new CompanyProject();
		CompanyProject clone2 = new CompanyProject();
		assertEquals(3,three.getPID());
	}
	
	@Test
	public void test4() {
		CompanyProject four = new CompanyProject();
		
		assertEquals("New Project", four.getPTitle());
		
	}
	
	@Test
	public void test5() {
		CompanyProject five = new CompanyProject();
		
		String name = five.setPTitle("abcd");
		assertEquals("New Project", name);
		String name1 = five.setPTitle("NewProjectName");
		assertEquals("NewProjectName", name);
		
	}

	@Test
	public void test() {
		CompanyProject cp = new CompanyProject("New Long Name");
		assertNotNull(cp.getPTitle());
		assertEquals(1, cp.getPID());
		CompanyProject cp2 = new CompanyProject();
		assertEquals(2, cp2.getPID());
		System.out.println(cp2.getPTitle());
	} 
	
	// Jacob's Tests
	//===== Next Phase Function tests ======
		//2.11.1 Returns true when Project Phase correctly incremented and doesn't 
		
		@Test
		public void testNPTrue() {
			CompanyProject cp = new CompanyProject();
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
			CompanyProject cp = new CompanyProject();
			
			while (i++ < CompanyEmailSystem.ProjectPhases.length) {
				cp.nextPhase();
				
			}
			
			assertFalse(cp.nextPhase());
		
		}
		
		//===== Get Phase Name Function tests ======
		//2.12.1 
		
			
		@Test
		public void testPhaseNames() {
			CompanyProject cp = new CompanyProject();
			
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
			CompanyProject cp = new CompanyProject();
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
			CompanyProject cp = new CompanyProject();
			ArrayList<String> empty = new ArrayList<String>();
			
			assertEquals(cp.getProjectContacts(), empty);
		}
		
		//2.14.2
		@Test
		public void testSomeContacts() {
			CompanyProject cp = new CompanyProject();
			ArrayList<String> some = new ArrayList<String>();
			
			some.add("jeed@gmail.com");
			cp.addContact("jeed@gmail.com");
			some.add("gota@gmail.com");
			cp.addContact("gota@gmail.com");
			
			assertEquals(cp.getProjectContacts(), some);
		}
		
		//===== Get Project Contacts Function tests ======
		//2.15.1
				
		@Test
		public void testNewTitleS() {
			CompanyProject cp = new CompanyProject();
			String tstr = "New Project [Feasibility]";
			assertEquals(cp.toString(), tstr);
			
		}
		
		//2.15.2
		@Test
		public void testNamedTitleS() {
			int i = 0;
			String[] tstr = new String[]{"Named Project [Feasibility]","Renamed Project [Feasibility]","Renamed Project [Design]"};
			CompanyProject cp = new CompanyProject("Named Project");
			
			assertEquals(cp.toString(), tstr[i++]);
			
			cp.setPTitle("Renamed Project");
			
			assertEquals(cp.toString(), tstr[i++]);
		
			cp.nextPhase();
			
			assertEquals(cp.toString(), tstr[i++]);
			
		}
}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		