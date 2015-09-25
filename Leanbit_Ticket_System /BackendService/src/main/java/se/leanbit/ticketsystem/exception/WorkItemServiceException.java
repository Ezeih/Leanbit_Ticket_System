package se.leanbit.ticketsystem.exception;

public class WorkItemServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WorkItemServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public WorkItemServiceException(final String message) {
		super(message);
	}
}
