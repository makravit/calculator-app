package app;

import javax.swing.*;
import app.ui.CalculatorUI;

public class CalculatorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);
            frame.setContentPane(new CalculatorUI().createUI());
            frame.setVisible(true);
        });
    }
}
