import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyEmail {
    private String fromAddress;
    private String toAddress;
    private String subjectLine;
    private String emailMessage;
    
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    private Matcher matcher;
    // TOM: ^ regex expression for checking if an email address is valid. Taken from https://www.journaldev.com/638/java-email-validation-regex by pankaj
    
    public CompanyEmail() {
        fromAddress = null;
        toAddress = null;
        subjectLine = null;
        emailMessage = null;
    }
    
    public CompanyEmail(String fAddress, String tAddress, String subLine, String eMessage) {
    	fromAddress = fAddress;
    	toAddress = tAddress;
    	subjectLine = subLine;
    	emailMessage = eMessage;
    }
    
    public String fromAddress() {
        return fromAddress;
    }
    
    public String toAddress() {
        return toAddress;
    }
    
    public String subjectLine() {
        return subjectLine;
    }
    
    public String emailMessage() { 
        //return emailMessage(); //Liam - removed parenthesise
    	return emailMessage;
    }
    
//    public void setFrom(String fromAddr) {  Original setFrom doesn't correctly vet email addresses.
//        if (fromAddr.contains("@")) {
//            fromAddress = fromAddr;
//        }
//    }
    
    public void setFrom(String fromAddr) {
    	//New setFrom method written by Tom to use regex to correctly reject invalid emails addresses.
    	//The regex expression was taken from https://www.journaldev.com/638/java-email-validation-regex by pankaj
    	matcher = pattern.matcher(fromAddr);
    	if (matcher.matches()) {
    		fromAddress = fromAddr;
    	}
    	
    }
    
    public void setTo(String toAddr) {
        if (toAddr.contains("@")) {
            toAddress = toAddr;
        }
    }
    
    public void setSubject(String subLine) {
        subjectLine = subLine;
    }
    
    public void setMessage(String eMessage) {
        emailMessage = eMessage;
    }
    
    public boolean isValid() {
        boolean isComplete = true;
        if (fromAddress == null) isComplete = false;
        if (toAddress == null) isComplete = false;
        if (subjectLine == null) isComplete = false;
        if (emailMessage == null) isComplete = false;
        return isComplete;
    }
    
    public String toString() {
    	if (subjectLine.equals("")) {
    		return "[no subject]";
    	} else {
    		return subjectLine;
    	}
    }
}
