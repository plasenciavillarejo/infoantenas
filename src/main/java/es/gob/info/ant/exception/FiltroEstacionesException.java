package es.gob.info.ant.exception;

import java.io.Serializable;

public class FiltroEstacionesException extends Exception implements Serializable {

	public FiltroEstacionesException(String mensaje) {
		super(mensaje);
	}

	public FiltroEstacionesException(Throwable t) {
		super(t);
	}

	public FiltroEstacionesException(String mensaje, Throwable t) {
		super(mensaje, t);
	}

	private static final long serialVersionUID = 5855480773235982794L;

}