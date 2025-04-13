import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A simple bank balance application using a GUI that allows users to deposit, withdraw, and display their balance.
 */
public class BankBalanceGUI {

    private static JFrame primaryFrame;
    private static JPanel panelInputSession, panelButtonSession, panelDisplaySession;
    private static JButton depositButton, withdrawButton, displayButton;
    private static JTextField inputField;
    private static JTextArea displayField;

    private static double balance = 0.0;

    /**
     * The main entry point of the Bank Balance Application.
     * Initializes the JFrame, panels, and buttons, and sets up the action listeners for the buttons.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {

        primaryFrame = new JFrame("Bank Balance Application");
        primaryFrame.setSize(400, 300);
        primaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelInputSession = new JPanel();
        panelInputSession.setLayout(new FlowLayout());

        panelButtonSession = new JPanel();
        panelButtonSession.setLayout(new FlowLayout());

        panelDisplaySession = new JPanel();
        panelDisplaySession.setLayout(new FlowLayout());

        JLabel amountLabel = new JLabel("Enter Amount:");
        inputField = new JTextField(15);

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        displayButton = new JButton("Display Balance");

        displayField = new JTextArea(5, 20);
        displayField.setEditable(false);

        panelInputSession.add(amountLabel);
        panelInputSession.add(inputField);
        panelButtonSession.add(depositButton);
        panelButtonSession.add(withdrawButton);
        panelButtonSession.add(displayButton);
        panelDisplaySession.add(new JScrollPane(displayField));

        primaryFrame.add(panelInputSession, BorderLayout.NORTH);
        primaryFrame.add(panelButtonSession, BorderLayout.CENTER);
        primaryFrame.add(panelDisplaySession, BorderLayout.SOUTH);

        primaryFrame.setLayout(new FlowLayout());

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accountDeposit();
            }
        });
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accountWithdrawal();
            }
        });
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayBalance();
            }
        });

        primaryFrame.setVisible(true);
    }

    /**
     * Handles the deposit functionality.
     * Reads the input amount, checks for validity, and updates the balance accordingly.
     * Displays the updated balance on the JTextArea.
     */
    private static void accountDeposit() {
        try {
            double depositAmount = Double.parseDouble(inputField.getText());
            if (depositAmount > 0) {
                balance += depositAmount;
                displayField.setText("Deposited: " + depositAmount + "\nCurrent Balance: " + balance);
                inputField.setText("");  // Clear the input field
            } else {
                JOptionPane.showMessageDialog(primaryFrame, "Please enter a positive amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(primaryFrame, "Invalid input. Please enter a valid number.");
        }
    }

    /**
     * Handles the withdrawal functionality.
     * Reads the input amount, checks for validity and sufficient balance, and updates the balance accordingly.
     * Displays the updated balance or error messages on the JTextArea.
     */
    private static void accountWithdrawal() {
        try {
            double withdrawAmount = Double.parseDouble(inputField.getText());
            if (withdrawAmount > 0 && withdrawAmount <= balance) {
                balance -= withdrawAmount;
                displayField.setText("Withdrawn: " + withdrawAmount + "\nCurrent Balance: " + balance);
                inputField.setText("");  // Clear the input field
            } else if (withdrawAmount > balance) {
                JOptionPane.showMessageDialog(primaryFrame, "Insufficient balance.");
            } else {
                JOptionPane.showMessageDialog(primaryFrame, "Please enter a positive amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(primaryFrame, "Invalid input. Please enter a valid number.");
        }
    }

    /**
     * Displays the current bank balance in the JTextArea.
     */
    private static void displayBalance() {
        displayField.setText("Current Balance: " + balance);
    }
}
