package com.ea.examples.canvas.exception;

/**
 * Thrown when cannot build a command starting from the input string inserted by the user.
 */
public class CanvasNotYetCreatedException extends CanvasException{

	private static final long serialVersionUID = -8629000422659650854L;

	public CanvasNotYetCreatedException(String message){
		super(message);
	}
	
	public CanvasNotYetCreatedException(String message, Throwable e){
		super(message,e);
	}
}
