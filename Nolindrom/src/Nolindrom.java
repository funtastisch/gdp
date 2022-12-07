import java.math.BigInteger;

public class Nolindrom {
	public static void main(String[] args) {
		if (args.length == 0) { // check if there is any argument
			System.out.println("Bitte geben Sie die Obergrenze als Parameter an.");
			System.exit(-1);
		}
		if (Integer.parseInt(args[0]) > 100000) { // check for max cap is 100,000
			System.out.println("Die Obergrenze darf maximal 100 000 betragen!");
			System.exit(-1);
		}
		int max = Integer.parseInt(args[0]); // max cap

		long[] nolindromesArray = new long[max]; // saving all nolindromes to check for real palindromes later (2nd
													// parameter is x)
		int numberOfNolindromes = 0; // to store found nolindromes in loop with overflow condition

		for (long count = 0; count < max; count++) { // check for all numbers up to max cap
			boolean palindromeFound = false;
			long n = count;
			long r = reverse(BigInteger.valueOf(n)).longValue(); // reverse will always be a long since input n is a
																	// long
			for (int i = 0; i < 100; i++) { // max 100 iterations - nolindrome algorithm
				if (n < 0)
					break; // break condition: n is overflowing floats value
				if (isPalindrome(BigInteger.valueOf(n)) && i == 0) { // do n + r at least once even if n is already a
																		// palindrome
					n += r;
					r = reverse(BigInteger.valueOf(n)).longValue(); // reverse will always be a long since input n is a
																	// long
					i++; // add iteration
				}
				if (isPalindrome(BigInteger.valueOf(n + r))) { // palindrome found -> go to count++ and search for
																// palindrome again
					palindromeFound = true;
					break;
				} else {
					n += r;
					r = reverse(BigInteger.valueOf(n)).longValue(); // reverse will always be a long since input n is a
																	// long
				}
			}
			if (!palindromeFound) {
				nolindromesArray[numberOfNolindromes] = count; // store nolindrome stackwise in array to read out and
																// check for real palindrom later
				numberOfNolindromes++;
				System.out.println(count);
			}
		}
		if (args.length == 1 || args.length > 2) {
			System.exit(-1);
		}
		if (args[1].equals("x")) {
			boolean palindromeFound = false;
			BigInteger palindrome = BigInteger.ZERO; // declare
			BigInteger nOfPalindrome = BigInteger.ZERO;
			int iterations = 0;
			// do same algorithm as before for all found nolindromes and check for real
			for (int i = 0; i < nolindromesArray.length && nolindromesArray[i] != 0 && !palindromeFound; i++) {
				/*
				 * palindromes conditions: 1st all longs in Array from earlier have to be looked
				 * at for possible real palindromes 2nd Array will most likely be bigger than
				 * nolindromes stored in it -> ignore all 0s if there was no palindrome found
				 * until last nolindrome in Array 3rd execute as long as there was no real
				 * palindrome found
				 */
				BigInteger n = BigInteger.valueOf(nolindromesArray[i]);
				BigInteger r = reverse(n);

				for (int j = 1; j <= 100; j++) { // max 100 iterations
					if (isPalindrome(n) && j == 0) { // do n+r at least once even n is already a palindrome
						n = n.add(r);
						r = reverse(n);
						j++;
					}
					if (isPalindrome(n.add(r))) {
						palindromeFound = true;
						nOfPalindrome = BigInteger.valueOf(nolindromesArray[i]);
						iterations = j;
						palindrome = n.add(r);
						break;
					} else {
						n = n.add(r);
						r = reverse(n);
					}
				}
			}

			if (palindromeFound) // differs to nolindrome algorithm -> goal: find the real palindrom
				System.out.println(
						nOfPalindrome + " braucht " + iterations + " Iterationen bis zum Palindrom " + palindrome);
			else
				System.out.println("Es wurde kein Palindrom innerhalb der Obergrenze gefunden.");
		}
	}

	// reverses BigInteger l -> work with BigInteger because of case 2nd parameter
	// is x
	public static BigInteger reverse(BigInteger b) {
		BigInteger reversed = BigInteger.ZERO;
		while (!(b == BigInteger.ZERO)) {
			BigInteger remainder = (b.remainder(BigInteger.TEN));
			reversed = reversed.multiply(BigInteger.TEN).add(remainder);
			b = b.divide(BigInteger.TEN);
		}
		return reversed;
	}

	// checks for palindrome -> work with BigInteger because of case 2nd parameter
	// is x
	public static boolean isPalindrome(BigInteger n) {
		BigInteger r = reverse(n);
		if (r.equals(n))
			return true;
		else
			return false;
	}

}