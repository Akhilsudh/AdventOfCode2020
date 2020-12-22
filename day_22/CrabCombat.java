// https://adventofcode.com/2020/day/22
package day_22;

import java.io.*;
import java.util.*;
public class CrabCombat {
  static List<Deque<Integer>> decks;

  void getDecks() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day_22/card.deck"));
    String line;
    decks = new ArrayList<Deque<Integer>>();
    Deque<Integer> deck = new ArrayDeque<Integer>();
    while((line = br.readLine()) != null) {
      if(line.equals("")) {
        decks.add(deck);
      }
      else if(line.startsWith("Player")) {
        deck = new ArrayDeque<Integer>();
      }
      else {
        deck.addLast(Integer.parseInt(line));
      }
    }
    br.close();
  }

  int getCombatScore() {
    int score = 0;
    while(!decks.get(0).isEmpty() && !decks.get(1).isEmpty()) {
      int card1 = decks.get(0).pop();
      int card2 = decks.get(1).pop();
      if(card1 > card2) {
        decks.get(0).addLast(card1);
        decks.get(0).addLast(card2);
      }
      else {
        decks.get(1).addLast(card2);
        decks.get(1).addLast(card1);
      }
    }
    int index = (decks.get(0).isEmpty()) ? 1 : 0;
    int i = 1;
    while(!decks.get(index).isEmpty()) {
      score = score + decks.get(index).removeLast() * i;
      i++;
    }
    return score;
  }

  public static void main(String[] args) throws IOException {
    CrabCombat obj = new CrabCombat();
    obj.getDecks();
    System.out.println(obj.getCombatScore());
  }
}
