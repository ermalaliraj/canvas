package com.ea.examples.canvas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ea.examples.canvas.cmd.Command;
import com.ea.examples.canvas.cmd.CommandBucketFill;
import com.ea.examples.canvas.cmd.CommandFactory;
import com.ea.examples.canvas.cmd.CommandLine;
import com.ea.examples.canvas.cmd.CommandRectangle;
import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.exception.CanvasException;
import com.ea.examples.exception.CommandWrongParamsException;

/** 
 * In this class are traced use cases which introduced errors in the test phase.
 */
public class UseCaseTest {

	public static final transient Log logger = LogFactory.getLog(UseCaseTest.class);
	public final static String COLOR = "o";
	
	Canvas canvas;
	int width = 20;
	int height = 4;
	
	@Before
	public void setUp() {
		canvas = Canvas.getCanvas(width, height);
	}

	@After
	public void drillDown() {
		canvas.printCanvas();
		canvas.clearCanvas();
	}	
	
	@Test
	public void testHP_ParseBucketFillCommand() throws CanvasException {
		String cmdLine = "B 2 3 a";
		Command cmd = CommandFactory.buildCommand(cmdLine);
		
		assertNotNull(cmd);
		assertTrue(cmd instanceof CommandBucketFill);		
	}
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_ParseBucketFilldWrongColor() throws CanvasException {
		String cmdLine = "B 2 3 add";
		CommandFactory.buildCommand(cmdLine);
	}
	

	@Test
	public void testHP_1() throws CanvasException {
		Canvas c = Canvas.getCanvas(20,  4);
		CommandLine l = new CommandLine(1, 1, 18, 1);
		c.insertLine(l);
		c.printCanvas();
		
		l = new CommandLine(3, 2, 3, 3);
		c.insertLine(l);
		c.printCanvas();
		
		CommandRectangle r = new CommandRectangle(3, 2, 8, 3);
		c.insertRectangle(r);
		c.printCanvas();
	}
}