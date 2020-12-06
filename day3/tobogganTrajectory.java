// https://adventofcode.com/2020/day/3
package day3;

import java.util.*;
import java.io.*;
public class tobogganTrajectory {
  public long puzzle(int x, int y, String fileName) throws IOException {
    long result = 0;
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    String line;
    List<String> map =  new ArrayList<String>();
    while((line = br.readLine()) != null) {
      map.add(line);
    }
    br.close();
    int i = 0, j = 0;
    int len = map.get(0).length();
    while(i < map.size()){
      if(map.get(i).charAt(j) == '#') {
        result += 1;
      }
      j = j + x;
      j = j % len;
      i = i + y;
    }
    return result;
  }
  public static void main(String[] args) throws IOException {
    tobogganTrajectory obj = new tobogganTrajectory();
    long slope11 = obj.puzzle(1, 1, "day3/geology.pattern");
    long slope31 = obj.puzzle(3, 1, "day3/geology.pattern");
    long slope51 = obj.puzzle(5, 1, "day3/geology.pattern");
    long slope71 = obj.puzzle(7, 1, "day3/geology.pattern");
    long slope12 = obj.puzzle(1, 2, "day3/geology.pattern");
    System.out.println("Puzzle 1 solution = " + slope31);
    System.out.println("Puzzle 2 solution = " + (slope11 * slope31 * slope51 * slope71 * slope12));
  }
}
