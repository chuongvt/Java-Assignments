/*Queens.java
 * Chuong Viet Truong
 * chvtruon
 * pa5
 * This program finds all solutions to n-Queens problem for 1<=n<=13
 */
public class Queens {
	public static void main(String[] args) {
		boolean verbose = false;
		int solutions = 0, f=0, n=0;
		int[] A;
		
		if (args.length == 0) {
			printUsageMessage();
		}
		else if (args.length == 1) {
			try {
				n = Integer.parseInt(args[0]);
			}catch (NumberFormatException e){
				printUsageMessage();
			}
		}
		else {
			if (args[0].equals("-v")) {
				verbose = true;
			}
			try {
				n = Integer.parseInt(args[1]);
			}catch (NumberFormatException e) {
				printUsageMessage();
			}
		}
		
		A = new int[n+1];
		for (int i=0; i<n+1 ; i++) {
			A[i] = i;
		}
		f = calcFactorial(n);
		for (int i=0; i<=f; i++) {
			nextPermutation(A);
			if (isSolution(A)) {
				if (verbose) {
					printSolution(A);
				}
				solutions++;
			}
		}
		System.out.println(n+"-Queens has "+solutions+" solutions");
	}
	
	static void nextPermutation(int[] A){
		int pivot = 0, successor = 0;
		for (int i = A.length-2; i > 0; i--) {
			if (A[i] < A[i+1]) {
				pivot = i;
				break;
			}
		}
		if (pivot == 0) {
			reverse(A, 1, A.length-1);
			return;
		}
		for (int i = A.length-1; i > 0; i--) {
			if (A[i] > A[pivot]) {
				successor = i;
				break;
			}
		}
		swap(A, pivot, successor);
		reverse(A, pivot+1, A.length-1);
		return;
	}
	
	static boolean isSolution(int[] A) {
		for (int i = 1; i < A.length; i++) {
			for (int j = i+1; j < A.length; j++) {
				if (j-i == Math.abs(A[i]-A[j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void reverse(int[]  A, int i, int j) {
		while (i < j) {
			swap(A, i, j);
			i++;
			j--;
		}
	}
	
	static void swap(int[] A, int i, int j) {
		int temp;
		temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	
	static void printUsageMessage() {
		System.out.println("Usage: Queens [-v] number");
		System.out.println("Option: -v verbose output, print all solutions");
		System.exit(1);
	}
	
	static void printSolution(int[] A) {
		System.out.print("(");
		for (int i=1; i<A.length-1; i++) {
			System.out.print(A[i]+", ");
		}
		System.out.print(A[A.length-1]);
		System.out.print(")\n");
	}
	
	static int calcFactorial(int num) {
		int answer;
		if (num < 0) {
			printUsageMessage();
		}
		if (num == 0 || num == 1) {
			return answer = 1;
		}
		answer = num * calcFactorial(num-1);
		return answer;
	}
	
}

