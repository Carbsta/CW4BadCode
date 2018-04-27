import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CompanyProjectTest {
	//Tests by Chris, Audrey and Jacob

	@Test
<<<<<<< HEAD
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
=======
	public void test() {
		CompanyProject cp = new CompanyProject("New Long Name");
		assertNotNull(cp.getPTitle());
		assertEquals(1, cp.getPID());
		CompanyProject cp2 = new CompanyProject();
		assertEquals(2, cp2.getPID());
		System.out.println(cp2.getPTitle());
>>>>>>> f98d48e51c4463ac4a70ea8f3ee8ca0d70ee97e2
	}

}
