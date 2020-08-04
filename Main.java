package processor;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void printMatrix(double[][] matrix) {
        for (double[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%.2f ", ints[j]);
            }
            System.out.println();
        }
        System.out.println();
        mainMenu();
    }

    public static void addMatrix () {
        Matrix matrixA = new Matrix();
        matrixA.setMatrix(" first", scanner);

        Matrix matrixB = new Matrix();
        matrixB.setMatrix(" second", scanner);

        Matrix matrixResult = new Matrix();
        matrixResult.matrix = new double[matrixA.matrix.length][matrixA.matrix[0].length];
        for (int i = 0; i < matrixA.matrix.length; i++) {
            for (int j = 0; j < matrixA.matrix[0].length; j++) {
                matrixResult.matrix[i][j] = matrixA.matrix[i][j] + matrixB.matrix[i][j];
            }
        }
        System.out.println("The addition result is:");
        printMatrix(matrixResult.matrix);
    }

    public static void multiplyToConstant() {
        Matrix matrixA = new Matrix();
        matrixA.setMatrix("", scanner);

        System.out.println("Enter a constant:");
        int constant = scanner.nextInt();

        System.out.println("The multiplication result is:");
        printMatrix(matrixA.multiplyToConst(matrixA.matrix, constant));
    }

    public static void multiplyMatrices () {
        Matrix matrixA = new Matrix();
        matrixA.setMatrix(" first", scanner);

        Matrix matrixB = new Matrix();
        matrixB.setMatrix(" second", scanner);

        Matrix matrixResult = new Matrix();
        if (matrixA.matrix[0].length != matrixB.matrix.length) {
            System.out.println("Impossible!");
            return;
        }
        matrixResult.matrix = new double[matrixA.matrix.length][matrixB.matrix[0].length];
        for (int i = 0; i < matrixResult.matrix.length; i++) {
            for (int j = 0; j < matrixResult.matrix[0].length; j++) {
                matrixResult.matrix[i][j] = 0;
                for (int k = 0; k < matrixA.matrix[0].length; k++) {
                    matrixResult.matrix[i][j] += matrixA.matrix[i][k] * matrixB.matrix[k][j];
                }
            }
        }
        System.out.println("The multiplication result is:");
        printMatrix(matrixResult.matrix);
    }

    public static void transposeMatrix() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n");
        int choice = scanner.nextInt();

        Matrix matrixA = new Matrix();
        matrixA.setMatrix("", scanner);

        printMatrix(matrixA.transpose(choice));
    }

    public static void inverse() {
        Matrix matrixA = new Matrix();
        matrixA.setMatrix("", scanner);
        double det = matrixA.determinantMatrix(matrixA.matrix);
        if (det == 0) {
            System.out.println("Impossible");
            mainMenu();
        } else {
            System.out.println("The result is:");
            printMatrix(matrixA.identityMatrix(det));
        }
    }

    public static void determinant() {
        Matrix matrixA = new Matrix();
        matrixA.setMatrix("", scanner);
        System.out.println("The result is:");
        System.out.println(matrixA.determinantMatrix(matrixA.matrix));
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("1. Add matrices\n" +
                "2. Multiply matrix to a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit\n");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addMatrix();
                break;
            case 2:
                multiplyToConstant();
                break;
            case 3:
                multiplyMatrices();
                break;
            case 4:
                transposeMatrix();
                break;
            case 5:
                determinant();
                break;
            case 6:
                inverse();
                break;
            case 0:
                scanner.close();
                break;
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
