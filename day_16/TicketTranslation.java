// https://adventofcode.com/2020/day/16
package day_16;

import java.io.*;
import java.util.*;
public class TicketTranslation {
  public int getScanningErrorRate() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day_16/ticket.rules"));
    String line = "";
    int result = 0;
    List<String> rules = new ArrayList<String>();
    while((line = br.readLine()) != null) {
      rules.add(line.split(": ")[1]);
    }
    br.close();
    br = new BufferedReader(new FileReader("day_16/ticket.list"));
    br.readLine();br.readLine();
    while((line = br.readLine()) != null) {
      int[] fields = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
      for(int field: fields) {
        boolean flag = false;
        for(int i = 0; i < rules.size(); i++) {
          String[] bounds = rules.get(i).split(" or ");
          int[] lowerBounds = Arrays.stream(bounds[0].split("-")).mapToInt(Integer::parseInt).toArray();
          int[] upperBounds = Arrays.stream(bounds[1].split("-")).mapToInt(Integer::parseInt).toArray();
          if((lowerBounds[0] <= field && lowerBounds[1] >= field) || (upperBounds[0] <= field && upperBounds[1] >= field)) {
            flag = true;
            break;
          }
        }
        if(!flag) {
          result += field;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) throws IOException{
    TicketTranslation obj = new TicketTranslation();
    System.out.println("Puzzle 1 solution = " + obj.getScanningErrorRate());
  }
}
