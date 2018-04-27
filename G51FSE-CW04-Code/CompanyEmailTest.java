import static org.junit.Assert.*;

import org.junit.Test;

public class CompanyEmailTest {
	//tests by Tom and Liam

	@Test
	public void testCompanyEmailDC() {
		CompanyEmail ce = new CompanyEmail();
		assertNull("fromAddress should be null", ce.fromAddress());
		assertNull("toAddress should be null", ce.toAddress());
		assertNull("subjectLine should be null", ce.subjectLine());
		assertNull("emailMessage should be null", ce.emailMessage());
	}
	
	@Test
	public void testCompanyEmail4() {
		CompanyEmail ce = new CompanyEmail("liam@mail.com", "tom@mail.com", "look at this", "its a dog");
		assertEquals("fromAddress should be liam@mail.com", "liam@mail.com",  ce.fromAddress());
		assertEquals("toAddress should be tom@mail.com", "tom@mail.com", ce.toAddress());
		assertEquals("subjectLine should be look at this", "look at this",  ce.subjectLine());
		assertEquals("emailMessage should be its a dog", "its a dog", ce.emailMessage());
	}
	
	/*@Test
	public void testCompanyEmail1() {
		CompanyEmail ce = new CompanyEmail("liam@mail.com");
		assertEquals("fromAddress should be liam@mail.com", "liam@mail.com",  ce.fromAddress());
		assertEquals("toAddress should be tom@mail.com", "tom@mail.com", ce.toAddress());
		assertEquals("subjectLine should be look at this", "look at this",  ce.subjectLine());
		assertEquals("emailMessage should be its a dog", "its not a dog", ce.emailMessage());
	}
	
	@Test
	public void testCompanyEmail2() {
		CompanyEmail ce = new CompanyEmail("liam@mail.com", "tom@mail.com)");
		assertEquals("fromAddress should be liam@mail.com", "liam@mail.com",  ce.fromAddress());
		assertEquals("toAddress should be tom@mail.com", "tom@mail.com", ce.toAddress());
		assertEquals("subjectLine should be look at this", "look at this",  ce.subjectLine());
		assertEquals("emailMessage should be its a dog", "its not a dog", ce.emailMessage());
	}
	
	@Test
	public void testCompanyEmail3() {
		CompanyEmail ce = new CompanyEmail("liam@mail.com", "tom@mail.com", "look at this");
		assertEquals("fromAddress should be liam@mail.com", "liam@mail.com",  ce.fromAddress());
		assertEquals("toAddress should be tom@mail.com", "tom@mail.com", ce.toAddress());
		assertEquals("subjectLine should be look at this", "look at this",  ce.subjectLine());
		assertEquals("emailMessage should be its a dog", "its not a dog", ce.emailMessage());
	}
	
	@Test
	public void testCompanyEmail5() {
		CompanyEmail ce = new CompanyEmail("liam@mail.com", "tom@mail.com", "look at this", "its a dog", "more text");
		assertEquals("fromAddress should be liam@mail.com", "liam@mail.com",  ce.fromAddress());
		assertEquals("toAddress should be tom@mail.com", "tom@mail.com", ce.toAddress());
		assertEquals("subjectLine should be look at this", "look at this",  ce.subjectLine());
		assertEquals("emailMessage should be its a dog", "its a dog", ce.emailMessage());
	} 
	
	@Test
	public void testCompanyEmail4() {
		CompanyEmail ce = new CompanyEmail(1,2,3,4);
		assertEquals("fromAddress should be liam@mail.com", "liam@mail.com",  ce.fromAddress());
		assertEquals("toAddress should be tom@mail.com", "tom@mail.com", ce.toAddress());
		assertEquals("subjectLine should be look at this", "look at this",  ce.subjectLine());
		assertEquals("emailMessage should be its a dog", "its a dog", ce.emailMessage());
	} */

}
