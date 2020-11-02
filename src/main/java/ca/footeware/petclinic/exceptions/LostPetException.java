/**
 * 
 */
package ca.footeware.petclinic.exceptions;

/**
 * @author Footeware.ca
 *
 */
public class LostPetException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public LostPetException() {
	}

	/**
	 * @param message
	 */
	public LostPetException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LostPetException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public LostPetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public LostPetException(Throwable cause) {
		super(cause);
	}

}
