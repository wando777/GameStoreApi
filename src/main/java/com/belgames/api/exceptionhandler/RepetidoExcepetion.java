package com.belgames.api.exceptionhandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RepetidoExcepetion extends Exception {

	private static final long serialVersionUID = 1L;

	public RepetidoExcepetion(String msg) {
		super(msg);
	}

	public RepetidoExcepetion(String msg, Throwable cause) {
		super(msg, cause);
	}
}
