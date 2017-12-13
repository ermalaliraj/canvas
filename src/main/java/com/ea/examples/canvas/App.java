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

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		Canvas canvas = null;
		boolean isExit = false;
		Command command = null;
		
		do{
			try {
				System.out.print("Insert command: ");
				String line = sc.nextLine();
				command = CommandFactory.buildCommand(line, canvas);			    
				canvas = command.execute();
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
