// https://adventofcode.com/2020/day/10
package day_10;

import java.io.*;
import java.util.*;
public class adapterArray {
  public int getDistributionOfJoltageDifferences() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day_10/joltage.list"));
    TreeSet<Integer> adapterSet = new TreeSet<Integer>();
    int result = 0;
    String line = "";
    while((line = br.readLine()) != null) {
      adapterSet.add(Integer.parseInt(line));
    }
    br.close();
    adapterSet.add(adapterSet.last() + 3);
    int count1 = 0, count3 = 0;
    int init = 0;
    for(int adapter: adapterSet) {
      if(adapter - init == 1) {
        count1 += 1;
      }
      else if(adapter - init == 3) {
        count3 += 1;
      }
      init = adapter;
    }
    result = count1 * count3;
    return result;
  }
  private long dp(List<Integer> adapterList, int index, Map<Integer, Long> memo) {
    if (index == adapterList.size() - 1) {
      return 1;
    }
    long result = 0;
    if(memo.containsKey(index)) {
      result = memo.get(index);
    }
    else {
      int nextIndex = index + 1;
      while((nextIndex < adapterList.size()) && (adapterList.get(nextIndex) - adapterList.get(index) <= 3)) {
        result += dp(adapterList, nextIndex, memo);
        nextIndex += 1;
      }
      memo.put(index, result);
    }
    return result;
  }
  public long getTotalArrangementWays() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_10/joltage.list"));
    TreeSet<Integer> adapterSet = new TreeSet<Integer>();
    String line = "";
    long result = 0;
    adapterSet.add(0);
    while((line = br.readLine()) != null) {
      adapterSet.add(Integer.parseInt(line));
    }
    br.close();
    List<Integer> adapterList = new ArrayList<Integer>(adapterSet);
    Map<Integer, Long> memo = new HashMap<Integer, Long>();
    result = dp(adapterList, 0, memo);
    return result;
  }
  public static void main(String[] args) throws IOException{
    adapterArray obj = new adapterArray();
    System.out.println("Puzzle 1 solution = " + obj.getDistributionOfJoltageDifferences());
    System.out.println("Puzzle 2 solution = " + obj.getTotalArrangementWays());
  }
}
