package day2;

import java.util.regex.*;
import java.io.*;
public class passwordPhilosophy{
  public int puzzle1() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day2/passwordPolicy.list"));
    int result = 0;
    String line;
    String patternString = "(\\d*)[-](\\d*)[\\s]([a-z])[:][\\s]([a-z]*).*";
    Pattern pattern = Pattern.compile(patternString);
    while((line = br.readLine()) != null) {
      Matcher match = pattern.matcher(line);
      match.find();
      int count = 0;
      int low = Integer.parseInt(match.group(1));
      int high = Integer.parseInt(match.group(2));
      char letter = match.group(3).charAt(0);
      String password = match.group(4);
      for(int i = 0; i < password.length(); i++) {
        if(password.charAt(i) == letter) {
          count += 1;
        }
      }
      if(count >= low && count <= high) {
        result += 1;
      }
    }
    return result;
  }
  public int puzzle2() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day2/passwordPolicy.list"));
    int result = 0;
    String line;
    String patternString = "(\\d*)[-](\\d*)[\\s]([a-z])[:][\\s]([a-z]*).*";
    Pattern pattern = Pattern.compile(patternString);
    while((line = br.readLine()) != null) {
      Matcher match = pattern.matcher(line);
      match.find();
      int num1 = Integer.parseInt(match.group(1)) - 1;
      int num2 = Integer.parseInt(match.group(2)) - 1;
      char letter = match.group(3).charAt(0);
      String password = match.group(4);
      if((password.charAt(num1) == letter && password.charAt(num2) != letter) || (password.charAt(num1) != letter && password.charAt(num2) == letter)) {
        result += 1;
      }
    }
    return result;
  }
  public static void main(String[] args) throws IOException {
    passwordPhilosophy obj = new passwordPhilosophy();
    System.out.println(obj.puzzle1());
    System.out.println(obj.puzzle2());
  }
}
