package com.ea.examples.canvas;

import static com.ea.examples.canvas.CanvasCostant.CMD_HELP;

import java.io.Closeable;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ea.examples.canvas.cmd.Command;
import com.ea.examples.canvas.cmd.CommandFactory;
import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;
import com.ea.examples.canvas.exception.QuitGameException;

/**
 * Main class for starting the application.
 * Tests the application in an interactive mode.
 */
public class App {

	protected static final transient Log logger = LogFactory.getLog(App.class);

	public static void printHelp() {
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

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		Canvas canvas = null;
		boolean isExit = false;
		
		do{
			try {
				System.out.print("Insert command: ");
				String line = sc.nextLine();
				Command command = CommandFactory.buildCommand(line);			    
				canvas = command.execute(canvas);
				canvas.printCanvas();
			} catch (QuitGameException e) {
				isExit = true;
				System.out.println("Thank you for tried Canvas App! Bye...");
			} catch (CanvasException e) {
				logger.error("CanvasException: " + e.getMessage(), e);
				System.out.println("Canvas Exception: " + e.getMessage());
				System.out.println("Insert '"+CMD_HELP+"' for Help.\n");
			} catch (Exception e) {
				logger.error("General Exception: " + e.getMessage(), e);
				System.out.println("General Exception: " + e.getMessage());
				System.out.println("Insert '"+CMD_HELP+"' for Help.\n");
			}			
		}while(!isExit);
		
		closeResource(sc);
	}

	/**
	 * Close the Scanner
	 */
	private static void closeResource(Closeable sc) {
		try {
			sc.close();
		} catch (Exception e) {
			logger.error("Error closing the console: " + e.getMessage(), e);
			System.out.println("Cannot close the console! e: "+e.getMessage());
		}
	}

}
