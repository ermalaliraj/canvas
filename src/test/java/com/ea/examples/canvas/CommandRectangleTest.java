package com.ea.examples.canvas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.ea.examples.canvas.cmd.CommandRectangle;
import com.ea.examples.canvas.exception.CanvasException;
import com.ea.examples.canvas.exception.CommandWrongParamsException;

public class CommandRectangleTest extends CommandTest {

	protected static final transient Log logger = LogFactory.getLog(CommandRectangleTest.class);
		
	/**
	 * Inserts a rectangle which covers the borders of the canvas. 
	 * Tests the presence of the starts all around the borders and counts the total number of stars.
	 */
	@Test
	public void testHP_CoverBordersWithStars() throws CanvasException {
		int x1 = 1, y1 = 1, x2 = width, y2 = height;
		CommandRectangle cmd = new CommandRectangle(x1, x1, width, height, canvas);
		canvas.insertRectangle(cmd);

		int expected = (x2 - x1) * 2 + (y2 - y1) * 2;
		assertExpected(canvas, expected);
	}

	// Will be a line
	@Test
	public void testCL_X1EqualX2() throws CanvasException {
		int x1 = 3, y1 = 1, x2 = 3, y2 = 4;
		CommandRectangle cmd = new CommandRectangle(x1, y1, x2, y2, canvas);
		canvas = canvas.insertRectangle(cmd);
		
		int expected = (y2 - y1) + 1;
		assertExpected(canvas, expected);
	}

	// Will be a line
	@Test
	public void testCL_Y1EqualY2() throws CanvasException {
		int x1 = 1, y1 = 1, x2 = 14, y2 = 1;
		CommandRectangle cmd = new CommandRectangle(x1, y1, x2, y2, canvas);
		canvas.insertRectangle(cmd);

		int expected = (x2 - x1) + 1;
		assertExpected(canvas, expected);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_X1GreaterThanX2() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(16, 1, 14, 3, canvas);
		canvas.insertRectangle(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_X1LessThanZero() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(-1, 1, 20, 3, canvas);
		canvas.insertRectangle(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_X1EqualZero() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(0, 1, 20, 3, canvas);
		canvas.insertRectangle(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_X2GreaterThanWidth() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(16, 1, width + 1, 3, canvas);
		canvas.insertRectangle(cmd);
	}

	@Test
	public void testCL_X2EqualWidth() throws CanvasException {
		int x1 = 16, y1 = 1, x2 = width, y2 = 4;
		CommandRectangle cmd = new CommandRectangle(x1, y1, x2, y2, canvas);
		canvas.insertRectangle(cmd);
		int expected = (x2 - x1) * 2 + (y2 - y1) * 2;
		assertExpected(canvas, expected);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_YLessThanZero() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(16, -1, 20, 3, canvas);
		canvas.insertRectangle(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_YEqualZero() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(16, 0, 20, 3, canvas);
		canvas.insertRectangle(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_YGreaterThanHeight() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(1, 1, 6, height + 1, canvas);
		canvas.insertRectangle(cmd);
	}

	// vertical lines

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_Y1GreaterThanY2() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(16, 3, 20, 1, canvas);
		canvas.insertRectangle(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_Y1LessThanZero() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(16, -1, 20, 3, canvas);
		canvas.insertRectangle(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_Y1EqualZero() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(16, 0, 20, 3, canvas);
		canvas.insertRectangle(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_Y2GreaterThanHeight() throws CanvasException {
		CommandRectangle cmd = new CommandRectangle(16, 1, 20, height + 1, canvas);
		canvas.insertRectangle(cmd);
	}

	@Test
	public void testCL_Y2EqualHeight() throws CanvasException {
		int x1 = 16, y1 = 1, x2 = 20, y2 = height;
		CommandRectangle cmd = new CommandRectangle(x1, y1, x2, y2, canvas);
		canvas.insertRectangle(cmd);

		int expected = (x2 - x1) * 2 + (y2 - y1) * 2;
		assertExpected(canvas, expected);
	}
}
