package day6;
import java.io.*;
import java.util.*;
public class customCustoms {
  public int returnCount() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day6/group.list"));
    int result = 0;
    String line = "";
    Set<Character> groupLetters = new HashSet<Character>();
    while((line = br.readLine()) != null) {
      if(line.length() != 0) {
        for(int i = 0; i < line.length(); i++) {
          groupLetters.add(line.charAt(i));
        }
      }
      else {
        result = result + groupLetters.size();
        groupLetters = new HashSet<Character>();
      }
    }
    br.close();
    return result;
  }
  public static void main(String[] args) throws IOException {
    customCustoms obj = new customCustoms();
    System.out.println("Puzzle 1 solution = " + obj.returnCount());
  }
}
