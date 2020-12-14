package day_14;

import java.io.*;
import java.util.*;
public class dockingData {
  private BitSet convert(long value) {
    return BitSet.valueOf(new long[] {value});
  }
  private long convert(BitSet bits) {
    return bits.toLongArray()[0];
  }
  public long getSum() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day_14/operations.list"));
    long result = 0;
    String mask = "";
    Map<Integer, Integer> maskMap = new HashMap<Integer, Integer>();
    Map<Long, Long> memoryMap = new HashMap<Long, Long>();
    String line = "";
    while((line = br.readLine()) != null) {
      String[] lineArr = line.split(" ");
      if(lineArr[0].equals("mask")) {
        mask = lineArr[2];
        maskMap = new HashMap<Integer, Integer>();
        for(int i = mask.length() - 1; i >= 0; i--) {
          if(mask.charAt(i) == '0') {
            maskMap.put(35 - i, 0);
          }
          else if(mask.charAt(i) == '1') {
            maskMap.put(35 - i, 1);
          }
        }
      }
      else {
        long address = Long.parseLong(lineArr[0].substring(4, lineArr[0].length() - 1));
        long value = Long.parseLong(lineArr[2]);
        BitSet bits = convert(value);
        for(int index: maskMap.keySet()) {
          if(maskMap.get(index) == 0) {
            bits.clear(index);
          }
          else if(maskMap.get(index) == 1) {
            bits.set(index);
          }
        }
        value = convert(bits);
        memoryMap.put(address, value);
      }
    }
    br.close();
    for(long address: memoryMap.keySet()) {
      result += memoryMap.get(address);
    }
    return result;
  }
  public static void main(String[] args) throws IOException{
    dockingData obj = new dockingData();
    System.out.println("Puzzle 1 solution = " + obj.getSum());
    System.out.println("Puzzle 2 solution = " + obj.getSum());
  }
}
