import java.util.*;

public class CompanyEmailSystem {

	public int GlobalProjectCounter;
	public static String[] ProjectPhases = new String[]{"Feasibility","Design","Implementation","Testing","Deployment","Completed"};
	
    private ArrayList<CompanyProject> AllProjects;
    private int currentProjShowing;
    
    //Created constants to ensure menu always displayed the same - Liam and Tom
    private final String mainMenu = "What do you want to do?\nP = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it";
    private final String projectMenu = "What do you want to do?\n L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project";
    
    public static void main(String[] args) {

    	//Moved the content of main into constructor for JUnit testing - Tom.
    	
    	new CompanyEmailSystem();
    	
    }
    
    //Test Method to return private variables - Liam and Tom
    public int getCurrentProjShow() {
    	return currentProjShowing;
    }
    
    //Test method to add premade projects - Tom and Liam
    public void AddProject(CompanyProject proj) {
    	AllProjects.add(proj);
    }
    
    public CompanyEmailSystem() { 
    	///////
        //Startup
        //////
        GlobalProjectCounter = 0;
        AllProjects = new ArrayList<CompanyProject>();
        // Removed test data and put in our testing class - Tom.
        
        // Call mainLoop to start the program. Also this allows to call it in testing without making a new CompanyEmailSystem. - Tom.
        mainLoop();
    }
    
    public void mainLoop() {
    	//Moved to a new method that can be called to be tested. Tom.
    	System.out.println(mainMenu);
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String s = in.next();
            try{
                if(currentProjShowing == 0) {
                    if (s.equals("P")) {
                        ListProjects();
                    } else if (s.equals("A")) {
                    	in.nextLine(); //Moved this out of the methods where it is repeated so those methods can be properly tested. Tom and Jacob.
                        AddProject(in);
                    } else if (s.equals("X")) {
                        System.out.println("Goodbye!");
                        break;
                        		//(Integer.parseInt(s) != -1) -- This allows numbers less than -1 to be entered and numbers over the project counter to be entered. Liam and Tom.
                    } else if ((s.chars().allMatch(Character::isDigit)) && (Integer.parseInt(s) <= GlobalProjectCounter && Integer.parseInt(s) > 0 )) { //New condition -- Liam and Tom.
                    	//currentProjShowing = Integer.parseInt(s)-1; This causes the 1st project to never be accessible. Tom and Liam.
                    	//Resolved Exception error, now checks input is numerical before trying to pass it as int - Liam.
                        currentProjShowing = Integer.parseInt(s);
                    } else {
                        System.out.println("Command not recognised");
                    }
                } else {
                    if (s.equals("A")) {
                    	in.nextLine(); //Moved this out of the methods where it is repeated so those methods can be properly tested. Tom and Jacob.
                        AddEmail(in, currentProjShowing); //Passes currentProjShowing as AddEmail was changed to take an Int - Jacob
                    }else if (s.equals("L")) {
                        ListEmails(0);
                    } else if (s.equals("F")) {
                        ListPhases();
                    } else if (s.equals("C")) {
                        ListContacts(currentProjShowing); //Passes currentProjShowing as ListContacts was changed to take an Int - Jacob
                    } else if (s.equals("N")) {
                        ChangeProjectPhase(currentProjShowing); //Passes currentProjShowing as ChangeProjectPhase was changed to take an Int - Jacob
                    } else if (s.equals("X")) {
                        currentProjShowing = 0;
                        //Same fix to if statement as above - Liam.
                    } else if ((s.chars().allMatch(Character::isDigit)) && Integer.parseInt(s) != -1 ) {
                        ListEmails(Integer.parseInt(s));
                    } else {
                        System.out.println("Command not recognised");
                    }
                }
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.toString() + "\n");
            }
            if(currentProjShowing == 0) {					
                System.out.println(mainMenu);	//Removed word Software and Space Character - Liam and Tom
            } else {							//Using constants for consistency - Liam and Tom
                System.out.println(projectMenu);
            }
        }
        in.close();
    }
    
    public void ListProjects(){
        for (int x = 0; x < AllProjects.size(); x++) {
            CompanyProject cp = AllProjects.get(x);
            int emailCount = cp.getEmailsForPhase().size();
            System.out.println((x+1) + ") " + cp.toString() + " - " + emailCount + "emails");
        }
    }
    
    public void AddProject(Scanner in) {
        System.out.println("What is the title of the project?");
        //in.nextLine(); // to remove read-in bug //Moved this into the MainLoop method. Tom and Jacob.
        String title = in.nextLine();
        AllProjects.add(new CompanyProject(this,title)); //Now passes a reference of this CompanyEmailSystem to the project. Tom and Jacob.
        System.out.println("[Project added]");
    }
    
    public void ListEmails(int phaseToShow) {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        ArrayList<CompanyEmail> projectPhaseEmails = null;
        if (phaseToShow==0) {
            projectPhaseEmails = cp.getEmailsForPhase();
        } else if (phaseToShow < cp.getPhaseByID()) {
            projectPhaseEmails = cp.getEmailsForPhase(phaseToShow);
        } else {
            System.out.println("Error: Unknown Phase");
        }
        if (projectPhaseEmails != null) {
            System.out.println(cp.toString());
            System.out.println("\n   From                Subject");
            System.out.println("--------------------------------");
            for (int x = 0; x < projectPhaseEmails.size(); x++) {
                CompanyEmail ce = projectPhaseEmails.get(projectPhaseEmails.size()-x-1);
                System.out.println((x+1) + ") " + ce.fromAddress() + " - " + ce.subjectLine());
                if (x==10) {
                    System.out.println("...");
                    break;
                }
            }
        }
    }
    
    public void ListPhases() {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        
        for (int x=0; x < cp.getPhaseByID(); x++) {
        	if(cp.getEmailsForPhase(x).size() == 0)
        		{
        			System.out.println((x+1)+") "+cp.getPhaseByName()+" - "+cp.getEmailsForPhase().size()+" Emails");
        		}
        }
    }
    

    public void ListContacts(int projID) { // Takes an integer rather than global variable - Jacob
        CompanyProject cp = AllProjects.get(projID - 1);//As it is an array need to -1 - Jacob
        ArrayList<String> projectContacts = cp.getProjectContacts();
        for (int x=0; x < projectContacts.size(); x++ ) {
            System.out.println((x+1)+") "+projectContacts.get(x));
        }
    }
    
    public void AddEmail(Scanner in,int pro) {
        System.out.println("Which email address is it from?");
        //in.nextLine(); //to remove read-in bug //Moved this to the mainloop method. Jacob and Tom.
        String fromAddress = in.nextLine();
        
        System.out.println("Which email address is it to?");
        String toAddress = in.nextLine();
        System.out.println("What is the Subject?");
        String subjectLine = in.nextLine();
        System.out.println("What is the Message?");
        String emailBody = in.nextLine();
        CompanyProject cp = AllProjects.get(pro);
        CompanyEmail ce = new CompanyEmail(fromAddress,toAddress,subjectLine,emailBody);
        if(cp.addEmail(ce)) {
        	System.out.println("[Email added to " + cp.toString() + "]");	
        } else {
        	System.out.println("[Email address(es) not valid]"); // Added if and else to return an error if the entry couldn't be made and prints and error - Jacob and Tom
        }
        
    }
    
    public void ChangeProjectPhase(int proj) {
        CompanyProject cp = AllProjects.get(proj- 1);//As it is an array need to -1 - Jacob
        if (cp.nextPhase()) {
            System.out.println("[Phase changed: " + cp.toString());
        } else {
            System.out.println("Project already in last phase.");
        }
    }

}
