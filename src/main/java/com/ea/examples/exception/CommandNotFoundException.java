package com.ea.examples.exception;

/**
 * Thrown when cannot build a command starting from the input string inserted by the user.
 */
public class CommandNotFoundException extends CanvasException{

	private static final long serialVersionUID = -8629000422659650854L;

	public CommandNotFoundException(String message){
		super(message);
	}
	
	public CommandNotFoundException(String message, Throwable e){
		super(message,e);
	}
}
