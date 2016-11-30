import java.util.*;

public class Statistics {
  List<Double> list;
  double standardDeviation;
  double mean;
  
  public Statistics(List<Double> list) {
    this.list = list;
    mean = list.stream().mapToDouble(x->x.doubleValue()).average().getAsDouble();
    standardDeviation = stdDev(list, mean);
  }

  public double stdDev(List<Double> list, double mean) {
    double sumDiff = 0;
    for(Double d: list){
      sumDiff += Math.pow((d.doubleValue() - mean), 2);
    }
    double variance = sumDiff / (list.size() - 1);
    return Math.sqrt(variance);
  }

  public String toString(){
    return "StdDev: " + standardDeviation + "\n" +
    "Mean: " + mean + "\n";
  }
}
