public class CommandLine {
  // -f files.txt
  final static String OPTION_INPUT_FILE = "-f";	
  // -n nodes.txt
  final static String OPTION_INPUT_NODE = "-n";
  // -o result.txt
  final static String OPTION_OUTPUT = "-o";
  // -h 
  final static String OPTION_HELP = "-h";



  public boolean parse(String args[]) {
    String inputFile = "", inputNode = "", outputResult = "";
    boolean helpFlag = false;
    for(int i = 0 ; i < args.length; i++) {
      System.out.println(args[i]);
      switch(args[i]) {
        case OPTION_INPUT_FILE:
          inputFile = args[++i];
          break;
        case OPTION_INPUT_NODE: 
          inputNode = args[++i];
          break;
        case OPTION_OUTPUT:
          outputResult = args[++i];  
        case OPTION_HELP:
          helpFlag = true;
          break;
      }
    }
    System.out.println(inputFile + " " + inputNode + " " + outputResult + " " + helpFlag); 
    return true;
  }
}
