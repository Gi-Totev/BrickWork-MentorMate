
public class BrickPlacer {
    //  This class is responsible for placing the second layer
    // and returning the new 2D array back to the BrickProcessor class
    int[][] matrix;
    int totalNumberOfBricks; // The number of bricks we have available


    //  Constructor for the BrickPlacer class
    // It gets called in the processMatrix method of the BrickProcessor class
    public BrickPlacer(int[][] matrix) {
        this.matrix = matrix;
        setTotalNumberOfBricks();
    }

    //  Calculates the totalNumberOfBricks by dividing the 2D array size by 2
    // and sets the value to the variable
    public void setTotalNumberOfBricks() {
        this.totalNumberOfBricks = matrix.length * matrix[0].length / 2;
    }

    // Responsible for the second layer
    //
    public int[][] placeSecondLayer() {
        //  This 2D array keeps track of the indexes where a brick has been placed
        boolean[][] bricksPlaced = new boolean[matrix.length][matrix[0].length];

        //  Keeps track of the number of bricks that are placed
        int numberOfBricksPlaced = 0;

        //  Current brick that will be placed in the next available position.
        // In the placement cycles current brick will be increased by 1 every time
        // a brick is placed in the second layer in order to have different numbers
        int currentBrick = 1;

        //  Places the horizontal bricks by comparing the current and next
        // element of the array, if they are different and a brick
        // has not been placed in that index then we need to place
        // a horizontal brick, otherwise it continues to the next index
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(j + 1 < matrix[i].length) {
                    if (matrix[i][j] != matrix[i][j + 1]
                            && !bricksPlaced[i][j] && !bricksPlaced[i][j + 1]) {
                        matrix[i][j] = currentBrick;
                        matrix[i][j + 1] = currentBrick;
                        currentBrick++;
                        bricksPlaced[i][j] = true;
                        bricksPlaced[i][j + 1] = true;
                    }
                }
            }
        }

        //  Places the vertical bricks. Works the same way as the previous cycle but
        // instead compares the current element to the next element in the column
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length ; j++) {
             if(i + 1 < matrix.length) {
                 if(matrix[i][j] != matrix[i+1][j]
                         && !bricksPlaced[i][j] && !bricksPlaced[i+1][j]) {
                     matrix[i][j] = currentBrick;
                     matrix[i+1][j] = currentBrick;
                     currentBrick++;
                     bricksPlaced[i][j] = true;
                     bricksPlaced[i+1][j] = true;
                 }
             }
            }
        }

        //  If all bricks are placed then the second layer is completed.
        // Iterating through the bricksPlaced array too see if all spots were
        // filled. If they were not, then there is no solution and the program
        // prints out -1, No solution was found and closes.
        for (int i = 0; i < bricksPlaced.length; i++) {
            for (int j = 0; j < bricksPlaced[i].length; j++) {
                if (!bricksPlaced[i][j]) {
                    System.out.println("-1");
                    System.out.println("No solution was found");
                    System.exit(0);
                }
            }
        }
        //  Returns the new 2D array (the second layer) back to the BrickProcessor
        // class.
        return this.matrix;
    }
}
