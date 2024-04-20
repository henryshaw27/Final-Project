public class LinkedList {
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
    public TNode findNode(Date date){
        TNode current = head;
        while (current != null) {
            if (current.getTrans().getDate().equals(date)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
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
    public int length() {
        int count = 0;
        TNode current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
//    public double accBalance(){
//
//    }
}

