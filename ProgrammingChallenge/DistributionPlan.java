import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

public class DistributionPlan {
  private ArrayList<File> files;
  private ArrayList<Node> nodes;

  public DistributionPlan(ArrayList<File> files, ArrayList<Node> nodes) {
    this.files = files;
    this.nodes = nodes;
  }

  public void balanceDistributedData() {
    Node maxNode = Collections.max(nodes);

    //Destructive -> Can't get back the original
    //Todo: Make a deep copy of the original
    
    // Remove files that can't possibly fit in any nodes
    files.removeIf(file -> file.size > maxNode.availableSpace);



    for(File file: files) {
      System.out.println(file.filename + " " + file.size);
    }   

    for(Node node: nodes) {
      System.out.println(node.nodeName + " " + node.availableSpace);
    }  
  }

  /**
   *  Output the node assignment for each file to a output buffer.
   *  @param buffer
   *    The output buffer.
   *  @return None.
   */
  public void output(BufferedWriter buffer) {
    try {
      for(File file: files) {
        buffer.write(file.filename + " " + file.nodeAssigned + "\n");
        //System.out.println(file.filename + " " + file.nodeAssigned);
      }
      buffer.flush();
    } catch (IOException ioe) {
      System.err.println("Error: " + ioe);
    }

  }

}
