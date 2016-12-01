import java.io.*;
import java.util.ArrayList;

/**
 * For parsing the "files" file and "nodes" file.
 */
public class FileParser {
  final static String REGEX_LINE = "[^\\s]+[\\s]+[\\d]+";
  final static String REGEX_COMMENT = "#.*";
  final static String USAGE = "Usage:\n1) Any blank line or line beginning with comment character ('#') is ignored.\n2) Any other line should have two fields separated by white space.\nThe first field is string representing either the file or node name.\nThe second field is a positive integer representing the size in bytes of either the file or the available space for the given node.";
  ArrayList<File> files;
  ArrayList<Node> nodes; 

  public FileParser() {
    files = new ArrayList<File>();
    nodes = new ArrayList<Node>();
  }
  
  /**
   * Parse the "files" and "nodes" files and store their parsed results in 
   * the class data members (files and nodes);
   * @param cl
   *   It contains the input buffers for the "files" and "nodes" files. 
   * @return whether or not all the files have valid formatting.
   */
  public boolean parse(CommandLine cl) {
    BufferedReader inputFileBuffer = cl.inputFileBuffer;
  	BufferedReader inputNodeBuffer = cl.inputNodeBuffer;  
  	String line;
    String tokens[];
    try {
      // Parsing for the "files" files.
      while((line = inputFileBuffer.readLine()) != null) {
        if(line.matches(REGEX_COMMENT) || line.length() == 0)
          // Skip comments and blank line.
          continue;
        if(line.matches(REGEX_LINE)) {
          tokens = line.split("[\\s]+");
          files.add(new File(tokens[0], Integer.parseInt(tokens[1])));
        } else {
          System.err.println("Error: invalid line \"" + line + "\" found at the given \"files\" file.\n" + USAGE);
          return false;
        }
      }
      // Parsing for the "nodes" file.
      while((line = inputNodeBuffer.readLine()) != null) {
        if(line.matches(REGEX_COMMENT) || line.length() == 0)
          // Skip comments and blank line.
          continue;
        if(line.matches(REGEX_LINE)) {
          tokens = line.split("[\\s]+");
          nodes.add(new Node(tokens[0], Integer.parseInt(tokens[1])));
        } else {
          System.err.println("Error: invalid line \"" + line + "\" found at the given \"nodes\" file.\n" + USAGE);
          return false;
        }
      }
    } catch (IOException ioe) {
      System.err.println("Error: " + ioe);
    }

    return true;
  }
}
