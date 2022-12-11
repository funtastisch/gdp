import gdp.stdlib.StdDraw;

public class PythagorasTree {

	public static void main(String[] args) {
		drawTreePart(0, 0, 1, 0);
		
	}
	static void drawTreePart(double x0, double y0, double c, double baseAnkle) {
		double ankle = (Math.random()*30) + 30 + baseAnkle;
		System.out.println(ankle);
		double a = Math.cos(ankle) * c; // adjacent length
		System.out.println(a);
		double b = Math.sin(ankle) * c; // oppsite leg length
		
		/*
		StdDraw.line(x0,y0, x1,y1);
		StdDraw.line(x1, y1, x2, y2);
		StdDraw.line(x2, y2, x0, y0);
		*/
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	// coordinate calculation
	
	static double[][] getTriangleCoordinates(double x0, double y0, double x1, double y1) {
		double[][] triangleCoords = new double[3][2];
		return triangleCoords;
		
	}
	
	static double[][] getSquareCoordinates() {
		double[][] coords = new double[4][2];
		return coords;
	}
	
	// drawing
	
	static void drawSquare(double[][] coords) {
		StdDraw.line(coords[0][0], coords[0][1], coords[1][0], coords[1][1]);
		StdDraw.line(coords[1][0], coords[1][1], coords[2][0], coords[2][1]);
		StdDraw.line(coords[2][0], coords[2][1], coords[3][0], coords[3][1]);
		StdDraw.line(coords[3][0], coords[3][1], coords[0][0], coords[0][1]);
		}
	
	static void drawTriangle(double[][] coords) {
		StdDraw.line(coords[0][0], coords[0][1], coords[1][0], coords[1][1]);
		StdDraw.line(coords[1][0], coords[1][1], coords[2][0], coords[2][1]);
		StdDraw.line(coords[2][0], coords[2][1], coords[0][0], coords[0][1]);
	}
	*/

}
