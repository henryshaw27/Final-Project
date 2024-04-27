import java.io.Serializable;
//node to store the question and answer, and the yes and no branch
public class Node implements Serializable {
    String question;
    String answer;
    Node yes;
    Node no;

    Node(String question, String answer){
        this.question = question;
        this.answer = answer;
    }
}