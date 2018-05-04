import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CompanyEmailSystemTest {

	// tests sorted by the primary method they test
	
		
		//Set up of variables for Liam and Tom's tests. Also Before and After setup. Paired coding Liam (Keyboard) Tom (Supervisor).

		private ByteArrayOutputStream outContent; 
		private CompanyEmailSystem testCES;
		

		private final String mainMenuString = "What do you want to do?\nP = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it";
		private final String projectMenuString = "What do you want to do?\n L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project";
		private final String gb = "Goodbye!";
		private final String nl = System.lineSeparator();
		private final String error = "Command not recognised";
		private final String whatTitle = "What is the title of the project?";
		
		@Before
		 public void setUpStreams() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			String input = "X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			testCES = new CompanyEmailSystem();
			
			///set up the test data after being transplanted from CompanyEmailSystem. - Tom.
	        
	        CompanyProject cp1 = new CompanyProject(testCES,"Proj1");
	        CompanyProject cp2 = new CompanyProject(testCES,"Proj2");
	        CompanyProject cp3 = new CompanyProject(testCES,"Proj3");
	        
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
			
	        
	        testCES.AddProject(cp1);
	        testCES.AddProject(cp2);
	        testCES.AddProject(cp3);
	        
	        /// END OF TEST DATA ///
	        outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
	        
		 }
		
		@After
		 public void cleanUpStreams() {
			System.setOut(null);
			System.setIn(null);
		 }
		
	//Main/Constructor/Mainloop tests - Paired Coding - Liam and Tom
	
		@Test
		 public void testInitialCurrentProjectShow() { 
			assertEquals(0, testCES.getCurrentProjShow());
		}
		
		@Test
		 public void testOfferMenuOnStartUp() {	//Escapes with input X
			String input = "X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			testCES.mainLoop();
		    assertEquals(mainMenuString+nl+gb+nl, outContent.toString());
		    
		 }
		
		@Test
		 public void testPressNUMOnOptions() { //Enters NUM, returns with X, escapes with X
			int n;
			for (n = 1; n <= testCES.GlobalProjectCounter; n++) {
				outContent = new ByteArrayOutputStream();
				System.setOut(new PrintStream(outContent));
				String input = n+nl+"X"+nl+"X";
				InputStream in = new ByteArrayInputStream(input.getBytes());
				System.setIn(in);
				testCES.mainLoop();
				assertEquals(mainMenuString+nl+projectMenuString+nl+mainMenuString+nl+gb+nl, outContent.toString());
			}
		}
		
		@Test
		 public void testCurrentProjVar0OnExit() { //Escapes with input X
			String input = "1\nX\nX";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			testCES.mainLoop();
		    assertEquals(0, testCES.getCurrentProjShow());
		}
		
		@Test
		 public void testPressinvalidNUMOnOptions() { //Enters -NUMs, escapes with X
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			String input = ""+Integer.MIN_VALUE+nl+"-4"+nl+"0"+nl+"4"+nl+Integer.MAX_VALUE+nl+"X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			testCES.mainLoop();
			assertEquals(mainMenuString+nl+error+nl+mainMenuString+nl+error+nl+mainMenuString+nl+error+nl+mainMenuString+nl+error+nl+mainMenuString+nl+error+nl+mainMenuString+nl+gb+nl, outContent.toString());
		}


		
		@Test
		 public void testSaysGoodbyeOnExit() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			String input = "X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			testCES.mainLoop();
			assertEquals(mainMenuString+nl+gb+nl, outContent.toString());
		}
		
	//At this point Liam takes over solo as Tom has to assist/supervise elsewhere.
		
		@Test
		 public void testInvalidInputMainMenu() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			String input = "B\nX";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			testCES.mainLoop();
			assertEquals(mainMenuString+nl+error+nl+mainMenuString+nl+gb+nl, outContent.toString());
		}
		
		@Test
		 public void testInvalidInputReturnsRightMenu() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			String input = "B\nA\nTest\n1\nB\nX\nX";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			testCES.mainLoop();
			assertEquals(mainMenuString+nl+error+nl+mainMenuString+nl+whatTitle+nl+"[Project added]"+nl+mainMenuString+nl+projectMenuString+nl+error+nl+projectMenuString+nl+mainMenuString+nl+gb+nl, outContent.toString());
		}
		
// Chris and Audrey start their tests for section 2 and 3 here.
		

// End of section 2 and 3
		
// Tom starts tests on section 4 and 5 here.
		
		//section 4
		
		@Test
		public void testEmailList() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			testCES.ListEmails(0,1);
			String expected = "";
			assertEquals(expected,outContent.toString());
			
		}
		
		
// End of sections 4 and 5.
		
// ========== Jacob started here ==========
	
		//===== List Contacts Function tests ======
		//3.6.1
		

		@Test
		 public void testListedContacts() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			String should = "1) me0@me.com\n" + "2) me3@me.com\n" + "3) me6@me.com\n" + "4) me9@me.com\n";
			testCES.ListContacts(0);
			assertEquals(should,outContent.toString());

		}
		
		//3.6.2
		@Test
		 public void testListedEmpty() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			String input = "X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			CompanyEmailSystem ces = new CompanyEmailSystem();
			
			String should = "";
			
			ces.AddProject(new CompanyProject(ces));
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			ces.ListContacts(1);
			assertEquals(should,outContent.toString());

		}
		
		
		//===== Add Email Function tests ======
		//3.7.1
		@Test
		 public void testAddEmailSuc() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			String input = "jacob@gmail.com"+nl+"peter@gmail.com"+nl+"Hi Peter!"+nl+"Was wondering how you are?";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
						
			String should = "Which email address is it from?"+nl+"Which email address is it to?"+nl+"What is the Subject?"+nl+"What is the Message?"+nl+"[Email added to Proj2 [Feasibility]]\n";
				
			Scanner scans = new Scanner(in);
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			testCES.AddEmail(scans, 1);
			
			
			assertEquals(should,outContent.toString());

		}
		
		//3.7.2
		@Test
		 public void testAddEmailBad() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			String input = "jacob@gmail@com"+nl+"peter@gmail@com"+nl+"Hi Peter!"+nl+"Was wondering how you are?";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
						
			String should = "Which email address is it from?"+nl+"Which email address is it to?"+nl+"What is the Subject?"+nl+"What is the Message?"+nl+"[Email address(es) not valid]\n";
				
			Scanner scans = new Scanner(in);
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			testCES.AddEmail(scans, 1);
			
			
			assertEquals(should,outContent.toString());

		}
		
		//===== Change Project Phase Function tests ======
		//3.8.1
		@Test
		 public void testNextPhaseSuc() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			String input = "X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			
			
			String should = "[Phase changed: Proj1 [Design]\n";
			
			
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			testCES.ChangeProjectPhase(1);
			assertEquals(should,outContent.toString());

		}
} 

















