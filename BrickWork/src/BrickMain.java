import java.util.Arrays;
import java.util.Scanner;

public class BrickMain {
    //  Main class that starts the program. I separated some of the methods into different
    // classes. BrickProcessor will check the input, call BrickPlacer,
    // and print the output. BrickPlacer will place the second layer and return it back
    // to BrickProcessor, ready to be printed.

    public static void main(String[] args) {
        // Scanner - reads the console input from the User
        Scanner scanner = new Scanner(System.in);


        // Takes the first 2 Integers of the input to define the size of the 2D array
        // It works by taking the entire line of input, splitting it by any number of white
        // spaces, then streams the result and parses it to integers and adds it as elements
        // of the wall size array
        int[] wallSize = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        //  Making sure that N and M are within the limits, if they are not the program
        // closes
        if (wallSize[0] < 0 || wallSize[0] > 100 || wallSize[1] < 0 || wallSize[1] > 100) {
            System.err.println("N and M should define a valid area of less than 100" +
                    " lines/ columns");
            System.exit(0);
        }

        // Creating the 2D array with size N * M
        int[][] brickWall = new int[wallSize[0]][wallSize[1]];

        //  Calling the readMatrix method and providing the scanner and 2D
        // array in order to fill in the 2D array
        readMatrix(scanner, brickWall);

        //  Custom class to process the 2D array
        // Creating an instance of BrickProcessor grants access to the processMatrix()
        // method.
        BrickProcessor brickProcessor = new BrickProcessor(brickWall);
        brickProcessor.processMatrix();

        //  Prints the transformed array to the user by using the overridden toString()
        // method in the class BrickProcessor
        System.out.println(brickProcessor.toString());
    }


    //  Takes the input from the user to fill the 2D array
    public static void readMatrix(Scanner scanner, int[][] brickWall) {
        //  The string "line" is used to store the user input
        String line;

        //  This 2D array will be used to measure if the user has provided more
        // elements in the row than expected
        int[][] tempWall = new int[brickWall.length][];

        //  Iterating from 0 to brickWall.length makes it so that the user cannot input
        // more rows than expected ( Unless it is copy pasted )
        for (int i = 0; i < brickWall.length; i++) {
            // Reading the input and storing it in the line variable as a String
            line = scanner.nextLine();

            //  Same as in the main method. It takes the user input ( in the form of
            // the line variable ) and fills the current row [i] of the tempWall
            // 2D array
            tempWall[i] = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();

            //  Makes sure that the user input is the same length as the defined
            // size of the brickWall. If true than the values can be added to
            // the brickWall. ( Note: System.arrayCopy didn't work ).
            // If the user has put more or less values than expected, the program will
            // print an error message and close.
            if (tempWall[i].length == brickWall[i].length) {
                brickWall[i] = Arrays.stream(line.split("\\s+"))
                        .mapToInt(Integer::parseInt).toArray();
            } else {
                System.err.println("Wrong size " + tempWall[i].length +
                                " expected size is " + brickWall[i].length);
                System.exit(0);
            }
        }

    }
}
