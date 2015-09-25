package se.leanbit.ticketsystem.exception;

public class IssueServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IssueServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public IssueServiceException(final String message) {
		super(message);
	}
}
