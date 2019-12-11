package pl.strojecki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Angles {

    public static double a;
    public static double b;

    public static void main(String[] args) {

        Angle angle1 = new Angle("0.00011", AngleType.GRAD);

        System.out.println(angle1.toRadians());
    }
}