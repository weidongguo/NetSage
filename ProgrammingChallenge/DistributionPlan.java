import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * For distributing files to nodes.
 */
public class DistributionPlan {
  private ArrayList<File> files;
  private ArrayList<Node> nodes;

  public DistributionPlan(ArrayList<File> files, ArrayList<Node> nodes) {
    this.files = files;
    this.nodes = nodes;
  }

  /**
   * Assign node to each files in a way that balances the data distributed to each node.
   */
  public void balanceDistributedData() {
    Node maxNode = Collections.max(nodes);
    Collections.sort(files);
    Collections.sort(nodes);

    // Remove files that can't possibly fit in any nodes.
    List<File> filteredFiles = files.stream().filter(file -> {
      return file.size <= maxNode.capacity;
    }).collect(Collectors.toList());

    int averageFileSize = filteredFiles.stream().mapToInt(file -> file.size).sum() / nodes.size();

    // First fit algo: not optimal, but fast with good approximation. 
    // It tries to make the usedSpace match the averageFileSize as close as possible. 
    // The runtime is O(F+N), where F is the number of files, and N is the number of nodes. 
    int fi = 0, ni = 0;
    File file;
    Node node;
    while(fi < filteredFiles.size() && ni < nodes.size()) {
      file = filteredFiles.get(fi);
      node = nodes.get(ni);
      if(
        file.size <= node.availableSpace &&
        Math.abs(node.usedSpace + file.size - averageFileSize) <= Math.abs(node.usedSpace - averageFileSize)
      ) {
        // If the file fits in the node and the resulting used space is closer to the average file size than before.
          node.consume(file.size);
          file.assignNode(node);
          fi++;
      } else {
        // Current node cannot consume any more files.
        ni++;
      }
    }   
    // Assigns remaining files to any nodes that fit.
    // Going through all the nodes again.
    ni = 0;
    while(fi < filteredFiles.size() && ni < nodes.size()) {
      file = filteredFiles.get(fi);
      node = nodes.get(ni);
      if(file.size <= node.availableSpace) {
        node.consume(file.size);
        file.assignNode(node);
        fi++;
      } else {
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
