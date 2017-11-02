/**Roots.java
 * Chuong Viet Truong
 * chvtruon
 * pa4
 * This program determines the real roots of a polynomial that lie within a specified range using the bisectional method.
 **/
import java.util.*;

public class Roots {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		double resolution = .01, tolerance = .0000001, threshold = .001;
		double left, right, b, root;
		int degree;
		boolean gotRoot = false;

		//enter degree
		System.out.print("Enter the degree: ");
		degree = scan.nextInt();
		double[] coefficient = new double[degree + 1];

		//enter coefficients
		System.out.print("Enter "+coefficient.length+" coefficients: ");
		for (int i = 0; i < coefficient.length; i++) {
			coefficient[i] = scan.nextDouble();
		}

		//enter search interval [L,R]
		System.out.print("Enter the left and right endpoints: ");     
		left = scan.nextDouble();
		right = scan.nextDouble();
		b = right - left;
		System.out.println();

		//calculate coefficient using function diff
		double[] derivative = diff(coefficient);

		//loop that iterates all over subintervals [a,b] forming partition of [L,R]
		for (double i = 0; i <= b; i += resolution) {
			if (poly(coefficient, (left+i)) * poly(coefficient, (left+i+resolution)) < 0) {
				root = findRoot(coefficient, left+i, left+i+resolution, tolerance);
				System.out.printf("Root found at %.5f%n", root);
				gotRoot = true;
			}
			else if (poly(derivative, (left+i)) * poly(derivative, (left + i + resolution)) < 0) {
				root = findRoot(derivative, left+i, left+i+resolution, tolerance);
				if (Math.abs(poly(coefficient, root)) < threshold) {
					System.out.printf("Root found at %.5f%n", root);
					gotRoot = true;
				}
			}
		}
		//if no even/odd roots then prints this message
		if (!gotRoot) {
			System.out.println("No roots were found in the specified range.");
		}
	}

	//returns value of x of polynomial with coefficient array C
	public static double poly(double[] C, double x) {
		double sum = 0.0;
		for (int i = 0; i < C.length; i++) {
			sum += (C[i] * Math.pow(x, i));
		}
		return sum;
	}

	//returns coefficients of the polynomial that is the derivative
	static double[] diff(double[] C) {
		double[] derivative = new double[C.length - 1];
		for (int i = 0; i < derivative.length - 1; i++) {
			derivative[i] = (i + 1)*C[i + 1];
		}
		return derivative;
	}

	//returns an approximation of the root in the interval a and b
	static double findRoot(double[] C, double a, double b, double tolerance) {
		double m = 0, residual;
		while (b - a > tolerance) {
			m = (a + b)/2.0;
			residual = poly(C, m);
			if (poly(C, a) * residual < 0) {
				b = m;
			}
			else {
				a = m;
			}
		}
		return m;
	}
}
