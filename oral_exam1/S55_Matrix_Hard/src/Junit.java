import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Junit {

    @Test
    void testEquality() {
        // Tests if two matrices are equal
        double[][] arrayA = {{2, 4, 6}, {8, 10, 12}};
        double[][] arrayB = {{2, 4, 6}, {8, 10, 12}};
        double[][] arrayC = {{1, 3, 5}, {7, 9, 11}};

        Matrix matrix1 = new Matrix(arrayA);
        Matrix matrix2 = new Matrix(arrayB);
        Matrix matrix3 = new Matrix(arrayC);

        assertTrue(matrix1.equals(matrix2)); // Matrices are identical
        assertFalse(matrix1.equals(matrix3)); // Matrices are different
    }

    @Test
    void testIdentityMatrix() {
        // Tests if identity matrix is correctly generated
        Matrix identity2 = Matrix.identity(2);
        Matrix identity3 = Matrix.identity(3);

        assertEquals("1.0 0.0 \n0.0 1.0 \n", identity2.toString());
        assertEquals("1.0 0.0 0.0 \n0.0 1.0 0.0 \n0.0 0.0 1.0 \n", identity3.toString());
    }

    @Test
    void testSubMatrix() {
        // Extracts a submatrix from a given matrix
        double[][] array = {{3, 6, 9}, {12, 15, 18}, {21, 24, 27}};
        Matrix matrix = new Matrix(array);

        Matrix sub1 = matrix.subMatrix(0, 2, 1, 2);
        Matrix sub2 = matrix.subMatrix(1, 3, 0, 2);

        assertEquals("6.0 9.0 \n15.0 18.0 \n", sub1.toString());
        assertEquals("12.0 15.0 \n21.0 24.0 \n", sub2.toString());
    }

    @Test
    void testAddition() {
        // Tests matrix addition (static, instance, and in-place)
        double[][] array1 = {{1, 2}, {3, 4}};
        double[][] array2 = {{5, 6}, {7, 8}};
        Matrix m1 = new Matrix(array1);
        Matrix m2 = new Matrix(array2);

        assertEquals("6.0 8.0 \n10.0 12.0 \n", Matrix.add(m1, m2).toString());
        assertEquals("6.0 8.0 \n10.0 12.0 \n", m1.add(m2).toString());

        m1.addInPlace(m2);
        assertEquals("6.0 8.0 \n10.0 12.0 \n", m1.toString());
    }

    @Test
    void testSubtraction() {
        // Tests matrix subtraction (static, instance, and in-place)
        double[][] array1 = {{10, 9}, {8, 7}};
        double[][] array2 = {{4, 3}, {2, 1}};
        Matrix m1 = new Matrix(array1);
        Matrix m2 = new Matrix(array2);

        assertEquals("6.0 6.0 \n6.0 6.0 \n", Matrix.subtract(m1, m2).toString());
        assertEquals("6.0 6.0 \n6.0 6.0 \n", m1.subtract(m2).toString());

        m1.subtractInPlace(m2);
        assertEquals("6.0 6.0 \n6.0 6.0 \n", m1.toString());
    }

    @Test
    void testScalarMultiplication() {
        // Tests scalar multiplication (static, instance, and in-place)
        double[][] array = {{2, 4}, {6, 8}};
        Matrix m = new Matrix(array);

        assertEquals("4.0 8.0 \n12.0 16.0 \n", Matrix.scalarMultiply(m, 2).toString());
        assertEquals("-2.0 -4.0 \n-6.0 -8.0 \n", m.scalarMultiply(-1).toString());

        m.scalarMultiplyInPlace(0.5);
        assertEquals("1.0 2.0 \n3.0 4.0 \n", m.toString());
    }

    @Test
    void testMultiplication() {
        // Tests matrix multiplication
        double[][] array1 = {{1, 2}, {3, 4}};
        double[][] array2 = {{2, 0}, {1, 2}};
        Matrix m1 = new Matrix(array1);
        Matrix m2 = new Matrix(array2);

        assertEquals("4.0 4.0 \n10.0 8.0 \n", Matrix.multiply(m1, m2).toString());
        assertEquals("4.0 4.0 \n10.0 8.0 \n", m1.multiply(m2).toString());

        m1.multiplyInPlace(m2);
        assertEquals("4.0 4.0 \n10.0 8.0 \n", m1.toString());
    }

    @Test
    void testIncompatibleAddition() {
        // Ensures matrices with different dimensions cannot be added
        double[][] array1 = {{1, 2, 3}, {4, 5, 6}};
        double[][] array2 = {{1, 2}, {3, 4}, {5, 6}};
        Matrix m1 = new Matrix(array1);
        Matrix m2 = new Matrix(array2);

        assertThrows(IllegalArgumentException.class, () -> Matrix.add(m1, m2),
                "Matrices must have the same dimensions.");
    }

    @Test
    void testIncompatibleMultiplication() {
        // Ensures incompatible matrices cannot be multiplied
        double[][] array1 = {{1, 2, 3}, {4, 5, 6}};
        Matrix m1 = new Matrix(array1);
        Matrix m2 = new Matrix(array1);

        assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(m1, m2),
                "Columns of Matrix A must be equal to Rows of Matrix B");
    }
}
