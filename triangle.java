// Import stuff
import java.io.*;
import java.util.*;
import java.lang.*;

public class triangle {

    // Get the Triangle data

    // Get side lengths of triangle
    public static int[] getSides() {
        int[] sides = { 0, 0, 0 };
        Scanner inLn = new Scanner(System.in);
        for (int i = 0; i < sides.length; i++) {
            sides[i] = inLn.nextInt();
        }
        return sides;
    }

    // Get the angles (in degrees) of the triangle
    public static double[] getAngles(int[] sides) {
        double[] angles = { 0.0, 0.0, 0.0 };
        angles[0] = angleMath(sides[0], sides[1], sides[2]);
        angles[1] = angleMath(sides[1], sides[2], sides[0]);
        angles[2] = angleMath(sides[2], sides[0], sides[1]);

        return angles;
    }

    // Detect triangle classifications

    // Finds the side type
    public static String getSideType(int[] sides) {
        String sideType = "Invalid";
        int sidesEqual = 0;
        for (int i = 0; i < 2; i++) {
            if (sideEqual(sides[i], sides[(i + 1)]))
                sidesEqual++;
        }
        if (sides[0] == sides[2]) {
            sidesEqual++;
        }
        if (sidesEqual == 0) {
            sideType = "Scalene";
        } else if (sidesEqual == 1) {
            sideType = "Isosceles";
        } else if (sidesEqual == 3) {
            sideType = "Equilateral";
        }
        return sideType;
    }

    // Finds the angle types
    public static String getAngleType(double[] angles) {
        String angleType = "Unknown";
        if (angles[0] >= angles[1] && angles[0] >= angles[2]) {
            if (angles[0] == 90.0) {
                angleType = "Right-Angle";
            } else if (angles[0] < 90.0) {
                angleType = "Acute";
            } else {
                angleType = "Obtuse";
            }
        } else if (angles[1] >= angles[0] && angles[1] >= angles[2]) {
            if (angles[1] == 90.0) {
                angleType = "Right-Angle";
            } else if (angles[1] < 90.0) {
                angleType = "Acute";
            } else {
                angleType = "Obtuse";
            }
        } else {
            if (angles[2] == 90.0) {
                angleType = "Right-Angle";
            } else if (angles[2] < 90.0) {
                angleType = "Acute";
            } else {
                angleType = "Obtuse";
            }
        }
        return angleType;
    }

    // Run all the functions
    public static void main(String[] args) {
        // Initialize variables
        int[] sides;
        String sideType;
        double[] angles;
        String angleType;
        
        // Main loop
        while (true) {
            System.out.println("Please Provide 3 side lengths for a valid triangle, type 0 0 0 to quit.");
            sides = getSides();
            sideType = getSideType(sides);
            angles = getAngles(sides);
            angleType = getAngleType(angles);

            // Catch invalid triangles
            if (checkOutput(sides, angles)) {
                // Pipe output
                output(sides, sideType, angles, angleType);
            } else if (isZero(sides)) {
                System.out.println("All Zeros, quitting program");
                break;
            } else {
                System.out.println("Triangle could not be formed");
            }
        }
    }

    // Invalid Triangle catcher, looks for invalid lengths(equal or below 0) and
    // angles(extreme weird stuff)
    public static boolean checkOutput(int[] sides, double[] angles) {
        boolean check = true;
        // Check invalid side lengths
        for (int i = 0; i < sides.length; i++) {
            if (sides[i] <= 0) {
                check = false;
            }
        }
        // Check invalid angles
        int sum = 0;
        for (int i = 0; i < angles.length; i++) {
            sum += angles[i];
        }
        if (sum > 180) {
            check = false;
        }
        // Check for invalid triangle
        if (!((sides[0] + sides[1]) > sides[2])) {
            check = false;
        }
        if (!((sides[1] + sides[2]) > sides[0])) {
            check = false;
        }
        if (!((sides[0] + sides[2]) > sides[1])) {
            check = false;
        }
        return check;
    }

    // Checks if the user wants to quit
    public static boolean isZero(int[] sides) {
        boolean check = false;
        for (int i = 0; i < 3; i++) {
            if (sides[i] == 0) {
                check = true;
            } else {
                check = false;
            }
        }
        return check;
    }

    // Refines data to readable output
    public static void output(int[] sides, String sideType, double[] angles, String angleType) {
        System.out.println("Triangle Found");
        // Display data
        System.out.print("Side Lengths: ");
        for (int i = 0; i < sides.length; i++) {
            System.out.print(sides[i] + ", ");
        }
        System.out.print(" Angles: ");
        for (int i = 0; i < angles.length; i++) {
            System.out.print(angles[i] + ", ");
        }
        // Display information found from data
        System.out.print("Side Type: " + sideType + ", ");
        System.out.print("Angle Type: " + angleType + ", ");
        System.out.print("Perimeter: " + getPerimetre(sides) + ", ");
        System.out.print("Area: " + getArea(sides));
        // Start new line
        System.out.println("");
    }

    // Extra functions tucked away for use in other functions - at the bottom as they aren't the main show

    // Checks 2 variables
    public static boolean sideEqual(int a, int b) {
        if (a == b) {
            return true;
        } else
            return false;
    }

    // All the code that gets an angle from 3 side lengths
    public static double angleMath(int x, int y, int z) {
        double angle = 0.0;
        // Setup
        double sx = Math.pow(x, 2);
        double sy = Math.pow(y, 2);
        double sz = Math.pow(z, 2);

        // Math
        double qAngle = Math.acos((sy + sz - sx) / (2.0 * y * z));
        double tAngle = qAngle * (180.0 / Math.PI);
        double X = tAngle * 10.0;
        double Xt = Math.round(X);
        angle = Xt / 10.0;
        return angle;
    }

    // Get Triangle Perimeter
    public static int getPerimetre(int[] lengths) {
        int perimetre = 0;
        for (int i = 0; i < lengths.length; i++) {
            perimetre += lengths[i];
        }
        return perimetre;
    }
    // Get Triangle Area
    public static double getArea(int[] lengths) {
        double area = 0;
        double semiPerimetre = ((lengths[0] + lengths[1] + lengths[2])/2);
        area = Math.round(10.0*(Math.sqrt(semiPerimetre*((semiPerimetre-lengths[0])*(semiPerimetre-lengths[1])*(semiPerimetre-lengths[2])))));
        area = area/10.0;
        return area;
    }

}
