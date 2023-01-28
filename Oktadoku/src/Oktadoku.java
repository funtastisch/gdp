import java.util.BitSet;

import gdp.stdlib.*;

public class Oktadoku {

	public enum Variante {
		normal, mitDiagonalen
	};

	private Variante v;
	// oktadoku [row][col]
	private int[][] oktadoku;
	private final int SIZE = 8;

	public Oktadoku(Variante var) {
		this.v = var;
	}

	public void read() {
		oktadoku = new int[8][8];
		for (int row = 0; row <= 7; row++) {
			for (int col = 0; col <= 7; col++) {
				char nextChar = StdIn.readChar();
				int number = Character.getNumericValue(nextChar);
				if (number != -1) {
					oktadoku[row][col] = number;
				}
			}
			StdIn.readChar();
		}
	}

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

	public void solve() {
		if (solvable())
			write();
		else
			StdOut.println("nicht loesbar :-(");
	}

	private boolean solvable() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (oktadoku[row][col] == 0) {
					for (int n = 1; n <= SIZE; n++) {
						if (isCellValid(row, col, n)) {
							oktadoku[row][col] = n;
							if (solvable()) {
								return true;
							} else
								oktadoku[row][col] = 0;
						}
					}
					return false;
				}
			}
		}
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
		;
		return false;
	}

	private boolean isNumberInDiagonal(boolean topLeftToBottomRight, int n) {
		if (topLeftToBottomRight) {
			for (int i = 0; i < SIZE; i++)
				if (oktadoku[i][i] == n)
					return true;
		} else {
			int col = SIZE - 1;
			for (int row = 0; row < SIZE; row++) {
				if (oktadoku[row][col] == n)
					return true;
				col--;
			}
		}
		return false;
	}

	private boolean isCellValid(int row, int column, int n) {
		if (this.v == Oktadoku.Variante.mitDiagonalen) {
			if (row == column)
				return !isNumberInRow(row, n) && !isNumberInColumn(column, n) && !isNumberInOktand(row, column, n)
						&& !isNumberInDiagonal(true, n);
			else if (row + column == SIZE - 1)
				return !isNumberInRow(row, n) && !isNumberInColumn(column, n) && !isNumberInOktand(row, column, n)
						&& !isNumberInDiagonal(false, n);
		}
		return !isNumberInRow(row, n) && !isNumberInColumn(column, n) && !isNumberInOktand(row, column, n);
	}
}