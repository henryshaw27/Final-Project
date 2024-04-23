
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.lang.NumberFormatException;




public class Main extends JFrame implements Serializable {
    public void drawCheckingChart() {
        JFrame chartFrame = new JFrame("Checking Account Balance History");
        chartFrame.setSize(600, 400);
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                int padding = 30;

                // Draw x-axis
                g2d.drawLine(padding, height - padding, width - padding, height - padding);
                // Draw y-axis
                g2d.drawLine(padding, padding, padding, height - padding);

                TNode current = checkingList.head;
                double maxBalance = 0;

                // Find maximum balance
                while (current != null) {
                    maxBalance = Math.max(maxBalance, current.getTrans().getAmount());
                    current = current.getNext();
                }

                int xScale = (width - 2 * padding) / (checkingList.length() + 1);
                int yScale = (height - 2 * padding) / (int) (maxBalance + 1);

                // Draw x-axis label
                g2d.drawString("Date Entered", width / 2, height - padding / 2);

                // Draw y-axis label
                g2d.rotate(-Math.PI / 2);
                g2d.drawString("Transaction Amount", -height / 2, padding / 2);
                g2d.rotate(Math.PI / 2);
                

                // Draw points and lines
                current = checkingList.head;
                int prevX = padding, prevY = height - padding;
                while (current != null) {
                    int x = padding + xScale;
                    int y = height - padding - (int) (current.getTrans().getAmount() * yScale);
                    g2d.fillOval(x - 2, y - 2, 4, 4); // Draw points
                    g2d.drawLine(prevX, prevY, x, y); // Draw lines
                    prevX = x;
                    prevY = y;
                    current = current.getNext();
                    xScale += (width - 2 * padding) / (checkingList.length() + 1);
                }
            }
        };


        chartFrame.add(chartPanel);
        chartFrame.setVisible(true);
    }
    static LinkedList checkingList = new LinkedList();
    LinkedList savingsList = new LinkedList();
    String cTransType, sTransType;
    JRadioButton checkingDeposit, checkingWithdrawal, checkingTransfer, savingsDeposit, savingsWithdrawal, savingsTransfer;
    JTextField checkingAmount, checkingMonth, checkingDay, checkingYear, checkingName, savingsAmount, savingsMonth, savingsDay, savingsYear, savingsName;
    JLabel checkingAmountLabel, checkingMonthLabel, checkingDayLabel, checkingYearLabel, checkingNameLabel, savingsAmountLabel, savingsMonthLabel, savingsDayLabel, savingsYearLabel, savingsNameLabel, checkingBalanceLabel, savingsBalanceLabel;
    JButton checkingAddButton, savingsAddButton, checkingSearchButton, checkingDataButton, savingsSearchButton, checkingRemoveButton, savingsRemoveButton, savingsDataButton, checkingVisualButton,savingsVisualButton;
    JTextArea outputChecking, outputSavings;
    public void bankApp(){
        setTitle("Brothers Banking");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveListToFile();
            }
        });

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
        checkingBalanceLabel = new JLabel("Checking Balance: ");

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
        savingsBalanceLabel = new JLabel("Savings Balance: ");

        //buttons for checking tab
        checkingAddButton = new JButton("Add Transaction");
        checkingRemoveButton = new JButton("Remove Transaction");
        checkingSearchButton = new JButton("Search Transaction");
        checkingDataButton = new JButton("View Stats");
        checkingVisualButton = new JButton("View Chart");

        //buttons for savings tab
        savingsAddButton = new JButton("Add Transaction");
        savingsRemoveButton = new JButton("Remove Transaction");
        savingsSearchButton = new JButton("Search Transaction");
        savingsDataButton = new JButton("View Stats");
        savingsVisualButton = new JButton("View Chart");

        //text area for checking tab
        outputChecking = new JTextArea(30, 30);
        JScrollPane checkingScroll = new JScrollPane(outputChecking);
        checkingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //text area for savings tab
        outputSavings = new JTextArea(30, 30);
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

                try {
                    int month = Integer.parseInt(checkingMonth.getText());
                    int day = Integer.parseInt(checkingDay.getText());
                    int year = Integer.parseInt(checkingYear.getText());

                    // Validate month, day, and year
                    if (month < 1 || month > 12) {
                        outputChecking.append("Invalid month. Please enter a number between 1 and 12." + "\n");
                        return;
                    }
                    if (day < 1 || day > 31) {
                        outputChecking.append("Invalid day. Please enter a number between 1 and 31." + "\n");
                        return;
                    }
                    if (year < 1900 || year > 2100) {
                        outputChecking.append("Invalid year. Please enter a number between 1900 and 2100." + "\n");
                        return;
                    }

                    date = new Date(month, day, year);
                } catch (NumberFormatException exception) {
                    outputChecking.append("Invalid date format. Please enter valid numerical values for date." + "\n");
                    exCaught = true;
                }

                if (!exCaught) {
                    try {
                        trans = new transaction(cTransType, checkingName.getText(), Double.parseDouble(checkingAmount.getText()), date);
                    } catch (NumberFormatException exception) {
                        outputChecking.append("Amount must be a number" + "\n");
                        exCaught = true;
                    }
                }

                if (!exCaught) {
                    checkingList.insertNode(trans);
                    outputChecking.append(trans.getName() + " added to transactions." + "\n");
                    if (trans.getTransType().equals("transfer")) {
                        transaction ttrans = new transaction("deposit",
                                "Savings Transfer: " + checkingName.getText(),
                                Double.parseDouble(checkingAmount.getText()),
                                date);
                        savingsList.insertNode(ttrans);
                    }
                    checkingBalanceLabel.setText("Checking account balance: $" + checkingList.calcBalance());
                    savingsBalanceLabel.setText("Savings account balance: $" + savingsList.calcBalance());
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
                checkingBalanceLabel.setText("Checking account balance: $" + checkingList.calcBalance());
                savingsBalanceLabel.setText("Savings account balance: $" + savingsList.calcBalance());
            }
        });

        checkingSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                TNode foundNode = checkingList.findNode(checkingName.getText());
                if (foundNode != null) {
                    transaction foundTransaction = foundNode.getTrans();
                    outputChecking.append("Transaction Found:" + "\n");
                    outputChecking.append("Name: " + foundTransaction.getName() + "\n");
                    outputChecking.append("Amount: $" + foundTransaction.getAmount() + "\n");
                    outputChecking.append("Type: " + foundTransaction.getTransType() + "\n");
                    outputChecking.append("Date: " + foundTransaction.getDate().getMonth() + "/" +
                            foundTransaction.getDate().getDay() + "/" +
                            foundTransaction.getDate().getYear() + "\n");
                } else {
                    outputChecking.append("Transaction not found." + "\n");
                }
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

        checkingVisualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawCheckingChart();
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
                    int month = Integer.parseInt(savingsMonth.getText());
                    int day = Integer.parseInt(savingsDay.getText());
                    int year = Integer.parseInt(savingsYear.getText());

                    // Validate month, day, and year
                    if (month < 1 || month > 12) {
                        outputSavings.append("Invalid month. Please enter a number between 1 and 12." + "\n");
                        return;
                    }
                    if (day < 1 || day > 31) {
                        outputSavings.append("Invalid day. Please enter a number between 1 and 31." + "\n");
                        return;
                    }
                    if (year < 1900 || year > 2100) {
                        outputSavings.append("Invalid year. Please enter a number between 1900 and 2100." + "\n");
                        return;
                    }

                    date = new Date(month, day, year);
                } catch (NumberFormatException exception) {
                    outputSavings.append("Invalid date format. Please enter valid numerical values for date." + "\n");
                    exCaught = true;
                }

                if (!exCaught) {
                    try {
                        trans = new transaction(sTransType, savingsName.getText(), Double.parseDouble(savingsAmount.getText()), date);
                    } catch (NumberFormatException exception) {
                        outputSavings.append("Amount must be a number" + "\n");
                        exCaught = true;
                    }
                }

                if (!exCaught) {
                    savingsList.insertNode(trans);
                    outputSavings.append(trans.getName() + " added to transactions." + "\n");
                    if (trans.getTransType().equals("transfer")) {
                        transaction ttrans = new transaction("deposit",
                                "Checking Transfer: " + savingsName.getText(),
                                Double.parseDouble(savingsAmount.getText()),
                                date);
                        checkingList.insertNode(ttrans);
                    }
                    checkingBalanceLabel.setText("Checking account balance: $" + checkingList.calcBalance());
                    savingsBalanceLabel.setText("Savings account balance: $" + savingsList.calcBalance());
                }
            }
        });

        savingsRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savingsList.removeDuplicate(savingsName.getText());
                checkingList.removeDuplicate("Savings Transfer: " + savingsName.getText());
                outputSavings.append(savingsName.getText() + " removed from transactions." + "\n");
                checkingBalanceLabel.setText("Checking account balance: $" + checkingList.calcBalance());
                savingsBalanceLabel.setText("Savings account balance: $" + savingsList.calcBalance());
            }
        });
        savingsSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TNode foundNode = savingsList.findNode(savingsName.getText());
                if (foundNode != null) {
                    transaction foundTransaction = foundNode.getTrans();
                    outputSavings.append("Transaction Found:" + "\n");
                    outputSavings.append("Name: " + foundTransaction.getName() + "\n");
                    outputSavings.append("Amount: $" + foundTransaction.getAmount() + "\n");
                    outputSavings.append("Type: " + foundTransaction.getTransType() + "\n");
                    outputSavings.append("Date: " + foundTransaction.getDate().getMonth() + "/" +
                            foundTransaction.getDate().getDay() + "/" +
                            foundTransaction.getDate().getYear() + "\n");
                } else {
                    outputSavings.append("Transaction not found." + "\n");
                }
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
        JPanel checkingButtonPanel = new JPanel(new GridLayout(1,5));
        checkingButtonPanel.add(checkingAddButton);
        checkingButtonPanel.add(checkingRemoveButton);
        checkingButtonPanel.add(checkingSearchButton);
        checkingButtonPanel.add(checkingDataButton);
        checkingButtonPanel.add(checkingVisualButton);

        //panel for savings account buttons
        JPanel savingsButtonPanel = new JPanel(new GridLayout(1,5));
        savingsButtonPanel.add(savingsAddButton);
        savingsButtonPanel.add(savingsRemoveButton);
        savingsButtonPanel.add(savingsSearchButton);
        savingsButtonPanel.add(savingsDataButton);
        savingsButtonPanel.add(savingsVisualButton);

        JPanel checkingHeader = new JPanel(new GridLayout(1,2));
        checkingHeader.add(new JLabel("Checking Account"));
        checkingHeader.add(checkingBalanceLabel);

        JPanel savingsHeader = new JPanel(new GridLayout(1,2));
        savingsHeader.add(new JLabel("Savings Account"));
        savingsHeader.add(savingsBalanceLabel);


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
        checkingPanel.add(checkingHeader);
        checkingPanel.add(checkingTopPanel);
        checkingPanel.add(checkingDatePanel);
        checkingPanel.add(checkingNamePanel);
        checkingPanel.add(checkingButtonPanel);
        checkingPanel.add(checkingScroll);

        //panel for savings account
        JPanel savingsPanel = new JPanel(new GridLayout(6,1));
        savingsPanel.add(savingsHeader);
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

    private void saveListToFile(){
        try {
            ObjectOutputStream cOutput = new ObjectOutputStream(new FileOutputStream("checkingList.dat"));
            ObjectOutputStream sOutput = new ObjectOutputStream(new FileOutputStream("savingsList.dat"));
            cOutput.writeObject(checkingList);
            sOutput.writeObject(savingsList);
            cOutput.close();
            sOutput.close();
        } catch(IOException ioe) {
            System.out.println("Error saving to file");
        }
    }

    private void loadListToFile(){
        try {
            ObjectInputStream cInput = new ObjectInputStream(new FileInputStream("checkingList.dat"));
            checkingList = (LinkedList) cInput.readObject();
            cInput.close();
            ObjectInputStream sInput = new ObjectInputStream(new FileInputStream("savingsList.dat"));
            savingsList = (LinkedList) sInput.readObject();
            sInput.close();
        } catch (FileNotFoundException e) {
            checkingList = new LinkedList();
            savingsList = new LinkedList();
            System.out.println("Files not found, making new lists");
        } catch(IOException ioe) {
            System.out.println("Error opening file");
        } catch(ClassNotFoundException cnfe){
            System.out.println("Object read is not a LinkedList");
        }

    }
    public static void main(String[] args){
//        Scanner sc1 = new Scanner(System.in);
//        System.out.println("");
        Main bankGUI = new Main();
        bankGUI.loadListToFile();
        bankGUI.bankApp();
        bankGUI.setVisible(true);
    }
}