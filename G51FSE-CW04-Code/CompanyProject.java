import java.util.ArrayList;

public class CompanyProject {
    private int PID;
    private String PTitle;
    private ArrayList<String> ProjectContacts;
    private int ProjectPhase;
    private ArrayList[] ProjectEmails = new ArrayList[6];
    private CompanyEmailSystem manager; // we need each project to know what System it is in, so the global project counter is no longer static. - Tom.
    
    public CompanyProject(CompanyEmailSystem caller) {
    	//We set up the reference to the project's CompanyEmailSystem and then increment that CES's project counter. Tom.
    	manager = caller;
        manager.GlobalProjectCounter++;
        PID = manager.GlobalProjectCounter;
        PTitle = "New Project";
        ProjectContacts = new ArrayList<String>();
        //ProjectPhase = 1;
        ProjectPhase = 0;//arrays start from 0 - Jacob
        ProjectEmails[ProjectPhase] = new ArrayList<CompanyEmail>();
    }
    
    public CompanyProject(CompanyEmailSystem caller, String pTitle) {
    	manager = caller;
    	manager.GlobalProjectCounter++;
        PID = manager.GlobalProjectCounter;
        PTitle = pTitle;
        ProjectContacts = new ArrayList<String>();
        //ProjectPhase = 1;
        ProjectPhase = 0;//arrays start from 0 - Jacob
        ProjectEmails[ProjectPhase] = new ArrayList<CompanyEmail>();
    }
    
    public int getPID() {
        return PID;
    }
    
    public String getPTitle() {
        return PTitle;
    }
    

    //edited by Audrey and Jacob
    public void setPTitle(String pTitle) {
    	if (pTitle.length() >= 10 ) {
    		//System.out.println("Project title should be 10 or more characters");
    		PTitle = pTitle;
    	}
    }
//    //edited by Audrey
//    public String setPTitle(String pTitle) {
//    	if (pTitle.length() < 11 ) {
//    		System.out.println("Project title should be 10 or more characters");
//    		return PTitle;
//    	}
//    	return PTitle = pTitle;
//    }
    
    //edited by Chris
    public boolean isContact(String emailAddress) {
        return ProjectContacts.contains(emailAddress);
    }
    
    //edited by Chris
    public String addContact(String emailAddress) {
        ProjectContacts.add(emailAddress);
		return emailAddress;
   }
    
    public boolean addEmail(CompanyEmail newEmail) {
        if (newEmail.isValid()) {
            ProjectEmails[ProjectPhase].add(newEmail);
            if (ProjectContacts.contains(newEmail.fromAddress())) {
                //do nothing
            } else {
                ProjectContacts.add(newEmail.fromAddress());              
            }
            return true; //Making this function return a boolean if the email is valid or not, so we can see if it is added correctly - Jacob
        }
        return false;
    }
    
    public ArrayList<CompanyEmail> getEmailsForPhase() {
        return ProjectEmails[ProjectPhase];
    }
    
    public ArrayList<CompanyEmail> getEmailsForPhase(int thePhase) {
        return ProjectEmails[thePhase];
    }
    
    public boolean nextPhase() {
        ProjectPhase++;
        if (ProjectPhase > CompanyEmailSystem.ProjectPhases.length) {
            return false;
        } else {
            return true;
        }
    }
    
    public String getPhaseByName() {
    	return CompanyEmailSystem.ProjectPhases[ProjectPhase]; 
    	
    }
    
    public int getPhaseByID() {
        return ProjectPhase; 
    }
    
    public ArrayList<String> getProjectContacts() {
        return ProjectContacts;
    }
    
    public String toString() {
        return PTitle + " [" + getPhaseByName() + "]";
    }
}
