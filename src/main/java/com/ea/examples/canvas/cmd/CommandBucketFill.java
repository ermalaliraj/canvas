package com.ea.examples.canvas.cmd;

import static com.ea.examples.canvas.util.CanvasCostant.CMD_FILL;
import static com.ea.examples.canvas.util.CanvasCostant.CMD_HELP;

import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;
import com.ea.examples.canvas.exception.CanvasNotYetCreatedException;

/**
 * Command used by the Canvas for filling the area around a given coordinate.
 * Same as "bucket fill" tool in paint programs.
 */
public class CommandBucketFill extends Command{

	private Canvas canvas;
	private int x;
	private int y;
	private String color;
	
	private CommandBucketFill() {
		name = CMD_FILL;
	}
	
	public CommandBucketFill(int x, int y, String color, Canvas canvas) {
		this();
		this.x = x;
		this.y = y;
		this.color = color;
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
		canvas.fillBucket(this);
	}
	
	@Override
	public Canvas getCanvas() {
		return canvas;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getColor() {
		return color;
	}
	
}