import java.io.Serializable;

public class TNode implements Serializable {
    private transaction trans;
    private TNode tNode;
    public TNode(transaction trans){
        this.trans = trans;
        this.tNode = null;
    }
    public TNode getTransNode() {
        return tNode;
    }
    public void setTransNode(TNode tNode) {
        this.tNode = tNode;
    }
    public transaction getTrans() {
        return trans;
    }

    public void setTrans(transaction trans) {
        this.trans = trans;
    }
    public void setNext(TNode tNode){
        this.tNode = tNode;
    }
    public TNode getNext(){
        return tNode;
    }
}
