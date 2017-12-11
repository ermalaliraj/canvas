package com.ea.examples.exception;

/**
 * Thrown when parameters inserted from command line are not congruent. 
 * Example: coordinates outside the canvas.
 */
public class CommandWrongParamsException extends CanvasException{

	private static final long serialVersionUID = -8629000422659650854L;

	public CommandWrongParamsException(String message){
		super(message);
	}
	
	public CommandWrongParamsException(String message, Throwable e){
		super(message,e);
	}
}
