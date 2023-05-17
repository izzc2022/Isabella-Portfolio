public class FailedSearchException extends Exception 
{ 
    // custom exception made for added functionality and to increase the robustness of the system
    // Adds more clarity
    // More relevant to the methods within the Patient Record System Class
    public FailedSearchException(String message) {  
        super(message);  
    }  
} 