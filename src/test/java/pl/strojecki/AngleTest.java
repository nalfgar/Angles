package pl.strojecki;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static java.lang.Math.PI;

import static pl.strojecki.AngleType.DMS;
import static pl.strojecki.AngleType.RAD;

@DisplayName("Angle should")
public class AngleTest {
// given
// when
// then

    public static final double HALF_PI = PI / 2.0;
    public static final double TWO_PI = PI * 2.0;

    public static final double DELTA_GRAD = 0.00001;
    public static final double DELTA_RAD =  0.0000001;

    private static Angle anglePiRad;
    private static Angle angleHalfPiRad;


    @BeforeAll
    public static void setup(){
        anglePiRad = new Angle(String.valueOf(PI), RAD);
        angleHalfPiRad = new Angle(String.valueOf(PI/2.0), RAD);
    }

    @Test
    @DisplayName("create correct angle in radians")
    public void testCreateAngleRadians() throws Exception {

//        given
        Angle newAngleHalfPiRad = new Angle("1.570796327", RAD);
//        when
//        then
        assertThat(newAngleHalfPiRad).isEqualTo(angleHalfPiRad);

//        assertEquals(angleHalfPi, new Angle("1.570796327", RAD));
//        assertEquals(angleHalfPi, new Angle("1,570796327", RAD));
//        assertEquals(angleHalfPi, new Angle(1.570796327, RAD));
//
//        assertEquals(anglePi, new Angle("3.141592654", RAD));
//        assertEquals(anglePi, new Angle("3,141592654", RAD));
//        assertEquals(anglePi, new Angle(3.141592654, RAD));

    }

    @Test
    @DisplayName("throws exception when value is double and angleType == DMS")
    public void testCreateDMS() throws Exception{

        assertThrows(IllegalArgumentException.class, () -> new Angle(1.3141592954, DMS));
    }

    @Test
    @DisplayName("correct compare two Angles")
    public void testCompareAngles() {
        assertEquals(anglePiRad, new Angle("3.14159265", RAD));
        assertEquals(anglePiRad, new Angle("3,14159265", RAD));
        assertEquals(anglePiRad, new Angle(3.14159265, RAD));
    }

    @Test
    @DisplayName("convert gradians to radians")
    public void testConvertGradiansToRadian(){
        assertEquals(0.0, new Angle("0.0", AngleType.GRAD).toRad(), DELTA_RAD);
        assertEquals(0.0000015708, new Angle("0.0001", AngleType.GRAD).toRad(), DELTA_RAD);
        assertEquals(HALF_PI, new Angle("100.0", AngleType.GRAD).toRad(), DELTA_RAD);
        assertEquals(PI, new Angle("200.0", AngleType.GRAD).toRad(), DELTA_RAD);
        assertEquals(0.0, new Angle("400.0", AngleType.GRAD).toRad(), DELTA_RAD);
    }

    @Test
    @DisplayName("convert rdadians to gradnians")
    public void testConvertRadiansToGradians(){
        assertEquals(0.0, new Angle("0.0", AngleType.RAD).toGrad(), DELTA_GRAD);
        assertEquals(0.00010, new Angle("0.00000157", AngleType.RAD).toGrad(), DELTA_GRAD);
        assertEquals(200.0, new Angle("3.1415926", AngleType.RAD).toGrad(), DELTA_GRAD);
    }



    @Test
    @DisplayName("create correct angle in gradians")
    public void testCreateAngleGradians() throws Exception {
        double expectedValue = 100.00001;

        assertEquals(expectedValue, new Angle("100.00001", AngleType.GRAD).getAngle(), DELTA_GRAD);
        assertEquals(expectedValue, new Angle("100,00001", AngleType.GRAD).getAngle(), DELTA_GRAD);

        assertEquals(expectedValue, new Angle("1300.00001", AngleType.GRAD).getAngle(), DELTA_GRAD);
        assertEquals(expectedValue, new Angle("1300,00001", AngleType.GRAD).getAngle(), DELTA_GRAD);

        expectedValue = 300.00000;
        assertEquals(expectedValue, new Angle("-100.0000", AngleType.GRAD).getAngle(), DELTA_RAD);
    }

    @Test
    @DisplayName("create correct angle in decimal degrees")
    public void testCreateAngleDecimalDegrees() throws Exception {
        double expectedValue = 180.00001;
        assertEquals(expectedValue, new Angle("180.00001", AngleType.DEG).getAngle(), DELTA_GRAD);
        assertEquals(expectedValue, new Angle("180,00001", AngleType.DEG).getAngle(), DELTA_GRAD);

        assertEquals(expectedValue, new Angle("2700.00001", AngleType.DEG).getAngle(), DELTA_GRAD);
        assertEquals(expectedValue, new Angle("2700,00001", AngleType.DEG).getAngle(), DELTA_GRAD);

        expectedValue = 270.00000;
        assertEquals(expectedValue, new Angle("-90.0000", AngleType.DEG).getAngle(), DELTA_GRAD);
    }

    @Test
    @DisplayName("create correct angle in DMS")
    public void testCreateAngleDegreesMinutesSeconds() throws Exception {
        double expectedValue = 1.0169444444444444;
        assertEquals(expectedValue, new Angle("1 1 1", AngleType.DMS).getAngle(), DELTA_GRAD);
        expectedValue = 10.169444444444444;
        assertEquals(expectedValue, new Angle("10 10 10", AngleType.DMS).getAngle(), DELTA_GRAD);

        expectedValue = 59.999722222222225;
        assertEquals(expectedValue, new Angle("59 59 59", AngleType.DMS).getAngle(), DELTA_GRAD);

    }
}
