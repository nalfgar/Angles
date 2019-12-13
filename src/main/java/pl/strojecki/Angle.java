package pl.strojecki;

import java.util.Formatter;
import java.util.Objects;

import static java.lang.Double.parseDouble;
import static java.lang.Math.*;
import static pl.strojecki.AngleType.*;

public class Angle {

    private static final double DOUBLE_DELTA_RAD =  0.00000001;
    private static final double DOUBLE_DELTA_GRAD = 0.00001;

    private static final double GRAD_2_RAD = PI / 200.0;
    private static final double RAD_2_GRAD = 200 / PI;

    private static final double DEG_2_RAD = PI / 180.0;
    private static final double RAD_2_DEG = 180.0 / PI;

    private static final double GRAD_2_DEG = 360.0 / 400.0;
    private static final double DEG_2_GRAD = 400.0 / 360.0;


    private String inputString;
    private double angle;
    private AngleType angleType;


    public Angle(String inputString, AngleType angleType) {
        this.inputString = inputString;
        this.angleType = angleType;

        if (angleType == GRAD || angleType == RAD || angleType == DEG) {
            inputString = inputString.replace(',','.');
            angle = parseDouble(inputString);
            angle = normalizeAngle(angle, this.angleType);
        }
        if (angleType == DMS){
            this.angleType = DEG;
            angle = normalizeAngle(dms2dd(inputString), this.angleType);
        }
    }

    public Angle(double input, AngleType angleType) {
        this.angleType = angleType;

        if (angleType == GRAD || angleType == RAD || angleType == DEG) {
            this.angle = normalizeAngle(input, this.angleType);
        }
        if (angleType == DMS){
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
        while (value >= angleType.getFullAngle()) {
            value -= angleType.getFullAngle();
        }
        while (value < 0.0) {
            value += angleType.getFullAngle();
        }
        return value;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public AngleType getAngleType() {
        return angleType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Angle)) return false;
        Angle angle = (Angle) o;
        if (this.angleType == RAD) {
            return abs(angle.getAngle() - getAngle()) < DOUBLE_DELTA_RAD &&
                    getAngleType() == angle.getAngleType();
        } else {return abs(angle.getAngle() - getAngle()) < DOUBLE_DELTA_GRAD &&
                getAngleType() == angle.getAngleType();}
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAngle(), getAngleType());
    }

    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format("Angle{angle=%.14f, angleType=%s}", angle, angleType);
        return formatter.toString().replaceFirst(",", ".");
    }

    public double toRad() {
        if (this.angleType == RAD){
            return this.angle;
        } else if (this.angleType == GRAD){
            return this.angle * GRAD_2_RAD;
        } else if (this.angleType == DEG){
            return this.angle * DEG_2_RAD;
        }
        return 0;
    }

    public double toGrad() {
        if (this.angleType == GRAD){
            return this.angle;
        } else if (this.angleType == RAD){
            return this.angle * RAD_2_GRAD;
        } else if (this.angleType == DEG){
            return this.angle * DEG_2_GRAD;
        }
        return 0;
    }

    public double toDeg() {
        if (this.angleType == DEG){
            return this.angle;
        } else if (this.angleType == RAD){
            return this.angle * RAD_2_DEG;
        } else if (this.angleType == GRAD){
            return this.angle * GRAD_2_DEG;
        }
        return 0;
    }
}
