import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Junit {

    @Test
    void isEqual(){
        double[][] test1 = {{1, 2, 3}, {4, 5, 6}};
        double [][] test2 = {{2,3,4}, {5,6,7}};
        Matrix m1 = new Matrix(test1);
        Matrix m2 = new Matrix(test2);

        assertEquals(m1.equals(m1),true);
        assertEquals(m1.equals(m2),false);
    }

    @Test
    void identityTest(){
        Matrix m1 = Matrix.identity(2);
        Matrix m2 = Matrix.identity(5);

        assertEquals(m1.toString(),"1.0 0.0 \n" +
                "0.0 1.0 \n");
        assertEquals(m2.toString(),
                "1.0 0.0 0.0 0.0 0.0 \n"+
                        "0.0 1.0 0.0 0.0 0.0 \n"+
                        "0.0 0.0 1.0 0.0 0.0 \n"+
                        "0.0 0.0 0.0 1.0 0.0 \n"+
                        "0.0 0.0 0.0 0.0 1.0 \n");
    }

    @Test
    void subMatrixTest(){
        double[][] test1 = {{1, 2, 3}, {4, 5, 6}};
        double [][] test2 = {{1,1,1}, {2,2,2},{3,3,3}};
        Matrix m1 = new Matrix(test1);
        Matrix m2 = new Matrix(test2);

        Matrix m3 = m1.subMatrix(0,2,0,1);
        Matrix m4 = m2.subMatrix(1,3,0,2);

        assertEquals(m3.toString(),"1.0 \n"+
                "4.0 \n");
        assertEquals(m4.toString(),"2.0 2.0 \n"+
                "3.0 3.0 \n");
    }



    @Test
    void addTests(){
        double[][] test1 = {{1, 2, 3}, {4, 5, 6}};
        double [][] test2 = {{2,3,4}, {5,6,7}};
        double [][] test3 = {{-2,-3,-4}, {-5,-6,-7}};
        double [][] test4 = {{1,1,1}, {2,2,2},{3,3,3}};
        double [][] test5 = {{2,2,2}, {3,3,3},{4,4,4}};
        Matrix m1 = new Matrix(test1);
        Matrix m2 = new Matrix(test2);
        Matrix m3 = new Matrix(test3);
        Matrix m4 = new Matrix(test4);
        Matrix m5 = new Matrix(test5);

        assertEquals(Matrix.add(m1,m2).toString(),"3.0 5.0 7.0 \n" +
                "9.0 11.0 13.0 \n"); // Static Tests
        assertEquals(Matrix.add(m3,m2).toString(),"0.0 0.0 0.0 \n" +
                "0.0 0.0 0.0 \n");
        assertEquals(Matrix.add(m4,m5).toString(), "3.0 3.0 3.0 \n"+
                "5.0 5.0 5.0 \n"+
                "7.0 7.0 7.0 \n");

        assertEquals(m1.add(m2).toString(),"3.0 5.0 7.0 \n" +
                "9.0 11.0 13.0 \n"); // Instance Tests
        assertEquals(m3.add(m2).toString(),"0.0 0.0 0.0 \n" +
                "0.0 0.0 0.0 \n");
        assertEquals(m4.add(m5).toString(),"3.0 3.0 3.0 \n"+
                "5.0 5.0 5.0 \n"+
                "7.0 7.0 7.0 \n");

        m1.addInPlace(m2);
        assertEquals(m1.toString(),"3.0 5.0 7.0 \n" +
                "9.0 11.0 13.0 \n");

        m1.addInPlace(m3);
        assertEquals(m1.toString(),"1.0 2.0 3.0 \n" +
                "4.0 5.0 6.0 \n");

        m4.addInPlace(m5);
        assertEquals(m4.toString(),"3.0 3.0 3.0 \n"+
                "5.0 5.0 5.0 \n"+
                "7.0 7.0 7.0 \n");

    }

    @Test
    void subtractTests(){
        double[][] test1 = {{1, 2, 3}, {4, 5, 6}};
        double [][] test2 = {{2,3,4}, {5,6,7}};
        double [][] test3 = {{-2,-3,-4}, {-5,-6,-7}};
        double [][] test4 = {{1,1,1}, {2,2,2},{3,3,3}};
        double [][] test5 = {{2,2,2}, {3,3,3},{4,4,4}};
        Matrix m1 = new Matrix(test1);
        Matrix m2 = new Matrix(test2);
        Matrix m3 = new Matrix(test3);
        Matrix m4 = new Matrix(test4);
        Matrix m5 = new Matrix(test5);

        assertEquals(Matrix.subtract(m1,m2).toString(),"-1.0 -1.0 -1.0 \n" +
                "-1.0 -1.0 -1.0 \n"); // Static Tests
        assertEquals(Matrix.subtract(m3,m2).toString(),"-4.0 -6.0 -8.0 \n" +
                "-10.0 -12.0 -14.0 \n");
        assertEquals(Matrix.subtract(m4,m5).toString(), "-1.0 -1.0 -1.0 \n"+
                "-1.0 -1.0 -1.0 \n"+
                "-1.0 -1.0 -1.0 \n");


        assertEquals(m1.subtract(m2).toString(),"-1.0 -1.0 -1.0 \n" +
                "-1.0 -1.0 -1.0 \n"); // Instance Tests
        assertEquals(m3.subtract(m2).toString(),"-4.0 -6.0 -8.0 \n" +
                "-10.0 -12.0 -14.0 \n");
        assertEquals(m4.subtract(m5).toString(),"-1.0 -1.0 -1.0 \n"+
                "-1.0 -1.0 -1.0 \n"+
                "-1.0 -1.0 -1.0 \n");

        m1.subtractInPlace(m2);
        assertEquals(m1.toString(),"-1.0 -1.0 -1.0 \n" +
                "-1.0 -1.0 -1.0 \n");

        m1.subtractInPlace(m3);
        assertEquals(m1.toString(),"1.0 2.0 3.0 \n" +
                "4.0 5.0 6.0 \n");

        m4.subtractInPlace(m5);
        assertEquals(m4.toString(),"-1.0 -1.0 -1.0 \n"+
                "-1.0 -1.0 -1.0 \n"+
                "-1.0 -1.0 -1.0 \n");


    }

    @Test
    void scalerTests(){
        double[][] test1 = {{1, 2, 3}, {4, 5, 6}};
        double [][] test2 = {{2,2,2}, {3,3,3},{4,4,4}};
        Matrix m1 = new Matrix(test1);
        Matrix m2 = new Matrix(test2);

        assertEquals(Matrix.scalarMultiply(m1,2).toString(),"2.0 4.0 6.0 \n" +
                "8.0 10.0 12.0 \n"); // Static Tests
        assertEquals(Matrix.scalarMultiply(m1,-2).toString(),"-2.0 -4.0 -6.0 \n" +
                "-8.0 -10.0 -12.0 \n");
        assertEquals(Matrix.scalarMultiply(m2,0.5).toString(), "1.0 1.0 1.0 \n"+
                "1.5 1.5 1.5 \n"+
                "2.0 2.0 2.0 \n");

        assertEquals(m1.scalarMultiply(2).toString(),"2.0 4.0 6.0 \n" +
                "8.0 10.0 12.0 \n"); // Instance Tests
        assertEquals(m1.scalarMultiply(-2).toString(),"-2.0 -4.0 -6.0 \n" +
                "-8.0 -10.0 -12.0 \n");
        assertEquals(m2.scalarMultiply(0.5).toString(), "1.0 1.0 1.0 \n"+
                "1.5 1.5 1.5 \n"+
                "2.0 2.0 2.0 \n");

        m1.scalarMultiplyInPlace(2);
        assertEquals(m1.toString(),"2.0 4.0 6.0 \n" +
                "8.0 10.0 12.0 \n");

        m1.scalarMultiplyInPlace(-1);
        assertEquals(m1.toString(),"-2.0 -4.0 -6.0 \n" +
                "-8.0 -10.0 -12.0 \n");

        m2.scalarMultiplyInPlace(0.5);
        assertEquals(m2.toString(),"1.0 1.0 1.0 \n"+
                "1.5 1.5 1.5 \n"+
                "2.0 2.0 2.0 \n");

    }

    @Test
    void multiplyTests(){

        double[][] test1 = {{1, 2, 3}, {4, 5, 6}};
        double [][] test2 = {{1,2},{3,4},{5,6}};
        Matrix m1 = new Matrix(test1);
        Matrix m2 = new Matrix(test2);
        Matrix m3 = new Matrix(test1);

        assertEquals(Matrix.multiply(m1,m2).toString(),"22.0 28.0 \n" +
                "49.0 64.0 \n"); // Static Tests

        assertEquals(Matrix.multiply(m2,m1).toString(),"9.0 12.0 15.0 \n" +
                "19.0 26.0 33.0 \n"+
                "29.0 40.0 51.0 \n");


        assertEquals(m1.multiply(m2).toString(),"22.0 28.0 \n" +
                "49.0 64.0 \n"); // Instance Tests

        assertEquals(m2.multiply(m1).toString(),"9.0 12.0 15.0 \n" +
                "19.0 26.0 33.0 \n"+
                "29.0 40.0 51.0 \n");


        m1.multiplyInPlace(m2);
        assertEquals(m1.toString(),"22.0 28.0 \n" +
                "49.0 64.0 \n");

        m2.multiplyInPlace(m3);
        assertEquals(m2.toString(),"9.0 12.0 15.0 \n" +
                "19.0 26.0 33.0 \n"+
                "29.0 40.0 51.0 \n");
    }

    @Test
    void sameSizeTest() {
        double[][] test1 = {{1, 2, 3}, {4, 5, 6}};
        double[][] test2 = {{1, 2}, {3, 4}, {5, 6}};
        Matrix m1 = new Matrix(test1);
        Matrix m2 = new Matrix(test2);

        assertThrows(IllegalArgumentException.class, () -> Matrix.add(m1, m2),
                "Matrices must have the same dimensions.");
    }
    @Test
    void abletoMultiplyTest() {
        double[][] test1 = {{1, 2, 3}, {4, 5, 6}};
        Matrix m1 = new Matrix(test1);
        Matrix m2 = new Matrix(test1);

        assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(m1, m2),
                "Columns of Matrix A must be equal to Rows of Matrix B");
    }





}


