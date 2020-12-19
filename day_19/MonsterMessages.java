// https://adventofcode.com/2020/day/19
package day_19;

import java.io.*;
import java.util.*;
public class MonsterMessages {

  Map<Integer, String> getRuleMap() throws IOException {
    Map<Integer, String> ruleMap = new HashMap<Integer, String>();
    BufferedReader br = new BufferedReader(new FileReader("day_19/rule.list"));
    String line = "";
    while((line = br.readLine()) != null) {
      String[] rule = line.split(": ");
      if(rule[1].charAt(0) != '"') {
        ruleMap.put(Integer.parseInt(rule[0]), rule[1]);
      }
      else {
        ruleMap.put(Integer.parseInt(rule[0]), rule[1].charAt(1) + "");
      }
    }
    br.close();
    return ruleMap;
  }
  
  Set<String> getMessageSet() throws IOException {
    Set<String> messageSet = new HashSet<String>();
    BufferedReader br = new BufferedReader(new FileReader("day_19/messages.list"));
    String line = "";
    while((line = br.readLine()) != null) {
      messageSet.add(line);
    }
    br.close();
    return messageSet;
  }

  String getRegex(Map<Integer, String> ruleMap, int index) {
    while(ruleMap.get(index).matches(".*\\d.*")) {
      String[] rules = ruleMap.get(index).split(" ");
      String regex = "";
      for(String rule: rules) {
        if(rule.matches("\\d+")) {
          String string = ruleMap.get(Integer.parseInt(rule));
          if(string.matches("[ab]")) {
            regex += string;
          }
          else {
            regex += "( " + string + " )";
          }
        }
        else {
          regex += rule;
        }
      }
      ruleMap.put(index, regex);
    }
    return "(" + ruleMap.get(index) + ")";
  }

  public int getValidCount() throws IOException{
    int result = 0;
    Map<Integer, String> ruleMap = getRuleMap();
    Set<String> messageSet = getMessageSet();
    String pattern = "^" + getRegex(ruleMap, 0) + "$";
    for(String message: messageSet) {
      if(message.matches(pattern)) {
        result += 1;
      }
    }
    return result;
  }

  // Rule "0: 8 11" and we need to change "8: 42" to "8: 42 | 42 8" and "11: 42 31" to "11: 42 31 | 42 11 31"
  // this means that the regex string must start with "42+" and must end with n number of 42 and n number of 31 
  public int getValidCountAfterEdit() throws IOException{
    int result = 0;
    Map<Integer, String> ruleMap = getRuleMap();
    Set<String> messageSet = getMessageSet();
    String pattern42 = getRegex(ruleMap, 42);
    String pattern31 = getRegex(ruleMap, 31);
    int depth = 1;
    String pattern = "^(" + pattern42 + ")+(";
    // Started with max depth as 20 and reduced it till the same solution came up.
    while(depth <= 5) {
      pattern += "(";
      for(int i = 1; i <= depth; i++) {
        pattern += pattern42;
      }
      pattern += ")(";
      for(int i = 1; i <= depth; i++) {
        pattern += pattern31;
      }
      pattern += ")|";
      depth++;
    }
    pattern = pattern.substring(0, pattern.length() - 1) + ")$";
    for(String message: messageSet) {
      if(message.matches(pattern)) {
        result += 1;
      }
    }
    return result;
  }

  public static void main(String[] args) throws IOException{
    MonsterMessages obj = new MonsterMessages();
    System.out.println("Puzzle 1 solution = " + obj.getValidCount());
    System.out.println("Puzzle 2 solution = " + obj.getValidCountAfterEdit());
  }
}
