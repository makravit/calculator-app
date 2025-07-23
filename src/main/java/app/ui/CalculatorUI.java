package app.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * CalculatorUI is responsible for building the Swing UI for the calculator.
 * It delegates all logic to CalculatorPresenter.
 */
public class CalculatorUI {
    private JTextField display;
    private JPanel mainPanel;
    private final CalculatorPresenter presenter;

    /**
     * Default constructor, creates a new CalculatorPresenter.
     */
    public CalculatorUI() {
        this(new CalculatorPresenter());
    }

    /**
     * Constructor for dependency injection, useful for testing.
     * @param presenter CalculatorPresenter instance
     */
    public CalculatorUI(CalculatorPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Builds and returns the main calculator UI panel.
     */
    public JPanel createUI() {
        mainPanel = new JPanel(new BorderLayout());
        createDisplay();
        JPanel panel = createButtonPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.setBackground(new Color(30, 34, 40));
        return mainPanel;
    }

    /**
     * Creates and adds the display field to the main panel.
     */
    private void createDisplay() {
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Segoe UI", Font.BOLD, 36));
        display.setBackground(new Color(40, 44, 52));
        display.setForeground(new Color(0, 255, 128));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        display.setText(presenter.getDisplay());
        mainPanel.add(display, BorderLayout.NORTH);
    }

    /**
     * Creates the button panel with ergonomic layout.
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 34, 40));

        addButtonRow(panel, new String[]{"7", "8", "9", "/"}, 0);
        addButtonRow(panel, new String[]{"4", "5", "6", "*"}, 1);
        addButtonRow(panel, new String[]{"1", "2", "3", "-"}, 2);
        // Row 3: 0 (spans 2 cols), ., +
        addNumberButton(panel, "0", 0, 3, 2, 1);
        addDecimalButton(panel, ".", 2, 3);
        addOperatorButton(panel, "+", 3, 3);
        // Row 4: C (spans 2 cols), = (spans 2 cols)
        addClearButton(panel, 0, 4, 2, 1);
        addEqualsButton(panel, 2, 4, 2, 1);
        return panel;
    }

    /**
     * Adds a row of buttons to the panel, using type-specific creation methods.
     */
    private void addButtonRow(JPanel panel, String[] labels, int row) {
        for (int col = 0; col < labels.length; col++) {
            String label = labels[col];
            if (label.matches("[0-9]")) {
                addNumberButton(panel, label, col, row, 1, 1);
            } else if (label.matches("[+\\-*/]")) {
                addOperatorButton(panel, label, col, row);
            }
        }
    }

    /**
     * Adds a number button to the panel.
     */
    private void addNumberButton(JPanel panel, String label, int gridx, int gridy, int gridwidth, int gridheight) {
        JButton button = createStyledButton(label, ButtonType.NUMBER);
        addButtonToPanel(panel, button, gridx, gridy, gridwidth, gridheight);
    }

    /**
     * Adds an operator button to the panel.
     */
    private void addOperatorButton(JPanel panel, String label, int gridx, int gridy) {
        JButton button = createStyledButton(label, ButtonType.OPERATOR);
        addButtonToPanel(panel, button, gridx, gridy, 1, 1);
    }

    /**
     * Adds the decimal button to the panel.
     */
    private void addDecimalButton(JPanel panel, String label, int gridx, int gridy) {
        JButton button = createStyledButton(label, ButtonType.DECIMAL);
        addButtonToPanel(panel, button, gridx, gridy, 1, 1);
    }

    /**
     * Adds the clear button to the panel.
     */
    private void addClearButton(JPanel panel, int gridx, int gridy, int gridwidth, int gridheight) {
        JButton button = createStyledButton("C", ButtonType.CLEAR);
        addButtonToPanel(panel, button, gridx, gridy, gridwidth, gridheight);
    }

    /**
     * Adds the equals button to the panel.
     */
    private void addEqualsButton(JPanel panel, int gridx, int gridy, int gridwidth, int gridheight) {
        JButton button = createStyledButton("=", ButtonType.EQUALS);
        addButtonToPanel(panel, button, gridx, gridy, gridwidth, gridheight);
    }

    /**
     * Adds a button to the panel with the given constraints.
     */
    private void addButtonToPanel(JPanel panel, JButton button, int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = gridwidth;
        gbc.weighty = gridheight;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(button, gbc);
    }

    /**
     * Creates a styled JButton based on the button type.
     */
    private JButton createStyledButton(String label, ButtonType type) {
        JButton button = new JButton(label);
        button.setFont(new Font("Segoe UI", Font.BOLD, 28));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(40, 44, 52), 2, true));
        button.addActionListener(new ButtonClickListener());
        switch (type) {
            case NUMBER:
                button.setBackground(new Color(60, 180, 255));
                break;
            case OPERATOR:
                button.setBackground(new Color(255, 85, 85));
                break;
            case DECIMAL:
                button.setBackground(new Color(60, 180, 255));
                break;
            case CLEAR:
                button.setBackground(new Color(255, 170, 0));
                break;
            case EQUALS:
                button.setBackground(new Color(0, 200, 83));
                break;
        }
        return button;
    }

    /**
     * Enum for button types to centralize style logic.
     */
    private enum ButtonType {
        NUMBER, OPERATOR, DECIMAL, CLEAR, EQUALS
    }

    // Removed addCalcButton: replaced by type-specific methods above

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = ((JButton) e.getSource()).getText();
            presenter.input(cmd);
            display.setText(presenter.getDisplay());
        }
    }
}
