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
		public void testnptrue() {
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
		public void testnpfalse() {
			int i = 0;
			CompanyProject cp = new CompanyProject();
			
			while (i++ < CompanyEmailSystem.ProjectPhases.length) {
				cp.nextPhase();
			}
			
			assertFalse(cp.nextPhase());
		
		}
		
		//===== Get Phase Name Function tests ======
		//2.12.1 
		
		@Test
		public void testcpnames() {
			CompanyProject cp = new CompanyProject();
			assertEquals(cp.getPhaseByName(), "Design");
			
			/*String[] projectNames = new String[]{"Feasibility","Design","Implementation","Testing","Deployment","Completed"};
			int i = 0;
			String pn;
			
			while(i < 5) {
				pn = projectNames[i++];
				assertEquals(cp.getPhaseByName(), pn);
				cp.nextPhase();
			}
			*/
		}

}
