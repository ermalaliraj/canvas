package com.ea.examples.canvas;

import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ea.examples.canvas.CanvasCostant;
import com.ea.examples.canvas.cmd.CommandLine;
import com.ea.examples.canvas.core.Canvas;
import com.ea.examples.canvas.exception.CanvasException;
import com.ea.examples.canvas.exception.CommandNotYetImplementedException;
import com.ea.examples.canvas.exception.CommandWrongParamsException;

public class CommandLineTest {

	protected static final transient Log logger = LogFactory.getLog(CommandLineTest.class);
	
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
	
	/**
	 * Inserts 4 lines which covers the borders of the canvas.
	 * First row, last row, first column and the last column.
	 * Tests the presence of the 'x' all around the borders and counts the total number of 'x'.
	 */
	@Test
	public void testHP_CoverBordersWithLines() throws CanvasException {
		// left column 
		CommandLine cmd = new CommandLine(1, 1, 1, height);
		canvas.insertLine(cmd);
		
		//upper row
		cmd = new CommandLine(1, 1, width, 1);
		canvas.insertLine(cmd);
		
		//right column
		cmd = new CommandLine(width, 1, width, height);
		canvas.insertLine(cmd);
		
		//bottom row
		cmd = new CommandLine(1, height, width, height);
		canvas.insertLine(cmd);
		
		//Test the created canvas
		String mat[][] = canvas.getMatrix();	
		int countPoints = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				//check if first and last row contains only 'x' elements 
				if(i==0 || i==mat.length-1){
					assert(mat[i][j].equals(CanvasCostant.PIXEL));
				}
				
				//check if first and last column contains only 'x' elements
				if(j==0 || j==mat[i].length-1){
					assert(mat[i][j].equals(CanvasCostant.PIXEL));
				}
				
				//count all the 'x' present in the matrix.
				if(mat[i][j].equals(CanvasCostant.PIXEL)){
					countPoints ++;	
				}
			}
		}
		
		int mustBe = (width*2) + (height*2) - 4; // 2 rows + 2 columns - 4 corners
		assertEquals( mustBe, countPoints);
	}

	@Test(expected=CommandNotYetImplementedException.class)
	public void testEX_NotVerticalLine() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 2, 7, 4);
		canvas.insertLine(cmd);
	}

	@Test(expected=CommandWrongParamsException.class)
	public void testEX_X1GreaterThanX2() throws CanvasException {
		CommandLine cmd = new CommandLine(4, 2, 1, 2);
		canvas.insertLine(cmd);
	}
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_X1LessThanZero() throws CanvasException {
		CommandLine cmd = new CommandLine(-2, 2, 6, 2);
		canvas.insertLine(cmd);
	}
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_X1EqualZero() throws CanvasException {
		CommandLine cmd = new CommandLine(0, 2, 6, 2);
		canvas.insertLine(cmd);
	}
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_X2GreaterThanWidth() throws CanvasException {
		CommandLine cmd = new CommandLine(1, 2, width+1, 2);
		canvas.insertLine(cmd);
	}

	@Test
	public void testCL_X2EqualWidth() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 2, width, 2);
		canvas.insertLine(cmd);
	}
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_YLessThanZero() throws CanvasException {
		CommandLine cmd = new CommandLine(1, -2, 6, -2);
		canvas.insertLine(cmd);
	}

	@Test(expected=CommandWrongParamsException.class)
	public void testEX_YEqualZero() throws CanvasException {
		CommandLine cmd = new CommandLine(1, 0, 6, 0);
		canvas.insertLine(cmd);
	}
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_YGreaterThanHeight() throws CanvasException {
		CommandLine cmd = new CommandLine(1, height+1, 6, height+1);
		canvas.insertLine(cmd);
	}
	
	//vertical lines
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_Y1GreaterThanY2() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 4, 6, 3);
		canvas.insertLine(cmd);
	}
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_Y1LessThanZero() throws CanvasException {
		CommandLine cmd = new CommandLine(6, -1, 6, 4);
		canvas.insertLine(cmd);
	}

	@Test(expected=CommandWrongParamsException.class)
	public void testEX_Y1EqualZero() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 0, 6, 4);
		canvas.insertLine(cmd);
	}
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_Y2GreaterThanHeight() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 2, 6, height+1);
		canvas.insertLine(cmd);
	}
	
	@Test
	public void testCL_Y2EqualHeight() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 2, 6, height);
		canvas.insertLine(cmd);
	}

	@Test(expected=CommandWrongParamsException.class)
	public void testEX_XLessThanZero() throws CanvasException {
		CommandLine cmd = new CommandLine(-1, 3, -1, 4);
		canvas.insertLine(cmd);
	}
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_XEqualZero() throws CanvasException {
		CommandLine cmd = new CommandLine(0, 3, 0, 4);
		canvas.insertLine(cmd);
	}
	
	@Test(expected=CommandWrongParamsException.class)
	public void testEX_XGreaterThanWidth() throws CanvasException {
		CommandLine cmd = new CommandLine(width+1, 3, width+1, 4);
		canvas.insertLine(cmd);
	}
	
	@Test
	public void testCL_XEqualWidth() throws CanvasException {
		CommandLine cmd = new CommandLine(width, 3, width, 4);
		canvas.insertLine(cmd);
	}
	
}
