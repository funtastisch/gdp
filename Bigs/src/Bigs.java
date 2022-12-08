public class Bigs {

	public static void main(String[] args) {
		int a = 35;
		//int[] number1 = fromInt(a);
		int b = 775600000;
		
		int[] number2 = fromInt(b);
		print(number2);
	}
	// idea: addition in writing
	public static int[] add(int[] a, int[] b) {
		if(a.length <= b.length) {	// swap reference so the larger number is always a
			int[] c = b;
			b = a;
			a = c;
		}
		int[] sum = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			if(i > b.length - 1) {
				sum[i] += a[i];
				continue;	// continue because there is nothing to calculate anymore
			}
			if(a[i] + b[i] >= 10) {
				if(i == a.length - 1) sum = oneBiggerArray(sum); // carryover at last digits
				sum[i] += a[i] + b[i] -10;
				sum[i+1] += 1;
			}else {
				sum[i] += a[i] + b[i];
			}
		}
		return sum;
	}

	static void print(int[] n) {
		if (n.length <= 68) {
			for (int i = n.length-1; i >= 0; i--) {
				System.out.print(n[i]);
			}
		}else{
			for (int i = n.length-1; i > n.length-68; i--) 
				System.out.print(n[i]);
			System.out.print("\n");
			for (int i = n.length-68; i > 0; i--) 
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
		int[] div10 = new int[n.length-1];
		for (int i = 0; i< div10.length; i++) {
			div10[i] = n[i+1];
		}
		return div10;
	}
	
	
	static int[] fromInt(int n) {
		int[] numberArray = new int[String.valueOf(n).length()];
		int count = 0;
		while(n!=0) {
			numberArray[count] = n % 10;
			n/= 10;
			count++;
		}
		return numberArray;
	}
	static int[] copy(int[] n) {
		int[] copy = new int[n.length];
		for(int i = 0; i < copy.length - 1; i++) {
			copy[i] = n[i];
		}
		return copy;
	}
	
	static int[] times(int[] n, int d) {
		int[] times = new int[n.length];
		for(int i = 1; i <= d; i++) {
			times = add(times,n);
		}
		return times;
	}
	static int[] times10(int[] n) {
		int[] times10 = new int[n.length+1];
		times10[0] = 0;
		for(int i = 0; i < n.length; i++) {
			times10[i+1] = n[i];
		}
		return times10;
	}
	// idea: multiplication in writing
	static int[] times(int[] a, int[] b) {
		if(a.length <= b.length) {	// swap reference so the larger number is always a
			int[] c = b;
			b = a;
			a = c;
		}
		int[] product = new int[a.length]; 
		for(int i = 0; i < b.length; i++) { // i is index from digits of a
			for(int j = 0; j < a.length; j++) { // j is index from digits of b
				int smallProduct = a[j] * b[i];
				if(smallProduct >= 10) { 
					if(i+j >= product.length-1) product = oneBiggerArray(product); // product array has to be enlarged to store carryover
					product[i+j] += smallProduct % 10;
					smallProduct /= 10;
					product[i+j+1] += smallProduct % 10;
					smallProduct /= 10;
					// smallProduct / 10 just twice because can't be higher than 81 (9*9)
				}else {
				product [i+j] = smallProduct;
				}
			}
		}
		return product;
	}
	
	
	
	//--------------------
	//own extra methods
	static int[] oneBiggerArray(int[] a) {
		int[] b = new int [a.length+1];
		for (int i = 0; i < a.length; i++)
			b[i] = a[i];
		return b;
	}
}
