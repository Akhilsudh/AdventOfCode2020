// https://adventofcode.com/2020/day/24
package day_24;

import java.io.*;
import java.util.*;
public class LobbyLayout {
  static Map<String, Boolean> tileType;

  void collectTileList() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_24/tiles.list"));
    String line;
    tileType = new HashMap<String, Boolean>();
    tileType.put("0,0", false);
    while((line = br.readLine()) != null) {
      int x = 0;
      int y = 0;
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
      String key = x + "," + y;
      if(tileType.containsKey(key)) {
        tileType.put(key, !tileType.get(key));
      }
      else {
        tileType.put(x + "," + y, true);
      }
    }
    br.close();
    return;
  }

  int blackSideCount() {
    int count = 0;
    for(boolean side: tileType.values()) {
      if(side) {
        count += 1;
      }
    }
    return count;
  }

  public static void main(String[] args) throws IOException {
    LobbyLayout obj = new LobbyLayout();
    obj.collectTileList();
    System.out.println(obj.blackSideCount());
    System.out.println(tileType);
  }
}
