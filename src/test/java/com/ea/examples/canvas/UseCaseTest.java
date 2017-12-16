package com.ea.examples.canvas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Test;

import com.ea.examples.canvas.cmd.Command;
import com.ea.examples.canvas.cmd.CommandBucketFill;
import com.ea.examples.canvas.cmd.CommandLine;
import com.ea.examples.canvas.cmd.CommandRectangle;
import com.ea.examples.canvas.cmd.factory.CommandFactory;
import com.ea.examples.canvas.cmd.factory.CommandFactoryImpl;
import com.ea.examples.canvas.exception.CanvasException;
import com.ea.examples.canvas.exception.CommandWrongParamsException;

/**
 * In this class are traced use cases which introduced errors in the test phase.
 */
public class UseCaseTest extends CommandTest {

	public static final transient Log logger = LogFactory.getLog(UseCaseTest.class);
	public final static String COLOR = "o";
	

	@After
	public void drillDown() {
		canvas.clearCanvas();
	}

	@Test
	public void testHP_ParseBucketFillCommand() throws CanvasException {
		String cmdLine = "B 2 3 a";
		Command cmd = commandFactory.buildCommand(cmdLine, canvas);

		assertNotNull(cmd);
		assertTrue(cmd instanceof CommandBucketFill);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_ParseBucketFilldWrongColor() throws CanvasException {
		String cmdLine = "B 2 3 add";
		commandFactory.buildCommand(cmdLine, canvas);
	}

	@Test
	public void testHP_1() throws CanvasException {
		CommandLine l = new CommandLine(1, 1, 18, 1, canvas);
		int stars = (18 - 1) + 1;
		canvas.insertLine(l);
		canvas.printCanvas();
		assertExpected(canvas, stars);

		l = new CommandLine(3, 2, 3, 3, canvas);
		stars = stars + 2;
		canvas.insertLine(l);
		canvas.printCanvas();
		assertExpected(canvas,stars);

		CommandRectangle r = new CommandRectangle(3, 2, 8, 3, canvas);
		stars = stars + 10;
		canvas.insertRectangle(r);
		canvas.printCanvas();
		assertExpected(canvas,stars);
	}
	
	@Test
	public void testHP_new() throws CanvasException {
//		CommandLine l = new CommandLine(1, 1, 18, 1, canvas);
//		int stars = (18 - 1) + 1;
//		canvas = canvas.insertLine(l);
//		canvas.printCanvas();
//		assertExpected(canvas, stars);
//
//		// error
//		l = new CommandLine(10, 10, 10, 3, canvas);
//		canvas = canvas.insertLine(l);
//		canvas.printCanvas();
//		
//		
//		l = new CommandLine(3, 2, 3, 3, canvas);
//		stars = stars + 2;
//		canvas.insertLine(l);
//		canvas.printCanvas();
//		assertExpected(canvas,stars);
	}
}