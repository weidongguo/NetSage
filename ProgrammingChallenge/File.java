import java.util.Comparator;

public class File implements Comparable<File> {
  public String filename;
  public int size;
  public File(String filename, int size) {
    this.filename = filename;
    this.size = size;
  }

  // Methods for Comparable
  public int compareTo(File f) {
    return size - f.size;
  }
}
