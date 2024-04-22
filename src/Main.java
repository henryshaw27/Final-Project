import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.NumberFormatException;

public class Main extends JFrame {
    LinkedList checkingList = new LinkedList();
    LinkedList savingsList = new LinkedList();
    String cTransType, sTransType;
    JRadioButton checkingDeposit, checkingWithdrawal, checkingTransfer, savingsDeposit, savingsWithdrawal, savingsTransfer;
    JTextField checkingAmount, checkingMonth, checkingDay, checkingYear, checkingName, savingsAmount, savingsMonth, savingsDay, savingsYear, savingsName;
    JLabel checkingAmountLabel, checkingMonthLabel, checkingDayLabel, checkingYearLabel, checkingNameLabel, savingsAmountLabel, savingsMonthLabel, savingsDayLabel, savingsYearLabel, savingsNameLabel;
    JButton checkingAddButton, savingsAddButton, checkingSearchButton, checkingDataButton, savingsSearchButton, checkingRemoveButton, savingsRemoveButton, savingsDataButton;
    JTextArea outputChecking, outputSavings;
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
        checkingDataButton = new JButton("View Stats");

        //buttons for savings tab
        savingsAddButton = new JButton("Add Transaction");
        savingsRemoveButton = new JButton("Remove Transaction");
        savingsSearchButton = new JButton("Search Transaction");
        savingsDataButton = new JButton("View Stats");

        //text area for checking tab
        outputChecking = new JTextArea(10, 30);
        JScrollPane checkingScroll = new JScrollPane(outputChecking);
        checkingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //text area for savings tab
        outputSavings = new JTextArea(10, 30);
        JScrollPane savingsScroll = new JScrollPane(outputSavings);
        savingsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //action listener to select deposit for checking account
        checkingDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cTransType = "deposit";
            }
        });
        //action listener to select withdrawal for checking account
        checkingWithdrawal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cTransType = "withdrawal";
            }
        });
        //action lister to select transfer for checking account
        checkingTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cTransType = "transfer";
            }
        });
        //add transaction button action listener for checking account
        checkingAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = null;
                transaction trans = null;
                boolean exCaught = false;
                //checking if date is numerical
                try {
                    date = new Date(Integer.parseInt(checkingMonth.getText()),
                            Integer.parseInt(checkingDay.getText()),
                            Integer.parseInt(checkingYear.getText()));
                } catch (NumberFormatException exception){
                    outputChecking.append("Date must be in numerical form" + "\n");
                    exCaught = true;
                }
                //checking if amount is numerical
                try {
                    trans = new transaction(cTransType, checkingName.getText(), Double.parseDouble(checkingAmount.getText()), date);
                } catch (NumberFormatException exception){
                    outputChecking.append("Amount must be a number" + "\n");
                    exCaught = true;
                }
                //printing statement
                if (!exCaught){
                    checkingList.insertNode(trans);
//                System.out.println("Yay!!");
                    outputChecking.append(trans.getName() + " added to transactions." + "\n");
                    if (trans.getTransType().equals("transfer")) {
                        transaction ttrans = new transaction("deposit",
                                "Savings Transfer: " + checkingName.getText(),
                                Double.parseDouble(checkingAmount.getText()),
                                date);
                        savingsList.insertNode(ttrans);
                    }
                    outputChecking.append("Checking account balance: $" + checkingList.calcBalance() + "\n");
                    outputChecking.append("Savings account balance: $" + savingsList.calcBalance() + "\n");
                }
            }
        });

        //action listener for remove transaction button in checking account
        checkingRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkingList.removeDuplicate(checkingName.getText());
                savingsList.removeDuplicate("Savings Transfer: " + checkingName.getText());
                outputChecking.append(checkingName.getText() + " removed from transactions." + "\n");
                outputChecking.append("Checking account balance: $" + checkingList.calcBalance() + "\n");
                outputChecking.append("Savings account balance: $" + savingsList.calcBalance() + "\n");
            }
        });

        checkingSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Date date = new Date(Integer.parseInt(checkingMonth.getText()),
                        Integer.parseInt(checkingDay.getText()),
                        Integer.parseInt(checkingYear.getText()));
                checkingList.findNode(date);
            }
        });
        checkingDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double maxTransaction = checkingList.max(checkingList.head);
                int length = checkingList.length();
                outputChecking.append("Max Transaction: $" + maxTransaction + "\n");
                outputChecking.append("Amount of Transactions: " + length + "\n");
            }
        });

        //action listeners for savings account, same as checking account
        savingsDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sTransType = "deposit";
            }
        });
        savingsWithdrawal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sTransType = "withdrawal";
            }
        });
        savingsTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sTransType = "transfer";
            }
        });
        savingsAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = null;
                transaction trans = null;
                boolean exCaught = false;
                try {
                    date = new Date(Integer.parseInt(savingsMonth.getText()),
                            Integer.parseInt(savingsDay.getText()),
                            Integer.parseInt(savingsYear.getText()));
                } catch (NumberFormatException exception){
                    outputSavings.append("Date must be in numerical form" + "\n");
                    exCaught = true;
                }
                try {
                    trans = new transaction(sTransType, savingsName.getText(), Double.parseDouble(savingsAmount.getText()), date);
                } catch (NumberFormatException exception){
                    outputSavings.append("Amount must be a number" + "\n");
                    exCaught = true;
                }
                if (!exCaught){
                    savingsList.insertNode(trans);
//                    System.out.println("Yay!!");
                    outputSavings.append(trans.getName() + " added to transactions." + "\n");
                    if (trans.getTransType().equals("transfer")) {
                        transaction ttrans = new transaction("deposit",
                                "Checking Transfer: " + savingsName.getText(),
                                Double.parseDouble(savingsAmount.getText()),
                                date);
                        checkingList.insertNode(ttrans);
                    }
                    outputSavings.append("Checking account balance: $" + checkingList.calcBalance() + "\n");
                    outputSavings.append("Savings account balance: $" + savingsList.calcBalance() + "\n");
                }
            }
        });

        savingsRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savingsList.removeDuplicate(savingsName.getText());
                checkingList.removeDuplicate("Savings Transfer: " + savingsName.getText());
                outputSavings.append(savingsName.getText() + " removed from transactions." + "\n");
                outputSavings.append("Checking account balance: $" + checkingList.calcBalance() + "\n");
                outputSavings.append("Savings account balance: $" + savingsList.calcBalance() + "\n");
            }
        });
        savingsSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Date date = new Date(Integer.parseInt(savingsMonth.getText()),
                        Integer.parseInt(savingsDay.getText()),
                        Integer.parseInt(savingsYear.getText()));
                savingsList.findNode(date);
            }
        });
        savingsDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double maxTransaction = savingsList.max(savingsList.head);
                int length = savingsList.length();
                outputSavings.append("Max Transaction: $" + maxTransaction + "\n");
                outputSavings.append("Amount of Transactions: " + length + "\n");
            }
        });

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
        JPanel checkingButtonPanel = new JPanel(new GridLayout(1,4));
        checkingButtonPanel.add(checkingAddButton);
        checkingButtonPanel.add(checkingRemoveButton);
        checkingButtonPanel.add(checkingSearchButton);
        checkingButtonPanel.add(checkingDataButton);

        //panel for savings account buttons
        JPanel savingsButtonPanel = new JPanel(new GridLayout(1,4));
        savingsButtonPanel.add(savingsAddButton);
        savingsButtonPanel.add(savingsRemoveButton);
        savingsButtonPanel.add(savingsSearchButton);
        savingsButtonPanel.add(savingsDataButton);

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
        JPanel checkingPanel = new JPanel(new GridLayout(6,1));
        checkingPanel.add(new JLabel("Checking Account"));
        checkingPanel.add(checkingTopPanel);
        checkingPanel.add(checkingDatePanel);
        checkingPanel.add(checkingNamePanel);
        checkingPanel.add(checkingButtonPanel);
        checkingPanel.add(checkingScroll);

        //panel for savings account
        JPanel savingsPanel = new JPanel(new GridLayout(6,1));
        savingsPanel.add(new JLabel("Savings Account"));
        savingsPanel.add(savingsTopPanel);
        savingsPanel.add(savingsDatePanel);
        savingsPanel.add(savingsNamePanel);
        savingsPanel.add(savingsButtonPanel);
        savingsPanel.add(savingsScroll);

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