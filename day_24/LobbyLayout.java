// https://adventofcode.com/2020/day/24
package day_24;

import java.io.*;
public class LobbyLayout {
  static boolean[][] tileType;
  // static Map<String, Boolean> tileType;

  void collectTileList() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_24/tiles.list"));
    String line;
    tileType = new boolean[500][500];
    int referenceX = 500/2;
    int referenceY = 500/2;
    while((line = br.readLine()) != null) {
      int x = referenceX;
      int y = referenceY;
      for(int i = 0; i < line.length(); i++) {
        char coord = line.charAt(i);
        if(coord == 'n') {
          i++;
          coord = line.charAt(i);
          if(coord == 'e') {
            x += 1;
            y += 1;
          }
          else {
            x -= 1;
            y += 1;
          }
        }
        else if(coord == 's') {
          i++;
          coord = line.charAt(i);
          if(coord == 'e') {
            x += 1;
            y -= 1;
          }
          else {
            x -= 1;
            y -= 1;
          }
        }
        else if(coord == 'e') {
          x += 2;
        }
        else {
          x -= 2;
        }
      }
      tileType[x][y] = !tileType[x][y];
    }
    br.close();
    return;
  }

  int blackSideCount() {
    int count = 0;
    for(int i = 0; i < tileType.length; i++) {
      for(int j = 0; j < tileType[i].length; j++) {
        if(tileType[i][j]) {
          count += 1;
        }
      }
    }
    return count;
  }

  int getBlackNeighbourCount(boolean[][] tileTypeCopy, int i, int j) {
    int count  = 0;
    int x = i - 2;
    int y = j;
    if(x >= 0 && tileTypeCopy[x][y]) {
      count++;
    }
    x = i + 2;
    if(x < tileTypeCopy.length && tileTypeCopy[x][y]) {
      count++;
    }
    x = i - 1;
    y = j - 1;
    if(y >= 0 && x >= 0 && tileTypeCopy[x][y]) {
      count++;
    }
    x = i - 1;
    y = j + 1;
    if(y < tileTypeCopy[i].length && x >= 0 && tileTypeCopy[x][y]) {
      count++;
    }
    x = i + 1;
    y = j + 1;
    if(y < tileTypeCopy[i].length && x < tileTypeCopy.length && tileTypeCopy[x][y]) {
      count++;
    }
    x = i + 1;
    y = j - 1;
    if(y >= 0 && x < tileTypeCopy.length && tileTypeCopy[x][y]) {
      count++;
    }
    return count;
  }

  int blackSideCountAfterChanges(int iterations) {
    for(int iter = 0; iter < iterations; iter++) {
      boolean[][] tileTypeCopy = new boolean[500][500];
      for(int i = 0; i < tileType.length; i++) {
        for(int j = 0; j < tileType[i].length; j++) {
          tileTypeCopy[i][j] = tileType[i][j];
        }
      }
      for(int i = 0; i < tileTypeCopy.length; i++) {
        for(int j = 0; j < tileTypeCopy[i].length; j++) {
          int count = getBlackNeighbourCount(tileTypeCopy, i, j);
          if(tileTypeCopy[i][j] && (count == 0 || count > 2)) {
            tileType[i][j] = false;
          }
          else if(count == 2) {
            tileType[i][j] = true;
          }
        }
      }
    }
    return blackSideCount();
  }

  public static void main(String[] args) throws IOException {
    LobbyLayout obj = new LobbyLayout();
    obj.collectTileList();
    System.out.println("Puzzle 1 solution = " + obj.blackSideCount());
    System.out.println("Puzzle 2 solution = " + obj.blackSideCountAfterChanges(100));
  }
}
