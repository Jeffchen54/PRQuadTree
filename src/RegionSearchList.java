
public class RegionSearchList<K extends Comparable<K>, V extends Comparable<V>>
    extends PointNodeList<K, V> {
    private int visited;

    public RegionSearchList() {
        super();
    }


    public void incrementVisited() {
        visited++;
    }


    public int numVisited() {
        return visited;
    }

}
