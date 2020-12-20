// https://adventofcode.com/2020/day/19
package day_19;

import java.io.*;
import java.util.*;
public class MonsterMessages {
  
  static class Rule {
    boolean leaf;
    String rule;
    
    public Rule(boolean leaf, String rule) {
      this.leaf = leaf;
      this.rule = rule;
    }
  }

  Map<Integer, Rule> getRuleMap() throws IOException {
    Map<Integer, Rule> ruleMap = new HashMap<Integer, Rule>();
    BufferedReader br = new BufferedReader(new FileReader("day_19/rule.list"));
    String line = "";
    while((line = br.readLine()) != null) {
      String[] rule = line.split(": ");
      if(rule[1].charAt(0) != '"') {
        ruleMap.put(Integer.parseInt(rule[0]), new Rule(false, rule[1]));
      }
      else {
        ruleMap.put(Integer.parseInt(rule[0]), new Rule(true, rule[1].charAt(2) + ""));
      }
    }
    br.close();
    return ruleMap;
  }
  
  Set<String> getMessageSet() throws IOException {
    Set<String> messageSet = new HashSet<String>();
    BufferedReader br = new BufferedReader(new FileReader("day_19/messages.list");
    String line = "";
    while((line = br.readLine()) != null) {
      messageSet.add(line);
    }
    return messageSet;
  }

  void dfs(Map<Integer, Rule> ruleMap, Set<String> messageSet, int id, String message, Set<String> validSet) {
    if(ruleMap.get(id).leaf) {
      valid
      return;
    }
    else {
      String[] rules = ruleMap.get(id).rule.split(" | ");
      
    }
  }

  public int getValidCount() throws IOException{
    int result = 0;
    Map<Integer, Rule> ruleMap = getRuleMap();
    Set<String> messageSet = getMessageSet();
    Set<String> validMessageSet = new HashSet<String>();
    dfs(ruleMap, messageSet, 0, "", validMessageSet);
    return result;
  }
}
