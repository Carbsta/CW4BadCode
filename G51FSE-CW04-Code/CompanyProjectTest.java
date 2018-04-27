import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CompanyProjectTest {
	//Tests by Chris, Audrey and Jacob

	@Test
	public void test() {
		CompanyProject cp = new CompanyProject("New Long Name");
		assertNotNull(cp.getPTitle());
		assertEquals(1, cp.getPID());
		CompanyProject cp2 = new CompanyProject();
		assertEquals(2, cp2.getPID());
		System.out.println(cp2.getPTitle());
	}

}
