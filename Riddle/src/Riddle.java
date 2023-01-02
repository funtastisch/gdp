
public class Riddle {
	static int foundRiddles = 0;

	public static void main(String[] args) {
		int n = 0;
		try {
			n = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.println("Der Parameter kann nicht benutzt werden!");
			System.exit(-1);
		}
		
		findSolutions(new int[n*2], new boolean[n], 0);

		if (foundRiddles == 0)
			System.out.println("keine Loesung");
		else if (foundRiddles == 1)
			System.out.println("eine Loesung");
		else
			System.out.println(foundRiddles + " Loesungen");
	}

	/**
	 * Goes through every n at every position and searches for solutions and puts
	 * them into ArrayList
	 * 
	 * @param riddle Array where n is possibly put
	 * @param used   boolean array of length = n -> saves all the used numbers
	 * @param i      index at where n should be put
	 */
	static void findSolutions(int[] riddle, boolean[] used, int i) {
		// to catch exceptions
		if (i < riddle.length - 1) {
			// only iterate through n if there is space
			for (int n = 1; n <= riddle.length / 2; n++) {
				if (!used[n - 1]) {
					if (i + n + 1 >= riddle.length)
						return;
					if (riddle[i + n + 1] == 0) {
						riddle[i] = n;
						riddle[i + n + 1] = n;
						used[n - 1] = true;
						if (allNmbrsUsed(used)) {
							if (riddle[0] < riddle[riddle.length - 1]) {
								foundRiddles++;
								if (riddle.length / 2 < 10)
									print(riddle);
							}
							riddle[i] = 0;
							riddle[i + n + 1] = 0;
							used[n - 1] = false;
							return;
						}
						findSolutions(riddle, used, nextAvailablePos(riddle));
						riddle[i] = 0;
						riddle[i + n + 1] = 0;
						used[n - 1] = false;
					}
				}
			}
		}
	}

	/**
	 * Adds an array to the list and checks if it doesn't exist in reverse
	 * 
	 * @param riddle Riddle that has to be added to the list
	 */
	static boolean allNmbrsUsed(boolean[] nmbrs) {
		for (boolean b : nmbrs) {
			if (!b)
				return false;
		}
		return true;
	}

	static int nextAvailablePos(int[] riddle) {
		for (int i = 0; i < riddle.length; i++)
			if (riddle[i] == 0)
				return i;
		return -1;
	}

	/**
	 * Prints out the array
	 * 
	 * @param a Array that has be printed
	 */
	static void print(int[] a) {
		for (int i : a)
			System.out.print(i);
		System.out.print("\n");
	}
}