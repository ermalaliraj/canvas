package com.ea.examples.canvas.cmd;

import static com.ea.examples.canvas.CanvasCostant.*;

import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;
import com.ea.examples.canvas.exception.CanvasNotYetCreatedException;

public class CommandClearCanvas extends Command {
	
	private Canvas canvas;
	
	private CommandClearCanvas() {
		name = CMD_CLEAR;
	}
	public CommandClearCanvas(Canvas canvas) {
		this();
		this.canvas = canvas;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void execute() throws CanvasException {
		if(canvas == null){
			throw new CanvasNotYetCreatedException("First create a new Canvas using 'C' command. Insert '"+CMD_HELP+"' for Help.\n");
		}
		canvas.clearCanvas();
	}
	
	@Override
	public Canvas getCanvas() {
		return canvas;
	}
}
