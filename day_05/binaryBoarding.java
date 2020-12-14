// https://adventofcode.com/2020/day/5
package day_05;

import java.io.*;
import java.util.*;
public class binaryBoarding {
  private int calculateSeatID(String pass) {
    int low = 0;
    int high = 127;
    int row = 0;
    for(int i = 0; i < 7; i++) {
      if(pass.charAt(i) == 'F') {
        high = (low + high) / 2;
        row = high;
      }
      else {
        low = (low + high) / 2 + 1;
        row = low;
      }
    }
    low = 0;
    high = 7;
    int col = 0;
    for(int i = 7; i < 10; i++) { 
      if(pass.charAt(i) == 'L') {
        high = (low + high) / 2;
        col = high;
      }
      else {
        low = (low + high) / 2 + 1;
        col = low;
      }
    }
    return row * 8 + col;
  }
  // Puzzle 1
  public int maxSeatID() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_05/boarding.pass"));
    int seatID = 0;
    String line = "";
    List<String> passes = new ArrayList<String>();
    while((line = br.readLine()) != null) {
      passes.add(line);
    }
    br.close();
    for(String pass: passes) {
      seatID = Math.max(calculateSeatID(pass), seatID);
    }
    return seatID;
  }
  // Puzzle 2
  public int calculateYourSeatID() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day_05/boarding.pass"));
    int seatID = 0;
    String line = "";
    int min = Integer.MAX_VALUE;
    int max = 0;
    Set<Integer> passes = new HashSet<Integer>();
    while((line = br.readLine()) != null) {
      int pass = calculateSeatID(line);
      min = Math.min(min, pass);
      max = Math.max(max, pass);
      passes.add(pass);
    }
    br.close();
    for(int i = min; i <= max; i++) {
      if(!passes.contains(i)) {
        return i;
      }
    }
    return seatID;
  }
  public static void main(String[] args) throws IOException {
    binaryBoarding obj = new binaryBoarding();
    System.out.println("Puzzle 1 solution = " + obj.maxSeatID());
    System.out.println("Puzzle 2 solution = " + obj.calculateYourSeatID());
  }
}
