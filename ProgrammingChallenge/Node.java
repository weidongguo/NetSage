import java.util.Comparator;

public class Node implements Comparable <Node> {
  public String nodeName;
  public int availableSpace;
  public int capacity;
  public int usedSpace;

  public Node(String nodeName, int availableSpace) {
    this.nodeName = nodeName;
    this.capacity = availableSpace;
    this.availableSpace = availableSpace;
    this.usedSpace = 0;
  }

  /**
   * Consumes the available space for the node.
   * @param space
   *   The space to be consumed.
   * @return None.
   */
  public void consume(int space) {
    availableSpace -= space;
    usedSpace += space;
  }

  @Override
  public int compareTo(Node n) {
    return availableSpace - n.availableSpace;
  }
}
