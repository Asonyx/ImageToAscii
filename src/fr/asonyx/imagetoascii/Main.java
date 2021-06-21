package fr.asonyx.imagetoascii;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    /**
     * Input from the console
     */
    public static Scanner input = new Scanner(System.in);

    /**
     * The max value of the RGB (#FFFFFF)
     */
    public static final int maxRGB = 16777215;

    /**
     * The max RGB value divide by 10 (for "density" verification)
     */
    public static final float RGBFrag = maxRGB / 10f;

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

        // First for loop layer (for the height of the image)
        for (int y = 0; y < image.getHeight(); y++) {

            // Second for loop layer (for the width of the image)
            for (int x = 0; x < image.getWidth(); x++) {
                // TODO : Checking the color of the current pixel, and adding the right character to the StringBuilder
            }
            // Adding a new line for the next row a pixels
            sb.append('\n');
        }

        // TODO : Printing the result of the StringBuilder into a file of the same name than the original image file, but with *.txt instead of *.png / *.jpg / *.gif
    }
}
