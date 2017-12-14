package com.ea.examples.canvas.cmd;

import static com.ea.examples.canvas.CanvasCostant.CMD_HELP;

import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;

/**
 * Help command
 */
public class CommandHelp extends Command {
	
	private Canvas canvas;

	public CommandHelp() {
		name = CMD_HELP;
	}
	public CommandHelp(Canvas canvas) {
		this();
		this.canvas = canvas;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Canvas execute() throws CanvasException {
		printHelp();
		return Canvas.getCanvas();
	}
	
	@Override
	public Canvas getCanvas() {
		return canvas;
	}
	
	private void printHelp() {
		System.out.println("");
		System.out.println("************ OPTIONS *************");
		System.out.println("C w h - Create a new canvas of width w and height h.");
		System.out.println("L x1 y1 x2 y2 - Create a new line from (x1,y1) to (x2,y2).");
		System.out.println("R x1 y1 x2 y2 - Create a new rectangle, whose upper left corner is (x1,y1) 	and lower right corner is (x2,y2).");
		System.out.println("B x y c - Fill the area connected to (x,y) with 'color' c.");
		System.out.println("H - Help.");
		System.out.println("Q - Quit the program.");
		System.out.println("**********************************");
		System.out.println();
	}

}
