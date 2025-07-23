package app.ui;


public class CalculatorPresenter {
    private String display = "0";
    private String operator = "";
    private boolean startNewNumber = true;
    private boolean justCalculated = false;
    private String leftOperand = "";

    public String getDisplay() {
        return display;
    }

    public void input(String cmd) {
        if (display.equals("Error") && !cmd.equals("C")) return;
        if (cmd.matches("[0-9]")) {
            handleDigit(cmd);
        } else if (cmd.equals(".")) {
            handleDecimalPoint();
        } else if (cmd.matches("[+\\-*/]")) {
            handleOperator(cmd);
        } else if (cmd.equals("C")) {
            handleClear();
        } else if (cmd.equals("=")) {
            handleEquals();
        }
    }

    private void handleDigit(String cmd) {
        if (startNewNumber || display.equals("Error") || justCalculated) {
            display = cmd;
            startNewNumber = false;
            justCalculated = false;
            if (!operator.isEmpty()) {
                display = leftOperand + operator + cmd;
            }
        } else {
            if (display.equals("0")) {
                display = cmd;
            } else {
                display += cmd;
            }
        }
    }

    private void handleDecimalPoint() {
        if (startNewNumber || display.equals("Error") || justCalculated) {
            display = "0.";
            startNewNumber = false;
            justCalculated = false;
            if (!operator.isEmpty()) {
                display = leftOperand + operator + "0.";
            }
        } else if (!getCurrentInput(display).contains(".")) {
            display += ".";
        }
    }

    private void handleOperator(String cmd) {
        if (display.equals("Error")) return;
        try {
            if (justCalculated) {
                leftOperand = display;
                operator = cmd;
                display = leftOperand + operator;
                startNewNumber = true;
                justCalculated = false;
            } else if (!operator.isEmpty() && getCurrentInput(display).isEmpty()) {
                // If last char is an operator, replace it
                operator = cmd;
                display = leftOperand + operator;
                startNewNumber = true;
            } else if (!operator.isEmpty() && !getCurrentInput(display).isEmpty()) {
                double left = Double.parseDouble(leftOperand);
                double right = Double.parseDouble(getCurrentInput(display));
                double result = calculate(left, right, operator);
                leftOperand = formatNumber(result);
                operator = cmd;
                display = leftOperand + operator;
                startNewNumber = true;
            } else {
                leftOperand = display;
                operator = cmd;
                display = leftOperand + operator;
                startNewNumber = true;
            }
        } catch (Exception ex) {
            display = "Error";
            startNewNumber = true;
            justCalculated = false;
            operator = "";
        }
    }

    private void handleClear() {
        display = "0";
        operator = "";
        leftOperand = "";
        startNewNumber = true;
        justCalculated = false;
    }

    private void handleEquals() {
        if (operator.isEmpty() || display.equals("Error")) {
            justCalculated = true;
            return;
        }
        try {
            double left = Double.parseDouble(leftOperand);
            double right = Double.parseDouble(getCurrentInput(display));
            double result = calculate(left, right, operator);
            display = formatNumber(result);
            startNewNumber = true;
            justCalculated = true;
            operator = "";
            leftOperand = display;
        } catch (ArithmeticException ex) {
            display = "Error";
            startNewNumber = true;
            justCalculated = true;
            operator = "";
            leftOperand = "";
        } catch (Exception ex) {
            display = "Error";
            startNewNumber = true;
            justCalculated = false;
            operator = "";
            leftOperand = "";
        }
    }

    private String getCurrentInput(String text) {
        if (operator.isEmpty()) return text;
        int idx = text.indexOf(operator);
        if (idx == -1) return "";
        return text.substring(idx + 1);
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": if (b == 0) throw new ArithmeticException("Error"); return a / b;
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    private String formatNumber(double num) {
        if (Double.isNaN(num) || Double.isInfinite(num)) return "Error";
        if (num % 1 == 0) return String.valueOf((int)num);
        return String.valueOf(num);
    }
}
