package es.gob.info.ant.exception;

import java.io.Serializable;

public class RuntimeGenericoException extends RuntimeException implements Serializable {

	public RuntimeGenericoException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = 5836529582341875412L;

}
