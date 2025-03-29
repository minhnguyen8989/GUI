import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    // Create JFrame
    private static JFrame frame;

    // Declare Components
    private static JPanel panel1, panel2, panel3;
    private static JButton depositButton, withdrawButton, displayButton;
    private static JTextField amountField;
    private static JTextArea balanceArea;

    // Initial Bank Account balance
    private static double balance = 0.0;

    public static void main(String[] args) {

        // Set up the JFrame
        frame = new JFrame("Bank Balance Application");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JPanel and layout manager
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());

        //Create components
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField(15);

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        displayButton = new JButton("Display Balance");

        balanceArea = new JTextArea(5, 20);
        balanceArea.setEditable(false);

        //Assign component to panel
        panel1.add(amountLabel);
        panel1.add(amountField);
        panel2.add(depositButton);
        panel2.add(withdrawButton);
        panel2.add(displayButton);
        panel3.add(new JScrollPane(balanceArea));

        //Layout Setup
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);

        frame.setLayout(new FlowLayout());

        // Add action listeners
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayBalance();
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    // Deposit Method
    private static void handleDeposit() {
        try {
            double depositAmount = Double.parseDouble(amountField.getText());
            if (depositAmount > 0) {
                balance += depositAmount;
                balanceArea.setText("Deposited: " + depositAmount + "\nCurrent Balance: " + balance);
                amountField.setText("");  // Clear the input field
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a positive amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
        }
    }

    // Withdraw Method
    private static void handleWithdraw() {
        try {
            double withdrawAmount = Double.parseDouble(amountField.getText());
            if (withdrawAmount > 0 && withdrawAmount <= balance) {
                balance -= withdrawAmount;
                balanceArea.setText("Withdrawn: " + withdrawAmount + "\nCurrent Balance: " + balance);
                amountField.setText("");  // Clear the input field
            } else if (withdrawAmount > balance) {
                JOptionPane.showMessageDialog(frame, "Insufficient balance.");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a positive amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
        }
    }

    // Display Balance Method
    private static void displayBalance() {
        balanceArea.setText("Current Balance: " + balance);
    }
}
