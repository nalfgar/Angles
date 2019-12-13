package pl.strojecki;

import static pl.strojecki.AngleType.*;

public class Angles {

    public static double a;
    public static double b;

    public static void main(String[] args) {

        Angle angleRad = new Angle("3.141592", RAD);
        System.out.println(angleRad);
        System.out.println(angleRad.toGrad());
        System.out.println(angleRad.toDeg());
        System.out.println();


        Angle angleDeg = new Angle("180.0000", DEG);
        System.out.println(angleDeg);
        System.out.println(angleDeg.toGrad());
        System.out.println(angleDeg.toRad());
        System.out.println();

        Angle angleGrad = new Angle("200.0000", GRAD);
        System.out.println(angleGrad);
        System.out.println(angleGrad.toRad());
        System.out.println(angleGrad.toDeg());
        System.out.println();

        Angle angleDMS = new Angle("180 00 00", DMS);
        System.out.println(angleDMS);
        System.out.println(angleDMS.toGrad());
        System.out.println(angleDMS.toRad());
        System.out.println();
//


    }
}