import static org.junit.Assert.*;

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

}
