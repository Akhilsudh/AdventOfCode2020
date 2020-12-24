// https://adventofcode.com/2020/day/23
package day_23;

import java.util.*;
public class CrabCups {
  static Node head = null;
  static Node tail = null;
  Set<Integer> removed;

  class Node {
    int value;
    Node next;

    public Node(int value) {
      this.value = value;
    }
  }

  void addNode(int value) {
    Node cur = new Node(value);
    if(head == null) {
      head = cur;
    }
    else {
      tail.next = cur;
    }
    tail = cur;
    tail.next = head;
  }

  void makeCircularLinkedList(String cups) {
    for(int i = 0; i < cups.length(); i++) {
      addNode(Integer.parseInt(cups.charAt(i)+""));
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
      System.out.println("Iteration " + (i + 1));
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
        Node node = start;
        Node max = new Node(Integer.MIN_VALUE);
        node = node.next;
        while(node != start) {
          if(node.value > max.value) {
            max = node;
          }
          node = node.next;
        }
        System.out.println("Destination " + max.value);
        addNext3(max, nodes);
        
      }
      else {
        Node node = start;
        node = node.next;
        while(node != start) {
          if(node.value == value) {
            break;
          }
          node = node.next;
        }
        System.out.println("Destination " + node.value);
        addNext3(node, nodes);
      }
      start = start.next;
      System.out.println("Start Node " + start.value);
      testCircularLinkedList();
      System.out.println();
    }
  }

  public static void main(String[] args) {
    CrabCups obj = new CrabCups();
    obj.makeCircularLinkedList("598162734");
    obj.testCircularLinkedList();
    System.out.println();
    obj.playGame(100);
  }
}