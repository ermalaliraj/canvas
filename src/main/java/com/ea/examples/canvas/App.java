package com.ea.examples.canvas;

import static com.ea.examples.canvas.CanvasCostant.CMD_HELP;

import java.io.Closeable;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ea.examples.canvas.cmd.Command;
import com.ea.examples.canvas.cmd.CommandFactory;
import com.ea.examples.canvas.cmd.CommandFactoryImpl;
import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;
import com.ea.examples.canvas.exception.QuitGameException;

/**
 * Main class for starting the application. Tests the application in an interactive mode.
 */
public class App {

	protected static final transient Log logger = LogFactory.getLog(App.class);
	private Scanner sc;
	private CommandFactory commandFactory;
	private Command command;
	
	public App(){
		commandFactory = new CommandFactoryImpl();
		sc = new Scanner(System.in);
	}

	public static void main(String[] args) {
		App app = new App();
		app.run();
	}

	private void run() {
		boolean isExit = false;
		Canvas canvas = null;
		do {
			try {
				System.out.print("Insert command: ");
				String line = sc.nextLine();
				command = commandFactory.buildCommand(line, canvas);
				command.execute();
				canvas = command.getCanvas();
				canvas.printCanvas();
			} catch (QuitGameException e) {
				isExit = true;
				System.out.println("Thank you for tried Canvas App! Bye...");
			} catch (CanvasException e) {
				logger.error("CanvasException: " + e.getMessage(), e);
				System.out.println("Canvas Exception: " + e.getMessage());
				System.out.println("Insert '" + CMD_HELP + "' for Help.\n");
			} catch (Exception e) {
				logger.error("General Exception: " + e.getMessage(), e);
				System.out.println("General Exception: " + e.getMessage());
				System.out.println("Insert '" + CMD_HELP + "' for Help.\n");
			}
		} while (!isExit);

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
			System.out.println("Cannot close the console! e: " + e.getMessage());
		}
	}

}
