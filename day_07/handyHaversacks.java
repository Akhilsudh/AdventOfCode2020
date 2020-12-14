// https://adventofcode.com/2020/day/7
package day_07;

import java.io.*;
import java.util.*;
public class handyHaversacks {
  private void DFS(Set<String> result, Map<String, Set<String>> map, String word) {
    if(!map.containsKey(word)) {
      return;
    }
    Set<String> bags = map.get(word);
    for(String bag: bags) {
      result.add(bag);
      DFS(result, map, bag);
    }
  }
  private int maxDFS(Map<String, Map<String, Integer>> map, String key) {
    if(!map.containsKey(key)) {
      return 0;
    }
    int sum = 0;
    Map<String, Integer> valueMap = map.get(key);
    for(String colour: valueMap.keySet()) {
      sum += valueMap.get(colour) * (1 + maxDFS(map, colour));
    }
    return sum;
  }
  public int getSuperBagCount() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_07/rules.list"));
    String line = "";
    Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    while((line = br.readLine()) != null) {
      String[] bagString = line.split(" contain ");
      String[] bagContents = bagString[1].split(", ");
      Set<String> set = new HashSet<String>();
      for(String content: bagContents) {
        content = content.substring(2, content.length());
        content = content.substring(0, content.lastIndexOf(" ", content.length()));
        set.add(content);
      }
      for(String key: set) {
        if (map.get(key) == null) {
          map.put(key, new HashSet<String>());
        }
        map.get(key).add(bagString[0].substring(0, bagString[0].lastIndexOf(" ", bagString[0].length())));
      }
    }
    br.close();
    String value = "shiny gold";
    Set<String> result = new HashSet<String>();
    DFS(result, map, value);
    return result.size();
  }
  public int getMaxBagCount() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_07/rules.list"));
    String line = "";
    Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
    while((line = br.readLine()) != null) {
      String[] bagString = line.split(" bags contain ");
      String[] bagContents = bagString[1].split(", ");
      if(bagString[1].equals("no other bags.")) {
        continue;
      }
      Map<String, Integer> valueMap = new HashMap<String, Integer>();
      for(String content: bagContents) {
        String[] arr = content.split(" ", 2);
        int value = Integer.parseInt(arr[0]);
        String key = arr[1].substring(0, content.lastIndexOf(" ", arr[1].length()) - 2);
        valueMap.put(key, value);
      }
      map.put(bagString[0], valueMap);
    }
    br.close();
    String value = "shiny gold";
    return maxDFS(map, value);
  }
  public static void main(String[] args) throws IOException{
    handyHaversacks obj = new handyHaversacks();
    System.out.println("Puzzle 1 solution = " + obj.getSuperBagCount());
    System.out.println("Puzzle 2 solution = " + obj.getMaxBagCount());
  }
}
