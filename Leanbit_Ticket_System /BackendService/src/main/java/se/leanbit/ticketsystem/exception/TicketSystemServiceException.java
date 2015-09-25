package se.leanbit.ticketsystem.exception;

public class TicketSystemServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TicketSystemServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TicketSystemServiceException(final String message) {
		super(message);
	}
}
