import java.io.*;
import java.util.ArrayList;

public class FileParser {
  ArrayList<File> files;
  ArrayList<Node> nodes; 

  public boolean parse(CommandLine cl) {
    BufferedReader inputFileBuffer = cl.inputFileBuffer;
  	BufferedReader inputNodeBuffer = cl.inputNodeBuffer;  
  	String line;
    try {
      while((line = inputFileBuffer.readLine()) != null) {
        System.out.println(line);
      }

      while((line = inputNodeBuffer.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException ioe) {
      
    }

    return true;
  }

}
