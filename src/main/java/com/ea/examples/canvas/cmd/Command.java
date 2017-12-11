package com.ea.examples.canvas.cmd;

import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;

/**
 * Each command must extends this class so must provide at least the name.
 */
public abstract class Command {
	
	protected String name;

	public abstract String getName();

	public abstract Canvas execute(Canvas c) throws CanvasException;
	
}
