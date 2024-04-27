import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Game2 extends JFrame implements Serializable {
    JButton startGame;
    JTextArea output;
    JLabel header;
    Node root;
    Node currentQuestion;

    public Game2() {
        setTitle("20 Questions Game");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //window listener so the game saves when the window is close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveTree();
            }
        });

        //buttons and labels for GUI
        startGame = new JButton("Start Game");

        output = new JTextArea(10, 30);
        header = new JLabel("Think of something for the computer to guess!");

        root = constructGame();
        currentQuestion = root;

       //action listener for button to start the game
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playGame(root);
            }
        });

        //panel for the GUI
        JPanel finalPanel = new JPanel(new GridLayout(7, 1));
        finalPanel.add(header);
        finalPanel.add(startGame);
        finalPanel.add(new JScrollPane(output));



        add(finalPanel);
    }

    //the construct game method which creates the default questions and which branches they belong to (yes or no)
    public static Node constructGame() {
        Node root = new Node("Is it a living thing?", null);
        root.yes = new Node("Does it have legs?", null);
        root.no = new Node("Is it man-made?", null);
        root.yes.yes = new Node("Is it a zebra?", "a zebra");
        root.yes.no = new Node("Is it a bird?", "a bird");
        root.no.yes = new Node("Is it a bike?", "a bike");
        root.no.no = new Node("Is it a lake?", "a lake");
        return root;
    }


    public void playGame(Node current) {
        //makes sure node is not null
        while (current != null) {
            //checks if it is a leaf node
            if (current.yes == null && current.no == null) {
                //if its a leaf node it will ask the final question
                output.append("Is it " + current.answer + "?\n");
                String answer = JOptionPane.showInputDialog(this, "Is it " + current.answer + "? (yes/no)").toLowerCase();
                //if the final guess is correct it outputs that the computer won
                if (answer.equals("yes")) {
                    output.append("Computer wins!\n");
                    //if the final output is incorrect it asks the user to input what they were thinking of and a question to distinguish
                } else {
                    String newObject = JOptionPane.showInputDialog(this, "What were you thinking of?");
                    String newQuestion = JOptionPane.showInputDialog(this, "Please give a question to distinguish between " + newObject + " and " + current.answer);
                    current.question = newQuestion;
                    current.answer = newObject;
                    //adding the new object and question to the game
                    current.yes = new Node(current.question, current.answer);
                    current.no = new Node(current.question, current.answer);
                    output.append("Thanks! I'll remember that for the next game.\n");
                }
                break;
            } else { // if it is not a leaf node it just sets the next question to the yes or no branch depending on the user answer
                output.append(current.question + "\n");
                String answer = JOptionPane.showInputDialog(this, current.question + " (yes/no)").toLowerCase();
                if (answer.equals("yes")) {
                    current = current.yes;
                } else {
                    current = current.no;
                }
            }
        }
    }


//method to save the tree
    private void saveTree(){
        try {
            ObjectOutputStream bOutput = new ObjectOutputStream(new FileOutputStream("binaryTree.dat"));
            bOutput.writeObject(root);
            bOutput.close();
        } catch(IOException ioe) {
            System.out.println("Error saving to file");
        }
    }
//method to load the tree
    private void loadListToFile(){
        try {
            ObjectInputStream bInput = new ObjectInputStream(new FileInputStream("binaryTree.dat"));
            root = (Node) bInput.readObject();
            bInput.close();

        } catch (FileNotFoundException e) {
            root = constructGame();
            System.out.println("Files not found, making new lists");
        } catch(IOException ioe) {
            System.out.println("Error opening file");
        } catch(ClassNotFoundException cnfe){
            System.out.println("Object read is not a LinkedList");
        }

    }

    public static void main(String[] args) {
        Game2 gameGUI = new Game2();
        gameGUI.loadListToFile();
        gameGUI.setVisible(true);
    }
}


