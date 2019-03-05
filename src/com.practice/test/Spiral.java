package test;

public class Spiral {
    public static void main(String[] args) {
        int[][] A = new int[][]{};
        A[0][0] = 1;
        A[0][1] = 2;
        A[1][0] = 3;
        A[1][1] = 4;
        spiral(A, 1,1);
    }

    /*
    [1 2
    3 4]

    Print it out : 1243

    Matrix

    [1 2 3
    4  5 6
    7 8 9]

    Print it out  123654 789


     */
    public static void spiral (int [] [] A, int rows, int cols){
        for(rows = 0; rows < A.length; rows++){
            for(cols=A.length-1;cols>0;cols--){
                System.out.println(A[rows][cols]+"\t");
            }
        }
    }
}
