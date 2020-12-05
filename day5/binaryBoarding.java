package day5;
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
  public int maxSeatID() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day5/boarding.pass"));
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
  public int calculateYourSeatID() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day5/boarding.pass"));
    int seatID = 0;
    String line = "";
    Set<Integer> passes = new HashSet<Integer>();
    while((line = br.readLine()) != null) {
      int pass = calculateSeatID(line);
      passes.add(pass);
    }
    System.out.println(passes);
    for(int i = 48; i < 875; i++) {
      if(!passes.contains(i)) {
        return i;
      }
    }
    return seatID;
  }
  public static void main(String[] args) throws IOException {
    binaryBoarding obj = new binaryBoarding();
    // System.out.println(obj.maxSeatID());
    System.out.println(obj.calculateYourSeatID());
  }
}
