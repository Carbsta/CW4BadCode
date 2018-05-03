import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CompanyEmailSystemTest {

		//Company Email Systems Tests - Paired Coding - Liam and Tom
		

		private ByteArrayOutputStream outContent; 

		private final String mainMenuString = "What do you want to do?\nP = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it";
		private final String projectMenuString = "What do you want to do?\n L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project";
		private final String gb = "Goodbye!";
		private final String nl = System.lineSeparator();
		private final String error = "Command not recognised";
		
		@Before
		 public void setUpStreams() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			//////////////
	        //example test data
	        //////////////
	        
	        CompanyProject cp1 = new CompanyProject("Proj1");
	        CompanyProject cp2 = new CompanyProject("Proj2");
	        CompanyProject cp3 = new CompanyProject("Proj3");
	        
	        for (int x=0;x <10; x++) {
	        	CompanyEmail ce = new CompanyEmail("me"+x+"@me.com", "you"+x+"@you.com", "this is a test subject for email"+x, "this is a test message for email "+x);
	        	
	        	switch(x%3) {
	        	case 0:
	        		cp1.addEmail(ce);
	        		break;
	        	case 1:
	        		cp2.addEmail(ce);
	        		break;
	        	case 2:
	        		cp3.addEmail(ce);
	        		break;
	        	}
	        }
	        
	        String input = "X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			CompanyEmailSystem ces = new CompanyEmailSystem();
	        
	        ces.AddProject(cp1);
	        ces.AddProject(cp2);
	        ces.AddProject(cp3);
	        
	        /// END OF TEST DATA ///
	        outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
	        
		 }
		
		@After
		 public void cleanUpStreams() {
			System.setOut(null);
			System.setIn(null);
		 }
	
		@Test
		 public void testInitialCurrentProjectShow() { 
			assertEquals(0, CompanyEmailSystem.getCurrentProjShow());
		}
		
		@Test
		 public void testOfferMenuOnStartUp() {	//Escapes with input X
			String input = "X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			new CompanyEmailSystem();
		    assertEquals(mainMenuString+nl+gb+nl, outContent.toString());
		    
		 }
		
		@Test
		 public void testPressNUMOnOptions() { //Enters NUM, returns with X, escapes with X
			int n;
			for (n = 1; n <= CompanyEmailSystem.GlobalProjectCounter; n++) {
				outContent = new ByteArrayOutputStream();
				System.setOut(new PrintStream(outContent));
				String input = n+nl+"X"+nl+"X";
				InputStream in = new ByteArrayInputStream(input.getBytes());
				System.setIn(in);
				new CompanyEmailSystem();
				assertEquals(mainMenuString+nl+projectMenuString+nl+mainMenuString+nl+gb+nl, outContent.toString());
			}
		}
		
		@Test
		 public void testCurrentProjVar0OnExit() { //Escapes with input X
			String input = "X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			new CompanyEmailSystem();
		    assertEquals(0, CompanyEmailSystem.getCurrentProjShow());
		}
		
		@Test
		 public void testPressinvalidNUMOnOptions() { //Enters -NUMs, escapes with X
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			String input = ""+Integer.MIN_VALUE+nl+"-4"+nl+"0"+nl+"4"+nl+Integer.MAX_VALUE+nl+"X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			new CompanyEmailSystem();
			assertEquals(mainMenuString+nl+error+nl+mainMenuString+nl+error+nl+mainMenuString+nl+error+nl+mainMenuString+nl+error+nl+mainMenuString+nl+error+nl+mainMenuString+nl+gb+nl, outContent.toString());
		}


		
// ========== Jacob started here ==========
	
		//===== List Phase Function tests ======
		//3.6.1
		

		@Test
		 public void testListedContacts() {
			String input = "X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			CompanyEmailSystem ces = new CompanyEmailSystem();
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			String should = "1) me0@me.com\n" + "2) me3@me.com\n" + "3) me6@me.com\n" + "4) me9@me.com\n";
			ces.ListContacts(0);
			assertEquals(should,outContent.toString());

		}
		
		
		
		
		
//		
//		@Test
//		 public void testNoContactsToList() {
//			String input = "X";
//			InputStream in = new ByteArrayInputStream(input.getBytes());
//			System.setIn(in);
//			CompanyEmailSystem ces = new CompanyEmailSystem();
//			CompanyEmailSystem ced = new CompanyEmailSystem();
//			
//			outContent = new ByteArrayOutputStream();
//			System.setOut(new PrintStream(outContent));
//			
//			String should = "";
//			ces.ListContacts(1);
//			assertEquals(should,outContent.toString());
//		}
//		


		
		@Test
		 public void testCheckXToRightMenu() { //Enters NUM, either returns with X OR escapes with X
			int n;
			for (n = 1; n < CompanyEmailSystem.GlobalProjectCounter; n++) {
				outContent = new ByteArrayOutputStream();
				System.setOut(new PrintStream(outContent));
				String input = n+nl+"X"+nl+"X";
				InputStream in = new ByteArrayInputStream(input.getBytes());
				System.setIn(in);
				new CompanyEmailSystem();
				assertEquals(mainMenuString+nl+projectMenuString+nl+mainMenuString+nl+gb+nl, outContent.toString());
			}
		}
		
} 

















