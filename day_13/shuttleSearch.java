package day_13;

import java.io.*;
import java.util.*;
public class shuttleSearch {
  public int getBus() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_13/bus.details"));
    int result = 0;
    int timestamp = Integer.parseInt(br.readLine());
    String[] busScheduleString = br.readLine().split(",");
    List<Integer> busSchedule = new ArrayList<Integer>();
    for(String bus: busScheduleString) {
      if(bus.equals("x")) {
        continue;
      }
      busSchedule.add(Integer.parseInt(bus));
    }
    int min = Integer.MAX_VALUE;
    int bus = 0;
    for(int i = 0; i < busSchedule.size(); i++) {
      int sum = busSchedule.get(i);
      while(sum < timestamp) {
        sum += busSchedule.get(i);
      }
      if(sum -timestamp < min) {
        min = sum - timestamp;
        bus = busSchedule.get(i);
      }
    }
    result = min * bus;
    return result;
  }
  public static void main(String[] args) throws IOException{
    shuttleSearch obj = new shuttleSearch();
    System.out.println("Puzzle 12 solution = " + obj.getBus());
  }
}
