package com.ea.examples.canvas.exception;

/**
 * General Canvas Exception. All application exceptions extends this class.
 */
public class QuitGameException extends CanvasException{

	private static final long serialVersionUID = 6056719373037617209L;

	public QuitGameException(String message){
		super(message);
	}
	
	public QuitGameException(String message, Throwable e){
		super(message,e);
	}
}
