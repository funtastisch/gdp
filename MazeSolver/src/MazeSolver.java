import gdp.stdlib.StdDraw;

public class MazeSolver {

	public static boolean solve(int[][] maze, int row, int col) {
		
		// if goal is found
		if (row == maze.length - 1 && col == 0 && maze [row][col] == 3) {
			maze[row][col] = 2;
			return true;
		}
		
		// check if current position is valid
		if (isOk(maze, row, col)) {
			// check if current position was already used in path
			if(row != 0 && col != maze.length - 1 && maze[row][col] == 2)
				return false;
			
			// set path
			maze[row][col] = 2;
			
			// move left
			boolean leftPossible = solve(maze, row, col - 1);
			if (leftPossible)
				return true;
			
			// move down
			boolean rightPossible = solve(maze, row + 1, col);
			if (rightPossible)
				return true;
			
			// remove step of path
			maze[row][col] = 1;
			return false;
		}
		return false;
	}

	public static void draw(int[][] maze) {
		StdDraw.setCanvasSize(800, 800);

		int length = maze.length;

		StdDraw.setXscale(0, length);
		StdDraw.setYscale(length, 0);

		for (int y = 0; y < length; y++) {
			for (int x = 0; x < length; x++) {
				if (maze[y][x] == 0) {
					StdDraw.setPenColor(StdDraw.GRAY);
					StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
				}
				if (maze[y][x] == 2) {
					StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
				}
				if (maze[y][x] == 3) {
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
				}
			}
		}
		drawGrid(length);
	}
	
	static void drawGrid(int length) {
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i <= length; i++) { // draw all parallels to y
			StdDraw.line(i, 0, i, length);
		}
		for (int i = 0; i <= length; i++) { // draw all parallels to x
			StdDraw.line(0, i, length, i);
		}
	}

	static boolean isOk(int[][] maze, int row, int col) {
		if (row > maze.length - 1 || col < 0)
			return false;
		else if (maze[row][col] == 0)
			return false;
		return true;
	}

}