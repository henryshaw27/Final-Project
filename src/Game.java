//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Game extends JFrame {
//    JTextField answerBox, newItemBox, newQuestionBox;
//    JButton answerButton, startGame, newItemButton;
//    JTextArea output;
//    JLabel header, answerLabel, newItemLabel, newQuestionLabel;
//    Node root;
//    Node currentQuestion;
//
//    public Game() {
//        setTitle("20 Questions Game");
//        setSize(1150, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        answerBox = new JTextField();
//        newItemBox = new JTextField();
//        newQuestionBox = new JTextField();
//        answerLabel = new JLabel("Answer Yes or No:");
//        newItemLabel = new JLabel("Enter what you thought of (if the computer loses): ");
//        newQuestionLabel = new JLabel("Enter a question to differentiate from the computers guess (only if the computer loses): ");
//        answerButton = new JButton("Submit Answer");
//        newItemButton = new JButton("Add to Game");
//        startGame = new JButton("Start Game");
//
//        output = new JTextArea(10, 30);
//        header = new JLabel("Think of something for the computer to guess!");
//
//        root = constructGame();
//        currentQuestion = root;
//
//        startGame.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                playGame(root);
//            }
//        });
//
//        answerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String answer = answerBox.getText().toLowerCase();
//                output.append("\n" + answer + "\n");
////                navigateTree(answer);
//                answerBox.setText("");
//            }
//        });
//
//        newItemButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                String newItem = newItemBox.getText();
//                currentQuestion.answer = newItem;
//                String newQuestion = newQuestionBox.getText();
//                currentQuestion.question = newQuestion;
//                currentQuestion.yes = new Node(currentQuestion.question, currentQuestion.answer);
//                currentQuestion.no = new Node(currentQuestion.question, currentQuestion.answer);
//                output.append("Thanks, I'll remember that for next game!");
//            }
//        });
//
//        JPanel answerPanel = new JPanel(new GridLayout(1, 2));
//        answerPanel.add(answerBox);
//        answerPanel.add(answerButton);
//
//        JPanel addPanel = new JPanel(new GridLayout(2,2));
//        addPanel.add(newItemLabel);
//        addPanel.add(newQuestionLabel);
//        addPanel.add(newItemBox);
//        addPanel.add(newQuestionBox);
//
//        JPanel finalPanel = new JPanel(new GridLayout(7, 1));
//        finalPanel.add(header);
//        finalPanel.add(startGame);
//        finalPanel.add(new JScrollPane(output));
//        finalPanel.add(answerLabel);
//        finalPanel.add(answerPanel);
//        finalPanel.add(addPanel);
//        finalPanel.add(newItemButton);
//
//        add(finalPanel);
//    }
//
//    public static Node constructGame() {
//        Node root = new Node("Is it a living thing?", null);
//        root.yes = new Node("Does it have legs?", null);
//        root.no = new Node("Is it man-made?", null);
//        root.yes.yes = new Node("Is it a zebra?", "a zebra");
//        root.yes.no = new Node("Is it a bird?", "a bird");
//        root.no.yes = new Node("Is it a bike?", "a bike");
//        root.no.no = new Node("Is it a lake?", "a lake");
//        return root;
//    }
//
//    public void playGame(Node current) {
//        while (current != null) {
//            if (current.yes == null && current.no == null) { // Leaf node (answer)
//                output.append("Is it " + current.answer + "?\n");
//                String answer = JOptionPane.showInputDialog(this, "Is it " + current.answer + "? (yes/no)").toLowerCase();
//                if (answer.equals("yes")) {
//                    output.append("Computer wins!\n");
//                } else {
//                    String newObject = JOptionPane.showInputDialog(this, "What were you thinking of?");
//                    String newQuestion = JOptionPane.showInputDialog(this, "Please give a question to distinguish between " + newObject + " and " + current.answer);
//                    current.question = newQuestion;
//                    current.answer = newObject;
//                    current.yes = new Node(current.question, current.answer);
//                    current.no = new Node(current.question, current.answer);
//                    output.append("Thanks! I'll remember that for next time.\n");
//                }
//                break;
//            } else { // Question node
//                output.append(current.question + "\n");
//                String answer = JOptionPane.showInputDialog(this, current.question + " (yes/no)").toLowerCase();
//                if (answer.equals("yes")) {
//                    current = current.yes;
//                } else {
//                    current = current.no;
//                }
//            }
//        }
//    }


//    public void playGame() {
//        output.setText("");
//        currentQuestion = root;
//        askQuestion();
//    }
//
//    private void askQuestion() {
//        if (currentQuestion != null) {
//            output.append(currentQuestion.question + "\n");
//        }
//    }
//
//
//
//
//
//
//    private void navigateTree(String answer) {
//        if (currentQuestion == null) {
//            return;
//        }
//        if ("yes".equals(answer)) {
//            currentQuestion = currentQuestion.yes;
//        } else if ("no".equals(answer)) {
//            currentQuestion = currentQuestion.no;
//        }
//        if (currentQuestion != null) {
//            if (currentQuestion.answer != null) {
//                output.append("Is it " + currentQuestion.answer + "?\n");
//                if ("yes".equals(answer)){
//                    output.append("Computer wins!" + "\n");
//                }else{
//                    output.append("Please enter what you guessed into the boxes below");
//                }
//
//            } else {
//                askQuestion();
//            }
//
//        }



//
//    public static void main(String[] args) {
//        Game gameGUI = new Game();
//        gameGUI.setVisible(true);
//    }
//}
