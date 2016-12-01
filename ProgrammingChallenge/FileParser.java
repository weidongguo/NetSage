import java.io.*;
import java.util.ArrayList;

public class FileParser {
  final static String REGEX_LINE = "[^\\s]+[\\s]+[\\d]+";
  final static String REGEX_COMMENT = "#.*";
  ArrayList<File> files;
  ArrayList<Node> nodes; 

  public FileParser() {
    files = new ArrayList<File>();
    nodes = new ArrayList<Node>();
  }
  
  /**
   * Parse the "files" and "nodes" files and store the parsed components for later use.
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
          System.err.println("Error: invalid line \"" + line + "\" found at the \"files\" file.");
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
          System.err.println("Error: invalid line \"" + line + "\" found at the \"nodes\" file.");
          return false;
        }
      }
    } catch (IOException ioe) {
      System.err.println("Error: " + ioe);
    }

    return true;
  }
}
