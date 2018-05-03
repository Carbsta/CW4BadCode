import java.util.ArrayList;

public class CompanyProject {
    private int PID;
    private String PTitle;
    private ArrayList<String> ProjectContacts;
    private int ProjectPhase;
    private ArrayList[] ProjectEmails = new ArrayList[6];
    
    public CompanyProject() {
        CompanyEmailSystem.GlobalProjectCounter++;
        PID = CompanyEmailSystem.GlobalProjectCounter;
        PTitle = "New Project";
        ProjectContacts = new ArrayList<String>();
        ProjectPhase = 1;
        ProjectEmails[ProjectPhase] = new ArrayList<CompanyEmail>();
    }
    
    public CompanyProject(String pTitle) {
    	CompanyEmailSystem.GlobalProjectCounter++;
        PID = CompanyEmailSystem.GlobalProjectCounter;
        PTitle = pTitle;
        ProjectContacts = new ArrayList<String>();
        ProjectPhase = 1;
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
    
    
    public boolean isContact(String emailAddress) {
        return ProjectContacts.contains(emailAddress);
    }
    
    public boolean addContact(String emailAddress) {
        return ProjectContacts.add(emailAddress);
    }
    
    public void addEmail(CompanyEmail newEmail) {
        if (newEmail.isValid()) {
            ProjectEmails[ProjectPhase].add(newEmail);
            if (ProjectContacts.contains(newEmail.fromAddress())) {
                //do nothing
            } else {
                ProjectContacts.add(newEmail.fromAddress());
            }
        }
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
        //return CompanyEmailSystem.ProjectPhases[ProjectPhase];
    	return CompanyEmailSystem.ProjectPhases[ProjectPhase - 1]; 
    	//String array starts at 0 where as the project phase starts at 1 - Jacob
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
