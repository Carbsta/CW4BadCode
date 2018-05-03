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
		
		private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		
		private final String mainMenuString = "What do you want to do?\nP = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it";
		private final String projectMenuString = "What do you want to do?\n L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project";
		private final String gb = "Goodbye!";
		private final String nl = System.lineSeparator();
		
		@Before
		 public void setUpStreams() {
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
<<<<<<< HEAD

		 public void testMain() {
			CompanyEmailSystem ces = new CompanyEmailSystem();
			ces.main(null);
			assertEquals("What do you want to do?\\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it",outContent.toString());
		 }

		
// ========== Jacob started here ==========
	
		//===== List Phase Function tests ======
		//3.5.1
		
		@Test
		 public void testListedEmails() {
			CompanyEmailSystem ces = new CompanyEmailSystem();
			ces.ListPhases()
		}
		
		

=======
		
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
		
>>>>>>> Before Dinner update
}

















