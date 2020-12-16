// https://adventofcode.com/2020/day/16
package day_16;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class TicketTranslation {

  static List<Rule> rules;
  static List<Ticket> tickets;
  static int size = 20;

  private static class Rule {
    String name;
    int low1;
    int high1;
    int low2;
    int high2;
    int[] indexMapping;
    
    public Rule(String line, int size) {
      String[] arr = line.replace(" or ", " ").replace("-", " ").split(": ");
      this.name = arr[0];
      String[] ranges = arr[1].split(" ");
      this.low1 = Integer.parseInt(ranges[0]);
      this.high1 = Integer.parseInt(ranges[1]);
      this.low2 = Integer.parseInt(ranges[2]);
      this.high2 = Integer.parseInt(ranges[3]);
      this.indexMapping = new int[size];
      for(int i = 0; i < size; i++) {
        this.indexMapping[i] = i;
      }
    }

    public void unsetMapping(int index) {
      this.indexMapping[index] = -1;
    }

    public int[] getvalidFields() {
      return Arrays.stream(indexMapping).filter(value -> value != -1).toArray();
    }

    public boolean isValid(int value) {
      return ((low1 <= value && value <= high1) || (low2 <= value && value <= high2));
    }
  }

  private static class Ticket {
    int[] values;
    boolean valid;

    public Ticket(String line) {
      this.values = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
      this.valid = true;
    }
  }

  private void getRules(List<Rule> rules) throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day_16/ticket.rules"));
    String line = "";
    while((line = br.readLine()) != null) {
      rules.add(new Rule(line, size));
    }
    br.close();
  }

  public int getScanningErrorRate(List<Rule> rules, List<Ticket> tickets) throws IOException{
    int result = 0;
    getRules(rules);
    BufferedReader br = new BufferedReader(new FileReader("day_16/ticket.list"));
    String line = "";
    while((line = br.readLine()) != null) {
      Ticket ticket = new Ticket(line);
      tickets.add(ticket);
      for(int field: ticket.values) {
        boolean flag = false;
        for(Rule rule: rules) {
          flag = rule.isValid(field);
          if(flag) {
            break;
          }
        }
        if(!flag) {
          ticket.valid = false;
          result += field;
        }
      }
    }
    br.close();
    return result;
  }

  public long getProductOfDeparture(List<Rule> rules, List<Ticket> tickets) {
    long result = 0;
    List<Ticket> validTickets = tickets.stream().filter(ticket -> ticket.valid == true).collect(Collectors.toList());
    for(Ticket ticket: validTickets) {
      for(int i = 0; i < ticket.values.length; i++) {
        for(Rule rule: rules) {
          if(!rule.isValid(ticket.values[i])) {
            rule.unsetMapping(i);
          }
        }
      }
    }
    rules.sort(Comparator.comparing(a -> a.getvalidFields().length));
    for(int i = 0; i < rules.size(); i++) {
      int index = rules.get(i).getvalidFields()[0];
      for(int j = i + 1; j < rules.size(); j++) {
        rules.get(j).unsetMapping(index);
      }
    }
    result = rules.stream().filter(rule -> rule.name.startsWith("departure")).mapToLong(rule -> tickets.get(0).values[rule.getvalidFields()[0]]).reduce((a, b) -> a * b).getAsLong();
    return result;
  }

  public static void main(String[] args) throws IOException{
    TicketTranslation obj = new TicketTranslation();
    rules = new ArrayList<Rule>(); 
    tickets = new ArrayList<Ticket>();
    System.out.println("Puzzle 1 solution = " + obj.getScanningErrorRate(rules, tickets));
    System.out.println(rules.size());
    System.out.println(tickets.size());
    System.out.println("Puzzle 2 solution = " + obj.getProductOfDeparture(rules, tickets));
  }
}
