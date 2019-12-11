package pl.strojecki;

public class Angles {

    public static double a;
    public static double b;

    public static void main(String[] args) {

        Angle angle6 = new Angle("3.141592", AngleType.RAD);
        Angle angle7 = new Angle("3.1415926", AngleType.RAD);

        System.out.println(angle6.toGradians());
        System.out.println(angle7.toGradians());
    }
}