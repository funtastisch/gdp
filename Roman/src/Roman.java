
public class Roman {

	static String roman(int n) {
		String roman = "";

		// all cases for M (1000)
		if (n >= 1000) {
			roman += "M";
			n -= 1000;
		} else if (n >= 900) {
			roman = roman(1000 - (n / 100 * 100)) + "M";
			n = n % 100;
		}
		// all cases for D (500)
		else if (n >= 500) {
			n -= 500;
			roman += "D";
		} else if(n >= 400) {
			roman = roman(500- (n / 100 * 100)) + "D";
			n %= 100;
		}
		// all cases for C (100)
		else if (n >= 100) {
			n -= 100;
			roman += "C";
		} else if (n >= 90) {
			roman = roman(100 - (n / 10 * 10)) + "C";
			n = n % 10;
		}
		// all cases for L (50)
		else if (n >= 50) {
			roman += "L";
			n -= 50;
		} else if (n >= 40) {
			roman += "XL";
			n -= 40;
		}

		// all cases for X (10)
		else if (n >= 10) {
			roman += "X";
			n -= 10;
		} else if (n >= 9) {
			roman = roman(10 - n) + "X";
			n = 0;
		}
		// all cases for V (5)
		else if (n >= 5) {
			roman += "V";
			n -= 5;
		} else if (n == 4) {
			roman = roman(5 - n) + "V";
			n = 0;
		} else if (n >= 1) {
			roman += "I";
			n -= 1;
		}

		if (n != 0)
			return roman + roman(n);
		else
			return roman;
	}

	public static void main(String[] args) {
		// Behandlung fehlender oder falscher Eingabeparameter
		int N = 0;
		try {
			N = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.println("Bitte eine Zahl als Parameter angeben.");
			System.exit(-1);
		}
		if (N < 1 || N > 5000) {
			System.out.println("Die Zahl muss zwischen 1 und 5000 liegen.");
			System.exit(-1);
		}
		assert (1 <= N && N <= 5000) : "Die Zahl muss zwischen 1 und 5000 liegen.";
		for (int i = 1; i <= 1000; i++) {
			if(i > 100) i+=99;
			System.out.print(i + ": ");
			System.out.println(roman(i));
		}
	}
}