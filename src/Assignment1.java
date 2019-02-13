import java.io.*;

public class Assignment1{

      public int[][] denseMatrixMult(int[][] A, int[][] B, int size)
      {

        int[][] m = initMatrix(size);

        //Base Case for Recursion MUST COME FIRST
    		if (size == 1) {
    				m[0][0] = A[0][0] * B[0][0];
    				return m;
    		}
        else {
          //Define Sub-Matrices, Could have also used dynamic double matrix creation for following declarations (don't need init Function)
      		int[][] A00 = initMatrix(size/2);
      		int[][] A01 = initMatrix(size/2);
      		int[][] A10 = initMatrix(size/2);
      		int[][] A11 = initMatrix(size/2);
      		int[][] B00 = initMatrix(size/2);
      		int[][] B01 = initMatrix(size/2);
      		int[][] B10 = initMatrix(size/2);
      		int[][] B11 = initMatrix(size/2);

          //Don't need to implement %2 Check with given assignments below
      		for (int i = 0; i < size/2; i++) {
      			for (int j = 0; j < size/2; j++) {
      				A00[i][j] = A[i][j];
      				A01[i][j] = A[i][j + size/2];
      				A10[i][j] = A[i + size/2][j];
      				A11[i][j] = A[i + size/2][j + size/2];

      				B00[i][j] = B[i][j];
      				B01[i][j] = B[i][j + size/2];
      				B10[i][j] = B[i + size/2][j];
      				B11[i][j] = B[i + size/2][j + size / 2];
      			}
      		}

          //Define Matrices M0 - M6 via recursion
      		int[][] M0 = denseMatrixMult(sum(A00, A11, 0, 0, 0, 0, size/2), sum(B00, B11, 0, 0, 0, 0, size/2), size/2);
      		int[][] M1 = denseMatrixMult(sum(A10, A11, 0, 0, 0, 0, size/2), B00, size/2);
      		int[][] M2 = denseMatrixMult(A00, sub(B01, B11, 0, 0, 0, 0, size/2), size/2);
      		int[][] M3 = denseMatrixMult(A11, sub(B10, B00, 0, 0, 0, 0, size/2), size/2);
      		int[][] M4 = denseMatrixMult(sum(A00, A01, 0, 0, 0, 0, size/2) , B11, size/2);
      		int[][] M5 = denseMatrixMult(sub(A10, A00, 0, 0, 0, 0, size/2), sum(B00, B01, 0, 0, 0, 0, size/2), size/2);
      		int[][] M6 = denseMatrixMult(sub(A01, A11, 0, 0, 0, 0, size/2), sum(B10, B11, 0, 0, 0, 0, size/2), size/2);

          //Compute resultant Matrix
      		int[][] C00 = sum(sub(sum(M0, M3, 0, 0, 0, 0, size/2), M4, 0, 0, 0, 0, size/2), M6, 0, 0, 0, 0, size/2);
      		int[][] C01 = sum(M2, M4, 0, 0, 0, 0, size/2);
      		int[][] C10 = sum(M1, M3, 0, 0, 0, 0, size/2);
      		int[][] C11 = sum(sum(sub(M0, M1, 0, 0, 0, 0, size/2), M2, 0, 0, 0, 0, size/2), M5, 0, 0, 0, 0, size/2);

          //Setting Output matrix to computed values
      		for (int i = 0; i < size / 2; i++) {
      			for (int j = 0; j < size / 2; j++) {
      				m[i][j] = C00[i][j];
      				m[i][j + size/2] = C01[i][j];
      				m[i + size/2][j] = C10[i][j];
      				m[i + size/2][j + size/2] = C11[i][j];
      			}
      		}
        } //End of else
    		return m;
    	}

      public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
      {
      int[][] m = initMatrix(n);
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          m[i][j] = A[i + x1][j + y1] + B[i + x2][j + y2];
        }
      }
      return m;
      }

      public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
      {
        int[][] m = initMatrix(n);
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            m[i][j] = A[i + x1][j + y1] - B[i + x2][j + y2];
          }
        }
        return m;
      }

    public int[][] initMatrix(int n)
    {
        int[][] m = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = 0;
            }
        }
        return m;
    }

    public void printMatrix(int n, int[][] A)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

  public int[][] readMatrix(String filename, int n) throws Exception
  {
      //String workingDir = System.getProperty("\SE 2205-LabAssignment1");
      BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
      String row;
      int[][] m = initMatrix(n);
      for(int i = 0; i < n; i++) {
        row = br.readLine();
        if (row != null) { 
          String[] val = row.split(" ");
          for (int j = 0; j < n; j++) {
            m[i][j] = Integer.parseInt(val[j]);
          }
        }
        else {
          break;
        }
      }
      return m;
      //FileReader, BufferedReader classes ARE PREFERRED*/
  }

}
