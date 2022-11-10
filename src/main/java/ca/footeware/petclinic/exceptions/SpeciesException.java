/**
 *
 */
package ca.footeware.petclinic.exceptions;

/**
 * @author craig
 *
 */
public class SpeciesException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public SpeciesException() {
	}

	/**
	 * @param message
	 */
	public SpeciesException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SpeciesException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SpeciesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public SpeciesException(Throwable cause) {
		super(cause);
	}

}
