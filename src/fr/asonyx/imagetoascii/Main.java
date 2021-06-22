package fr.asonyx.imagetoascii;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    /**
     * Input from the console
     */
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Image to ASCII by Asonyx, see https://github.com/Asonyx/ImageToAscii for sources");
        System.out.println("Enter the path to your picture, or drag and drop here :");

        // User file path input
        String filePath = input.nextLine();

        // File from the user file path
        File file = new File(filePath);

        // Check if the file exist
        if (!file.exists()) {
            System.err.println("File doesn't exist");
            return;
        }

        // The image of the file
        BufferedImage image;

        try {
            // Try to read the file
            image = ImageIO.read(file);

        } catch (IOException ignored) {
            // Generic error when reading the image file
            System.err.println("Error, can't read the image");
            return;
        }
        // A StringBuilder, for append every pixel transform into characters
        StringBuilder sb = new StringBuilder();


        // Shift amount : how the image will be small
        int shiftAmount;

        // Get the shift amount by user
        while (true) {

            System.out.println("Enter the shift amount (the bigger it'll be, the smaller the image will be) must be an integer and >= 1");

            try {
                // Read the input as an integer
                shiftAmount = input.nextInt();

                // Check if the input is superior than 1, if not it throw an exception
                if (shiftAmount < 1) throw new IllegalStateException();

                // End the loop
                break;

            } catch (InputMismatchException | IllegalStateException ignored) {

                // User doesn't enter a valid number and re-ask the user for a valid number
                System.err.println("You must enter a valid number");
            }
        }

        // First for loop layer (for the height of the image)
        for (int y = 0; y < image.getHeight(); y++) {

            // Second for loop layer (for the width of the image)
            for (int x = 0; x < image.getWidth(); x++) {
                // Append the character to the StringBuilder
                sb.append(PixelConvertor.convert(-image.getRGB(x, y)));

                // Do it a second time so that the image is not squashed
                sb.append(PixelConvertor.convert(-image.getRGB(x, y)));

                // Apply the shift amount on x
                x += shiftAmount;
            }
            // Adding a new line for the next row a pixels
            sb.append('\n');

            // Apply the shift amount on y
            y += shiftAmount;
        }

        // Printing the result to the console
        System.out.println(sb  + "\n");

        try {
            // Creating the instance of the new file
            File outputFile = new File(file.getParent() + "/" + file.getName().substring(0, file.getName().indexOf('.')) + ".txt");

            // Create the file
            outputFile.createNewFile();

            // Create an output stream for write in the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            // Write the content of sb to the file
            writer.write(sb.toString());

            System.out.println("Content was saved to " + outputFile.getPath());
        } catch (IOException e) {
            // If an error happened
            System.err.println("Error, can't write to the output file ! ( " + e.getMessage() + " )");
        }
    }
}
