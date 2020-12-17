// https://adventofcode.com/2020/day/17
package day_17;

import java.io.*;
import java.util.*;
public class ConwayCubes {

  int getActiveNeighbors(Set<String> activeSet, String coordinate) {
    int[] point = Arrays.stream(coordinate.split(",")).mapToInt(Integer::parseInt).toArray();
    int count = 0;
    for(int i = -1; i < 2; i++) {
      for(int j = -1; j < 2; j++) {
        for(int k = -1; k < 2; k++) {
          if(!(i == 0 && j == 0 && k == 0)) {
            int x = point[0] + i;
            int y = point[1] + j;
            int z = point[2] + k;
            if(activeSet.contains(x + "," + y + "," + z)) {
              count += 1;
            }
          }
        }
      }
    }
    return count;
  }

  public int getActiveAfterSix() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_17/initial.state"));
    Set<String> activeSet = new HashSet<String>();
    int result = 0;
    String line = "";
    int iterations = 6;
    int y = 0;
    int z = 0;
    int width = 0;
    while((line = br.readLine()) != null) {
      width = line.length();
      for(int x = 0; x < line.length(); x++) {
        if(line.charAt(x) == '#') {
          activeSet.add(x + "," + y + "," + z);
        }
      }
      y += 1;
    }
    br.close();
    int height = y;
    for(int i = 0; i < iterations; i++) {
      Set<String> activeSetCopy = new HashSet<String>();
      for(int x = -iterations; x < width + iterations; x++) {
        for(y = -iterations; y < height + iterations; y++) {
          for(z = -iterations; z < iterations; z++) {
            String coordinate = x + "," + y + "," + z;
            int activeCount = getActiveNeighbors(activeSet, coordinate);
            if(activeSet.contains(coordinate) && (activeCount == 2 || activeCount == 3)){
              activeSetCopy.add(coordinate);
            }
            else if(!(activeSet.contains(coordinate)) && activeCount == 3) {
              activeSetCopy.add(coordinate);
            }
          }
        }
      }
      activeSet = activeSetCopy;
    }
    result = activeSet.size();
    return result;
  }

  public static void main(String[] args) throws IOException {
    ConwayCubes obj = new ConwayCubes();
    System.out.println("Puzzle 1 solution = " + obj.getActiveAfterSix());
  }
}
