package pl.strojecki;

import static java.lang.Math.*;

/**
 * Enum represents type of angle.
 *
 * DEG - Decimal Degrees
 * DMS - Degrees, Minutes, Seconds
 * RAD - Radians
 * GRAD - gradians (gons)
 * */
public enum AngleType {

    DEG(360.0),
    DMS(360.0),
    RAD(2 * PI),
    GRAD(400.0);

    private final double fullAngle;

    AngleType(double fullAngle) {
        this.fullAngle = fullAngle;
    }

    public double getFullAngle() {
        return fullAngle;
    }
}