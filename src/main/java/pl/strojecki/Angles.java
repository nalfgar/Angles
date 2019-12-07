package pl.strojecki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Angles {

    public static double a;
    public static double b;

    public static void main(String[] args) {

        Angle angle1 = new Angle("234.3324", AngleType.GRAD);

        System.out.println(angle1);
    }
}