package pl.strojecki;

import java.util.Formatter;
import java.util.Objects;

import static java.lang.Double.parseDouble;
import static java.lang.Math.*;

public class Angle {

    private static final double DOUBLE_DELTA_RAD =  0.00000001;
    private static final double DOUBLE_DELTA_GRAD = 0.00001;
    private static final double GRAD_2_RAD = PI / 200.0;
    private static final double RAD_2_GRAD = 200 / PI;


    private String inputString;
    private double value;
    private AngleType angleType;


    public Angle(String inputString, AngleType angleType) {
        this.inputString = inputString;
        this.angleType = angleType;

        if (angleType == AngleType.GRAD || angleType == AngleType.RAD || angleType == AngleType.DD) {
            inputString = inputString.replace(',','.');
            value = parseDouble(inputString);
            value = normalizeAngle(value, this.angleType);
        }
        if (angleType == AngleType.DMS){
            this.angleType = AngleType.DD;
            value = normalizeAngle(dms2dd(inputString), this.angleType);
        }
    }

    public Angle(double input, AngleType angleType) {
        this.angleType = angleType;

        if (angleType == AngleType.GRAD || angleType == AngleType.RAD || angleType == AngleType.DD) {
            this.value = normalizeAngle(input, this.angleType);
        }
        if (angleType == AngleType.DMS){
            throw new IllegalArgumentException("If value == double, angleType can be [GRAD|RAD|DD]");
        }
    }

    private double dms2dd(String inputString) {
        String[] splittedImputString = inputString.split("\\s+");
        final int[] DMS = new int[]{1, 60, 3600};
        double[] dms = new double[3];
        for (int i = 0; i < splittedImputString.length; i++) {
            dms[i] = Double.parseDouble(splittedImputString[i]) / DMS[i];
        }
        return dms[0] + dms[1] + dms[2];
    }

    private double normalizeAngle(double value, AngleType angleType) {
        while (value >= angleType.getMaxValue()) {
            value -= angleType.getMaxValue();
        }
        while (value < 0.0) {
            value += angleType.getMaxValue();
        }
        return value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public AngleType getAngleType() {
        return angleType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Angle)) return false;
        Angle angle = (Angle) o;
        if (this.angleType == angleType.RAD) {
            return Math.abs(angle.getValue() - getValue()) < DOUBLE_DELTA_RAD &&
                    getAngleType() == angle.getAngleType();
        } else {return Math.abs(angle.getValue() - getValue()) < DOUBLE_DELTA_GRAD &&
                getAngleType() == angle.getAngleType();}
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getAngleType());
    }

    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format("Angle{value=%.14f, angleType=%s}", value, angleType);
        return formatter.toString().replaceFirst(",", ".");
    }

    public double toRadians() {
        return this.value * GRAD_2_RAD;
    }

    public double toGradians() {
        return this.value * RAD_2_GRAD;
    }
}
