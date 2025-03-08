public class MainDriver {
    public static void main(String[] args) { //made sure it shows all functionality in a pretty way

        // Creating matrices for testing
        double[][] dataA = {{3, 5, 7}, {2, 4, 6}};
        double[][] dataB = {{1, 3, 5}, {7, 9, 11}};
        double[][] dataC = {{2, 4}, {6, 8}, {10, 12}};

        Matrix matA = new Matrix(dataA);
        Matrix matB = new Matrix(dataB);
        Matrix emptyMat = new Matrix(2, 3);
        Matrix diffSizeMat = new Matrix(3, 2);

        // Testing Equality
        System.out.println("matA == matA: " + matA.equals(matA)); // Should be true
        System.out.println("matA == matB: " + matA.equals(matB)); // Should be false
        System.out.println("matA == diffSizeMat: " + matA.equals(diffSizeMat)); // Should be false

        // Testing Identity Matrix
        Matrix identityMat = Matrix.identityMat(3);
        System.out.println("Identity Matrix (Size 3):\n" + identityMat);

        // Testing Submatrix
        Matrix subMat = matA.subMatrix(0, 1, 0, 2);
        System.out.println("Submatrix of matA (First Row, First Three Columns):\n" + subMat);

        // Testing Addition
        System.out.println("Matrix A:\n" + matA);
        System.out.println("Matrix B:\n" + matB);

        System.out.println("Addition (Static Method):\n" + Matrix.add(matA, matB));
        System.out.println("Addition (Instance Method):\n" + matA.add(matB));

        matA.addInPlace(matB);
        System.out.println("Addition (In-Place):\n" + matA);

        // Testing Subtraction
        Matrix matC = new Matrix(dataA);
        Matrix matD = new Matrix(dataB);

        System.out.println("Subtraction (Static Method):\n" + Matrix.subtract(matC, matD));
        System.out.println("Subtraction (Instance Method):\n" + matC.subtract(matD));

        matC.subtractInPlace(matD);
        System.out.println("Subtraction (In-Place):\n" + matC);

        //Testing Scalar Multiplication
        Matrix matE = new Matrix(dataB);

        System.out.println("Scalar Multiplication (Static, ×2):\n" + Matrix.scalarMultiply(matE, 2));
        System.out.println("Scalar Multiplication (Instance, ×3):\n" + matE.scalarMultiply(3));

        matE.scalarMultiplyInPlace(0.5);
        System.out.println("Scalar Multiplication (In-Place, ÷2):\n" + matE);

        //Testing Matrix Multiplication
        Matrix matF = new Matrix(dataA);
        Matrix matG = new Matrix(dataC);

        System.out.println("Matrix F:\n" + matF);
        System.out.println("Matrix G:\n" + matG);

        System.out.println("Multiplication (Static Method):\n" + Matrix.multiply(matF, matG));
        System.out.println("Multiplication (Instance Method):\n" + matF.multiply(matG));

        matF.multiplyInPlace(matG);
        System.out.println("Multiplication (In-Place):\n" + matF);
    }
}
