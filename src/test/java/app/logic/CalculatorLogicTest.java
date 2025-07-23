package app.logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorLogicTest {
    private final CalculatorLogic calc = new CalculatorLogic();

    @Test
    void testAdd() {
        assertEquals(5.0, calc.add(2, 3));
        assertEquals(-1.0, calc.add(-2, 1));
        assertEquals(0.0, calc.add(0, 0));
        assertEquals(-5.0, calc.add(-2, -3));
        assertEquals(5.7, calc.add(2.2, 3.5), 1e-9);
        assertEquals(0.3, calc.add(0.1, 0.2), 1e-9);
    }

    @Test
    void testSubtract() {
        assertEquals(-1.0, calc.subtract(2, 3));
        assertEquals(-3.0, calc.subtract(-2, 1));
        assertEquals(0.0, calc.subtract(0, 0));
        assertEquals(1.0, calc.subtract(-2, -3));
        assertEquals(-1.3, calc.subtract(2.2, 3.5), 1e-9);
        assertEquals(-0.1, calc.subtract(0.1, 0.2), 1e-9);
    }

    @Test
    void testMultiply() {
        assertEquals(6.0, calc.multiply(2, 3));
        assertEquals(-2.0, calc.multiply(-2, 1));
        assertEquals(0.0, calc.multiply(0, 0));
        assertEquals(6.0, calc.multiply(-2, -3));
        assertEquals(7.7, calc.multiply(2.2, 3.5), 1e-9);
        assertEquals(0.02, calc.multiply(0.1, 0.2), 1e-9);
    }

    @Test
    void testDivide() {
        assertEquals(2.0, calc.divide(6, 3));
        assertEquals(-2.0, calc.divide(-6, 3));
        assertEquals(2.0, calc.divide(-6, -3));
        assertEquals(0.0, calc.divide(0, 3));
        assertEquals(2.2, calc.divide(7.7, 3.5), 1e-9);
        assertEquals(0.5, calc.divide(0.1, 0.2), 1e-9);
    }

    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
}
