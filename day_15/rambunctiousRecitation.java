// https://adventofcode.com/2020/day/15
package day_15;

import java.util.*;
public class rambunctiousRecitation {

  private static class State {
    int index;
    int oldIndex;
    public State(int index, int oldIndex) {
      this.index = index;
      this.oldIndex = oldIndex;
    }
  }

  public int getSpokenNumber(String numbers, int spokenIndex) {
    String[] numbersArr = numbers.split(",");
    Map<Integer, State> input = new HashMap<Integer, State>();
    int i = 0;
    int value = 0;
    for(i = 0; i < numbersArr.length; i++) {
      value = Integer.parseInt(numbersArr[i]);
      input.put(value, new State(i, -1));
    }
    int index = i - 1;
    while(index < spokenIndex - 1) {
      int number = value;
      if(input.get(number).oldIndex == -1) {
        value = 0;
        index += 1;
        if(input.containsKey(0)) {
          input.put(0, new State(index, input.get(0).index));
        }
        else {
          input.put(0, new State(index, -1));
        }
      }
      else {
        value = input.get(number).index - input.get(number).oldIndex;
        index += 1;
        if(input.containsKey(value)) {
          input.put(value, new State(index, input.get(value).index));
        }
        else {
          input.put(value, new State(index, -1));
        }
      }
    }
    return value;
  }

  public static void main(String[] args) {
    rambunctiousRecitation obj = new rambunctiousRecitation();
    System.out.println("Puzzle 1 solution = " + obj.getSpokenNumber("6,3,15,13,1,0", 2020));
    System.out.println("Puzzle 2 solution = " + obj.getSpokenNumber("6,3,15,13,1,0", 30000000));
  }
}
