
public class BrickProcessor {
    //  This class will will call the processMatrix(), then it will use the
    // checkForLargerBricks() method to validate that the input is correct.

    private int[][] matrix;

    // Constructor method to get the 2D array from the Main class
    public BrickProcessor(int[][] matrix) {
        this.matrix = matrix;
    }

    // Getter that will return the 2D array to the Main class


    //  This method calls in the rest of the methods in this class:
    // 1. It calls the checkForLargerBricks to validate that the input is correct
    // 2. Creates an instance of the BrickPlacer class
    // 3. Calls the placeSecondLayer method that will generate the second layer
    // and sets the 2D array that was returned from the placeSecondLayer method.
    public void processMatrix() {
        checkForLargerBricks();
        BrickPlacer brickPlacer = new BrickPlacer(this.matrix);
        this.matrix = brickPlacer.placeSecondLayer();
    }


    //  Checks for Bricks that are larger than 1*2, if such a brick is found
    // it prints a message and the program closes
    private void checkForLargerBricks() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(j + 2 < matrix[i].length) {
                    if (matrix[i][j] == matrix[i][j + 1] && matrix[i][j] == matrix[i][j + 2]) {
                        System.err.println("Found a Brick wider than 2");
                        System.exit(0);
                    }
                }
                if(i + 2 < matrix.length) {
                    if (matrix[i][j] == matrix[i + 1][j] && matrix[i][j] == matrix[i + 2][j]) {
                        System.err.println("Found a Brick longer than 2");
                        System.exit(0);
                    }
                }
            }
        }
    }

    // To string method responsible for returning the 2D array as a String
    @Override
    public String toString() {
        // StringBuilder makes it easier to shape the output
        StringBuilder sb = new StringBuilder();

        //  This line creates the first row of the output. The first row
        // will always be * only.
        sb.append("**").append("****".repeat(matrix[0].length))
                .append("*").append(System.lineSeparator());

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //  If j = 0 then current element is in the first column of the array
                // and we have to add an *. The first column will always be
                // * only
                if(j == 0) {
                    sb.append("* ");
                }


                if(matrix[i][j] < 10) {
                    sb.append(" ").append(matrix[i][j]);
                } else {
                    sb.append(matrix[i][j]);
                }

                //   If j = the length of the matrix row - 1 that means that the current
                //  element is in the last column and if we try to get the index of
                //  lastColumn + 1, it will produce a Null Pointer Exception and
                //  since there is no element after the element in the last column,
                //  then there can be no more horizontal bricks
                if(j < matrix[i].length -1) {
                    if (matrix[i][j] == matrix[i][j + 1]) {
                        sb.append(" ");
                    } else {
                        sb.append(" * ");
                    }
                }

                // Similar to the first column of the output it has to end with a *
                if(j == matrix[i].length - 1) {
                    sb.append(" *").append(System.lineSeparator()).append("*");
                    //  After it goes to a new line with lineSeparator(),
                    // it's time to place the other * for the bricks
                    // This time the difference is that if the current element and
                    // the next one are the same in the 2D array it will print out *
                    // and skip one element by increasing k with 1
                    // if they aren't equal it will print out spaces until it gets to
                    // the next brick
                    for (int k = 0; k < matrix[i].length; k++) {
                        if(i + 1 < matrix.length && k < matrix[i].length - 1) {
                            if (matrix[i][k] == matrix[i][k+1]) {
                                sb.append("********");
                                k++;
                            } else {
                                sb.append("    *");
                            }
                        }
                        //  If we are at the last element of the row of the array,
                        // we compare the value of the current element and the previous
                        // one and print out the appropriate divider
                        else if (i + 1 < matrix.length && k == matrix[i].length - 1) {
                            if(matrix[i][k] == matrix[i][k-1]) {
                                sb.append("********");
                            } else {
                                sb.append("    *");
                            }
                        }
                    }
                }

            }

            if(i + 1 < matrix.length) {
                sb.append(System.lineSeparator());
            }
        }

        //  Same as the first row of the output. The last row will always be a number of *
        sb.append("**").append("****".repeat(matrix[matrix.length - 1].length));

        //  Returns the StringBuilder as a String back to the BrickMain class so that it
        //  can be printed out
        return sb.toString();
    }
}
