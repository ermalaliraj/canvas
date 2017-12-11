package com.ea.examples.canvas;

import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ea.examples.canvas.cmd.CommandBucketFill;
import com.ea.examples.canvas.cmd.CommandRectangle;
import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;
import com.ea.examples.canvas.exception.CommandWrongParamsException;

/**
 * Class to test the BucketFiller Command. 
 * All the tests start with a Canvas already initialized to the following state (ignore numbers): 
 	----------------------
	|xxxxxxxx            |
	|x      x      2     |
	|x  1   x            |
	|x      xxxxxxxx     |
	|xxxxxxxx  3   x     |
	----------------------
 */
public class CommandBucketFillTest {

	protected static final transient Log logger = LogFactory.getLog(CommandBucketFillTest.class);
	
	public final static String COLOR = "o";
	Canvas canvas;
	int width = 20;
	int height = 5;
	
	/**
	 * Initialize the canvas used for all tests.
	 */
	@Before
	public void setUp(){
		canvas = Canvas.getCanvas(width, height);
		
		try {
			CommandRectangle cmd = new CommandRectangle(1, 1, 8, height);
			canvas.insertRectangle(cmd);

			cmd = new CommandRectangle(8, height-1, 15, height-1);
			canvas.insertRectangle(cmd);
			
			cmd = new CommandRectangle(15, height-1, 15, height);
			canvas.insertRectangle(cmd);
		} catch (Exception e) {
			logger.error("Error initializing the Canvas for testing FillBucket Command");
		}
	}
	
	@After
	public void drillDown() {
		canvas.printCanvas();
		canvas.clearCanvas();
	}	
	
	/**
	 * Check correctness of the filler when starting point is as follow: 
	 	----------------------
		|xxxxxxxxO           |
		|x      x            |
		|x      x            |
		|x      xxxxxxxx     |
		|xxxxxxxx      x     |
		----------------------
	 * Count and compare the number of colored pixels.
	 */
	@Test
	public void testHP_FillArea2LeftUpperStartingPoint() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(9, 1, COLOR);
		canvas.fillBucket(cmd2);
		//c.printStatusCanvas();
		
		int expected = (12*3)+(5*2);
		assertExpected(canvas, COLOR, expected);
	}
	
	/**
	 * Check correctness of the filler when starting point is as follow:  
	 	----------------------
		|xxxxxxxx           O|
		|x      x            |
		|x      x            |
		|x      xxxxxxxx     |
		|xxxxxxxx      x     |
		----------------------
	 * Count and compare the number of colored pixels.
	 */
	@Test
	public void testHP_FillArea2RightUpperStartingPoint() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(20, 1, COLOR);
		canvas.fillBucket(cmd2);
		//c.printStatusCanvas();
		
		int expected = (12*3)+(5*2);
		assertExpected(canvas, COLOR, expected);
	}
	
	/**
	 * Check correctness of the filler when starting point is as follow:  
	 	----------------------
		|xxxxxxxx            |
		|x      x            |
		|x      xO           |
		|x      xxxxxxxx     |
		|xxxxxxxx      x     |
		----------------------
	 * Count and compare the number of colored pixels.
	 */
	@Test
	public void testHP_FillArea2LeftDownStartingPoint() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(9, 3, COLOR);
		canvas.fillBucket(cmd2);
		//c.printStatusCanvas();
		
		int expected = (12*3)+(5*2);
		assertExpected(canvas, COLOR, expected);
	}
	
	/**
	 * Check correctness of the filler when starting point is as follow:  
	 	----------------------
		|xxxxxxxx            |
		|x      x            |
		|x      x            |
		|x      xxxxxxxxO    |
		|xxxxxxxx      x     |
		----------------------
	 * Count and compare the number of colored pixels.
	 */
	@Test
	public void testHP_FillArea2EdgeStartingPoint() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(16, 4, COLOR);
		canvas.fillBucket(cmd2);
		//c.printStatusCanvas();
		
		int expected = (12*3)+(5*2);
		assertExpected(canvas, COLOR, expected);
	}
	
	/**
	 * Check correctness of the filler when starting point is as follow:  
	 	----------------------
		|xxxxxxxx            |
		|x      x            |
		|x      x            |
		|x      xxxxxxxx     |
		|xxxxxxxx      x    O|
		----------------------
	 * Count and compare the number of colored pixels.
	 */
	@Test
	public void testHP_FillArea2RightDownStartingPoint() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(20, 5, COLOR);
		canvas.fillBucket(cmd2);
		//c.printStatusCanvas();
		
		int expected = (12*3)+(5*2);
		assertExpected(canvas, COLOR, expected);
	}

	/**
	 * Check correctness (Area 3) of the filler when starting point is as follow:  
	 	----------------------
		|xxxxxxxx            |
		|x      x            |
		|x      x            |
		|x      xxxxxxxx     |
		|xxxxxxxx  O   x     |
		----------------------
	 * Count and compare the number of colored pixels.
	 */
	@Test
	public void testHP_FillArea3() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(11, 5, COLOR);
		canvas.fillBucket(cmd2);
		//c.printStatusCanvas();
		
		int expected = 6;
		assertExpected(canvas, COLOR, expected);
	}
	
	/**
	 * Check correctness (Area 1) of the filler when starting point is as follow:  
	 	----------------------
		|xxxxxxxx            |
		|x      x            |
		|x   O  x            |
		|x      xxxxxxxx     |
		|xxxxxxxx      x     |
		----------------------
	 * Count and compare the number of colored pixels.
	 */
	@Test
	public void testHP_FillArea1() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(5, 3, COLOR);
		canvas.fillBucket(cmd2);
		//c.printStatusCanvas();
		
		int expected = 6*3;
		assertExpected(canvas, COLOR, expected);
	}	
	
	/**
	 * Counts the 'pixels/elements' present in the canvas and compare it with the expected value.
	 */
	private void assertExpected(Canvas c, String elementToCount, int expected) throws CanvasException {
		String mat[][] = c.getMatrix();	
		int found = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if(mat[i][j].equals(elementToCount)){
					found ++;	
				}
			}
		}

		assertEquals(expected, found);
	}
	
	//Check exceptions
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_XZero() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(0, 3, COLOR);
		canvas.fillBucket(cmd2);
	}
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_XLessThanZero() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(-1, 3, COLOR);
		canvas.fillBucket(cmd2);
	}
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_YZero() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(7, 0, COLOR);
		canvas.fillBucket(cmd2);
	}
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_YLessThanZero() throws CanvasException {
		CommandBucketFill cmd2 = new CommandBucketFill(7, -1, COLOR);
		canvas.fillBucket(cmd2);
	}
}
