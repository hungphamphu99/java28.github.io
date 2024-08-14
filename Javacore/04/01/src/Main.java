import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of row in matrix A");
        int rowA = scanner.nextInt();
        System.out.println("Enter the number of row in matrix A");
        int columA = scanner.nextInt();

        System.out.println("Enter the number of row in matrix B");
        int rowB = scanner.nextInt();
        System.out.println("Enter the number of colum in matrix B");
        int columB = scanner.nextInt();

        if (rowA != rowB || columA != columB) {
            System.out.println("Error: rows and columns do not match.");
        } else {
            int[][] matrixA = new int[rowA][columA];
            int[][] matrixB = new int[rowB][columB];
            int[][] matrixC = new int[rowA][columA];

            System.out.println("Enter the elements A:");
            for (int i = 0; i < rowA; i++) {
                for (int j = 0; j < columA; j++) {
                    System.out.print("A[" + i + "][" + j + "]: ");
                    matrixA[i][j] = scanner.nextInt();
                }
            }
            for (int i = 0; i < rowA; i++) {
                for (int j = 0; j < columA; j++) {
                    System.out.print(matrixA[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Enter the elements B:");
            for (int i = 0; i < rowB; i++) {
                for (int j = 0; j < columB; j++) {
                    System.out.print("B[" + i + "][" + j + "]: ");
                    matrixB[i][j] = scanner.nextInt();
                }
            }
            for (int i = 0; i < rowA; i++) {
                for (int j = 0; j < columA; j++) {
                    System.out.print(matrixB[i][j] + " ");
                }
                System.out.println();
            }

            for (int i = 0; i < rowA; i++) {
                for (int j = 0; j < columA; j++) {
                    matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
                }
            }

            System.out.println("Sum :");
            for (int i = 0; i < rowA; i++) {
                for (int j = 0; j < columA; j++) {
                    System.out.print(matrixC[i][j] + " ");
                }
                System.out.println();
            }
        }

    }
}
