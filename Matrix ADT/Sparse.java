/*
#Chuong Viet Truong
#CruzID: Chvtruon
#PA 3
*/

import java.io.*;
import java.util.Scanner;

public class Sparse{
    public static void main(String[] args) throws IOException{
        Scanner in = null;
        PrintWriter out = null;
        int n = 0;
        int a = 0;
        int b = 0;
        Matrix A = null;
        Matrix B = null;

        if(args.length < 2){
            System.err.println("Usage: Sparse infile outfile");
            System.exit(1);
        }

        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));

        while(in.hasNextInt()){
            n = in.nextInt();
            a = in.nextInt();
            b = in.nextInt();

            A = new Matrix(n);
            for(int i=1; i<=a; i++){
                int row = in.nextInt();
                int column = in.nextInt();
                double value = in.nextDouble();
                A.changeEntry(row, column, value);
            }

            B = new Matrix(n);
            for(int i=1; i<=b; i++){
                int row = in.nextInt();
                int column = in.nextInt();
                double value = in.nextDouble();
                B.changeEntry(row, column, value);
            }
        }

        // Print statements to output file
        out.println("A has " + A.getNNZ() + " non-zero entries:");
        out.println(A);

        out.println("B has " + B.getNNZ() + " non-zero entries:");
        out.println(B);

        out.println("(1.5)*A =");
        out.println(A.scalarMult(1.5));

        out.println("A+B =");
        out.println(A.add(B));

        out.println("A+A =");
        out.println(A.add(A));

        out.println("B-A =");
        out.println(B.sub(A));

        out.println("A-A =");
        out.println(A.sub(A));

        out.println("Transpose(A) =");
        out.println(A.transpose());

        out.println("A*B =");
        out.println(A.mult(B));

        out.println("B*B =");
        out.println(B.mult(B));

        in.close();
        out.close();
    }
}
