import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

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

  public static void generateInput(BufferedWriter fileWriter, BufferedWriter nodeWriter) throws IOException {
    int nNodes = randInt(MIN_N_NODES, MAX_N_NODES);
    int nFiles = randInt(MIN_N_FILES, MAX_N_FILES);
    ArrayList<Integer> nodes = new ArrayList();
    ArrayList<Integer> files = new ArrayList();
    for(int i = 0 ; i < nNodes; i++) {
      nodes.add(randInt(MIN_FILE_SIZE, MAX_FILE_SIZE));
    }
    Collections.sort(nodes);
    
    int fileSizeSum = 0, item;
    for(int i = 0 ; i < nFiles; i++) {
      item = randInt(MIN_NODE_SIZE, MAX_NODE_SIZE);
      fileSizeSum += item; 
      files.add(item);
    }
    Collections.sort(files);
    int fileSizeSum2 = files.stream().mapToInt(Integer::intValue).sum();
      

    System.out.println("== Nodes ==");
    for(int i = 0 ; i < nNodes; i++) {
      System.out.println(nodes.get(i));
      nodeWriter.write("node" + i + " " + nodes.get(i) + "\n");
      nodeWriter.flush();

    }
    System.out.println("== Files ==");
    for(int i = 0 ; i < nFiles; i++) {
      System.out.println(files.get(i));
      fileWriter.write("file" + i + " " + files.get(i) + "\n");
      fileWriter.flush();
    }
    System.out.println("-- Files Stat --");
    System.out.println("File Size Sum = " + fileSizeSum);
    System.out.println("# of Files = " + nFiles);
    System.out.println("File Size Sum / # of nodes = " + fileSizeSum / nNodes);
  }

  public static void main(String args[]) {
    BufferedWriter fileWriter, nodeWriter;
    try {
      fileWriter = new BufferedWriter(new FileWriter("files1"));
      nodeWriter = new BufferedWriter(new FileWriter("nodes1"));
      generateInput(fileWriter, nodeWriter);
    } catch (IOException ioe) {
      System.out.println("ERROR: " + ioe.toString());  
    }
  }

}
