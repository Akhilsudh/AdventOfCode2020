package day4;
import java.io.*;
import java.util.*;
public class passportProcessing {
  public int processPassports(String filename) throws IOException {
    // BufferedReader r = new BufferedReader(new FileWriter(new File("day4/test.data"), true));
    
    
    
    BufferedReader br = new BufferedReader(new FileReader(filename));
    int result = 0;
    Set<String> keys = new HashSet<String>();
    String line;
    boolean flag = true;
    String[] valid = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"}; 
    String passport = "";
    while((line = br.readLine()) != null) {
      if(line.length() != 0) {
        passport = passport + " " + line;
        continue;
      }
      
      System.out.println(" ");
      System.out.println(passport);
      String[] properties = passport.split("\\s+");
      for(String property: properties) {
        if(property.length() != 0) {
          keys.add(property.split(":")[0]);
        }
      }
      for(String property: valid) {
        if(!keys.contains(property)) {
          flag = false;
          break;
        }
      }
      System.out.println(keys + " " + flag);
      if(flag) {
        result = result + 1;
      }
      flag = true;
      keys = null;
      keys = new HashSet<String>();
      System.out.println(keys);
      passport  = "";
    }
    return result;
  }
  public static void main(String[] args) throws IOException {
    passportProcessing obj = new passportProcessing();
    System.out.println(obj.processPassports("day4/passport.list"));
  }
}
