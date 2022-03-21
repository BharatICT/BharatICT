package com.tatvasoft.exception;

public class EntityModificationException  extends RuntimeException {
	 private static final long serialVersionUID = 756532366516638568L;

	    public EntityModificationException() {
	    }

	    public EntityModificationException(String message) {
	        super(message);
	    }

	    public EntityModificationException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public EntityModificationException(Throwable cause) {
	        super(cause);
	    }

	    public EntityModificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
}
