/**
 * 
 */
package ca.footeware.petclinic.exceptions;

/**
 * @author Footeware.ca
 *
 */
public class LostPersonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public LostPersonException() {
	}

	/**
	 * @param message
	 */
	public LostPersonException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LostPersonException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LostPersonException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public LostPersonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
