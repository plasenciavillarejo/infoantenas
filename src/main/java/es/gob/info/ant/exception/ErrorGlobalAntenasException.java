package es.gob.info.ant.exception;

import java.io.Serializable;

public class ErrorGlobalAntenasException extends Exception implements Serializable {

	public ErrorGlobalAntenasException() {
		super();
	}
	public ErrorGlobalAntenasException(String mensaje) {
		super(mensaje);
	}

	public ErrorGlobalAntenasException(Throwable t) {
		super(t);
	}

	public ErrorGlobalAntenasException(String mensaje, Throwable t) {
		super(mensaje, t);
	}

	private static final long serialVersionUID = -6768470238498308273L;

}