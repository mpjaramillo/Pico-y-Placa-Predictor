package Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import predictor.CarEntity;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CarEntityTest {
    CarEntity car;

    @Parameter(0)
    public String plateNumber;
    @Parameter(1)
    public int lastPlateNumber;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { "AAA-2233", 3 },
                { "ZZZ-9999", 9 }, { "PCO-8631", 1 },
                { "XYB-661", 1 } };
        return Arrays.asList(data);
    }

    @Before
    public void setUp() throws Exception {
        car = new CarEntity();
        car.setPlateNumber(plateNumber);
    }

    @Test
    public void testGetLastPlateNumber() {
        int lastNumber = car.getLastPlateNumber();
        assertTrue(0<= lastNumber && lastNumber <=9);
    }

    @Test
    public void testSetPlateNumber() {
        assertTrue(CarEntity.isPlateNumber(car.getPlateNumber()));
        assertEquals(plateNumber, car.getPlateNumber());
        assertEquals(lastPlateNumber, car.getLastPlateNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPlateNumberException() {
        car.setPlateNumber("XXXX-000");
    }

}
