import java.util.Random;

public class InputGenerator {
  private static Random rand = new Random();
  private static int MAX_N_NODES = 10;
  private static int MIN_N_NODES = 2;
  private static int MAX_N_FILES = 10;
  private static int MIN_N_FILES = 2;
  private static int MAX_FILE_SIZE = 50;
  private static int MIN_FILE_SIZE = 1;
  private static int MAX_NODE_SIZE = 50;
  private static int MIN_NODE_SIZE = 1;

  public static int randInt(int min, int max) {
    return rand.nextInt(max - min + 1) + min;
  }

  public static void main(String args[]) {
    int nNodes = randInt(MIN_N_NODES, MAX_N_NODES);
    int nFiles = randInt(MIN_N_FILES, MAX_N_FILES);
    System.out.println("== Nodes ==");
    for(int i = 0 ; i < nNodes; i++) {
      System.out.println(randInt(MIN_FILE_SIZE, MAX_FILE_SIZE));
    }
    System.out.println("== Files ==");
    for(int i = 0 ; i < nFiles; i++) {
      System.out.println(randInt(MIN_NODE_SIZE, MAX_NODE_SIZE));
    }

  }
}
