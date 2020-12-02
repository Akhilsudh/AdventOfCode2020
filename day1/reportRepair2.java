/*--- Day 1: Report Repair ---
  --- Part Two ---
  The Elves in accounting are thankful for your help; one of them even offers you a starfish coin they had left over from a past vacation. 
  They offer you a second one if you can find three numbers in your expense report that meet the same criteria.
  Using the above example again, the three entries that sum to 2020 are 979, 366, and 675. Multiplying them together produces the answer, 241861950.
  In your expense report, what is the product of the three entries that sum to 2020?
*/
package day1;
import java.io.*;
import java.util.*;
public class reportRepair2 {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day1/reportRepair.list"));
    Set<Integer> dates = new HashSet<Integer>();
    String line;
    boolean flag = false;
    while((line = br.readLine()) != null) {
      dates.add(Integer.parseInt(line));
    }
    for(int date1: dates) {
      if(flag) {
        break;
      }
      for(int date2: dates) {
        if(date2 == date1) {
          continue;
        }
        if((2020 - date1 - date2 != date1) && (2020 - date1 - date2 != date2) && (dates.contains(2020 - date1 - date2))) {
          System.out.println(date1 + " * " + date2 + " * " + (2020 - date1 - date2) + " = ");
          System.out.println(date1 * date2 * (2020 - date1 - date2));
          flag = true;
          break;
        }
      }
    }
  }
}
