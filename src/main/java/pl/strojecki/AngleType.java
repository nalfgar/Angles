package pl.strojecki;

import static java.lang.Math.*;

/**
 * Enum represents type of angle.
 *
 * DD  - Decimal Degrees
 * DMS - Degrees, Minutes, Seconds
 * RAD - Radians
 * GRAD - gradians (gons)
 * */
public enum AngleType {
    DD(360.0),
    DMS(360.0),
    RAD(2 * PI),
    GRAD(400.0);

    private final double maxValue;

    AngleType(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getMaxValue() {
        return maxValue;
    }
}