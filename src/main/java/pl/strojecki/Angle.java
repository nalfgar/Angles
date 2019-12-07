package pl.strojecki;

import lombok.ToString;


import static java.lang.Double.parseDouble;

@ToString
public class Angle {

    @ToString.Exclude
    private String inputString;
    private double value;
    private final AngleType angleType;

    public Angle(String inputString, AngleType angleType) {
        this.inputString = inputString;
        this.angleType = angleType;

        if (angleType == AngleType.GRAD || angleType == AngleType.RAD || angleType == AngleType.DD) {
            inputString = inputString.replace(',','.');
            value = parseDouble(inputString);
        }
        value = normalizeAngle(value, this.angleType);
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
}
