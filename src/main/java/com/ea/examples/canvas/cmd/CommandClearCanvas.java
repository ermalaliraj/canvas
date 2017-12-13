package com.ea.examples.canvas.cmd;

import static com.ea.examples.canvas.CanvasCostant.*;

import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;
import com.ea.examples.canvas.exception.CanvasNotYetCreatedException;

public class CommandClearCanvas extends Command{
	
	public CommandClearCanvas() {
		name = CMD_CLEAR;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Canvas execute(Canvas c) throws CanvasException {
		if(c == null){
			throw new CanvasNotYetCreatedException("First create a new Canvas using 'C' command. Insert '"+CMD_HELP+"' for Help.\n");
		}
		return c.clearCanvas();
	}
}
