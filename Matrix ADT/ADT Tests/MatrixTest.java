//-----------------------------------------------------------------------------
//  MatrixTest.java
//  A test client for the Matrix ADT
//-----------------------------------------------------------------------------

public class MatrixTest{
   public static void main(String[] args){
      int i, j, n=100000;
      Matrix A = new Matrix(n);
      Matrix B = new Matrix(n);

      A.changeEntry(1,1,1); B.changeEntry(1,1,1);
      A.changeEntry(1,2,2); B.changeEntry(1,2,0);
      A.changeEntry(1,3,3); B.changeEntry(1,3,1);
      A.changeEntry(2,1,4); B.changeEntry(2,1,0);
      A.changeEntry(2,2,5); B.changeEntry(2,2,1);
      A.changeEntry(2,3,6); B.changeEntry(2,3,0);
      A.changeEntry(3,1,7); B.changeEntry(3,1,1);
      A.changeEntry(3,2,8); B.changeEntry(3,2,1);
      A.changeEntry(3,3,9); B.changeEntry(3,3,1);

      System.out.println("Matrix A =");
      System.out.println("Size: "+A.getSize());
      System.out.println("NNZ: "+A.getNNZ());
      System.out.println(A);

      System.out.println("Matrix B =");
      System.out.println("Size: "+B.getSize());
      System.out.println("NNZ: "+B.getNNZ());
      System.out.println(B);

      Matrix C = A.scalarMult(1.5);
      System.out.println("(1.5)*A =");
      System.out.println("Size: "+C.getSize());
      System.out.println("NNZ: "+C.getNNZ());
      System.out.println(C);

      Matrix D = A.add(A);
      System.out.println("A+A =");
      System.out.println("Size: "+D.getSize());
      System.out.println("NNZ: "+D.getNNZ());
      System.out.println(D);

      Matrix E = A.sub(A);
      System.out.println("A-A =");
      System.out.println("Size: "+E.getSize());
      System.out.println("NNZ: "+E.getNNZ());
      System.out.println(E);

      Matrix F = B.transpose();
      System.out.println("Transpose(B) =");
      System.out.println("Size: "+F.getSize());
      System.out.println("NNZ: "+F.getNNZ());
      System.out.println(F);

      Matrix G = B.mult(B);
      System.out.println("B*B =");
      System.out.println("Size: "+G.getSize());
      System.out.println("NNZ: "+G.getNNZ());
      System.out.println(G);

      Matrix H = A.copy();
      System.out.println("Copy(A) =");
      System.out.println("Size: "+H.getSize());
      System.out.println("NNZ: "+H.getNNZ());
      System.out.println(H);
      System.out.println(A.equals(H));
      System.out.println(A.equals(B));
      System.out.println(A.equals(A));
      System.out.println();

      A.makeZero();
      System.out.println("makeZero(A) =");
      System.out.println("Size: "+A.getSize());
      System.out.println("NNZ: "+A.getNNZ());
      System.out.println(A);
   }
}
