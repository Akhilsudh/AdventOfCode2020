package day10;

import java.io.*;
import java.util.*;
public class adapterArray {
  public int puzzle1() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day10/joltage.list"));
    Set<Integer> adapterSet = new TreeSet<Integer>();
    int result = 0;
    String line = "";
    while((line = br.readLine()) != null) {
      adapterSet.add(Integer.parseInt(line));
    }
    System.out.println(adapterSet);
    int count1 = 0, count3 = 0;
    int init = 0;
    for(int adapter: adapterSet) {
      System.out.println();
      System.out.println(adapter);
      if(adapter - init == 1) {
        count1 += 1;
      }
      else if(adapter - init == 3) {
        count3 += 1;
      }
      System.out.println(count1 + " " + count3);
      init = adapter;
    }
    result = count1 * (count3 + 1);
    return result;
  }
  public static void main(String[] args) throws IOException{
    adapterArray obj = new adapterArray();
    System.out.println("Puzzle 1 solution = " + obj.puzzle1());
  }
}
