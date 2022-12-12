import gdp.stdlib.StdDraw;
import gdp.stdlib.StdOut;

public class PythagorasTree {

	
	static void test () {
		
	}
	public static void main(String[] args) {
		int n = 0;
		try {
			n = Integer.parseInt(args[0]);
			
		} catch (Exception e) {
			StdOut.println("Der Parameter entspricht dem benoetigten Format!");
			System.exit(-1);
		}
		if(args.length != 1) {
			StdOut.println("Kein oder mehr als ein Parameter!");
			System.exit(-1);
		}
		if(n == 0) {
			StdOut.println("Es muss mindestens eine Iteration geben!");
			System.exit(-1);
		}
		
		StdDraw.setXscale(0, 100);
		StdDraw.setYscale(0, 100);
		
		//draw base square
		double x0 = 40;
		double y0 = 20;
		double size = 15;
		StdDraw.line(x0, y0, x0 + size, y0);
		StdDraw.line(x0, y0, x0, y0 + size);
		StdDraw.line(x0 + size, y0, x0 + size, y0 + size);
		drawTreePart(n, x0, y0 + size, size, 0);
	}
	
	/**
	 * Draws a part of the tree with fix point at the bottom left corner of the triangle
	 * @param n Iteration
	 * @param x0 x coordinate at alpha ankle
	 * @param y0 y coordinate of alpha ankle
	 * @param c length of hypothenuse of triangle
	 * @param baseAlphaRadiant ankle at which the iteration is in relative to x axis of canvas
	 */
	static void drawTreePart(int n, double x0, double y0, double c, double baseAlphaRadiant) {
		
		double alphaRadiant = Math.random()*Math.PI/6 + Math.PI/6; // radiant of ankle alpha
		double betaRadiant = Math.PI - Math.PI/2 - alphaRadiant; // radiant of ankle beta
		
		double a = Math.cos(alphaRadiant) * c; // adjacent length
		double b = Math.sin(alphaRadiant) * c; // oppsite leg length
		
		// P1 (x1|y2) is point at beta ankle
		double x1 = x0 + (Math.cos(alphaRadiant + baseAlphaRadiant) * a);
		double y1 = y0 + (Math.sin(alphaRadiant + baseAlphaRadiant) * a);
		
		// P2 (x2|y2) is point opposite of alpha
		double x2 = x0 + (Math.cos(baseAlphaRadiant) * c);
		double y2 = y0 + (Math.sin(baseAlphaRadiant) * c);
		
		
		//draw triangle with P0, P1, P2
		StdDraw.line(x0, y0, x1,y1);
		StdDraw.line(x1, y1, x2, y2);
		StdDraw.line(x2, y2, x0, y0);
		
		// calculate missing 2 points of left square by using sin and cos parallel to x and y axis of canvas
		double x0SquareLeft = x0 + (Math.cos(Math.PI/2 + alphaRadiant + baseAlphaRadiant)) * a;
		double y0SquareLeft = y0 + (Math.sin(Math.PI/2 + alphaRadiant + baseAlphaRadiant)) * a;
		
		double x1SquareLeft = x1 + (Math.cos(Math.PI/2 + alphaRadiant + baseAlphaRadiant)) * a;
		double y1SquareLeft = y1 + (Math.sin(Math.PI/2 + alphaRadiant + baseAlphaRadiant)) * a;
		
		// draw missing 3 lines to complete left square
		StdDraw.line(x0, y0, x0SquareLeft, y0SquareLeft);
		StdDraw.line(x0SquareLeft, y0SquareLeft, x1SquareLeft, y1SquareLeft);
		StdDraw.line(x1SquareLeft, y1SquareLeft, x1, y1);
		
		// calculate missing 2 points of right square by using sin and cos parallel to x and y axis of canvas
		double x0SquareRight = x1 + (Math.cos(alphaRadiant + baseAlphaRadiant)) * b;
		double y0SquareRight = y1 + (Math.sin(alphaRadiant + baseAlphaRadiant)) * b;
		
		double x1SquareRight = x2 + (Math.cos(alphaRadiant + baseAlphaRadiant)) * b;
		double y1SquareRight = y2 + (Math.sin(alphaRadiant + baseAlphaRadiant)) * b;
		
		// draw missing 3 lines to complete right square
		StdDraw.line(x1, y1, x0SquareRight, y0SquareRight);
		StdDraw.line(x0SquareRight, y0SquareRight, x1SquareRight, y1SquareRight);
		StdDraw.line(x1SquareRight, y1SquareRight, x2, y2);
		
		n -= 1; // iteration done
		
		// recursive call
		if(n > 0) {
			drawTreePart(n, x0SquareLeft, y0SquareLeft, a, baseAlphaRadiant + alphaRadiant);
			drawTreePart(n, x0SquareRight, y0SquareRight, b, baseAlphaRadiant + 2*Math.PI - betaRadiant);
		}
	}
}
