package pl.strojecki;

import java.util.Formatter;

import static java.lang.Double.parseDouble;

public class Angle {

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
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format("Angle{value=%.4f, angleType=%s}", value, angleType);
        return formatter.toString().replaceFirst(",", ".");
    }
}
