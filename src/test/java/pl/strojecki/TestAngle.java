package pl.strojecki;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Angle should")
public class TestAngle {

    public static final double DELTA_GRAD = 0.0000001;
    public static final double DELTA_RAD = 0.00001;

    private Angle angle;

    @Test
    public void testCreateAngleRadians(){
        double expectedValue = 3.141592654;

        assertEquals(expectedValue, new Angle("3.141592654", AngleType.RAD).getValue(), DELTA_RAD);
        assertEquals(expectedValue, new Angle("3,141592654", AngleType.RAD).getValue(), DELTA_RAD);

        assertEquals(expectedValue, new Angle("21.991148578", AngleType.RAD).getValue(), DELTA_RAD);
        assertEquals(expectedValue, new Angle("21,991148578", AngleType.RAD).getValue(), DELTA_RAD);
    }

    @Test
    public void testCreateAngleGradians(){
        double expectedValue = 100.00001;

        assertEquals(expectedValue, new Angle("100.00001", AngleType.GRAD).getValue(), DELTA_GRAD);
        assertEquals(expectedValue, new Angle("100,00001", AngleType.GRAD).getValue(), DELTA_GRAD);

        assertEquals(expectedValue, new Angle("1300.00001", AngleType.GRAD).getValue(), DELTA_GRAD);
        assertEquals(expectedValue, new Angle("1300,00001", AngleType.GRAD).getValue(), DELTA_GRAD);
    }

}
