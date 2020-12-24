// https://adventofcode.com/2020/day/23
package day_23;

import java.util.*;
public class CrabCups {
  static Node head;
  static Node tail;
  static Set<Integer> removed;
  static Map<Integer, Node> cupMap;

  class Node {
    int value;
    Node next;

    public Node(int value) {
      this.value = value;
    }
  }

  Node addNode(int value) {
    Node cur = new Node(value);
    if(head == null) {
      head = cur;
    }
    else {
      tail.next = cur;
    }
    tail = cur;
    tail.next = head;
    return cur;
  }

  void makeCircularLinkedList(String cups, int size) {
    head = null;
    tail = null;
    cupMap = new HashMap<Integer, Node>();
    Node node;
    for(int i = 0; i < cups.length(); i++) {
      int value = Integer.parseInt(cups.charAt(i)+"");
      node = addNode(value);
      cupMap.put(value, node);
    }
    for(int i = cupMap.size() + 1; i <= size; i++) {
      node = addNode(i);
      cupMap.put(i, node);
    }
  }

  void testCircularLinkedList() {
    Node node = head;
    do {
      System.out.print(node.value + " ");
      node = node.next;
    } while(node != head);
    System.out.println();
  }


  List<Node> removeNext3(Node node) {
    List<Node> nodes = new ArrayList<Node>();
    nodes.add(node.next);
    nodes.add(nodes.get(0).next);
    nodes.add(nodes.get(1).next);
    node.next = nodes.get(2).next;
    removed = new HashSet<Integer>();
    removed.add(nodes.get(0).value);
    removed.add(nodes.get(1).value);
    removed.add(nodes.get(2).value);
    return nodes;
  }

  void addNext3(Node node, List<Node> nodes) {
    Node destination = node.next;
    node.next = nodes.get(0);
    nodes.get(2).next = destination;
  }

  void playGame(int iterations) {
    Node start = head;
    for(int i = 0; i < iterations; i++) {
      List<Node> nodes = removeNext3(start);
      int value = start.value;
      value = value - 1;
      while(value > 0) {
        if(removed.contains(value)) {
          value = value - 1;
        }
        else {
          break;
        }
      }
      if(value == 0) {
        value = cupMap.size();
        while(removed.contains(value)) {
          value = value - 1;
        }
        Node node = cupMap.get(value);
        addNext3(node, nodes);
      }
      else {
        Node node = cupMap.get(value);
        addNext3(node, nodes);
      }
      start = start.next;
    }
  }

  String getCupString() {
    String string = "";
    Node node = cupMap.get(1).next;
    while(node != cupMap.get(1)) {
      string += node.value;
      node = node.next;
    }
    return string;
  }

  long twoStarPositionProduct() {
    return (long)cupMap.get(1).next.value * (long)cupMap.get(1).next.next.value;
  }

  public static void main(String[] args) {
    CrabCups obj = new CrabCups();
    obj.makeCircularLinkedList("598162734", 0);
    obj.playGame(100);
    System.out.println("Puzzle 1 solution = " + obj.getCupString());
    obj.makeCircularLinkedList("598162734", 1000000);
    obj.playGame(10000000);
    System.out.println("Puzzle 2 solution = " + obj.twoStarPositionProduct());
  }
}