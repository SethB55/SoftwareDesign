/**
 * Matrix class supporting common matrix operations. The frame of the code.
 * All elements are represented with double precision.
 */
public class Matrix {
    private double[][] data; // Internal representation of the matrix
    private int rows;
    private int columns;

    // =========================
    // Constructors
    // =========================

    /**
     * Constructs a matrix with specified dimensions, initialized with zeros.
     *
     * @param rows    Number of rows in the matrix
     * @param columns Number of columns in the matrix
     */
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
    }

    /**
     * Constructs a matrix from a given 2D array.
     *
     * @param values A 2D array of doubles representing matrix elements
     */
    public Matrix(double[][] values) {
        this.rows = values.length;
        this.columns = values[0].length;
        this.data = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] = values[i][j];
            }
        }
    }

    // =========================
    // Basic Getters and Setters
    // =========================

    /**
     * Sets the value at a specified matrix position.
     *
     * @param row   Row index
     * @param col   Column index
     * @param value Value to be set
     */
    public void set(int row, int col, double value) {
        data[row][col] = value;
    }

    /**
     * Retrieves the value at a specified matrix position.
     *
     * @param row Row index
     * @param col Column index
     * @return Value at the specified position
     */
    public double get(int row, int col) {
        return data[row][col];
    }

    // =========================
    // Utility Methods
    // =========================

    /**
     * Checks if this matrix is equal to another matrix.
     * Matrices are equal if they have the same dimensions and identical elements.
     *
     * @param other Matrix to compare against
     * @return True if matrices are identical, otherwise false
     */
    public boolean equals(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            return false;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (this.data[i][j] != other.data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Generates an identity matrix of given size.
     *
     * @param size The size of the square identity matrix
     * @return A new identity matrix
     */
    public static Matrix identity(int size) {
        Matrix identity = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            identity.set(i, i, 1.0);
        }
        return identity;
    }

    /**
     * Extracts a submatrix from this matrix.
     *
     * @param rowStart Starting row index (inclusive)
     * @param rowEnd   Ending row index (exclusive)
     * @param colStart Starting column index (inclusive)
     * @param colEnd   Ending column index (exclusive)
     * @return A new submatrix
     */
    public Matrix subMatrix(int rowStart, int rowEnd, int colStart, int colEnd) {
        if (rowStart < 0 || rowEnd > this.rows || colStart < 0 || colEnd > this.columns || rowStart >= rowEnd || colStart >= colEnd) {
            throw new IllegalArgumentException("Invalid submatrix indices.");
        }
        double[][] sub = new double[rowEnd - rowStart][colEnd - colStart];
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                sub[i - rowStart][j - colStart] = this.data[i][j];
            }
        }
        return new Matrix(sub);
    }

    // =========================
    // Matrix Arithmetic
    // =========================

    /**
     * Add method(Static)
     * Inputs: Matrix a, Matrix B
     * Outputs: A new Matrix object
     */
    public static Matrix add(Matrix a, Matrix b) {
        checkSameSize(a, b);
        Matrix result = new Matrix(a.rows, a.columns);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.columns; j++) { // Adds the 2 elements of the matrices and then places sum
                result.data[i][j] = a.data[i][j] + b.data[i][j];
            }
        }
        return result;
    }

    /**
     * Add method(Instance)
     * Inputs: Matrix Other
     * Outputs: A new Matrix object
     */
    public Matrix add(Matrix other) { // Uses same logic as Static method
        return add(this, other);
    }

    /**
     * Add method(In Place)
     * Inputs: Matrix Other
     * Outputs: None, modifies current matrix object
     */
    public void addInPlace(Matrix other) {
        checkSameSize(this, other);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) { // Adds the values from other matrix and modifies the current matrix
                this.data[i][j] += other.data[i][j];
            }
        }
    }

    // Subtraction operations

    /**
     * Subtract method(Static)
     * Inputs: Matrix a, Matrix B
     * Outputs: A new Matrix object
     */
    public static Matrix subtract(Matrix a, Matrix b) {
        checkSameSize(a, b);
        Matrix result = new Matrix(a.rows, a.columns); // Creates new matrix of proper size
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.columns; j++) { // Subtracts the 2 elements of the matrices and places difference into the proper spot in new matrix
                result.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        return result;
    }

    /**
     * Subtract method(Instance)
     * Inputs: Matrix Other
     * Outputs: A new Matrix object
     */
    public Matrix subtract(Matrix other) { // Uses same logic as Static method
        return subtract(this, other);
    }

    /**
     * Add method(In Place)
     * Inputs: Matrix Other
     * Outputs: None, modifies current matrix object
     */
    public void subtractInPlace(Matrix other) {
        checkSameSize(this, other);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] -= other.data[i][j];
            }
        }
    }


    /**
     * Scalar Multiply Method(Static)
     * Inputs: Matrix A, Scalar(double)
     * Outputs: A new Matrix object
     */
    public static Matrix scalarMultiply(Matrix a, double scalar) {

        Matrix result = new Matrix(a.rows, a.columns);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.columns; j++) {
                result.data[i][j] = a.data[i][j] * scalar;
            }
        }
        return result;
    }

    /**
     * Scalar Multiply Method(Instance)
     * Inputs: Scalar(double)
     * Outputs: A new Matrix object
     */
    public Matrix scalarMultiply(double scalar) { // Uses same logic as Static method
        return scalarMultiply(this, scalar);
    }

    /**
     * Scalar Multiply Method(In Place)
     * Inputs: Scalar(double)
     * Outputs: None, modifies current Matrix object
     */
    public void scalarMultiplyInPlace(double scalar) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] = this.data[i][j] * scalar;
            }
        }
    }



    /**
     * Multiply Method(Static)
     * Inputs: Matrix a, Matrix b
     * Outputs: A new Matrix object
     */
    public static Matrix multiply(Matrix a, Matrix b) {

        checkAbleToMultiply(a, b);
        Matrix result = new Matrix(a.rows, b.columns); // Creates new matrix

        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < b.columns; j++) {

                double sum = 0; // Creates value to be stored
                for (int k = 0; k < a.columns; k++) {
                    sum += a.get(i, k) * b.get(k, j);
                }
                result.set(i, j, sum);
            }
        }

        return result;
    }

    /**
     * Multiply Method(Instance)
     * Inputs: Matrix other
     * Outputs: A new Matrix object
     */
    public Matrix multiply(Matrix other){
        return multiply(this, other);
    }

    /**
     * Multiply Method(In Place)
     * Inputs: Matrix other
     * Outputs: None, modifies current Matrix object
     */
    public void multiplyInPlace(Matrix other) {
        checkAbleToMultiply(this, other);

        // Create a new matrix to store result
        Matrix result = new Matrix(this.rows, other.columns);

        // Same logic to compute as static method
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                double sum = 0;
                for (int k = 0; k < this.columns; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        this.rows = result.rows;
        this.columns = result.columns;
        this.data = result.data;
    }
    // =========================
    // Helper Methods for Validations
    // =========================

    /**
     * Ensures two matrices have the same dimensions before performing operations.
     *
     * @param a First matrix
     * @param b Second matrix
     * @throws IllegalArgumentException If matrix dimensions do not match
     */
    private static void checkSameSize(Matrix a, Matrix b) {
        if (a.rows != b.rows || a.columns != b.columns) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }
    }

    /**
     * Ensures two matrices can be multiplied.
     *
     * @param a First matrix
     * @param b Second matrix
     * @throws IllegalArgumentException If the matrices cannot be multiplied
     */
    private static void checkAbleToMultiply(Matrix a, Matrix b) {
        if (a.columns != b.rows) {
            throw new IllegalArgumentException("Columns of Matrix A must be equal to Rows of Matrix B");
        }
    }

    // =========================
    // toString Method
    // =========================

    /**
     * Converts the matrix to a formatted string representation.
     *
     * @return String representation of the matrix
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.append(data[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
