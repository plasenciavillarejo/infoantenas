package es.gob.info.ant.exception;

import java.io.Serializable;

public class FiltroAntenasException extends Exception implements Serializable {

	public FiltroAntenasException(String mensaje) {
		super(mensaje);
	}

	public FiltroAntenasException(Throwable t) {
		super(t);
	}

	public FiltroAntenasException(String mensaje, Throwable t) {
		super(mensaje, t);
	}

	private static final long serialVersionUID = -3083995476292727517L;

}
