public class Bigs {

	public static void main(String[] args) {
		int[ ] a = One();
	    for (int i=0; i<33222; ++i) { a = times(a, 2); }
	    System.out.println("2^33222 hat " + a.length + " Stellen");
	    print(a);
	    System.out.println();
	    int[ ] b = fromInt(13);
	    int[ ] c = copy(b);
	    for (int i=1; i<8978; ++i) { c = times(c, b); }
	    System.out.println("13^8978 hat " + c.length + " Stellen");
	    print(c);
	    System.out.println();
	    System.out.println(less(a, c));
	    maxDigit(a);
	    maxDigit(c);
	}

	// idea: addition in writing
	public static int[] add(int[] a, int[] b) {
		if (a.length <= b.length) { // swap reference so the larger number is always a
			int[] c = b;
			b = a;
			a = c;
		}
		int[] sum = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			if (i > b.length - 1) {
				sum[i] += a[i];
				continue; // continue because there is nothing to calculate anymore
			}
			if (a[i] + b[i] >= 10) {
				if (i == a.length - 1)
					sum = oneBiggerArray(sum); // carryover at last digits
				sum[i] += a[i] + b[i] - 10;
				sum[i + 1] += 1;
			} else {
				sum[i] += a[i] + b[i];
			}
		}
		return sum;
	}

	static void print(int[] n) {
		int counter = 0;
		for (int i = n.length - 1; i >= 0; i--) {
			counter++;
			if (counter > 68) {
				System.out.print("\\ \n");
				counter = 1;
			}
			System.out.print(n[i]);

		}
		System.out.print("\n");
	}

	static int[] digit(int d) {
		int[] digit = { d };
		return digit;
	}

	static int[] Null() {
		int[] zero = { 0 };
		return zero;
	}

	static int[] One() {
		int[] one = { 1 };
		return one;
	}

	static int mod10(int[] n) {
		return n[0];

	}

	static int[] div10(int[] n) {
		int[] div10 = new int[n.length - 1];
		for (int i = 0; i < div10.length; i++) {
			div10[i] = n[i + 1];
		}
		return div10;
	}

	static int[] fromInt(int n) {
		int[] numberArray = new int[String.valueOf(n).length()];
		int count = 0;
		while (n != 0) {
			numberArray[count] = n % 10;
			n /= 10;
			count++;
		}
		return numberArray;
	}

	static int[] copy(int[] n) {
		int[] copy = new int[n.length];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = n[i];
		}
		return copy;
	}

	static int[] times(int[] n, int d) {
		int[] times = new int[n.length];
		for (int i = 1; i <= d; i++) {
			times = add(times, n);
		}
		return times;
	}

	static int[] times10(int[] n) {
		int[] times10 = new int[n.length + 1];
		times10[0] = 0;
		for (int i = 0; i < n.length; i++) {
			times10[i + 1] = n[i];
		}
		return times10;
	}

	// idea: multiplication in writing
	static int[] times(int[] a, int[] b) {
		if (a.length <= b.length) { // swap reference so the larger number is always a
			int[] c = b;
			b = a;
			a = c;
		}
		int[] product = new int[a.length];
		for (int i = 0; i < b.length; i++) { // i is index from digits of a
			for (int j = 0; j < a.length; j++) { // j is index from digits of b
				if (i + j == product.length) { // if 
					product = oneBiggerArray(product);
				}
				product[i + j] += a[j] * b[i];
			}
		}
		for (int i = 0; i < product.length; i++) {
			if (i == product.length - 1 && product[i] > 9) { // if there is a carryover at last array index
				product = oneBiggerArray(product);
			}
			if (product[i] > 9) {
				product[i + 1] += product[i] / 10;
				product[i] %= 10;
			}
		}
		return product;
	}

	static int[] square(int[] n) {
		int[] square = times(n, n);
		return square;
	}

	static int[] cubic(int[] n) {
		int[] cubic = times(n, times(n, n));
		return cubic;
	}

	static boolean less(int[] a, int[] b) {
		if (a.length > b.length)
			return false;
		if (a.length < b.length)
			return true; 
		for (int i = a.length - 1; i >= 0; i--) {
			if (a[i] > b[i])
				return false;
			if(a[i] < b[i]) 
				return true;
		}
		if (a[0] == b[0])
			return false;
		return true;
	}

	static boolean equal(int[] a, int[] b) {
		if (a == b) return true; // both a and b referencing the same
		if (a.length != b.length)
			return false;
		for (int i = 0; i < a.length; i++)
			if (a[i] != b[i])
				return false;
		return true;
	}

	static boolean ok(int[] n) {
		// basic assumption: int[] n is ok
		if (n == null)
			return false;
		boolean greaterZeroSeen = false;
		for (int i = n.length - 1; i >= 0; i--) {
			if (n[i] < 0 || n[i] > 9)
				return false;
			if (n.length == 1 && n[0] == 0)
				return true; // case: single digit 0
			if (!greaterZeroSeen && n[i] > 0)
				greaterZeroSeen = true;
			if (!greaterZeroSeen && n[i] == 0)
				return false;
		}
		return true;
	}

	static void maxDigit(int[] n) {
		int[] counter = new int[10];
		for (int i = 0; i < counter.length; i++) {
			int count = 0;
			for (int j = 0; j < n.length; j++) {
				if (n[j] == i)
					count++;
			}
			counter[i] = count;
		}
		int maxIndex = 0;
		for (int i = 0; i < counter.length; i++) {
			if (counter[i] > counter[maxIndex])
				maxIndex = i;
		}
		System.out.println(maxIndex + " mit " + counter[maxIndex] + " Stellen");
	}

	
	// own helping methods
	static int[] oneBiggerArray(int[] a) {
		int[] b = new int[a.length + 1];
		for (int i = 0; i < a.length; i++)
			b[i] = a[i];
		return b;
	}
}
