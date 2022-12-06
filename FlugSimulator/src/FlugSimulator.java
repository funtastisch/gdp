public class FlugSimulator {

	public static void main(String[] args) {
		try {
			int nrFlights = Integer.parseInt(args[0]);
			
			if (hasMultipleArguments(args)) {
				System.out.println("Die Eingabe beinhaltete mehr als eine Zahl.");
				System.exit(-1);
			}
			if(!isGreaterZero(nrFlights)) {
				System.out.println("Die Eingabe war kleiner gleich 0.");
				System.exit(-1);
			}
			int nrOverbookedFlights = 0;
			int summe = 0;
			
			for (int i = 0; i < nrFlights; i++) {				//neuen Flug betrachten, bis Anzahl Fluege erreicht
				int nrPassengers = 0;							//Flugzeug leeren
				for (int j = 0; j < 78; j++) 					//jedes gebuchte Ticket betrachten
					if (Math.random()<0.92) nrPassengers++;		//checken, ob der Passagier kommt
				if (nrPassengers > 75) nrOverbookedFlights++;	//wenn mehr als 75 Passagiere kommen, dann Anzahl ueberbuchter Fluege um 1 erhöhen
				summe = summe + nrPassengers;					//Anzahl aller erscheinenden Passagiere stets summieren => für Durchschnitt
			}
			String formattedPercentage = String.format("%.2f", (double)nrOverbookedFlights/nrFlights*100).replace(",", ".");	//Prozent berechnen und ins Format #.## bringen
			String formattedAverage = String.format("%.1f", (double)summe/nrFlights).replace(",", ".");							//Durchschnitt berechnen und ins Format #.# bringen
			System.out.println("Überbuchungen: " + nrOverbookedFlights + " (" + formattedPercentage + "%)");
			System.out.println("Mittelwert: " + formattedAverage);
		
		} catch (NumberFormatException e) {
			System.out.println("Die Eingabe war fehlerhaft.");
		}
	}
	public static boolean hasMultipleArguments(String args[]) {
		if (args.length >= 2) return true;
		return false;
	}
	public static boolean isGreaterZero(int n) {
		if (n > 0) return true;
		return false;
	}
}
