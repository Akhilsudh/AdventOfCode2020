// https://adventofcode.com/2020/day/6
package day_06;

import java.io.*;
import java.util.*;
public class customCustoms {
  public int returnCount() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day6/group.list"));
    int result = 0;
    String line = "";
    Set<Character> groupLetters = new HashSet<Character>();
    while((line = br.readLine()) != null) {
      if(line.length() != 0) {
        for(int i = 0; i < line.length(); i++) {
          groupLetters.add(line.charAt(i));
        }
      }
      else {
        result = result + groupLetters.size();
        groupLetters = new HashSet<Character>();
      }
    }
    br.close();
    return result;
  }
  public int returnYesCount() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day6/group.list"));
    int result = 0;
    int groupCount = 0;
    String line = "";
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    while((line = br.readLine()) != null) {
      if(line.length() != 0) {
        groupCount += 1;
        for(int i = 0; i < line.length(); i++) {
          if(!map.containsKey(line.charAt(i))) {
            map.put(line.charAt(i), 1);
          }
          else {
            map.put(line.charAt(i), map.get(line.charAt(i)) + 1);
          }
        }
      }
      else {
        for(char character: map.keySet()) {
          if(map.get(character) == groupCount) {
            result += 1;
          }
        }
        groupCount = 0;
        map = new HashMap<Character, Integer>();
      }
    }
    br.close();
    return result;
  }
  public static void main(String[] args) throws IOException {
    customCustoms obj = new customCustoms();
    System.out.println("Puzzle 1 solution = " + obj.returnCount());
    System.out.println("Puzzle 2 solution = " + obj.returnYesCount());
  }
}
