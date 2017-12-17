package com.ea.examples.canvas;

import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.ea.examples.canvas.cmd.CommandLine;
import com.ea.examples.canvas.exception.CanvasException;
import com.ea.examples.canvas.exception.CommandNotYetImplementedException;
import com.ea.examples.canvas.exception.CommandWrongParamsException;
import com.ea.examples.canvas.util.CanvasCostant;

public class CommandLineTest extends CommandTest {

	protected static final transient Log logger = LogFactory.getLog(CommandLineTest.class);

	@Test
	public void testHP_leftBorder() throws CanvasException {
		// left column
		CommandLine cmd = new CommandLine(1, 1, 1, height, canvas);
		canvas.insertLine(cmd);

		String mat[][] = canvas.getMatrix();
		int countPoints = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				// check if first column contains only 'x' elements
				if (j == 0) {
					assert (mat[i][j].equals(CanvasCostant.PIXEL));
				}
				// count all the 'x' present in the matrix.
				if (mat[i][j].equals(CanvasCostant.PIXEL)) {
					countPoints++;
				}
			}
		}
		assertEquals(height, countPoints);
	}

	@Test
	public void testHP_upperBorder() throws CanvasException {
		CommandLine cmd = new CommandLine(1, 1, width, 1, canvas);
		canvas.insertLine(cmd);

		String mat[][] = canvas.getMatrix();
		int countPoints = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				// check if first row contains only 'x' elements
				if (i == 0) {
					assert (mat[i][j].equals(CanvasCostant.PIXEL));
				}
				// count all the 'x' present in the matrix.
				if (mat[i][j].equals(CanvasCostant.PIXEL)) {
					countPoints++;
				}
			}
		}
		assertEquals(width, countPoints);
	}

	@Test
	public void testHP_rightBorder() throws CanvasException {
		// right column
		CommandLine cmd = new CommandLine(width, 1, width, height, canvas);
		canvas.insertLine(cmd);

		String mat[][] = canvas.getMatrix();
		int countPoints = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				// check if last column contains only 'x' elements
				if (j == mat[i].length - 1) {
					assert (mat[i][j].equals(CanvasCostant.PIXEL));
				}
				// count all the 'x' present in the matrix.
				if (mat[i][j].equals(CanvasCostant.PIXEL)) {
					countPoints++;
				}
			}
		}
		assertEquals(height, countPoints);
	}

	@Test
	public void testHP_bottomBorder() throws CanvasException {
		// bottom row
		CommandLine cmd = new CommandLine(1, height, width, height, canvas);
		canvas.insertLine(cmd);

		String mat[][] = canvas.getMatrix();
		int countPoints = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				// check if last row contains only 'x' elements
				if (i == mat.length - 1) {
					assert (mat[i][j].equals(CanvasCostant.PIXEL));
				}
				// count all the 'x' present in the matrix.
				if (mat[i][j].equals(CanvasCostant.PIXEL)) {
					countPoints++;
				}
			}
		}
		assertEquals(width, countPoints);
	}

	/**
	 * Inserts 4 lines which covers the borders of the canvas. First row, last row, first column and the last column. 
	 * Tests the presence of the 'x' all around the borders and counts the total number of 'x'.
	 */
	@Test
	public void testHP_CoverBordersWithLines() throws CanvasException {
		// left column
		CommandLine cmd = new CommandLine(1, 1, 1, height, canvas);
		canvas.insertLine(cmd);

		// upper row
		cmd = new CommandLine(1, 1, width, 1, canvas);
		canvas.insertLine(cmd);

		// right column
		cmd = new CommandLine(width, 1, width, height, canvas);
		canvas.insertLine(cmd);

		// bottom row
		cmd = new CommandLine(1, height, width, height, canvas);
		canvas.insertLine(cmd);

		// Test the created canvas
		String mat[][] = canvas.getMatrix();
		int countPoints = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				// check if first and last row contains only 'x' elements
				if (i == 0 || i == mat.length - 1) {
					assert (mat[i][j].equals(CanvasCostant.PIXEL));
				}

				// check if first and last column contains only 'x' elements
				if (j == 0 || j == mat[i].length - 1) {
					assert (mat[i][j].equals(CanvasCostant.PIXEL));
				}

				// count all the 'x' present in the matrix.
				if (mat[i][j].equals(CanvasCostant.PIXEL)) {
					countPoints++;
				}
			}
		}

		int mustBe = (width * 2) + (height * 2) - 4; // 2 rows + 2 columns - 4 corners
		assertEquals(mustBe, countPoints);
	}

	@Test(expected = CommandNotYetImplementedException.class)
	public void testEX_NotVerticalLine() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 2, 7, 4, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_X1GreaterThanX2() throws CanvasException {
		CommandLine cmd = new CommandLine(4, 2, 1, 2, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_X1LessThanZero() throws CanvasException {
		CommandLine cmd = new CommandLine(-2, 2, 6, 2, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_X1EqualZero() throws CanvasException {
		CommandLine cmd = new CommandLine(0, 2, 6, 2, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_X2GreaterThanWidth() throws CanvasException {
		CommandLine cmd = new CommandLine(1, 2, width + 1, 2, canvas);
		canvas.insertLine(cmd);
	}

	@Test
	public void testCL_X2EqualWidth() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 2, width, 2, canvas);
		canvas.insertLine(cmd);
		assertExpected(canvas, (width - 6) + 1);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_YLessThanZero() throws CanvasException {
		CommandLine cmd = new CommandLine(1, -2, 6, -2, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_YEqualZero() throws CanvasException {
		CommandLine cmd = new CommandLine(1, 0, 6, 0, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_YGreaterThanHeight() throws CanvasException {
		CommandLine cmd = new CommandLine(1, height + 1, 6, height + 1, canvas);
		canvas.insertLine(cmd);
	}

	// vertical lines

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_Y1GreaterThanY2() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 4, 6, 3, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_Y1LessThanZero() throws CanvasException {
		CommandLine cmd = new CommandLine(6, -1, 6, 4, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_Y1EqualZero() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 0, 6, 4, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_Y2GreaterThanHeight() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 2, 6, height + 1, canvas);
		canvas.insertLine(cmd);
	}

	@Test
	public void testCL_Y2EqualHeight() throws CanvasException {
		CommandLine cmd = new CommandLine(6, 2, 6, height, canvas);
		canvas.insertLine(cmd);
		assertExpected(canvas, (height - 2) + 1);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_XLessThanZero() throws CanvasException {
		CommandLine cmd = new CommandLine(-1, 3, -1, 4, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_XEqualZero() throws CanvasException {
		CommandLine cmd = new CommandLine(0, 3, 0, 4, canvas);
		canvas.insertLine(cmd);
	}

	@Test(expected = CommandWrongParamsException.class)
	public void testEX_XGreaterThanWidth() throws CanvasException {
		CommandLine cmd = new CommandLine(width + 1, 3, width + 1, 4, canvas);
		canvas.insertLine(cmd);
	}

	@Test
	public void testCL_XEqualWidth() throws CanvasException {
		CommandLine cmd = new CommandLine(width, 3, width, 4, canvas);
		canvas.insertLine(cmd);
		assertExpected(canvas, (4 - 3) + 1);
	}

}
