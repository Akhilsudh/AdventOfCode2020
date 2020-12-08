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
    boolean[] visited = new boolean[instructionList.size()];
    int index = 0;
    while(true) {
      if(visited[index] == true) {
        break;
      }
      visited[index] = true;
      instruction = instructionList.get(index);
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
  public int getFixedAccumulator() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day8/instruction.list"));
    List<String> instructionList = new ArrayList<String>();
    int accumulator = 0;
    String line = "";
    while((line = br.readLine()) != null) {
      instructionList.add(line);
    }
    br.close();

    for(int i = 0; i < instructionList.size(); i++) {
      ArrayList<String> moddedInstructionList = new ArrayList<String>(instructionList);
      String[] moddedInstruction = moddedInstructionList.get(i).split(" ");
      if(moddedInstruction[0].equals("jmp")) {
        moddedInstructionList.set(i, "nop " + moddedInstruction[1]);
      }
      else if(moddedInstruction[0].equals("nop")) {
        moddedInstructionList.set(i, "jmp " + moddedInstruction[1]);
      }
      else {
        continue;
      }
      String instruction = "";
      boolean[] visited = new boolean[moddedInstructionList.size()];
      int index = 0;
      accumulator = 0;
      while(index < moddedInstructionList.size()) {
        if(visited[index] == true) {
          break;
        }
        visited[index] = true;
        instruction = moddedInstructionList.get(index);
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
      if(index >= moddedInstructionList.size() - 1) {
        break;
      }
    }
    
    return accumulator;
  }
  public static void main(String[] args) throws IOException{
    handheldHalting obj = new handheldHalting();
    System.out.println("Puzzle 1 solution = " + obj.getAccumulator());
    System.out.println("Puzzle 2 solution = " + obj.getFixedAccumulator());
  }
}
