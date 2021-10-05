
public class QuadNode<K, V> {

    private KVPair<K, V> data;
    private QuadNode<K, V> NW;
    private QuadNode<K, V> NE;
    private QuadNode<K, V> SW;
    private QuadNode<K, V> SE;


    public QuadNode(KVPair<K, V> data) {
        this.data = data;

    }

    public KVPair<K, V> getData(){
        return data;
    }

    public QuadNode<K, V> getNW(){
        return NW;
    }


    public QuadNode<K, V> getNE(){
        return NE;
    }

    public QuadNode<K, V> getSW(){
        return SW;
    }

    public QuadNode<K, V> getSE(){
        return SE;
    }
    
    public void setNW(QuadNode<K, V> node) {
        NW = node;
    }
    
    public void setNE(QuadNode<K, V> node) {
        NE = node;
    }
    
    public void setSW(QuadNode<K, V> node) {
        SW = node;
    }
    
    public void setSE(QuadNode<K, V> node) {
        SE = node;
    }
}
