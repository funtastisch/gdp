public class Gray {
	/**
	 * Returns the gray code decimal value of a binary code decimal
	 * @param n decimal of binary code
	 * @return decimal of gray code
	 */
	public static int toGray(int n) {
		if(n == 0 || n == 1)
			return n;
		String binary = Integer.toBinaryString(n);
		String gray = "" + binary.charAt(0);
		for (int i = 1; i < binary.length(); i++) {
			int sum = binary.charAt(i)+ binary.charAt(i-1) - 96;
			if (sum == 1)
				gray += "1"; 
			else
				gray += "0";
		}
		return toDecimal(gray);
	}
	
	/**
	 * Returns the binary code decimal value of a gray code decimal
	 * @param n decimal of gray code
	 * @return decimal of binary code
	 */
	public static int fromGray(int n) {
		if(n == 0 || n == 1)
			return n;
		String gray = Integer.toBinaryString(n);
		String binary = "" + gray.charAt(0);
		for (int i = 1; i < gray.length(); i++) {
			int sum = gray.charAt(i) + binary.charAt(i-1) - 96;
			if(sum == 1)
				binary += "1";
			else
				binary += "0";
		}
		return toDecimal(binary);
	}

	/**
	 * Returns decimal value of bit number
	 * 
	 * @param bit String in bit format
	 * @return Integer
	 */
	static int toDecimal(String bit) {
		int dec = 0;
		int exp = 0;
		for (int i = bit.length() - 1; i >= 0; i--) {
			if (bit.charAt(i) == '1') {
				dec += Math.pow(2, exp);
			}
			exp++;
		}
		return dec;
	}
}