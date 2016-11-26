public class Solution {
  public static void main(String args[]) {
    CommandLine cl = new CommandLine();
    if(!cl.parse(args))
			return;    

    FileParser fp = new FileParser();
    if(!fp.parse(cl))
      return;

    DistributionPlan dp = new DistributionPlan(fp.files, fp.nodes);
    dp.balanceDistributedData();
    dp.output(cl.outputBuffer);
    cl.cleanUp();
  }
}
