package com.example.projectdemouser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectOrderRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectOrderRequestException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IncorrectOrderRequestException(String mensaje, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(mensaje, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public IncorrectOrderRequestException(String mensaje, Throwable cause) {
		super(mensaje, cause);
		// TODO Auto-generated constructor stub
	}

	public IncorrectOrderRequestException(String mensaje) {
		super(mensaje);
		// TODO Auto-generated constructor stub
	}

	public IncorrectOrderRequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
