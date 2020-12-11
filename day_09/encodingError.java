// https://adventofcode.com/2020/day/9
package day_09;

import java.io.*;
import java.util.*;
public class encodingError {
  public long getBreakingNumber() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day9/xmas.cipher"));
    long result = 0;
    String line = "";
    List<Long> numbers = new ArrayList<Long>();
    while((line = br.readLine()) != null) {
      numbers.add(Long.parseLong(line));
    }
    for(int i = 25; i < numbers.size(); i++) {
      long sum = numbers.get(i);
      Set<Long> preamble = new HashSet<Long>();
      for(int j = i - 25; j < i; j++) {
        preamble.add(numbers.get(j));
      }
      boolean flag = false;
      for(long item: preamble) {
        if((sum - item != item) && preamble.contains(sum - item)) {
          flag = true;
          break;
        }
      }
      if(!flag){
        result = sum;
        break;
      }
    }
    br.close();

    return result;
  }
  public long getWeakness(long breakingNumber) throws IOException{
    long result = 0;
    BufferedReader br = new BufferedReader(new FileReader("day9/xmas.cipher"));
    String line = "";
    List<Long> numbers = new ArrayList<Long>();
    List<Long> range = new ArrayList<Long>();
    while((line = br.readLine()) != null) {
      numbers.add(Long.parseLong(line));
    }
    br.close();
    long sum = numbers.get(0);
    int start = 0;
    int end = -1;
    boolean flag = false;
    for(int i = 0; i < numbers.size(); i++) {
      sum = numbers.get(i);
      for(int j = i + 1; j <= numbers.size(); j++) {
        if(sum == breakingNumber) {
          start = i;
          end = j;
          flag = true;
          range = numbers.subList(start, end);
          break;
        }
        if(sum > breakingNumber || j == numbers.size()) {
          break;
        }
        sum = sum + numbers.get(j);
      }
      if(flag) {
        break;
      }
    }
    result = Collections.min(range) + Collections.max(range);
    return result;
  }
  public static void main(String[] args) throws IOException{
    encodingError obj = new encodingError();
    long breakingNumber = obj.getBreakingNumber();
    System.out.println("Puzzle 1 solution = " + breakingNumber);
    System.out.println("Puzzle 2 solution = " + obj.getWeakness(breakingNumber));
  }
}
