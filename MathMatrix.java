import java.util.Arrays;

// MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
*
*  On my honor, Dominic Paruolo, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: dmp3588
*  email address: dominicparuolo78@gmail.com
*  Unique section number: 52615
*  Number of slip days I am using: 0
*/

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {

    // instance variable
	private int[][] values;

    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0 || !rectangularMatrix(mat)) {
        	throw new IllegalArgumentException("Violation of precondition: Math.Matrix. " +
        			 "The parameter must not be null, must have at least one row " +
        			 "must have at least one column, and must be rectangular.");
        }
        //creates array
        values = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
        	for (int j = 0; j < mat[0].length; j++) {
        		values[i][j] = mat[i][j];
        	}
        }
    }


    /**
     * create a MathMatrix of the specified size with all cells set to the intialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns.
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
        if (numRows <= 0 || numCols <= 0) {
        	throw new IllegalArgumentException("Violation of precondition: MathMatrix." +
                     "Both parameters must be greater than zero.");
        }
        //creates array
        values = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
        	for (int j = 0; j < numCols; j++) {
        		values[i][j] = initialVal;
        	}
        }
    }

    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
        return values.length;
    }


    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns(){
        return values[0].length;
    }


    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()
     * @param  row  0 <= row < getNumRows()
     * @param  col  0 <= col < getNumColumns()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
    	if (row < 0 || row >= getNumRows() || col < 0 || col >= getNumColumns()) {
    		throw new IllegalArgumentException("Violation of precondtion: getVal." +
    	             "Both parameters must be greater than or equal to zero, " +
    				 "row must be less than getNumRows(), " +
    				 "and col must be less than getNumColumns.");
    	}
        return values[row][col];
    }


    /**
     * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
     * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned Matrix is equal to the number of columns
     * in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide){
    	if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows()
    			|| rightHandSide.getNumColumns() != getNumColumns()) {
    		throw new IllegalArgumentException("Violation of precondtion: add." +
    		         "Parameter must not be null and must have an equal number of rows " +
    				 "and columns to this MathMatrix's rows and columns.");
    	}
    	//adds values
    	MathMatrix sum = new MathMatrix(values);
    	for (int i = 0; i < getNumRows(); i++) {
    		for (int j = 0; j < getNumColumns(); j++) {
    			sum.values[i][j] = sum.getVal(i, j) + rightHandSide.getVal(i, j);
    		}
    	}
        return sum;
    }


    /**
     * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide
     * from this MathMatrix. The number of rows in the returned MathMatrix is equal to the number
     * of rows in this MathMatrix.The number of columns in the returned MathMatrix is equal to
     * the number of columns in this MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide){
    	if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows()
    			|| rightHandSide.getNumColumns() != getNumColumns()) {
    		throw new IllegalArgumentException("Violation of precondtion: add." +
    		         "Parameter must not be null and must have an equal number of rows " +
    				 "and columns to this MathMatrix's rows and columns.");
    	}
    	//subtracts values
    	MathMatrix difference = new MathMatrix(values);
    	for (int i = 0; i < getNumRows(); i++) {
    		for (int j = 0; j < getNumColumns(); j++) {
    			difference.values[i][j] = difference.getVal(i, j) - rightHandSide.getVal(i, j);
    		}
    	}
        return difference;
    }


    /**
     * implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() = getNumColumns()
     * <br>post: This method should not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying 
     * this MathMatrix and rightHandSide.
     * The number of rows in the returned MathMatrix is equal to the number of rows
     * in this MathMatrix. The number of columns in the returned MathMatrix is equal to the number
     * of columns in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide){
    	if (rightHandSide == null || rightHandSide.getNumRows() != getNumColumns()) {
    		throw new IllegalArgumentException("Violation of precondtion: multiply. " +
    	             "Parameter must not equal null and must have an equal number of rows" +
    		         "to this Math.Matrix's rows.");
    	}
    	//new MathMatrix that is product of other matrices
    	MathMatrix product = new MathMatrix(getNumRows(), rightHandSide.getNumColumns(), 0);
    	for (int i = 0; i < product.getNumRows(); i++) {
    		for (int j = 0; j < product.getNumColumns(); j++) {
    			int currentProduct = 0;
    			//multiplies values
    			for (int k = 0; k < getNumColumns(); k++) {
    				currentProduct += (getVal(i, k) * rightHandSide.getVal(k, j));
    			}
    			//updates product array
    			product.values[i][j] = currentProduct;
    		}
    	}
        return product;
    }


    /**
     * Create and return a new Matrix that is a copy
     * of this matrix, but with all values multiplied by a scale
     * value.
     * <br>pre: none
     * <br>post: returns a new Matrix with all elements in this matrix
     * multiplied by factor.
     * In other words after this method has been called
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     * for all valid r and c.
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     * values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {
    	MathMatrix scaled = new MathMatrix(values);
    	//scales values by a factor
    	for (int i = 0; i < scaled.getNumRows(); i++) {
    		for (int j = 0; j < scaled.getNumColumns(); j++) {
    			scaled.values[i][j] *= factor;
    		}
    	}
        return scaled;
    }


    /**
     * accessor: get a transpose of this MathMatrix.
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
    	MathMatrix transposed = new MathMatrix(getNumColumns(), getNumRows(), 0);
    	//transposes the matrix
    	for (int i = 0; i < transposed.getNumRows(); i++) {
    		for (int j = 0; j < transposed.getNumColumns(); j++) {
    			transposed.values[i][j] = getVal(j, i);
    		}
    	}
        return transposed;
    }


    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide){
        /* CS314 Students. The following is standard equals
         * method code. We will learn about in the coming weeks.
         *
         * We use getClass instead of instanceof because we only want a MathMatrix to equal
         * another MathMatrix as opposed to any possible sub classes. We would
         * use instance of if we were implementing am interface and wanted to equal
         * other objects that are instances of that interface but not necessarily
         * MathMatrix objects.
         */

        if (rightHandSide == null || this.getClass() != rightHandSide.getClass()) {
            return false;
        }
        // We know rightHandSide refers to a non-null MathMatrix object, safe to cast.
        MathMatrix otherMathMatrix = (MathMatrix) rightHandSide;
        // Now we can access the private instance variables of otherMathMatrix
        // and / or call MathMatrix methods on otherMathMatrix.

        // Finds if this MathMatrix and rightHandSide are the samesize.
        if (getNumRows() != otherMathMatrix.getNumRows() || getNumColumns() != otherMathMatrix.getNumColumns()) {
        	return false;
        }
        
        // Finds if this MathMatrix and rightHandSide have the same values.
        for (int i = 0; i < getNumRows(); i++) {
        	for (int j = 0; j < getNumColumns(); j++) {
        		if (getVal(i, j) != otherMathMatrix.getVal(i, j)) {
        			return false;
        		}
        	}
        }
        return true; // CS314 students, alter as necessary
    }


    /**
     * override toString.
     * @return a String with all elements of this MathMatrix.
     * Each row is on a separate line.
     * Spacing based on longest element in this Matrix.
     */
    public String toString() {
    	
    	// Finds longest value in the matrix.
    	int longestValue = 0;
    	for (int i = 0; i < getNumRows(); i++) {
    		for (int j = 0; j < getNumColumns(); j++) {
    			if (longestValue < ("" + getVal(i, j)).length()) {
    				longestValue = ("" + getVal(i, j)).length();
    			}
    		}
    	}
    	
    	// Appends values of values[][] to StringBuilder
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < getNumRows(); i++) {
    		sb.append("|");
    		for (int j = 0; j < getNumColumns(); j++) {
    			//longestValue + 1 for number of spaces
    			for (int k = 0; k < longestValue + 1 - ("" + getVal(i, j)).length(); k++) {
    				sb.append(" ");
    			}
    			sb.append(getVal(i, j));
    		}
    		sb.append("|\n");
    	}
        return sb.toString();
    }



    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main
     * diagonal must be 0.<br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise.
     */
    public boolean isUpperTriangular(){
    	if (getNumRows() != getNumColumns()) {
    		throw new IllegalArgumentException("Violation of precondition: isUpperTriangular. " +
    	             "The number of rows and columns in this MathMatrix must be equal.");
    	}
    	//finds if matrix is triangular
    	for (int j = 0; j < getNumRows(); j++) {
    		for (int i = j + 1; i < getNumColumns(); i++) {
    			if (getVal(i, j) != 0) {
    				return false;
    			}
    		}
    	}
        return true;
    }

    // method to ensure mat is rectangular. It is public so that
    // tester classes can use it.
    // pre: mat != null, mat has at least one row
    // return true if all rows in mat have the same
    // number of columns false otherwise.
    public static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }

}