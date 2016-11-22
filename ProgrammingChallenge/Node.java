import java.util.Comparator;

public class Node implements Comparable <Node> {
  public String nodeName;
  public int availableSpace;
  public Node(String nodeName, int availableSpace) {
    this.nodeName = nodeName;
    this.availableSpace = availableSpace;
  }


  // Methods for Comparable.
  public int compareTo(Node n) {
    return availableSpace - n.availableSpace;
  }
}
