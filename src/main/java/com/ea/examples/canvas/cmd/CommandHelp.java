package com.ea.examples.canvas.cmd;

import static com.ea.examples.canvas.CanvasCostant.*;

import com.ea.examples.canvas.App;
import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;

/**
 * Help command
 */
public class CommandHelp extends Command {

	public CommandHelp() {
		name = CMD_HELP;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Canvas execute(Canvas c) throws CanvasException {
		App.printHelp(); //Leave help commands in App.java for readability purpose. Must be here in a real world app.
		return null;
	}
}
