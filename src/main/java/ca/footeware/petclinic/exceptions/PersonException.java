/**
 *
 */
package ca.footeware.petclinic.exceptions;

/**
 * @author Footeware.ca
 *
 */
public class PersonException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public PersonException() {
	}

	/**
	 * @param message
	 */
	public PersonException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PersonException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PersonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public PersonException(Throwable cause) {
		super(cause);
	}

}
