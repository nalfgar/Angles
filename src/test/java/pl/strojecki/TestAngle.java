package pl.strojecki;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.strojecki.AngleType.DMS;
import static pl.strojecki.AngleType.RAD;

@DisplayName("Angle should")
public class TestAngle {

    public static final double HALF_PI = PI / 2.0;
    public static final double TWO_PI = PI * 2.0;

    public static final double DELTA_GRAD = 0.00001;
    public static final double DELTA_RAD = 0.0000001;

    private static Angle anglePi;
    private static Angle angleHalfPi;


    @BeforeAll
    public static void setup(){
        anglePi = new Angle(String.valueOf(PI), RAD);
        angleHalfPi = new Angle(String.valueOf(PI/2.0), RAD);
    }

    @Test
    @DisplayName("create correct angle in radians")
    public void testCreateAngleRadians() throws Exception {

        assertEquals(angleHalfPi, new Angle("1.570796327", RAD));
        assertEquals(angleHalfPi, new Angle("1,570796327", RAD));
        assertEquals(angleHalfPi, new Angle(1.570796327, RAD));

        assertEquals(anglePi, new Angle("3.141592654", RAD));
        assertEquals(anglePi, new Angle("3,141592654", RAD));
        assertEquals(anglePi, new Angle(3.141592654, RAD));

    }

    @Test
    @DisplayName("throw exception when value is double and angleType == DMS")
    public void testCreateDMS() throws Exception{

        assertThrows(IllegalArgumentException.class, () -> new Angle(1.3141592954, DMS));
    }

    @Test
    @DisplayName("correct compare two Angles")
    public void testCompareAngles() {

        assertEquals(anglePi, new Angle("3.141592", RAD));
        assertEquals(anglePi, new Angle("3,141592", RAD));

    }

    @Test
    @DisplayName("convert gradians to radians")
    public void test(){
        assertEquals(HALF_PI, new Angle("100.0", AngleType.GRAD).toRadians(), DELTA_RAD);
        assertEquals(PI, new Angle("200.0", AngleType.GRAD).toRadians(), DELTA_RAD);
//        TODO fix below test
//        assertEquals(TWO_PI, new Angle("400.0", AngleType.GRAD).toRadians(), DELTA_RAD);

    }


    @Test
    @DisplayName("create correct angle in gradians")
    public void testCreateAngleGradians() throws Exception {
        double expectedValue = 100.00001;

        assertEquals(expectedValue, new Angle("100.00001", AngleType.GRAD).getValue(), DELTA_GRAD);
        assertEquals(expectedValue, new Angle("100,00001", AngleType.GRAD).getValue(), DELTA_GRAD);

        assertEquals(expectedValue, new Angle("1300.00001", AngleType.GRAD).getValue(), DELTA_GRAD);
        assertEquals(expectedValue, new Angle("1300,00001", AngleType.GRAD).getValue(), DELTA_GRAD);

        expectedValue = 300.00000;
        assertEquals(expectedValue, new Angle("-100.0000", AngleType.GRAD).getValue(), DELTA_RAD);
    }

    @Test
    @DisplayName("create correct angle in decimal degrees")
    public void testCreateAngleDecimalDegrees() throws Exception {
        double expectedValue = 180.00001;
        assertEquals(expectedValue, new Angle("180.00001", AngleType.DD).getValue(), DELTA_GRAD);
        assertEquals(expectedValue, new Angle("180,00001", AngleType.DD).getValue(), DELTA_GRAD);

        assertEquals(expectedValue, new Angle("2700.00001", AngleType.DD).getValue(), DELTA_GRAD);
        assertEquals(expectedValue, new Angle("2700,00001", AngleType.DD).getValue(), DELTA_GRAD);

        expectedValue = 270.00000;
        assertEquals(expectedValue, new Angle("-90.0000", AngleType.DD).getValue(), DELTA_GRAD);
    }

    @Test
    @DisplayName("create correct angle in DMS")
    public void testCreateAngleDegreesMinutesSeconds() throws Exception {
        double expectedValue = 1.0169444444444444;
        assertEquals(expectedValue, new Angle("1 1 1", AngleType.DMS).getValue(), DELTA_GRAD);
        expectedValue = 10.169444444444444;
        assertEquals(expectedValue, new Angle("10 10 10", AngleType.DMS).getValue(), DELTA_GRAD);

        expectedValue = 59.999722222222225;
        assertEquals(expectedValue, new Angle("59 59 59", AngleType.DMS).getValue(), DELTA_GRAD);

    }
}
