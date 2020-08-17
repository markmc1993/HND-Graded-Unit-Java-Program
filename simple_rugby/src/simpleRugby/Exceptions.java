package simpleRugby;


/**
 * <h1>Exceptions</h1>
 * This is the exception class that is used when try catches are done on text inputs. If invalid information entered, these are caught and an exception is thrown.
 * 
 * 
 * @author Mark McAllister
 *
 */
@SuppressWarnings("serial")
public class Exceptions extends Exception{
	
	//setup variables to be used by class
	static final String DEFAULT_ERROR = "Invalid entry";
	private String errorMessage;
	
	/**
	 * This method creates the exception and sets errorMessage to the set default error
	 * 
	 * @param none
	 * 
	 */
	public Exceptions() {
		errorMessage = DEFAULT_ERROR;
	}
	
	/**
	 * This method creates the exception and sets errorMessage to the string sent
	 * 
	 * @param altErrorMessage set error message to altErrorMessage
	 * 
	 */
	public Exceptions(String altErrorMessage) {
		errorMessage = altErrorMessage;
	}
	
	
	public String getMessage() {
		return errorMessage;
	}
	
}
