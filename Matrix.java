package processor;

import java.util.Scanner;

public class Matrix {
    public double[][] matrix;

    public void setMatrix(String str, Scanner scanner) {
        System.out.printf("Enter size of%s matrix:%n", str);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.printf("Enter%s matrix:%n", str);
        matrix = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    public double[][] subMatrix (double[][] matrix,int raw, int column) {
        double [][] newMatrix = new double[matrix.length - 1][matrix[0].length - 1];
        for (int i = 0, n = 0; i < matrix.length && n < newMatrix.length; i ++) {
            if (i == raw) {
                continue;
            }
            for (int j = 0, m = 0; j < matrix[0].length && m < newMatrix[0].length; j++) {
                if (j == column) {
                    continue;
                }
                newMatrix[n][m] = matrix[i][j];
                m++;
            }
            n++;
        }
        return newMatrix;
    }

    public double determinantMatrix (double[][] matrix) {
        if (matrix.length == 2 && matrix[0].length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            double det = 0;
            for (int i = 0; i < matrix.length; i++) {
                det += matrix[0][i] * Math.pow(-1, i + 2) * determinantMatrix(subMatrix(matrix,0, i));
            }
            return det;
        }
    }

    public double[][] multiplyToConst(double[][] matrix, double constant) {
        double [][] matrixResult = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrixResult[i][j] = matrix[i][j] * constant;
            }
        }
        return matrixResult;
    }

    public double[][] identityMatrix (double det) {
        double[][] transposeMatrix = transpose(1);
        double[][] matrixOfCofactors = new double[transposeMatrix.length][transposeMatrix[0].length];
        for (int i = 0; i < matrixOfCofactors.length; i++) {
            for (int j = 0; j < matrixOfCofactors[0].length; j++) {
                matrixOfCofactors[i][j] = Math.pow(-1, i + j + 2) * determinantMatrix(subMatrix(transposeMatrix, i, j));
            }
        }
        return multiplyToConst(matrixOfCofactors, (1 / det));
    }

    public double[][] transpose(int number) {
        double[][] resultMatrix = new double[matrix.length][matrix[0].length];
        switch (number) {
            case 1:
                resultMatrix = new double[matrix[0].length][matrix.length];
                for (int i = 0; i < resultMatrix.length; i++) {
                    for (int j = 0; j < resultMatrix[0].length; j++) {
                        resultMatrix[j][i] = matrix[i][j];
                    }
                }
                break;
            case 2:
                for (int i = 0, k = matrix[0].length - 1; i < resultMatrix.length && k >= 0; i++, k--) {
                    for (int j = 0, n = matrix.length - 1; j < resultMatrix[0].length && n >= 0; j++, n--) {
                        resultMatrix[i][j] = matrix[n][k];
                    }
                }
                break;
            case 3:
                for (int i = 0; i < resultMatrix.length; i++) {
                    for (int j = 0; j < resultMatrix[0].length; j++) {
                        resultMatrix[i][j] = matrix[i][matrix[0].length - 1 - j];
                    }
                }
                break;
            default:
                for (int i = 0; i < resultMatrix.length; i++) {
                    System.arraycopy(matrix[matrix.length - 1 - i], 0, resultMatrix[i], 0, resultMatrix[0].length);
                }

        }
        return resultMatrix;
    }
}
