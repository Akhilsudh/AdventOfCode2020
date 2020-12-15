// https://adventofcode.com/2020/day/15
package day_15;

import java.util.*;
public class rambunctiousRecitation {

  private static class State {
    int index;
    int oldIndex;
    boolean fresh;
    public State(int index, int oldIndex, boolean fresh) {
      this.index = index;
      this.oldIndex = oldIndex;
      this.fresh = fresh;
    }
  }

  public int get2020(String numbers) {
    String[] numbersArr = numbers.split(",");
    Map<Integer, State> input = new HashMap<Integer, State>();
    List<Integer> list = new ArrayList<Integer>();
    int i = 0;
    for(i = 0; i < numbersArr.length; i++) {
      input.put(Integer.parseInt(numbersArr[i]), new State(i, 0, true));
      list.add(Integer.parseInt(numbersArr[i]));
    }
    System.out.println(i);
    int index = list.size() - 1;
    while(index <= 2020) {
      int number = list.get(index);
      if(input.get(number).fresh) {
        list.add(0);
        index += 1;
        if(input.containsKey(0)) {
          input.put(0, new State(index, input.get(0).index, false));
        }
        else {
          input.put(0, new State(index, 0, true));
        }
      }
      else {
        int value = input.get(number).index - input.get(number).oldIndex;
        list.add(value);
        index += 1;
        if(input.containsKey(value)) {
          input.put(value, new State(index, input.get(value).index, false));
        }
        else {
          input.put(value, new State(index, 0, true));
        }
      }
    }
    return list.get(2019);
  }

  public static void main(String[] args) {
    rambunctiousRecitation obj = new rambunctiousRecitation();
    System.out.println("Puzzle 1 solution = " + obj.get2020("6,3,15,13,1,0"));
  }
}
