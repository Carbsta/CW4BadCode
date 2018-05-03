import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CompanyEmailSystemTest {


		
		private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		
		@Before
		 public void setUpStreams() {
			System.setOut(new PrintStream(outContent));
		 }
		
		@After
		 public void cleanUpStreams() {
		 System.setOut(null);
		 }
		
		@Test
		 public void testMain() {
		 new CompanyEmailSystem();
		 String s = "X";
		 InputStream in = new ByteArrayInputStream(s.getBytes());
		 System.setIn(in);
		 assertEquals("test","What do you want to do?\\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it"+System.lineSeparator(),outContent.toString());
		 }

}
