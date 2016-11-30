import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;
import java.util.stream.Collectors;

public class DistributionPlan {
  private ArrayList<File> files;
  private ArrayList<Node> nodes;

  public DistributionPlan(ArrayList<File> files, ArrayList<Node> nodes) {
    this.files = files;
    this.nodes = nodes;
  }

  public void balanceDistributedData() {
    Node maxNode = Collections.max(nodes);
    Collections.sort(files);
    Collections.sort(nodes);

    // Remove files that can't possibly fit in any nodes.
    List<File> filteredFiles = files.stream().filter(file -> {
      return file.size <= maxNode.capacity;
    }).collect(Collectors.toList());

    int totalFileSize = filteredFiles.stream().mapToInt(
      (file) -> file.size
    ).sum();
    int averageFileSizePerNode = totalFileSize / nodes.size();
    
    // First fit algo: not optimal, but fast with good approximation.
    int fi = 0, ni = 0; 
    File file; 
    Node node;
    while(fi < filteredFiles.size() && ni < nodes.size()) {
      file = filteredFiles.get(fi);
      node = nodes.get(ni);
      if(file.size <= node.availableSpace &&
        Math.abs(node.usedSpace + file.size - averageFileSizePerNode) <= Math.abs(node.usedSpace - averageFileSizePerNode)
      ) {
          node.consume(file.size);
          file.nodeAssigned = node.nodeName;
          fi++;
      } else {
        // Current node cannot consume more files.
        ni++;
      }
    }   
  }

  /**
   *  Output the node assignment for each file to an output buffer.
   *  @param buffer
   *    The output buffer.
   *  @return None.
   */
  public void output(BufferedWriter buffer) {
    try {
      for(File file: files) {
        buffer.write(file.filename + " " + file.nodeAssigned + "\n");
      }
      buffer.flush();
    } catch (IOException ioe) {
      System.err.println("Error: " + ioe);
    }

  }

}
