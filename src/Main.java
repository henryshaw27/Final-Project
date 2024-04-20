import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    JRadioButton checkingDeposit, checkingWithdrawal, checkingTransfer, savingsDeposit, savingsWithdrawal, savingsTransfer;
    JTextField checkingAmount, checkingMonth, checkingDay, checkingYear, checkingName, savingsAmount, savingsMonth, savingsDay, savingsYear, savingsName;
    JLabel checkingAmountLabel, checkingMonthLabel, checkingDayLabel, checkingYearLabel, checkingNameLabel, savingsAmountLabel, savingsMonthLabel, savingsDayLabel, savingsYearLabel, savingsNameLabel;
    JButton checkingAddButton, savingsAddButton, checkingSearchButton, savingsSearchButton, checkingRemoveButton, savingsRemoveButton;
    public void bankApp(){
        setTitle("Brothers Banking");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Radio Buttons for checking tab along with button group
        checkingDeposit = new JRadioButton("Deposit");
        checkingWithdrawal = new JRadioButton("Withdrawal");
        checkingTransfer = new JRadioButton("Transfer");
        ButtonGroup checkingOptions = new ButtonGroup();
        checkingOptions.add(checkingDeposit);
        checkingOptions.add(checkingWithdrawal);
        checkingOptions.add(checkingTransfer);

        //radio buttons for savings tab along with button group
        savingsDeposit = new JRadioButton("Deposit");
        savingsWithdrawal = new JRadioButton("Withdrawal");
        savingsTransfer = new JRadioButton("Transfer");
        ButtonGroup savingsOptions = new ButtonGroup();
        savingsOptions.add(savingsDeposit);
        savingsOptions.add(savingsWithdrawal);
        savingsOptions.add(savingsTransfer);

        //text fields and labels for checking tab
        checkingAmountLabel = new JLabel("Enter Amount of Transaction:");
        checkingAmount = new JTextField(10);
        checkingMonthLabel = new JLabel("Month:");
        checkingMonth = new JTextField(10);
        checkingDayLabel = new JLabel("Day:");
        checkingDay = new JTextField(10);
        checkingYearLabel = new JLabel("Year:");
        checkingYear = new JTextField(10);
        checkingNameLabel = new JLabel("Enter What You Would Like to Name the Transaction:");
        checkingName = new JTextField(10);

        //text fields and labels for savings tab
        savingsAmountLabel = new JLabel("Enter Amount of Transaction:");
        savingsAmount = new JTextField(10);
        savingsMonthLabel = new JLabel("Month:");
        savingsMonth = new JTextField(10);
        savingsDayLabel = new JLabel("Day:");
        savingsDay = new JTextField(10);
        savingsYearLabel = new JLabel("Year:");
        savingsYear = new JTextField(10);
        savingsNameLabel = new JLabel("Enter What You Would Like to Name the Transaction:");
        savingsName = new JTextField(10);

        //buttons for checking tab
        checkingAddButton = new JButton("Add Transaction");
        checkingRemoveButton = new JButton("Remove Transaction");
        checkingSearchButton = new JButton("Search Transaction");

        //buttons for savings tab
        savingsAddButton = new JButton("Add Transaction");
        savingsRemoveButton = new JButton("Remove Transaction");
        savingsSearchButton = new JButton("Search Transaction");

        

        //panel for checking radio buttons
        JPanel checkingRadioButtonPanel = new JPanel(new GridLayout(3,1));
        checkingRadioButtonPanel.add(checkingDeposit);
        checkingRadioButtonPanel.add(checkingWithdrawal);
        checkingRadioButtonPanel.add(checkingTransfer);

        //panel for savings radio buttons
        JPanel savingsRadioButtonPanel = new JPanel(new GridLayout(3,1));
        savingsRadioButtonPanel.add(savingsDeposit);
        savingsRadioButtonPanel.add(savingsWithdrawal);
        savingsRadioButtonPanel.add(savingsTransfer);

        //panel for checking amount label and text field
        JPanel checkingAmountPanel = new JPanel(new GridLayout(2,1));
        checkingAmountPanel.add(checkingAmountLabel);
        checkingAmountPanel.add(checkingAmount);

        //panel for savings amount label and text field
        JPanel savingsAmountPanel = new JPanel(new GridLayout(2,1));
        savingsAmountPanel.add(savingsAmountLabel);
        savingsAmountPanel.add(savingsAmount);

        //panel for checking date labels and text fields
        JPanel checkingDatePanel = new JPanel(new GridLayout(2,3));
        checkingDatePanel.add(checkingMonthLabel);
        checkingDatePanel.add(checkingDayLabel);
        checkingDatePanel.add(checkingYearLabel);
        checkingDatePanel.add(checkingMonth);
        checkingDatePanel.add(checkingDay);
        checkingDatePanel.add(checkingYear);

        //panel for savings date labels and text fields
        JPanel savingsDatePanel = new JPanel(new GridLayout(2,3));
        savingsDatePanel.add(savingsMonthLabel);
        savingsDatePanel.add(savingsDayLabel);
        savingsDatePanel.add(savingsYearLabel);
        savingsDatePanel.add(savingsMonth);
        savingsDatePanel.add(savingsDay);
        savingsDatePanel.add(savingsYear);

        //panel for checking transaction name label and text field
        JPanel checkingNamePanel = new JPanel(new GridLayout(2,1));
        checkingNamePanel.add(checkingNameLabel);
        checkingNamePanel.add(checkingName);

        //panel for savings transaction name label and text field
        JPanel savingsNamePanel = new JPanel(new GridLayout(2,1));
        savingsNamePanel.add(savingsNameLabel);
        savingsNamePanel.add(savingsName);

        //panel for checking account buttons
        JPanel checkingButtonPanel = new JPanel(new GridLayout(1,3));
        checkingButtonPanel.add(checkingAddButton);
        checkingButtonPanel.add(checkingRemoveButton);
        checkingButtonPanel.add(checkingSearchButton);

        //panel for savings account buttons
        JPanel savingsButtonPanel = new JPanel(new GridLayout(1,3));
        savingsButtonPanel.add(savingsAddButton);
        savingsButtonPanel.add(savingsRemoveButton);
        savingsButtonPanel.add(savingsSearchButton);

        //panel organizing the top half of the checking tab
        JPanel checkingTopPanel = new JPanel(new GridLayout(1,2));
        checkingTopPanel.add(checkingRadioButtonPanel);
        checkingTopPanel.add(checkingAmountPanel);

        //panel organizing the top half of the savings tab
        JPanel savingsTopPanel = new JPanel(new GridLayout(1,2));
        savingsTopPanel.add(savingsRadioButtonPanel);
        savingsTopPanel.add(savingsAmountPanel);

// tab panel to separate checking and savings accounts
        JTabbedPane tabPanel = new JTabbedPane();

        //panel for checking account
        JPanel checkingPanel = new JPanel(new GridLayout(5,1));
        checkingPanel.add(new JLabel("Checking Account"));
        checkingPanel.add(checkingTopPanel);
        checkingPanel.add(checkingDatePanel);
        checkingPanel.add(checkingNamePanel);
        checkingPanel.add(checkingButtonPanel);

        //panel for savings account
        JPanel savingsPanel = new JPanel(new GridLayout(5,1));
        savingsPanel.add(new JLabel("Savings Account"));
        savingsPanel.add(savingsTopPanel);
        savingsPanel.add(savingsDatePanel);
        savingsPanel.add(savingsNamePanel);
        savingsPanel.add(savingsButtonPanel);

        //adding panels to the tabbed pane
        tabPanel.addTab("Checking Account", checkingPanel);
        tabPanel.addTab("Savings Account", savingsPanel);

        add(tabPanel);
    }
    public static void main(String[] args){
//        Scanner sc1 = new Scanner(System.in);
//        System.out.println("");
        Main bankGUI = new Main();
        bankGUI.bankApp();
        bankGUI.setVisible(true);

    }
}