import java.util.Comparator;

public class File implements Comparable<File> {
  public final static String NOT_ASSIGNED = "NULL";

  public String filename;
  public int size;
  public String nodeAssigned;

  public File(String filename, int size) {
    this.filename = filename;
    this.size = size;
    this.nodeAssigned = NOT_ASSIGNED; 
  }

  public void assignNode(Node node) {
    nodeAssigned = node.nodeName;
  }

  @Override 
  public int compareTo(File f) {
    return size - f.size;
  }
}
