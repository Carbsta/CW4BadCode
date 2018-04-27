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
	public void testCompanyEmailint() {
		CompanyEmail ce = new CompanyEmail(1,2,3,4);
		assertEquals("fromAddress should be liam@mail.com", "liam@mail.com",  ce.fromAddress());
		assertEquals("toAddress should be tom@mail.com", "tom@mail.com", ce.toAddress());
		assertEquals("subjectLine should be look at this", "look at this",  ce.subjectLine());
		assertEquals("emailMessage should be its a dog", "its a dog", ce.emailMessage());
	} */
	
	@Test
	public void testfromAddressstr() {
		CompanyEmail fe = new CompanyEmail("liam@mail.com", "tom@mail.com", "look at this", "its a dog");
		assertEquals("fromAddress should be liam@mail.com", "liam@mail.com", fe.fromAddress());
	}
	
	/*@Test
	public void testfromAddressint() {
		CompanyEmail fe = new CompanyEmail(1, "tom@mail.com", "look at this", "its a dog");
		assertEquals("fromAddress should be liam@mail.com", "liam@mail.com", fe.fromAddress());
	} */
	
	@Test
	public void testfromAddressnull() {
		CompanyEmail fe = new CompanyEmail(null, "tom@mail.com", "look at this", "its a dog");
		assertNull("fromAddress should be null", fe.fromAddress());
	}
	
	@Test
	public void testtoAddressstr() {
		CompanyEmail te = new CompanyEmail("liam@mail.com", "tom@mail.com", "look at this", "its a dog");
		assertEquals("toAddress should be tom@mail.com", "tom@mail.com", te.toAddress());
	}
	
	/*@Test
	public void testtoAddressint() {
		CompanyEmail te = new CompanyEmail("liam@mail.com", 1, "look at this", "its a dog");
		assertEquals("toAddress should be tom@mail.com", "tom@mail.com", te.toAddress());
	} */
	
	@Test
	public void testtoAddressnull() {
		CompanyEmail te = new CompanyEmail("liam@mail.com", null, "look at this", "its a dog");
		assertNull("toAddress should be null", te.toAddress());
	}
	
	@Test
	public void testsubjecLinestr() {
		CompanyEmail sl = new CompanyEmail("liam@mail.com", "tom@mail.com", "look at this", "its a dog");
		assertEquals("subjectLine should be look at this", "look at this", sl.subjectLine());
	}
	
	/*@Test
	public void testsubjectLineint() {
		CompanyEmail sl = new CompanyEmail("liam@mail.com", "tom@mail.com", 1, "its a dog");
		assertEquals("subjectLine should be look at this", "look at this", sl.subjectLine());
	} */
	
	@Test
	public void testsubjectLinenull() {
		CompanyEmail sl = new CompanyEmail("liam@mail.com", "tom@mail.com", null, "its a dog");
		assertNull("subjectLine should be null", sl.subjectLine());
	}
	
	@Test
	public void testemailMessagestr() {
		CompanyEmail em = new CompanyEmail("liam@mail.com", "tom@mail.com", "look at this", "its a dog");
		assertEquals("emailMessage should be its a dog", "its a dog", em.emailMessage());
	}
	
	/*@Test
	public void testemailMessageint() {
		CompanyEmail em = new CompanyEmail("liam@mail.com", "tom@mail.com", "look at this", 1);
		assertEquals("emailMessage should be its a dog", "its a dog", em.emailMessage());
	} */
	
	@Test
	public void testemailMessagenull() {
		CompanyEmail em = new CompanyEmail("liam@mail.com", "tom@mail.com", "look at this", null);
		assertNull("emailMessage should be null", em.emailMessage());
	}
		

}
