package pl.strojecki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Angles {

    public static double a;
    public static double b;

    public static void main(String[] args) {
//        Angle angleGRAD = new Angle("405.01", AngleType.GRAD);
//        System.out.println(angleGRAD);
//
//        Angle angleRAD = new Angle("15,7079", AngleType.RAD);
//        System.out.println(angleRAD);

//        a = Double.parseDouble("45.450");
//        b = Double.parseDouble("45.45");

        String inputString = "10 01 10";


//        Pattern pattern = Pattern.compile("^\\d{2}");
//        Matcher matcher = pattern.matcher(inputString);
//
//        System.out.println(matcher.matches());


        String[] x = inputString.split("\\s+");

        for (int i = 0; i < x.length; i++) {
            System.out.println("---> " + x[i]);
        }


//        System.out.println(a.);
    }
}
