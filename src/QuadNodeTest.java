import student.TestCase;

public class QuadNodeTest extends TestCase {

    public void testConstructor() {
        KVPair<String, Integer> pair = new KVPair<>("a",2);
        QuadNode<String, Integer> node = new QuadNode<>(pair);
    }
    
    
}
