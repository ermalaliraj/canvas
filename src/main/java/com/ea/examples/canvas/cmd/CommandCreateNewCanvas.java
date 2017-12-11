package com.ea.examples.canvas.cmd;

import static com.ea.examples.canvas.CanvasCostant.CMD_CREATE_CANVAS;

import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;

/**
 * Command used by the canvas for creating a new Canvas
 */
public class CommandCreateNewCanvas extends Command{
	
	private int weight;
	private int height;
	
	public CommandCreateNewCanvas(int w, int h) {
		this();
		weight = w;
		height = h;
	}
	
	public Canvas execute(Canvas c) throws CanvasException {		
		return Canvas.getCanvas(weight, height);
	}
	
	public CommandCreateNewCanvas() {
		name = CMD_CREATE_CANVAS;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}
	
}
