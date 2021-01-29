package predictor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("MagicConstant")
public class Predictor {
    public Predictor() {
    }

    /**
     * Checks if the Car is in Rush hour.
     *
     * @param car      Vehicle to be checked.
     * @param calendar DateTime to be checked.
     * @return True if the Vehicle is in 'Pico y Placa' time.
     */
    @SuppressWarnings("MagicConstant")
    private static boolean isPicoPlaca(CarEntity car, Calendar calendar) {
        int dayOfWeek = getPicoPlacaDayOfWeek(car.getLastPlateNumber());

        if (calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
            // Morning 7:00 a.m. - 9:30 a.m.
            Calendar start = (Calendar) calendar.clone();
            start.set(Calendar.HOUR_OF_DAY, 7);
            start.set(Calendar.MINUTE, 0);
            start.set(Calendar.SECOND, 0);

            Calendar end = (Calendar) calendar.clone();
            end.set(Calendar.HOUR_OF_DAY, 9);
            end.set(Calendar.MINUTE, 30);
            end.set(Calendar.SECOND, 0);

            if ((start.before(calendar) || start.equals(calendar)) && calendar.before(end)) {
                return true;
            }

            // Afternoon 4.00 p.m - 7:30 p.m
            start.set(Calendar.HOUR_OF_DAY, 16);
            start.set(Calendar.MINUTE, 0);
            start.set(Calendar.SECOND, 0);

            end.set(Calendar.HOUR_OF_DAY, 19);
            end.set(Calendar.MINUTE, 30);
            end.set(Calendar.SECOND, 0);

            return (start.before(calendar) || start.equals(calendar)) && calendar.before(end);
        }

        return false;
    }

    /**
     * Checks if the Vehicle is in 'Pico y Placa' time.
     *
     * @param plateNumber Plate Number to be checked.
     * @param date        Date to be checked.
     * @param time        Time to be checked.
     * @return True if the Vehicle is in 'Pico y Placa' time.
     * @throws IllegalArgumentException If any of the arguments is invalid.
     */
    public static boolean isPicoPlaca(String plateNumber, String date, String time) throws IllegalArgumentException {
        CarEntity car = new CarEntity(plateNumber);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date mDate;
        String dateTime = date + " " + time;
        try {
            mDate = format.parse(dateTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    "Date and/or Time arguments are not a valid DateTime 'yyyy-MM-dd HH:mm'.");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);

        return isPicoPlaca(car, calendar);
    }

    /**
     * Gets the 'Pico y Placa' day of week.
     *
     * @param plateNumber Plate Number of the Vehicle.
     * @return An int representing the day of the week.
     */
    private static int getPicoPlacaDayOfWeek(int plateNumber) {
        int dayOfWeek;

        switch (plateNumber) {
            case 1:
            case 2:
                dayOfWeek = Calendar.MONDAY;
                break;
            case 3:
            case 4:
                dayOfWeek = Calendar.TUESDAY;
                break;
            case 5:
            case 6:
                dayOfWeek = Calendar.WEDNESDAY;
                break;
            case 7:
            case 8:
                dayOfWeek = Calendar.THURSDAY;
                break;
            case 9:
            case 0:
                dayOfWeek = Calendar.FRIDAY;
                break;
            default:
                dayOfWeek = -1;
                break;
        }

        return dayOfWeek;
    }
}
