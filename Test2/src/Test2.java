
public class Test2 {

	public static void main(String[] args) {
		System.out.println(estimatePrimeNumberCount(100));

	}
	public static int estimatePrimeNumberCount(int n){
        double range = n* Math.pow(Math.log(n),2);
        if (n==1) range=1;
        if (range>2) range=range-2;
        while (range<n){
            range = range*10;
        }
        return (int) range;
    }

}
