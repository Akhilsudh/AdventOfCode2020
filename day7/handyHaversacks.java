package day7;

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
  private int maxDFS(Map<String, Map<String, Integer>> map, String key, int sum, int multi) {
    if(!map.containsKey(key)) {
      return 1;
    }
    Map<String, Integer> valueMap = map.get(key);
    for(String colour: valueMap.keySet()) {
      sum += valueMap.get(colour) * maxDFS(map, colour);
    }
    return sum;
  }
  public int getSuperBagCount() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day7/rules.list"));
    String line = "";
    Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    while((line = br.readLine()) != null) {
      String[] bagString = line.split(" contain ");
      String[] bagContents = bagString[1].split(", ");
      // bagContents[bagContents.length - 1] = bagContents[bagContents.length - 1].substring(0, bagContents[bagContents.length - 1].length() - 1);
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
    System.out.println(map);
    String value = "shiny gold";
    Set<String> result = new HashSet<String>();
    DFS(result, map, value);
    return result.size();
  }
  public int getMaxBagCount() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day7/rules.list"));
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
        // if(content.equals("no other bags.")) {
        //   continue;
        // }
        String[] arr = content.split(" ", 2);
        int value = Integer.parseInt(arr[0]);
        String key = arr[1].substring(0, content.lastIndexOf(" ", arr[1].length()) - 2);
        System.out.println(key + " " + key.length());
        valueMap.put(key, value);
      }
      map.put(bagString[0], valueMap);
    }
    br.close();
    System.out.println(map);
    String value = "shiny gold";
    // maxDFS
    return 0;
  }
  public static void main(String[] args) throws IOException{
    handyHaversacks obj = new handyHaversacks();
    // System.out.println("Puzzle 1 solution = " + obj.getSuperBagCount());
    System.out.println("Puzzle 2 solution = " + obj.getMaxBagCount());
  }
}
