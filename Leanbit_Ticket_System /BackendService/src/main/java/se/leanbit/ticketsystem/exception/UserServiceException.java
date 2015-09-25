package se.leanbit.ticketsystem.exception;

public class UserServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public UserServiceException(final String message) {
		super(message);
	}
}
