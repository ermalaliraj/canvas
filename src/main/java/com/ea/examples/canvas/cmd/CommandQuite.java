package com.ea.examples.canvas.cmd;

import static com.ea.examples.canvas.CanvasCostant.CMD_QUITE;

import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.exception.CanvasException;
import com.ea.examples.exception.QuitGameException;

/**
 * Command used for quite the canvas.
 */
public class CommandQuite extends Command{
		
	public CommandQuite() {
		name = CMD_QUITE;
	}

	public String getName() {
		return name;
	}

	@Override
	public Canvas execute(Canvas c) throws CanvasException {
		throw new QuitGameException("Quiting the game!");
	}

}