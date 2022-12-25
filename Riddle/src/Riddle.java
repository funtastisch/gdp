
public class Riddle {
	static int maxN;

	public static void main(String[] args) {
		int n = 3;
		maxN = n;
		int[] riddle = new int[n * 2];
		/*
		 * for (int i = 0; i < riddle.length - 1; i++) { int[] solution =
		 * findRiddle(riddle, 1, i); if (solution != null) addSolution(solutions,
		 * solution); }
		 */
		int[][] solutions = new int[0][];
		int[] a = { 1, 2, 3, 4, 5 };
		int[] b = { 1, 2, 3, 2, 1 };
		int[] c = { 1, 2, 3, 4, 5 };
		int[] d = { 5, 4, 3, 2, 1 };
		int[] e = { 5, 4, 3, 2, 2 };
		int[] f = { 2, 2, 3, 4, 5 };
		if (!exists(a, solutions))
			solutions = addSolution(solutions, a);
		if (!exists(b, solutions))
			solutions = addSolution(solutions, b);
		if (!exists(c, solutions))
			solutions = addSolution(solutions, c);
		if (!exists(d, solutions))
			solutions = addSolution(solutions, d);
		if (!exists(e, solutions))
			solutions = addSolution(solutions, e);
		if (!exists(f, solutions))
			solutions = addSolution(solutions, f);

		if (solutions.length > 0)
			for (int i = 0; i <= solutions.length - 1; i++) {
				print(solutions[i]);
				System.out.print("\n");
			}
		else
			System.out.println("keine Loesung gefunden.");

	}

	static int[] findRiddle(int[] riddle, int n, int index) {
		if(n == 1 && index > riddle.length-2) return null;
		else {
			
			if(riddle[index] == 0 && riddle [index + n + 1] == 0) {
				riddle[index] = n;
				riddle[index + n + 1] = n;
				riddle = findRiddle(riddle, ++n, 0);
			}
			
			
			return riddle;
		}
		
	}

	static int[] clearRiddleUpToN(int[] riddle, int max) {
		for (int i = 0; i < riddle.length - 1; i++) {
			if (riddle[i] >= max)
				riddle[i] = 0;
		}
		return riddle;
	}

	static int[][] addSolution(int[][] solutions, int[] riddle) {
		int lastIndex = 0;
		if (solutions.length > 0)
			lastIndex = solutions.length;
		solutions = upSizeArray(solutions);
		solutions[lastIndex] = riddle;
		return solutions;
	}

	static int[][] upSizeArray(int[][] array) {
		int[][] biggerArray = new int[array.length + 1][];
		if (biggerArray.length == 1)
			return biggerArray;
		// transfer references
		for (int i = 0; i <= array.length - 1; i++) {
			biggerArray[i] = array[i];
		}
		return biggerArray;
	}

	static boolean exists(int[] riddle, int[][] solutions) {
		if (solutions.length == 0)
			return false;
		boolean riddleFoundFw = true;
		boolean riddleFoundBw = true;

		// first loop: go through all riddle saved in solutions
		for (int i = 0; i <= solutions.length - 1; i++) {
			riddleFoundFw = true;
			// second loop: go through single digits in riddle
			for (int j = 0; j <= solutions[i].length - 1; j++) {
				if (solutions[i][j] != riddle[j]) {
					riddleFoundFw = false;
					break;
				}
			}
			// check to reduce unnecessary operations
			if (riddleFoundFw)
				return true;

			riddleFoundBw = true;
			int count = solutions[i].length - 1;
			for (int j = 0; j <= solutions[i].length - 1; j++) {
				if (solutions[i][j] != riddle[count]) {
					riddleFoundBw = false;
					break;
				}
				count--;
			}
			// check to reduce unnecessary operations
			if (riddleFoundBw)
				return true;
		}
		if (riddleFoundFw || riddleFoundBw)
			return true;
		else
			return false;
	}

	static void print(int[] a) {
		for (int i : a)
			System.out.print(i);
	}
}
