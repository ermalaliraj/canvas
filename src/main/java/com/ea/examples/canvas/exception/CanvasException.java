package com.ea.examples.canvas.exception;

/**
 * General Canvas Exception. All application exceptions extends this class.
 */
public class CanvasException extends Exception{

	private static final long serialVersionUID = -8629000422659650854L;

	public CanvasException(String message){
		super(message);
	}
	
	public CanvasException(String message, Throwable e){
		super(message,e);
	}
}
