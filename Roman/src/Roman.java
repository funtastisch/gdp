
public class Roman {

	static String roman(int n) {
		String roman = "";

		// all cases for M (1000)
		if (n >= 1000) {
			roman += "M";
			n -= 1000;
		} else if (n >= 998) {
			roman = roman(1000 - n) + "M";
			n = 0;
		} else if (n >= 980) {
			roman = roman(1000 - (n / 10 * 10)) + "M";
			n = n % 10;

		} else if (n >= 800) {
			roman = roman(1000 - (n / 100 * 100)) + "M";
			n = n % 100;

			// all cases for D (500)
		} else if (n >= 500) {
			n -= 500;
			roman += "D";
		} else if (n >= 498) {
			roman = roman(500 - n) + "D";
			n = 0;
		} else if (n >= 480) {
			roman = roman(500 - (n / 10 * 10)) + "D";
			n = n % 10;

		} else if (n >= 300) {
			roman = roman(500 - (n / 100 * 100)) + "D";
			n = n % 100;

			// all cases for C (100)
		} else if (n >= 100) {
			n -= 100;
			roman += "C";
		} else if (n >= 98) {
			roman = roman(100 - n) + "C";
			n = 0;
		} else if (n >= 80) {
			roman = roman(100 - (n / 10 * 10)) + "C";
			n = n % 10;

			// all cases for L (50)
		} else if (n >= 50) {
			roman += "L";
			n -= 50;
		} else if (n >= 48) {
			roman = roman(50 - n) + "L";
			n = 0;
		} else if (n >= 30) {
			roman = roman(50 - (n / 10 * 10)) + "L";
			n = n % 10;

			// all cases for X (10)
		} else if (n >= 10) {
			roman += "X";
			n -= 10;
		} else if (n >= 8) {
			roman = roman(10 - n) + "X";
			n = 0;

			// all cases for V (5)
		} else if (n >= 5) {
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
		if (N < 1) {
			System.out.println("Die Zahl muss zwischen 1 und 5000 liegen.");
		}
		assert (1 <= N && N <= 5000);
		System.out.println(roman(N));
	}
}