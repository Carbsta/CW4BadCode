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
		@Test
		public void testListProject() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			testCES.ListProjects();
			assertEquals("1) Proj1 [Feasibility] - 4emails\r\n" + "2) Proj2 [Feasibility] - 3emails\r\n" + "3) Proj3 [Feasibility] - 3emails\r\n", outContent.toString());
			
		}

		
		
// Section 3
		
		@Test
		public void testAddProject() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String input = "A";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		System.out.print("What is the title of the project.\n");
		assertEquals("What is the title of the project.\n",outContent.toString());
		
		
		}
		
		
		
		
		
// End of section 2 and 3
		
// Tom starts tests on section 4 here.
		
		//section 4
		
		@Test
		public void testEmailList() {
			String expected1 = "Proj1 [Feasibility]\n"+ nl + "   From                Subject\n" + "--------------------------------\n";
			String expected2 = "Proj2 [Feasibility]\n"+ nl + "   From                Subject\n" + "--------------------------------\n";
			String expected3 = "Proj3 [Feasibility]\n"+ nl + "   From                Subject\n" + "--------------------------------\n";
			//For loop to generate test string by listing the emails in reverse order to go most recent first.
			int y = 1;
			for (int x=9;x >= 0; x--) {
	        	switch(x%3) {
	        	case 0:
	        		expected1 = expected1 + (y/3+1)+") "+"me"+x+"@me.com" + " - this is a test subject for email"+x+nl;
	        		break;
	        	case 1:
	        		expected2 = expected2 + (y/3)+") "+"me"+x+"@me.com" + " - this is a test subject for email"+x+nl;
	        		break;
	        	case 2:
	        		expected3 = expected3 + (y/3+1)+") "+"me"+x+"@me.com" + " - this is a test subject for email"+x+nl;
	        		break;
	        	}
	        	y++;
	        }
			
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			testCES.ListEmails(0,1);
			assertEquals(expected1,outContent.toString());
			
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			testCES.ListEmails(0,2);
			assertEquals(expected2,outContent.toString());
			
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			testCES.ListEmails(0,3);
			assertEquals(expected3,outContent.toString());
			
		}
		
	
		
// End of section 4.
		
// Liam starts tests on section 5 here.
		
		//Section 5
		
		@Test
		 public void testNoPreviousPhases() {
			 String input = "X";
			 InputStream in = new ByteArrayInputStream(input.getBytes());
			 System.setIn(in);
			 CompanyEmailSystem ces = new CompanyEmailSystem();
			/* String phase1 = "1) Feasibility - 0 Emails\n";
			 String phase12 = "[Phase changed: test [Design]";
			 String phase2 = "2) Design - 0 Emails\n";
			 String phase23 = "[Phase changed: test [Implementation]";
			 String phase3 = "3) Implementation - 0 Emails\n";
			 String phase34 = "[Phase changed: test [Testing]";
			 String phase4 = "4) Testing - 0 Emails\n";
			 String phase45 = "[Phase changed: test [Deployment]";
			 String phase5 = "5) Deployment - 0 Emails\n";
			 String phase56 = "[Phase changed: test [Completed]";
			 String phase6 = "6) Completed - 0 Emails\n";*/
			 
			 String output = "1) Feasibility - 0 Emails\n" + "2) Design - 0 Emails\n" + "3) Implementation - 0 Emails\n";
				
			 CompanyProject test = new CompanyProject(ces, "test");
				
		 	 ces.AddProject(test);
			 test.nextPhase();
			 test.nextPhase();
				
			 outContent = new ByteArrayOutputStream();
			 System.setOut(new PrintStream(outContent));
				
			 ces.ListPhases(1);
			 assertEquals(output,outContent.toString());
		}
		
		
		
// ========== Jacob started here ==========
	
		//===== List Contacts Function tests ======
		//3.6.1
		

		@Test
		 public void testListedContacts() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			String should = "1) me0@me.com\n" + "2) me3@me.com\n" + "3) me6@me.com\n" + "4) me9@me.com\n";
			testCES.ListContacts(1);
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
			
			
			String should = "[Phase changed: Proj1 [Design]\n";
			
			
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			testCES.ChangeProjectPhase(1);
			assertEquals(should,outContent.toString());

		}
		
		//3.8.1
		@Test
		 public void testNextPhaseLast() {
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			String input = "X";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			CompanyEmailSystem ces = new CompanyEmailSystem();
			
			String should = "Project already in last phase.\n";
			
			
			
			CompanyProject last = new CompanyProject(ces, "last");
			
			ces.AddProject(last);
			
			last.nextPhase();
			last.nextPhase();
			last.nextPhase();
			last.nextPhase();
			last.nextPhase();
			
			
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			
			ces.ChangeProjectPhase(1);
			assertEquals(should,outContent.toString());

		}
} 

















