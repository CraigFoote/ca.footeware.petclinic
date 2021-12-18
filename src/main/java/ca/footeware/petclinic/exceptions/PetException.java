/**
 *
 */
package ca.footeware.petclinic.exceptions;

/**
 * @author Footeware.ca
 *
 */
public class PetException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public PetException() {
	}

	/**
	 * @param message
	 */
	public PetException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PetException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public PetException(Throwable cause) {
		super(cause);
	}

}
