// https://adventofcode.com/2020/day/20
package day_20;

import java.io.*;
import java.util.*;
public class JurassicJigsaw {
  class Tile {
    int id;
    char[][] tile;
    String edge1;
    String edge2;
    String edge3;
    String edge4;
    boolean corner;

    public Tile(int id, char[][] tile) {
      this.id = id;
      this.tile = tile;
      
      edge1 = edge2 = edge3 = edge4 = "";
      for (int c = 0; c < tile[0].length; c++) {
        edge1 += tile[0][c];
      }
      for (int r = 0; r < tile.length; r++) {
        edge2 += tile[r][tile[0].length - 1];
      }
      for (int c = 0; c < tile[0].length; c++) {
        edge3 += tile[tile.length - 1][c];
      }
      for (int r = 0; r < tile.length; r++) {
        edge4 += tile[r][0];
      }
      this.corner = false;
    }
  }

  List<Tile> getTiles() throws IOException{
    List<Tile> tileList = new ArrayList<Tile>();
    BufferedReader br = new BufferedReader(new FileReader("day_20/tiles.list"));
    String line;
    List<String> input = new ArrayList<String>();
    while((line = br.readLine()) != null) {
      if(line.equals("")) {
        int id = Integer.parseInt(input.get(0).substring(5, input.get(0).length() - 1));
        input.remove(0);
        char[][] tile = new char[input.size()][input.get(0).length()];
        for(int i = 0; i < input.size(); i++) {
          String s = input.get(i);
          for(int j = 0; j < s.length(); j++) {
            tile[i][j] = s.charAt(j);
          }
        }
        tileList.add(new Tile(id, tile));
        input.clear();
      }
      else {
        input.add(line);
      }
    }
    br.close();
    return tileList;
  }

  String reverse(String s) {
    return new StringBuilder(s).reverse().toString();
  }

  long getCornerProduct() throws IOException{
    List<Tile> tileList = getTiles();
    long result = 1;
    for(int i = 0; i < tileList.size(); i++) {
      int count = 0;
      Tile t1 = tileList.get(i);
      for(int j = 0; j < tileList.size(); j++) {
        if(i == j) {
          continue;
        }
        Tile t2 = tileList.get(j);
        if (t1.edge1.equals(t2.edge1)) {
          count++;
        }
				else if (t1.edge1.equals(t2.edge2)) {
          count++;
        }
				else if (t1.edge1.equals(t2.edge3)) {
          count++;
        }
				else if (t1.edge1.equals(t2.edge4)) {
          count++;
        }

				else if (t1.edge1.equals(reverse(t2.edge1))) {
          count++;
        }
				else if (t1.edge1.equals(reverse(t2.edge2))) {
          count++;
        }
				else if (t1.edge1.equals(reverse(t2.edge3))) {
          count++;
        }
				else if (t1.edge1.equals(reverse(t2.edge4))) {
          count++;
        }

				else if (t1.edge2.equals(t2.edge1)) {
          count++;
        }
				else if (t1.edge2.equals(t2.edge2)) {
          count++;
        }
				else if (t1.edge2.equals(t2.edge3)) {
          count++;
        }
				else if (t1.edge2.equals(t2.edge4)) {
          count++;
        }

				else if (t1.edge2.equals(reverse(t2.edge1))) {
          count++;
        }
				else if (t1.edge2.equals(reverse(t2.edge2))) {
          count++;
        }
				else if (t1.edge2.equals(reverse(t2.edge3))) {
          count++;
        }
				else if (t1.edge2.equals(reverse(t2.edge4))) {
          count++;
        }

				else if (t1.edge3.equals(t2.edge1)) {
          count++;
        }
				else if (t1.edge3.equals(t2.edge2)) {
          count++;
        }
				else if (t1.edge3.equals(t2.edge3)) {
          count++;
        }
				else if (t1.edge3.equals(t2.edge4)) {
          count++;
        }

				else if (t1.edge3.equals(reverse(t2.edge1))) {
          count++;
        }
				else if (t1.edge3.equals(reverse(t2.edge2))) {
          count++;
        }
				else if (t1.edge3.equals(reverse(t2.edge3))) {
          count++;
        }
				else if (t1.edge3.equals(reverse(t2.edge4))) {
          count++;
        }

				else if (t1.edge4.equals(t2.edge1)) {
          count++;
        }
				else if (t1.edge4.equals(t2.edge2)) {
          count++;
        }
				else if (t1.edge4.equals(t2.edge3)) {
          count++;
        }
				else if (t1.edge4.equals(t2.edge4)) {
          count++;
        }

				else if (t1.edge4.equals(reverse(t2.edge1))) {
          count++;
        }
				else if (t1.edge4.equals(reverse(t2.edge2))) {
          count++;
        }
				else if (t1.edge4.equals(reverse(t2.edge3))) {
          count++;
        }
				else if (t1.edge4.equals(reverse(t2.edge4))) {
          count++;
        }
      }
      if(count == 2) {
        result *= t1.id;
        t1.corner = true;
      }
    }
    return result;
  }

  public static void main(String[] args) throws IOException{
    JurassicJigsaw obj = new JurassicJigsaw();
    System.out.println("Puzzle 1 solution = " + obj.getCornerProduct());
  }
}