package es.gob.info.ant.exception;

import java.io.Serializable;

public class DetalleAntenasException extends Exception implements Serializable {

	public DetalleAntenasException(String mensaje) {
		super(mensaje);
	}

	public DetalleAntenasException(Throwable t) {
		super(t);
	}

	public DetalleAntenasException(String mensaje, Throwable t) {
		super(mensaje, t);
	}

	private static final long serialVersionUID = -6768470238498308273L;

}