public class Solution {
  public static void main(String args[]) {
    CommandLine cl = new CommandLine();
    if(!cl.parse(args))
			return;    

    FileParser fp = new FileParser();
    if(!fp.parse(cl))
      return;
    for(File file: fp.files) {
      System.out.println(file.filename + " " + file.size);
    }

    for(Node node: fp.nodes) {
      System.out.println(node.nodeName + " " + node.availableSpace);
    }

    
  }
}
