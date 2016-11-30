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

    int totalFileSize = filteredFiles.stream().mapToInt(
      (file) -> file.size
    ).sum();
    int averageFileSize = totalFileSize / nodes.size();
    
    // First fit algo: not optimal, but fast with good approximation. It also tries to make the usedSpace match the averageFileSize as close as possible.
    int fi = 0, ni = 0;
    File file;
    Node node;
    while(fi < filteredFiles.size() && ni < nodes.size()) {
      file = filteredFiles.get(fi);
      node = nodes.get(ni);
      if(file.size <= node.availableSpace &&
        Math.abs(node.usedSpace + file.size - averageFileSize) <= Math.abs(node.usedSpace - averageFileSize)
      ) {
          node.consume(file.size);
          file.assignNode(node);
          fi++;
      } else {
        // Current node cannot consume more files.
        ni++;
      }
    }   
    // Second pass to assigns remaining files to any nodes that fit.
    fi = 0; ni = 0;
    while(fi < filteredFiles.size() && ni < nodes.size()) {
      file = filteredFiles.get(fi);
      node = nodes.get(ni);
      if(file.nodeAssigned.equals(File.NOT_ASSIGNED)) {
        if(file.size <= node.availableSpace) {
          node.consume(file.size);
          file.assignNode(node);
        } else {
          ni++;
        }
      } else {
        fi++;
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
