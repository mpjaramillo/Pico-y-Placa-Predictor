package mainApp;

import predictor.CarEntity;
import predictor.Predictor;

import javax.swing.*;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanIn = new Scanner(System.in);
        JFrame f = new JFrame();

        /*I used a JFrame to present the textbox for a better understand to the user.
         * In this case, the input is the PlateNumber, date and time (hour)*/
        String plateNumber = JOptionPane.showInputDialog(f,"Plate Number ('LAA-9001'):");
        String date = JOptionPane.showInputDialog(f,"Date ('yyyy-MM-dd'):");
        String time = JOptionPane.showInputDialog(f,"Time ('HH:mm):");
        /*In this case I validate using the class Predictor if the car can be on the road*/
        try {
            if (Predictor.isPicoPlaca(plateNumber, date, time)) {
                JOptionPane.showMessageDialog(f, " You are in 'Pico y Placa' time, your car can not be on the road.");

            } else {
                JOptionPane.showMessageDialog(f, " You are not in 'Pico y Placa' time, go ahead.");

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
