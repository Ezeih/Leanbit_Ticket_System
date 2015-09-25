package se.leanbit.ticketsystem.exception;

public class TeamServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TeamServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TeamServiceException(final String message) {
		super(message);
	}
}
