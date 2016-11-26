import java.util.Comparator;

public class File implements Comparable<File> {
  final static String NOT_ASSIGNED = "NULL";

  public String filename;
  public int size;
  public String nodeAssigned;

  public File(String filename, int size) {
    this.filename = filename;
    this.size = size;
    this.nodeAssigned = NOT_ASSIGNED; 
  }

  // Methods for Comparable
  public int compareTo(File f) {
    return size - f.size;
  }
}
