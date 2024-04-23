import java.util.Objects;
import java.io.Serializable;

public class LinkedList implements Serializable{
    TNode head;
    TNode tail;

    LinkedList() {
        head = null;
        tail = null;
    }

    // creates a linked list with a head and tail
    //inserts node into list
    public void insertNode(transaction trans) {
        TNode node = new TNode(trans);
        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }
    //removes node from list by name
    public void removeDuplicate(String name) {
        TNode current = head;
        TNode previous = null;
        while (current != null) {
            if (current.getTrans().getName().equals(name)) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                if (current == tail) {
                    tail = previous;
                }
                current = current.getNext();
            } else {
                previous = current;
                current = current.getNext();
            }
        }
    }
    //calculates account balance
    public double calcBalance(){
        TNode current = head;
        double balance = 0;
        while (current != null){
            if (Objects.equals(current.getTrans().getTransType(), "deposit")){
                balance += current.getTrans().getAmount();
            }
            else  {
                balance -= current.getTrans().getAmount();
            }
            current = current.getNext();
        }
        return balance;
    }
    //searches for node based on name
    public TNode findNode(String name){
        TNode current = head;
        while (current != null) {
            if (current.getTrans().getName().equals(name)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    //finds maximum transaction
    public double max(TNode node){
        double maxTransaction = 0;
        TNode current = node;
        if (current == null){
            return maxTransaction;
        }
        while (current != null) {
            if (current.getTrans().getAmount() > maxTransaction) {
                maxTransaction = current.getTrans().getAmount();
            }
            current = current.getNext();
        }
        return maxTransaction;
    }
    //finds amount of transactions
    public int length() {
        int count = 0;
        TNode current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
    public double maxBalance(TNode node){
        double maxBalance = 0;
        TNode current = node;
        if (current == null){
            return maxBalance;
        }
        while (current != null) {
            if (current.getTrans().getBalance() > maxBalance) {
                maxBalance = current.getTrans().getBalance();
            }
            current = current.getNext();
        }
        return maxBalance;
    }
}



