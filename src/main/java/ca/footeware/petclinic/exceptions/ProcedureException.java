/**
 * 
 */
package ca.footeware.petclinic.exceptions;

/**
 * @author craig
 *
 */
public class ProcedureException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ProcedureException() {
	}

	/**
	 * @param message
	 */
	public ProcedureException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ProcedureException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProcedureException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ProcedureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
