package com.ea.examples.canvas.cmd;

import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;

/**
 * Each command must extends this class so must provide at least the name, canvas status, and execute method.
 */
public abstract class Command {
	
	protected String name;
	
	public abstract String getName();

	public abstract void execute() throws CanvasException;
	
	public abstract Canvas getCanvas();
	
}
