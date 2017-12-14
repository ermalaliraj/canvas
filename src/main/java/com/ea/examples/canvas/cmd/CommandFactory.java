package com.ea.examples.canvas.cmd;

import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;

/**
 * Factory for creating Command objects.
 */
public interface CommandFactory {
	
	/**
	 * Factory which creates Command objects starting from the string inserted as input.
	 * The commands handled by the application are the following:
	 * 
	 * C w h         -  Create a new canvas of width w and height h.
	 * L x1 y1 x2 y2 - Create a new line from (x1,y1) to (x2,y2).
	 * R x1 y1 x2 y2 - Create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2).
	 * B x y c       - Fill the entire area connected to (x,y) with "color" c.
	 * H             - Help.
	 * Q             - Quit the program.
	 * 
	 * @param line Input string to parse as command
	 * @param canvas 
	 * @return the command object created from the string input
	 * @throws CanvasException If the string input cannot be converted to a command
	 */
	public Command buildCommand(String line, Canvas canvas) throws CanvasException;
	
}
