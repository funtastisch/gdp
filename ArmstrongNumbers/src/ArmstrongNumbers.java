public class ArmstrongNumbers {
	public static void main(String[] args) {
		int[] test = giveArmstrongNumbers(20);
		// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634
		printArray(test);
	}

	public static boolean isArmstrongNumber(int number) {
		String numberString = String.valueOf(number);
		int sum = 0;
		for(int i = 0; i < numberString.length(); i++) {
			int digit = Character.getNumericValue(numberString.charAt(i));
			sum += Math.pow(digit, numberString.length());
		}
		if (sum == number) return true;
		return false;
	}

	public static int[] giveArmstrongNumbers(int n) {
		int[] numbers = new int[n];
		int i = 0;
		int counter = 0;
		while(numbers[numbers.length-1] == 0) {
			if(isArmstrongNumber(i)) {
				numbers[counter] = i;
				counter++;
			}
			i++;
		}
		return numbers;
	}

	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			System.out.print(i < a.length - 1 ? ", " : "\n");
		}
	}
}