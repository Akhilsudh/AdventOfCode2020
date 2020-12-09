package day9;

import java.io.*;
import java.util.*;
public class encodingError {
  public long getBreakingNumber() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day9/xmas.cipher"));
    long result = 0;
    String line = "";
    // int index = 0;
    List<Long> numbers = new ArrayList<Long>();
    while((line = br.readLine()) != null) {
      numbers.add(Long.parseLong(line));
      // index += 1;
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
    // System.out.println(index);
    br.close();

    return result;
  }
  
  public static void main(String[] args) throws IOException{
    encodingError obj = new encodingError();
    long breakingNumber = obj.getBreakingNumber();
    System.out.println("Puzzle 1 solution = " + breakingNumber);
    // System.out.println("Puzzle 2 solution = " + obj.getWeakness(breakingNumber));
  }
}
