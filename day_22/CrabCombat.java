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

  int recursiveCombat(Deque<Integer> deck1, Deque<Integer> deck2) {
    Set<List<Integer>> playedGames = new HashSet<>();
    while(!deck1.isEmpty() && !deck2.isEmpty()) {
      if(!playedGames.add(new ArrayList<Integer>(deck1))){
        return 1;
      }
      int card1 = deck1.pop();
      int card2 = deck2.pop();
      if(deck1.size() >= card1 && deck2.size() >= card2) {
        Deque<Integer> subDeck1 = new ArrayDeque<Integer>(new ArrayList<Integer>(deck1).subList(0, card1));
        Deque<Integer> subDeck2 = new ArrayDeque<Integer>(new ArrayList<Integer>(deck2).subList(0, card2));
        int winner = recursiveCombat(subDeck1, subDeck2);
        if(winner == 1) {
          deck1.addLast(card1);
          deck1.addLast(card2);
        }
        else {
          deck2.addLast(card2);
          deck2.addLast(card1);
        }
      }
      else {
        if(card1 > card2) {
          deck1.addLast(card1);
          deck1.addLast(card2);
        }
        else {
          deck2.addLast(card2);
          deck2.addLast(card1);
        }
      }
    }
    return (deck1.isEmpty()) ? 2 : 1;
  }

  int getRecursiveCombatScore() {
    int score = 0;
    int winner = recursiveCombat(decks.get(0), decks.get(1)) - 1;
    int i = 1;
    while(!decks.get(winner).isEmpty()) {
      score = score + decks.get(winner).removeLast() * i;
      i++;
    }
    return score;
  }

  public static void main(String[] args) throws IOException {
    CrabCombat obj = new CrabCombat();
    obj.getDecks();
    System.out.println("Puzzle 1 solution = " + obj.getCombatScore());
    obj.getDecks();
    System.out.println("Puzzle 2 solution = " + obj.getRecursiveCombatScore());
  }
}
