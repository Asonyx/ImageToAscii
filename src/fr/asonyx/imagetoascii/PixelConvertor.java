package fr.asonyx.imagetoascii;

import java.util.ArrayList;
import java.util.List;

public class PixelConvertor {

    /**
     * A list of the different density level (of ASCII chars)
     */
    public static List<Character> densityLevel = new ArrayList<>();

    /**
     * The max value of the RGB (#FFFFFF)
     */
    public static final int maxRGB = 16777215;

    /**
     * The max RGB value divide by 10 (for "density" verification)
     */
    public static final float RGBFrag = maxRGB / 10f;


    // Initialise the densityLevel List
    static {
        densityLevel.add(' '); // Density 0
        densityLevel.add('.'); // Density 1
        densityLevel.add('-'); // Density 2
        densityLevel.add('+'); // Density 3
        densityLevel.add('*'); // Density 4
        densityLevel.add(':'); // Density 5
        densityLevel.add('='); // Density 6
        densityLevel.add('#'); // Density 7
        densityLevel.add('%'); // Density 8
        densityLevel.add('@'); // Density 9
    }

    /**
     * Get the density of a rgb color
     * @param rgbHex The color in RGB (converted in decimal)
     * @return The density level of the color
     */
    public static int density(int rgbHex) {
        if (rgbHex < RGBFrag) return 0;                                // Density 0
        else if (rgbHex >= RGBFrag && rgbHex < RGBFrag*2) return 1;    // Density 1
        else if (rgbHex >= RGBFrag*2 && rgbHex < RGBFrag*3) return 2;  // Density 2
        else if (rgbHex >= RGBFrag*3 && rgbHex < RGBFrag*4) return 3;  // Density 3
        else if (rgbHex >= RGBFrag*4 && rgbHex < RGBFrag*5) return 4;  // Density 4
        else if (rgbHex >= RGBFrag*5 && rgbHex < RGBFrag*6) return 5;  // Density 5
        else if (rgbHex >= RGBFrag*6 && rgbHex < RGBFrag*7) return 6;  // Density 6
        else if (rgbHex >= RGBFrag*7 && rgbHex < RGBFrag*8) return 7;  // Density 7
        else if (rgbHex >= RGBFrag*8 && rgbHex < RGBFrag*9) return 8;  // Density 8
        else if (rgbHex >= RGBFrag*9) return 9;                        // Density 9
        else return 0;                                                 // Will never happen
    }

    /**
     * Convert a color into a char with the right density level
     * @param rgbHex The color in RGB (converted in decimal)
     * @return The character with the corresponding density
     */
    public static char convert(int rgbHex) {
        return densityLevel.get(density(rgbHex));
    }
}
