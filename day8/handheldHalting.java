// https://adventofcode.com/2020/day/8
package day8;

import java.io.*;
import java.util.*;
public class handheldHalting {
  public int getAccumulator() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day8/instruction.list"));
    List<String> instructionList = new ArrayList<String>();
    int accumulator = 0;
    String line = "";
    while((line = br.readLine()) != null) {
      instructionList.add(line);
    }
    br.close();
    String instruction = "";
    System.out.println(instructionList);
    boolean[] visited = new boolean[instructionList.size()];
    System.out.println(visited[0]);
    int index = 0;
    while(true) {
      System.out.println(index);
      System.out.println(visited[index]);
      if(visited[index] == true) {
        System.out.println("hello");
        break;
      }
      visited[index] = true;
      instruction = instructionList.get(index);
      System.out.println(instruction);
      String[] instructions = instruction.split(" ");
      if(instructions[0].equals("nop")) {
        index += 1;
      }
      else if(instructions[0].equals("acc")) {
        if(instructions[1].charAt(0) == '+') {
          accumulator += Integer.parseInt(instructions[1].substring(1));
        }
        else {
          accumulator -= Integer.parseInt(instructions[1].substring(1));
        }
        index += 1;
      }
      else if(instructions[0].equals("jmp")){
        if(instructions[1].charAt(0) == '+') {
          index += Integer.parseInt(instructions[1].substring(1));
        }
        else {
          index -= Integer.parseInt(instructions[1].substring(1));
        }
      }
    }
    return accumulator;
  }
  public int getFixedAAccumulator() {
    BufferedReader br = new BufferedReader(new FileReader("day8/instruction.list"));
    List<String> instructionList = new ArrayList<String>();
    int accumulator = 0;
    String line = "";
    while((line = br.readLine()) != null) {
      instructionList.add(line);
    }
    br.close();
    String instruction = "";
    System.out.println(instructionList);
    boolean[] visited = new boolean[instructionList.size()];
    System.out.println(visited[0]);
    int index = 0;
    while(true) {
      System.out.println(index);
      System.out.println(visited[index]);
      if(visited[index] == true) {
        System.out.println("hello");
        break;
      }
      visited[index] = true;
      instruction = instructionList.get(index);
      System.out.println(instruction);
      String[] instructions = instruction.split(" ");
      if(instructions[0].equals("nop")) {
        index += 1;
      }
      else if(instructions[0].equals("acc")) {
        if(instructions[1].charAt(0) == '+') {
          accumulator += Integer.parseInt(instructions[1].substring(1));
        }
        else {
          accumulator -= Integer.parseInt(instructions[1].substring(1));
        }
        index += 1;
      }
      else if(instructions[0].equals("jmp")){
        if(instructions[1].charAt(0) == '+') {
          index += Integer.parseInt(instructions[1].substring(1));
        }
        else {
          index -= Integer.parseInt(instructions[1].substring(1));
        }
      }
    }
    return accumulator;
  }
  public static void main(String[] args) throws IOException{
    handheldHalting obj = new handheldHalting();
    System.out.println("Puzzle 1 solution = " + obj.getAccumulator());
  }
}
