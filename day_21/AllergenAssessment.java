// https://adventofcode.com/2020/day/21
package day_21;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
public class AllergenAssessment {
  int getNoAllergen() throws IOException{
    int result = 0;
    BufferedReader br = new BufferedReader(new FileReader("day_21/allergen.list"));
    Set<String> allergens = new HashSet<String>();
    Set<String> ingredients = new HashSet<String>();
    List<String> lines = new ArrayList<String>();
    Map<String, Integer> count = new HashMap<String, Integer>();
    String line;
    while((line = br.readLine()) != null) {
      lines.add(line);
      String[] arr = line.substring(0, line.length() - 1).split(" \\(contains ");
      String[] ing = arr[0].split(" ");
      String[] all = arr[1].split(", ");
      for(String ingredient: ing) {
        ingredients.add(ingredient);
        if(!count.containsKey(ingredient)) {
          count.put(ingredient, 0);
        }
        count.put(ingredient, count.get(ingredient) + 1);
      }
      for(String allergen: all) {
        allergens.add(allergen);
      }
    }
    br.close();
    Map<String, Set<String>> possibleMap = new HashMap<String, Set<String>>();
    for(String allergen: allergens) {
      possibleMap.put(allergen, new HashSet<String>(ingredients));
    }
    for(String l: lines) {
      String[] arr = l.substring(0, l.length() - 1).split(" \\(contains ");
      Set<String> ing = new HashSet<String>(Arrays.asList(arr[0].split(" ")));
      Set<String> all = new HashSet<String>(Arrays.asList(arr[1].split(", ")));
      for(String allergen: all) {
        for(String ingredient: ingredients) {
          if(!ing.contains(ingredient)) {
            possibleMap.get(allergen).remove(ingredient);
          }
        }
      }
    }
    System.out.println(possibleMap);
    for(Set<String> ings: possibleMap.values()) {
      for(String ing: ings) {
        count.remove(ing);
      }
    }
    // System.out.println(count);
    result = count.values().stream().reduce(Integer::sum).get();
    return result;
  }

  public static void main(String[] args) throws IOException{
    AllergenAssessment obj = new AllergenAssessment();
    System.out.println(obj.getNoAllergen());
  }
}
