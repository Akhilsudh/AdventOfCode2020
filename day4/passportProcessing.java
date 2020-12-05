package day4;

import java.io.*;
import java.util.*;
import java.util.regex.*;
public class passportProcessing {
  public int processPassportsPuzzle1(String filename) throws IOException {
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
      if(flag) {
        result = result + 1;
      }
      flag = true;
      keys = null;
      keys = new HashSet<String>();
      passport  = "";
    }
    br.close();
    return result;
  }
  public int processPassportsPuzzle2(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));
    int result = 0;
    Map<String, String> map = new HashMap<String, String>();
    String line;
    boolean flag = true;
    String[] eyeColour = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"}; 
    String passport = "";
    while((line = br.readLine()) != null) {
      if(line.length() != 0) {
        passport = passport + " " + line;
        continue;
      }
      String[] properties = passport.split("\\s+");
      for(String property: properties) {
        if(property.length() != 0) {
          map.put(property.split(":")[0], property.split(":")[1]);
        }
      }
      if(flag && map.keySet().contains("byr")) {
        if(!(Integer.parseInt(map.get("byr")) >= 1920 && Integer.parseInt(map.get("byr")) <= 2002)) {
          flag = false;
        }
      }
      else{
        flag = false;
      }
      if(flag && map.keySet().contains("iyr")) {
        if(!(Integer.parseInt(map.get("iyr")) >= 2010 && Integer.parseInt(map.get("iyr")) <= 2020)) {
          flag = false;
        }
      }
      else{
        flag = false;
      }
      if(flag && map.keySet().contains("eyr")) {
        if(!(Integer.parseInt(map.get("eyr")) >= 2020 && Integer.parseInt(map.get("eyr")) <= 2030)) {
          flag = false;
        }
      }
      else{
        flag = false;
      }

      if(flag && map.keySet().contains("hgt")) {
        String height = map.get("hgt");
        String pattern = "^(\\d+)(cm|in)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(height);
        if (m.find()) {
          int val = Integer.parseInt(m.group(1));
          if(!((val >= 150 && val <= 193 && m.group(2).equals("cm")) || (val >= 59 && val <= 76 && m.group(2).equals("in")))) {
            flag = false;
          }
        }
        else {
          flag = false;
        }
      }
      else{
        flag = false;
      }
      if(flag && map.keySet().contains("hcl")) {
        String hair = map.get("hcl");
        String pattern = "^#([a-f0-9]+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(hair);
        if (m.find()) {
          String colour = m.group(1);
          if(!(colour.length() == 6 && hair.length() == 7)) {
      
            flag = false;
          }
        }
        else {
          flag = false;
        }
      }
      else{
        flag = false;
      }
      if(flag && map.keySet().contains("ecl")) {
        String eye = map.get("ecl");
        flag = false;
        for(String colour: eyeColour) {
          if(eye.equals(colour)) {
            flag = true;
            break;
          }
        }
  
      }
      else{
  
        flag = false;
      }
      if(flag && map.keySet().contains("pid")) {
        String pid = map.get("pid");
        String pattern = "^[0-9]{9}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(pid);
        if (!m.find()) {
          flag = false;
        }
      }
      else{
        flag = false;
      }
      if(flag) {
        result = result + 1;
      }
      flag = true;
      map = null;
      map = new HashMap<String, String>();
      passport  = "";
    }
    br.close();
    return result;
  }
  public static void main(String[] args) throws IOException {
    passportProcessing obj = new passportProcessing();
    System.out.println("Puzzle 1 solution = " + obj.processPassportsPuzzle1("day4/passport.list"));
    System.out.println("Puzzle 2 solution = " + obj.processPassportsPuzzle2("day4/passport.list"));
  }
}
