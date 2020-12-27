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
        tileType.put(key, true);
        x = x - 2;
        key = x + "," + y;
        if(!tileType.containsKey(key)) {
          tileType.put(key, false);
        }
        x = x + 2;
        key = x + "," + y;
        if(!tileType.containsKey(key)) {
          tileType.put(key, false);
        }
        x = x - 1;
        y = y - 1;
        key = x + "," + y;
        if(!tileType.containsKey(key)) {
          tileType.put(key, false);
        }
        x = x - 1;
        y = y + 1;
        key = x + "," + y;
        if(!tileType.containsKey(key)) {
          tileType.put(key, false);
        }
        x = x + 1;
        y = y + 1;
        key = x + "," + y;
        if(!tileType.containsKey(key)) {
          tileType.put(key, false);
        }
        x = x + 1;
        y = y - 1;
        key = x + "," + y;
        if(!tileType.containsKey(key)) {
          tileType.put(key, false);
        }
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

  int blackSideCountAfterChanges(int iterations) {
    for(int i = 0; i < iterations; i++) {
      Map<String, Boolean> tileTypeCopy = new HashMap<String, Boolean>(tileType);
      for(String key: tileTypeCopy.keySet()) {
        int xc = Integer.parseInt(key.split(",")[0]);
        int yc = Integer.parseInt(key.split(",")[1]);
        if(tileTypeCopy.get(key)) {
          int count = 0;
          String keyCopy = key;
          int x = xc - 2;
          key = x + "," + yc;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }
          x = xc + 2;
          key = x + "," + yc;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }
          x = xc - 1;
          int y = yc - 1;
          key = x + "," + y;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }
          x = xc - 1;
          y = yc + 1;
          key = x + "," + y;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }
          x = xc + 1;
          y = yc + 1;
          key = x + "," + y;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }
          x = xc + 1;
          y = yc - 1;
          key = x + "," + y;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }

          if(count == 0 || count > 2) {
            tileType.put(keyCopy, false);
          }
        }
        else {
          int count = 0;
          String keyCopy = key;
          int x = xc - 2;
          key = x + "," + yc;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }
          x = xc + 2;
          key = x + "," + yc;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }
          x = xc - 1;
          int y = yc - 1;
          key = x + "," + y;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }
          x = xc - 1;
          y = yc + 1;
          key = x + "," + y;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }
          x = xc + 1;
          y = yc + 1;
          key = x + "," + y;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }
          x = xc + 1;
          y = yc - 1;
          key = x + "," + y;
          if(!tileTypeCopy.containsKey(key)) {
            tileType.put(key, false);
          }
          else {
            count += (tileTypeCopy.get(key)) ? 1 : 0;
          }

          if(count == 2) {
            tileType.put(keyCopy, true);
          }
        }
      }
    }
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
    System.out.println(obj.blackSideCountAfterChanges(1));
    System.out.println(tileType);
  }
}
