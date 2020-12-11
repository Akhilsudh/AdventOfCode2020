// https://adventofcode.com/2020/day/1
package day_01;

import java.util.*;
import java.io.*;

public class reportRepair {
  public int puzzle1() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day1/reportRepair.list"));
    Set<Integer> dates = new HashSet<Integer>();
    String line;
    while((line = br.readLine()) != null) {
      dates.add(Integer.parseInt(line));
    }
    br.close();
    for(int date: dates) {
      if((2020 - date !=  date) && (dates.contains(2020 - date))) {
        return(date * (2020 - date));
      }
    }
    return 0;
  }
  public int puzzle2() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day1/reportRepair.list"));
    Set<Integer> dates = new HashSet<Integer>();
    String line;
    boolean flag = false;
    while((line = br.readLine()) != null) {
      dates.add(Integer.parseInt(line));
    }
    br.close();
    for(int date1: dates) {
      if(flag) {
        break;
      }
      for(int date2: dates) {
        if(date2 == date1) {
          continue;
        }
        if((2020 - date1 - date2 != date1) && (2020 - date1 - date2 != date2) && (dates.contains(2020 - date1 - date2))) {
          return (date1 * date2 * (2020 - date1 - date2));
        }
      }
    }
    return 0;
  }
  public static void main(String[] args) throws IOException{
    reportRepair obj = new reportRepair();
    System.out.println("Puzzle 1 solution = " + obj.puzzle1());
    System.out.println("Puzzle 2 solution = " + obj.puzzle2());
  }
}