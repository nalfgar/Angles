package pl.strojecki;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Angle should")
public class TestAngle {

    public static final double DELTA_GRAD = 0.00001;
    public static final double DELTA_RAD = 0.0000001;

    private Angle angle;

    @Test
    @DisplayName("create correct angle in radians")
    public void testCreateAngleRadians() throws Exception {
        double expectedValue = 3.141592654;

        assertEquals(expectedValue, new Angle("3.141592654", AngleType.RAD).getValue(), DELTA_RAD);
        assertEquals(expectedValue, new Angle("3,141592654", AngleType.RAD).getValue(), DELTA_RAD);

        assertEquals(expectedValue, new Angle("21.991148578", AngleType.RAD).getValue(), DELTA_RAD);
        assertEquals(expectedValue, new Angle("21,991148578", AngleType.RAD).getValue(), DELTA_RAD);

        expectedValue = 4.71238898;
        assertEquals(expectedValue, new Angle("-1.570796327", AngleType.RAD).getValue(), DELTA_RAD);
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
