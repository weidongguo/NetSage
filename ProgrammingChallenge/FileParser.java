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

  public boolean parse(CommandLine cl) {
    BufferedReader inputFileBuffer = cl.inputFileBuffer;
  	BufferedReader inputNodeBuffer = cl.inputNodeBuffer;  
  	String line;
    String tokens[];
    try {
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
