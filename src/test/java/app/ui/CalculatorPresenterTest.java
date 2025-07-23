package app.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorPresenterTest {
    @Test
    void testOperatorReplacement() {
        CalculatorPresenter p = new CalculatorPresenter();
        p.input("1");
        p.input("+");
        assertEquals("1+", p.getDisplay());
        p.input("-");
        assertEquals("1-", p.getDisplay());
        p.input("*");
        assertEquals("1*", p.getDisplay());
        p.input("/");
        assertEquals("1/", p.getDisplay());
        p.input("2");
        p.input("=");
        assertEquals("0.5", p.getDisplay());
    }
    @Test
    void testSimpleAdd() {
        CalculatorPresenter p = new CalculatorPresenter();
        p.input("1");
        p.input("+");
        p.input("2");
        p.input("=");
        assertEquals("3", p.getDisplay());
    }

    @Test
    void testChainedAdd() {
        CalculatorPresenter p = new CalculatorPresenter();
        p.input("1");
        p.input("+");
        p.input("2");
        p.input("=");
        p.input("+");
        p.input("3");
        p.input("=");
        assertEquals("6", p.getDisplay());
    }

    @Test
    void testDecimal() {
        CalculatorPresenter p = new CalculatorPresenter();
        p.input("1");
        p.input(".");
        p.input("5");
        p.input("+");
        p.input("2");
        p.input(".");
        p.input("5");
        p.input("=");
        assertEquals("4", p.getDisplay());
    }

    @Test
    void testDivideByZero() {
        CalculatorPresenter p = new CalculatorPresenter();
        p.input("8");
        p.input("/");
        p.input("0");
        p.input("=");
        assertEquals("Error", p.getDisplay());
    }

    @Test
    void testClear() {
        CalculatorPresenter p = new CalculatorPresenter();
        p.input("1");
        p.input("+");
        p.input("2");
        p.input("C");
        assertEquals("0", p.getDisplay());
    }

    @Test
    void testMultipleOperators() {
        CalculatorPresenter p = new CalculatorPresenter();
        p.input("2");
        p.input("+");
        p.input("2");
        p.input("*");
        p.input("2");
        p.input("=");
        assertEquals("8", p.getDisplay()); // Now expects 2+2=4, then 4*2=8
    }

    @Test
    void testInvalidInput() {
        CalculatorPresenter p = new CalculatorPresenter();
        p.input("+");
        assertEquals("0+", p.getDisplay());
        p.input("=");
        assertEquals("Error", p.getDisplay());
    }
}
