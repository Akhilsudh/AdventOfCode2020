// https://adventofcode.com/2020/day/12
package day_12;

import java.io.*;
import java.util.*;
public class rainRisk {
  public int getManhattanDistance() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day_12/navigation.instructions"));
    int result = 0;
    String line = "";
    List<String> instructions = new ArrayList<String>();
    while((line = br.readLine()) != null) {
      instructions.add(line);
    }
    br.close();
    int[] position = {0, 0};
    char direction = 'E';
    char[][] directions = {{'N',}}; 
    for(String instruction: instructions) {
      char command = instruction.charAt(0);
      int value = Integer.parseInt(instruction.substring(1));
      switch(command) {
        case 'N':
          position[1] = position[1] + value;
          break;
        case 'S':
          position[1] = position[1] + value;
          break;
        case 'E':
          position[0] = position[0] + value;
          break;
        case 'W':
          position[0] = position[0] - value;
          break;
      }
    }
    
    
    // Map<Character, String> directions = new HashMap<Character, String>();
    // directions.put('E', "00");
    // directions.put('S', "31");
    // directions.put('W', "22");
    // directions.put('N', "13");
    // String[] positions = {"EE", "NS", "WW", "SN"};
    // Map<String, Integer> resultMap = new HashMap<String, Integer>();
    // resultMap.put("hor", 0);
    // resultMap.put("ver", 0);
    // char direction = 'E';
    // for(String instruction: instructions) {
    //   int value = Integer.parseInt(instruction.substring(1));
    //   if(instruction.charAt(0) == 'L' ) {
    //     int angle = (Integer.parseInt(directions.get(direction).charAt(0) + "") + (value / 90)) % 4;
    //     direction = positions[angle].charAt(0);
    //   }
    //   else if(instruction.charAt(0) == 'R') {
    //     int angle = (Integer.parseInt(directions.get(direction).charAt(1) + "") + (value / 90)) % 4;
    //     direction = positions[angle].charAt(1);
    //   }
    //   else if(instruction.charAt(0) == 'N') {
    //     resultMap.put("ver", resultMap.get("ver") + value);
    //   }
    //   else if(instruction.charAt(0) == 'E') {
    //     resultMap.put("hor", resultMap.get("hor") + value);
    //   }
    //   else if(instruction.charAt(0) == 'S') {
    //     resultMap.put("ver", resultMap.get("ver") - value);
    //   }
    //   else if(instruction.charAt(0) == 'W') {
    //     resultMap.put("hor", resultMap.get("hor") - value);
    //   }
    //   else if(instruction.charAt(0) == 'F') {
    //     if(direction == 'E') {
    //       resultMap.put("hor", resultMap.get("hor") + value);
    //     }
    //     else if(direction == 'W') {
    //       resultMap.put("hor", resultMap.get("hor") - value);
    //     }
    //     else if(direction == 'N') {
    //       resultMap.put("ver", resultMap.get("ver") + value);
    //     }
    //     else if(direction == 'S') {
    //       resultMap.put("ver", resultMap.get("ver") - value);
    //     }
    //   }
    // }
    // result = Math.abs(resultMap.get("hor")) + Math.abs(resultMap.get("ver"));
    return result;
  }
  public int getNewManhattanDistance() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day_12/navigation.instructions"));
    int result = 0;
    String line = "";
    List<String> instructions = new ArrayList<String>();
    while((line = br.readLine()) != null) {
      instructions.add(line);
    }
    br.close();
    int[] position = {0, 0};
    int[] wayPoint = {10, 1};
    for(String instruction: instructions) {
      char command = instruction.charAt(0);
      int value = Integer.parseInt(instruction.substring(1));
      if(command == 'L') {
        for(int i = 0; i < value / 90; i++) {
          int x = wayPoint[0];
          wayPoint[0] = - wayPoint[1];
          wayPoint[1] = x;
        }
      }
      else if(command == 'R') {
        for(int i = 0; i < value / 90; i++) {
          int x = wayPoint[0];
          wayPoint[0] = wayPoint[1];
          wayPoint[1] = - x;
        }
      }
      else if(command == 'N') {
        wayPoint[1] += value;
      }
      else if(command == 'S') {
        wayPoint[1] -= value;
      }
      else if(command == 'E') {
        wayPoint[0] += value;
      }
      else if(command == 'W') {
        wayPoint[0] -= value;
      }
      else if(command == 'F') {
        position[0] = position[0] + value * wayPoint[0];
        position[1] = position[1] + value * wayPoint[1];
      }
    }
    result = Math.abs(position[0]) + Math.abs(position[1]);
    return result;
  }
  public static void main(String[] args) throws IOException{
    rainRisk obj = new rainRisk();
    System.out.println("Puzzle 1 solution = " + obj.getManhattanDistance());
    System.out.println("Puzzle 2 solution = " + obj.getNewManhattanDistance());
  }
}
