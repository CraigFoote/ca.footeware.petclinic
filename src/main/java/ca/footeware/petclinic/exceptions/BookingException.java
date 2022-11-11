/**
 *
 */
package ca.footeware.petclinic.exceptions;

/**
 * @author Footeware.ca
 *
 */
public class BookingException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public BookingException() {
	}

	/**
	 * @param message
	 */
	public BookingException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BookingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BookingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public BookingException(Throwable cause) {
		super(cause);
	}

}
