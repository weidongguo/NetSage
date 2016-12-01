import java.io.*;

/**
 * For parsing command line arguments.
 */
public class CommandLine {
  // -f files.txt
  final static String OPTION_INPUT_FILE = "-f";	
  // -n nodes.txt
  final static String OPTION_INPUT_NODE = "-n";
  // -o result.txt
  final static String OPTION_OUTPUT = "-o";
  // -h 
  final static String OPTION_HELP = "-h";
  
  final static String USAGE = "Usage:\n-f filename: Input file for files. Required.\n-n filename: Input files for nodes. Required.\n-o filename: Output file. If this option is not given, assume standard output\n-h: Print usage information to standard error and stop";

  final static String STATE_NORMAL = "";
  final static String STATE_FILE = OPTION_INPUT_FILE;
  final static String STATE_NODE = OPTION_INPUT_NODE;
  final static String STATE_OUTPUT = OPTION_OUTPUT;
  
  BufferedReader inputFileBuffer;
  BufferedReader inputNodeBuffer;
  BufferedWriter outputBuffer;

  private static void printError(String errorMessage) {
    System.err.println("Error: " + errorMessage + "\n" + USAGE);
  }
  
  /** 
   * Determine if the arg is an invalid flag or the -h flag.
   */
  private static boolean inspect(String arg) { 
    switch(arg) {
      case OPTION_INPUT_FILE:
        break; 
      case OPTION_INPUT_NODE: 
        break;
      case OPTION_OUTPUT:
        break;
      case OPTION_HELP:
        System.out.println(USAGE);
        return false;
      default:
        printError("Invalid option is given: " + arg); 
        return false;
    }
    return true;
  }

  /** 
   * A state machine (Moore model) to parse command line arguments.
   */
  public boolean parse(String args[]) {
    String flag = STATE_NORMAL;
    String inputFile = "", inputNode = "", outputFile = "";
    for(int i = 0 ; i < args.length; i++) {
      // Take action for current state and set next state 
      switch(flag) {
        case STATE_NORMAL: 
          if(!inspect(args[i])) 
            return false; 
          else
            flag = args[i];
          break;
        case STATE_FILE:
          inputFile = args[i]; 
          flag = STATE_NORMAL;
          break;
        case STATE_NODE: 
          inputNode = args[i];
          flag = STATE_NORMAL;
          break;
        case STATE_OUTPUT: 
          outputFile = args[i];
          flag = STATE_NORMAL; 
          break;
      }
    }
    if(flag != STATE_NORMAL) {
      printError("The argument for " + flag + " is not provided");
      return false;
    } else if(inputFile.length() == 0 || inputNode.length() == 0) {
      printError("Must provide the -f filename and -n filename parameters.");
      return false;
    }

    try {    
      inputFileBuffer = new BufferedReader(new FileReader(inputFile));  
      inputNodeBuffer = new BufferedReader(new FileReader(inputNode));  
      outputBuffer = new BufferedWriter(
        (outputFile.length() > 0 ? new FileWriter(outputFile) : new FileWriter(FileDescriptor.out))
      );
    } catch(IOException ioe) {
      printError(ioe.toString());
      return false;
    }
    return true;
  }
  
  /**
   * Close opened input and output buffers.
   */
  public void cleanUp() {
    try { 
      inputFileBuffer.close();
      inputNodeBuffer.close();
      outputBuffer.close();
    } catch(Exception e) {
      printError(e.toString()); 
    }
  }
}
