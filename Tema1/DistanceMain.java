import distance.TestEditDistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DistanceMain {
    public static void main(String[] args) throws IOException {
        String inputFileName = "./test.in";
        String outputFileName = "./test.out";
        
        // open output file
        File outputFile = new File(outputFileName);
        File existingOutputFile = new File(outputFileName);

        // if output file already exists, delete and generate it
        if (existingOutputFile.delete()) {
            outputFile = new File(outputFileName);
        }

        try {
            // open and read from input file
            File inputFile = new File(inputFileName);
            Scanner readFile = new Scanner(inputFile);
            String testedAlgorithm;

            if (readFile.hasNextLine()) {
                // get the algorithm to be tested
                testedAlgorithm = readFile.nextLine();
            } else {
                // the file is empty
                readFile.close();
                return;
            }
            if (testedAlgorithm.compareTo("Edit Distance") == 0) {
                // the tested algorithm is Edit Distance
                TestEditDistance editDistanceTest = new TestEditDistance(outputFile);

                String str1;
                String str2;
                if (readFile.hasNextLine()) {
                    // get first string
                    str1 = readFile.nextLine();
                } else {
                    // there is no other line in file
                    readFile.close();
                    return;
                }
                if (readFile.hasNextLine()) {
                    // get second string
                    str2 = readFile.nextLine();
                } else {
                    // there is no other line in file
                    readFile.close();
                    return;
                }
                // test Edit Distance functionalities
                editDistanceTest.testEditDistance(str1, str2);
            }
            // close file
            readFile.close();
        } catch (FileNotFoundException exception) {
            // no file found
            System.out.println("Could not find file");
            exception.printStackTrace();
        }
    }
}
