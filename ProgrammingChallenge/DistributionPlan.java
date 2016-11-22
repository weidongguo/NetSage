import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    files.removeIf(file -> file.size > maxNode.availableSpace);





    for(File file: files) {
      System.out.println(file.filename + " " + file.size);
    }   

    for(Node node: nodes) {
      System.out.println(node.nodeName + " " + node.availableSpace);
    }  
  }
}
