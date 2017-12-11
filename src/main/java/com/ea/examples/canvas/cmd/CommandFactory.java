package com.ea.examples.canvas.cmd;

import static com.ea.examples.canvas.CanvasCostant.*;

import com.ea.examples.exception.CanvasException;
import com.ea.examples.exception.CommandNotFoundException;
import com.ea.examples.exception.CommandWrongParamsException;

/**
 * Factory for creating Command objects.
 */
public class CommandFactory {
	 
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
	 * @param line string to parse as command
	 * @return the command represented by the string
	 * @throws CanvasException
	 */
	public static Command buildCommand(String line) throws CanvasException {

		Command cmd = null;
		line = line.trim();
		String sp[] = line.split("\\s+");//one or more space
		if (sp.length == 0) {
			throw new CommandWrongParamsException("Wrong command! Try one of the following: C w h; L x1 y1 x2 y2; R x1 y1 x2 y2; B x y c or Q for quit.");
		}
		
		if (sp[0].equalsIgnoreCase(CMD_CREATE_CANVAS)) {
			// C w h
			if (sp.length != 3) {
				throw new CommandWrongParamsException("Wrong Parameters! For creating new canvas try 'C w h',  example: 'C 40 20'");
			} else {
				try {
					int w = Integer.parseInt(sp[1]);
					int h = Integer.parseInt(sp[2]);
					cmd = new CommandCreateNewCanvas(w, h);
				} catch (Exception e) {
					throw new CommandWrongParamsException("Weight and Height must be numbers! Example: 'C 40 20'");
				}
			}
		} else if (sp[0].equalsIgnoreCase(CMD_LINE)) {
			// L x1 y1 x2 y2
			if (sp.length != 5) {
				throw new CommandNotFoundException("Wrong Parameters! For drawing a line try 'L x1 y1 x2 y2',  example: 'L 1 2 6 2'");
			} else {
				try {
					int x1 = Integer.parseInt(sp[1]);
					int y1 = Integer.parseInt(sp[2]);
					int x2 = Integer.parseInt(sp[3]);
					int y2 = Integer.parseInt(sp[4]);
					cmd = new CommandLine(x1, y1, x2, y2);
				} catch (Exception e) {
					throw new CommandWrongParamsException("Coordinates must be numbers! Example: 'L 1 1 9 1'");
				}
			}
		} else if (sp[0].equalsIgnoreCase(CMD_RECTANGLE)) {
			// R x1 y1 x2 y2
			if (sp.length != 5) {
				throw new CommandNotFoundException("Wrong Parameters! For drawing a Rectangle try 'R x1 y1 x2 y2',  example: 'R 1 1 6 3'");
			} else {
				try {
					int x1 = Integer.parseInt(sp[1]);
					int y1 = Integer.parseInt(sp[2]);
					int x2 = Integer.parseInt(sp[3]);
					int y2 = Integer.parseInt(sp[4]);
					cmd = new CommandRectangle(x1, y1, x2, y2);
				} catch (Exception e) {
					throw new CommandWrongParamsException("Coordinates must be numbers! Example: 'R 1 1 9 4'");
				}
			}
		} else if (sp[0].equalsIgnoreCase(CMD_FILL)) {
			// B x y c
			if (sp.length != 4) {
				throw new CommandNotFoundException("Wrong Parameters! For filling an area try 'B x1 y1 color',  example: 'B 2 3 c'");
			} else {
				int x = -1;
				int y = -1;
				try{
					x = Integer.parseInt(sp[1]);
					y = Integer.parseInt(sp[2]);
				} catch (Exception e) { 
					throw new CommandWrongParamsException("Coordinates must be numbers! Example: 'B 2 3 c'");
				}
				
				String color = sp[3];
				if(color.length() > 1){
					throw new CommandWrongParamsException("Wrong Parameters! Color must be a single character. Example: 'B 2 3 c'");
				}
				cmd = new CommandBucketFill(x, y, color);
			}
		} else if (sp[0].equalsIgnoreCase(CMD_QUITE)) {
			cmd = new CommandQuite();
		} else if (sp[0].equalsIgnoreCase(CMD_HELP)) {
			cmd = new CommandHelp();
		} else if (sp[0].equalsIgnoreCase(CMD_CLEAR)) {
			cmd = new CommandClearCanvas();
		} else {
			throw new CommandNotFoundException("Command not found! The line must start with letter: 'C', 'L', 'R, 'B' or 'Q' for exit.");
		}

		return cmd;
	}

}
