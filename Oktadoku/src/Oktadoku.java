import gdp.stdlib.*;

public class Oktadoku {

	public enum Variante {
		normal, mitDiagonalen
	};

	private Variante v;
	private int[][] oktadoku;
	private final int SIZE = 8;

	/**
	 * Constructs an Oktadoku
	 * @param var Variant that the oktadoku applies to
	 */
	public Oktadoku(Variante var) {
		this.v = var;
	}
	/**
	 * Reads Oktadoku inputs from StdIn
	 */
	public void read() {
		oktadoku = new int[8][8];
		for (int row = 0; row <= 7; row++) {
			for (int col = 0; col <= 7; col++) {
				char nextChar = StdIn.readChar();
				int number = Character.getNumericValue(nextChar);
				if (number != -1)
					oktadoku[row][col] = number;
			}
			StdIn.readChar();
		}
	}
	/**
	 * Prints out the Oktadoku
	 */
	public void write() {
		StdOut.print("Oktadoku");
		if (v == Variante.mitDiagonalen)
			StdOut.print(" mit Diagonalen");
		StdOut.print("\n");

		for (int row = 0; row <= 7; row++) {
			if (row == 0 || row == 4)
				StdOut.print("+-----+-----+-----+-----+\n");
			for (int col = 0; col <= 7; col++) {

				if (col == 0 || col == 2 || col == 4 || col == 6)
					StdOut.print("| ");
				if (oktadoku[row][col] != 0)
					StdOut.print(oktadoku[row][col] + " ");
				else
					StdOut.print("  ");
			}
			StdOut.print("|\n");
		}
		StdOut.print("+-----+-----+-----+-----+\n");
	}
	/**
	 * Checks if Oktadoku applies to its rules
	 * @return boolean
	 */
	public boolean check() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (oktadoku[row][col] != 0) {
					int n = oktadoku[row][col];
					oktadoku[row][col] = 0;
					if (!isCellValid(row, col, n))
						return false;
					oktadoku[row][col] = n;
				}
			}
		}
		return true;
	}
	/**
	 * Prints out solved Oktadoku if solvable
	 */
	public void solve() {
		if (solvable())
			write();
		else
			StdOut.println("nicht loesbar :-(");
	}
	/**
	 * Returns if Oktadoku is solvable. If solvable it also solves it.
	 * @return boolean 
	 */
	private boolean solvable() {
		// go through all cells in Oktadoku
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				// check if there is space
				if (oktadoku[row][col] == 0) {
					// iterate through all n
					for (int n = 1; n <= SIZE; n++) {
						// check if n can be placed
						if (isCellValid(row, col, n)) {
							// place n
							oktadoku[row][col] = n;
							// recursive call to try new n at next free spot
							if (solvable())
								return true;
							else
							// backtrack: replace n with 0
								oktadoku[row][col] = 0;
						}
					}
					// no n was found
					return false;
				}
			}
		}
		// all spots have been used
		return true;
	}
	private boolean isNumberInRow(int row, int n) {
		for (int col = 0; col < SIZE; col++)
			if (oktadoku[row][col] == n)
				return true;
		return false;
	}

	private boolean isNumberInColumn(int col, int n) {
		for (int row = 0; row < SIZE; row++)
			if (oktadoku[row][col] == n)
				return true;
		return false;
	}
	private boolean isNumberInOktand(int row, int col, int n) {
		int rowOktand = row - row % 4;
		int colOktand = col - col % 2;
		for (int i = rowOktand; i < rowOktand + 4; i++)
			for (int j = colOktand; j < colOktand + 2; j++)
				if (oktadoku[i][j] == n)
					return true;
		return false;
	}
	/**
	 * Returns if n already exists in a diagonal.
	 * @param topLeftToBottomRight true: this diagonal is checked; false: other diagonal is checked
	 * @param n NUmber to be checked
	 * @return true: n exists; false: n does not exist
	 */
	private boolean isNumberInDiagonal(boolean topLeftToBottomRight, int n) {
		if (topLeftToBottomRight) 
			for (int i = 0; i < SIZE; i++)
				if (oktadoku[i][i] == n)
					return true;
		else {
			int col = SIZE - 1;
			for (int row = 0; row < SIZE; row++) {
				if (oktadoku[row][col] == n)
					return true;
				col--;
			}
		}
		return false;
	}
/**
 * Returns if a cell is valid for n
 * @param row Row of cell
 * @param column Column of Cell
 * @param n Number that is checked
 * @return boolean
 */
	private boolean isCellValid(int row, int column, int n) {
		// include diagonal checks if variant is set to
		if (this.v == Oktadoku.Variante.mitDiagonalen) {
			// include diagonal top left to bottom right if cell is on line
			if (row == column)
				return !isNumberInRow(row, n) && !isNumberInColumn(column, n) && !isNumberInOktand(row, column, n)
						&& !isNumberInDiagonal(true, n);
			// include diagonal top right to bottom left if cell is on line
			else if (row + column == SIZE - 1)
				return !isNumberInRow(row, n) && !isNumberInColumn(column, n) && !isNumberInOktand(row, column, n)
						&& !isNumberInDiagonal(false, n);
		}
		// existing checks for row, column and oktand field
		return !isNumberInRow(row, n) && !isNumberInColumn(column, n) && !isNumberInOktand(row, column, n);
	}
}