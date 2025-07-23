package app.ui.integration;

import app.ui.CalculatorUI;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorUIIntegrationTest {
    @Test
    void testFullAdditionFlow() {
        CalculatorUI ui = new CalculatorUI();
        JPanel panel = ui.createUI();
        JButton btn1 = findButton(panel, "1");
        JButton btnPlus = findButton(panel, "+");
        JButton btn2 = findButton(panel, "2");
        JButton btnEq = findButton(panel, "=");
        JTextField display = findDisplay(panel);
        btn1.doClick();
        btnPlus.doClick();
        btn2.doClick();
        btnEq.doClick();
        assertEquals("3", display.getText());
    }

    @Test
    void testOperatorReplacement() {
        CalculatorUI ui = new CalculatorUI();
        JPanel panel = ui.createUI();
        JButton btn1 = findButton(panel, "1");
        JButton btnPlus = findButton(panel, "+");
        JButton btnMinus = findButton(panel, "-");
        JTextField display = findDisplay(panel);
        btn1.doClick();
        btnPlus.doClick();
        btnMinus.doClick();
        assertEquals("1-", display.getText());
    }

    @Test
    void testClearButton() {
        CalculatorUI ui = new CalculatorUI();
        JPanel panel = ui.createUI();
        JButton btn1 = findButton(panel, "1");
        JButton btnC = findButton(panel, "C");
        JTextField display = findDisplay(panel);
        btn1.doClick();
        btnC.doClick();
        assertEquals("0", display.getText());
    }

    @Test
    void testDivideByZero() {
        CalculatorUI ui = new CalculatorUI();
        JPanel panel = ui.createUI();
        JButton btn8 = findButton(panel, "8");
        JButton btnDiv = findButton(panel, "/");
        JButton btn0 = findButton(panel, "0");
        JButton btnEq = findButton(panel, "=");
        JTextField display = findDisplay(panel);
        btn8.doClick();
        btnDiv.doClick();
        btn0.doClick();
        btnEq.doClick();
        assertEquals("Error", display.getText());
    }

    @Test
    void testDecimalInput() {
        CalculatorUI ui = new CalculatorUI();
        JPanel panel = ui.createUI();
        JButton btn1 = findButton(panel, "1");
        JButton btnDot = findButton(panel, ".");
        JButton btn5 = findButton(panel, "5");
        JButton btnPlus = findButton(panel, "+");
        JButton btn2 = findButton(panel, "2");
        JButton btnEq = findButton(panel, "=");
        JTextField display = findDisplay(panel);
        btn1.doClick();
        btnDot.doClick();
        btn5.doClick();
        btnPlus.doClick();
        btn2.doClick();
        btnEq.doClick();
        assertEquals("3.5", display.getText());
    }

    @Test
    void testMultipleOperatorsAndChaining() {
        CalculatorUI ui = new CalculatorUI();
        JPanel panel = ui.createUI();
        JButton btn2 = findButton(panel, "2");
        JButton btnPlus = findButton(panel, "+");
        JButton btn2b = findButton(panel, "2");
        JButton btnMul = findButton(panel, "*");
        JButton btn2c = findButton(panel, "2");
        JButton btnEq = findButton(panel, "=");
        JTextField display = findDisplay(panel);
        btn2.doClick();
        btnPlus.doClick();
        btn2b.doClick();
        btnMul.doClick();
        btn2c.doClick();
        btnEq.doClick();
        assertEquals("8", display.getText());
    }

    @Test
    void testInvalidInputEquals() {
        CalculatorUI ui = new CalculatorUI();
        JPanel panel = ui.createUI();
        JButton btnPlus = findButton(panel, "+");
        JButton btnEq = findButton(panel, "=");
        JTextField display = findDisplay(panel);
        btnPlus.doClick();
        btnEq.doClick();
        assertEquals("Error", display.getText());
    }

    @Test
    void testZeroStartAndLeadingZeros() {
        CalculatorUI ui = new CalculatorUI();
        JPanel panel = ui.createUI();
        JButton btn0 = findButton(panel, "0");
        JButton btn1 = findButton(panel, "1");
        JButton btnPlus = findButton(panel, "+");
        JButton btn2 = findButton(panel, "2");
        JButton btnEq = findButton(panel, "=");
        JTextField display = findDisplay(panel);
        btn0.doClick();
        btn0.doClick();
        btn1.doClick();
        btnPlus.doClick();
        btn0.doClick();
        btn2.doClick();
        btnEq.doClick();
        assertEquals("3", display.getText());
    }

    // --- Utility methods ---
    private JButton findButton(Container panel, String label) {
        AtomicReference<JButton> found = new AtomicReference<>();
        findButtonRecursive(panel, label, found);
        assertNotNull(found.get(), "Button '" + label + "' not found");
        return found.get();
    }
    private void findButtonRecursive(Container c, String label, AtomicReference<JButton> found) {
        for (Component comp : c.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals(label)) {
                found.set((JButton) comp);
                return;
            } else if (comp instanceof Container) {
                findButtonRecursive((Container) comp, label, found);
            }
        }
    }
    private JTextField findDisplay(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JTextField) return (JTextField) comp;
        }
        fail("Display field not found");
        return null;
    }
}
