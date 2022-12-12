public class GGT {

	public static void main(String[] args) {
		int zahl1 = Integer.parseInt(args[0]);
		int zahl2 = Integer.parseInt(args[1]);
		
		if (zahl1 <= 0 || zahl2 <= 0) {
			System.out.println("nur positive ganze Zahlen als Argumente erlaubt");
			System.exit(-1);
		}

		/*if(!hasTwoPositiveNumbers(zahl1, zahl2)) {
			System.out.println("Mindestens eine Zahl war kleiner als 0.");
			System.exit(-1);
		}
		*/
		int r = 1;
		int m = zahl1;
		int n = zahl2;
		int speicher;
		while (r != 0) {
			if (m < n) {		//2. Schritt: m < n, dann m und n tauschen 
				speicher = m;
				m = n;
				n = speicher;
			}
			r = m - n; 			//3. Schritt: r berechnen
			m = n; 
			n = r;					//4. Schritt
		}
		System.out.println("ggT(" + zahl1 + ", " + zahl2 + ") = " + m);
		
		
		
	}
	public static boolean hasTwoPositiveNumbers(int zahl1, int zahl2) {
		if(zahl1 >= 0 && zahl2 >= 0) return true;
		return false;
	}

}
