package se.leanbit.ticketsystem.exception;

public class ModelException extends IllegalArgumentException {
	public ModelException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ModelException(final String message) {
		super(message);
	}
}
