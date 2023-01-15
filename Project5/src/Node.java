import java.util.HashMap;
import java.util.Map;

public class Node<T> implements Comparable<Node<T>> {
    private final T name;
    private Integer capacity = Integer.MAX_VALUE;
    private Node<T> parent;
    private Map<Node<T>, Integer> adjacentNodes = new HashMap<>();

    public Node(T name) {
        this.name = name;
    }

    public void addAdjacentNode(Node<T> node, int weight) {
        adjacentNodes.put(node, weight);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.capacity, node.getDistance());
    }

    public T getName() {
        return this.name;
    }

    public Integer getDistance() {
        return this.capacity;
    }

    public Map<Node<T>, Integer> getAdjacentNodes() {
        return this.adjacentNodes;
    }

    public void setDistance(Integer distance) {
        this.capacity = distance;
    }

    public Node<T> getParent() {
        return this.parent;
    }
    public void SetParent(Node<T> parent) {
       this.parent = parent;
    }

    public void setAdjacentNodes(Map<Node<T>, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}
