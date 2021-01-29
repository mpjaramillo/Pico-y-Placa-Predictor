package Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import predictor.CarEntity;
import predictor.Predictor;

@RunWith(Parameterized.class)
public class PredictorTest {
    @Parameter(0)
    public String plateNumber;
    @Parameter(1)
    public String date;
    @Parameter(2)
    public String time;
    @Parameter(3)
    public int dayOfWeek;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { "AAA-2233", "2021-04-25", "07:00", 3 },
                { "ZZZ-9999", "2022-02-10", "09:29", 6 },
                { "PCO-8631", "2021-8-27", "16:00", 2 },
                { "XYB-661", "2020-05-01", "19:00", 2 } };
        return Arrays.asList(data);
    }

    @Test
    public void testIsPicoPlacaStringStringString() {
        assertTrue(Predictor.isPicoPlaca(plateNumber, date, time));
    }

    @Test
    public void testGetPicoPlacaDayOfWeek() {
        assertEquals(dayOfWeek, Predictor.getPicoPlacaDayOfWeek(new CarEntity(plateNumber).getLastPlateNumber()));
    }
}
