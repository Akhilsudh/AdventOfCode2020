package day1;

/*--- Day 1: Report Repair ---
  --- Part One ---
  The Elves in accounting just need you to fix your expense report (your puzzle input); apparently, something isn't quite adding up.
  Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.
  For example, suppose your expense report contained the following:
  1721
  979
  366
  299
  675
  1456
  In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.
*/
import java.util.*;
import java.io.*;

public class reportRepair1 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day1/reportRepair.list"));
    Set<Integer> dates = new HashSet<Integer>();
    String line;
    while((line = br.readLine()) != null) {
      dates.add(Integer.parseInt(line));
    }
    br.close();
    for(int date: dates) {
      if((2020 - date !=  date) && (dates.contains(2020 - date))) {
        System.out.println(date + " * " + (2020 - date) + " = ");
        System.out.println(date * (2020 - date));
        break;
      }
    }
  }
}