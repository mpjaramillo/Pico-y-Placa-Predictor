package mainApp;

import predictor.CarEntity;
import predictor.Predictor;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanIn = new Scanner(System.in);

        System.out.print("Plate Number ('LAA-9001'):");
        String plateNumber = scanIn.nextLine();
        System.out.print("Date ('yyyy-MM-dd'):");
        String date = scanIn.nextLine();
        System.out.print("Time ('HH:mm):");
        String time = scanIn.nextLine();
        scanIn.close();

        try {
            if (Predictor.isPicoPlaca(plateNumber, date, time)) {
                System.out.println("You are in 'Pico y Placa' time, your vehicle can not be on the road.");
            } else {
                System.out.println("You are not in 'Pico y Placa' time, go ahead.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
