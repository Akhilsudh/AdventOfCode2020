package day_13;

import java.io.*;
import java.util.*;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;
public class shuttleSearch {
  private static class Bus {
    long id;
    int index;
    public Bus(long id, int index) {
      this.id = id;
      this.index = index;
    }
    public boolean fitsSchedule(long timestamp) {
      return ((timestamp + index) % id) == 0;
    }
  }
  public int getBus() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_13/bus.details"));
    int result = 0;
    int timestamp = Integer.parseInt(br.readLine());
    String[] busScheduleString = br.readLine().split(",");
    br.close();
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
  public long getConsecutiveTime() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_13/bus.details"));
    long result = 0;
    br.readLine();
    String[] busScheduleString = br.readLine().split(",");
    br.close();
    List<Bus> bus = new ArrayList<Bus>();
    for(int i = 0; i < busScheduleString.length; i++) {
      if(busScheduleString[i].equals("x")) {
        continue;
      }
      bus.add(new Bus(Long.parseLong(busScheduleString[i]), i));
    }
    result = bus.get(0).id;
    while(true) {
      int count = 0;
      for(int i = 0; i < bus.size(); i++) {
        if(bus.get(i).fitsSchedule(result)) {
          count += 1;
        }
      }
      if(count == bus.size()) {
        break;
      }
      result += bus.get(0).id;
    }
    return result;
  }
  public long part2() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("day_13/bus.details"));
    br.readLine();
    String[] s = br.readLine().split(",");
    br.close();
    long[][] nums = range(0, s.length).filter(i -> !s[i].equals("x"))
            .mapToObj(i -> new long[]{parseLong(s[i]), i})
            .toArray(long[][]::new);
    long product = stream(nums).mapToLong(a -> a[0]).reduce((a, b) -> a * b).getAsLong();
    long sum = stream(nums).mapToLong(a -> a[1] * (product/a[0]) * inverseModulo(product/a[0], a[0])).sum();
    return product - sum % product;
  }

  long inverseModulo(long x, long y){
    if(x!=0){
        long modulo = y % x;
        return modulo == 0 ? 1 : y - inverseModulo(modulo, x) * y / x;
    }
    return 0;
  }
  public static void main(String[] args) throws IOException{
    shuttleSearch obj = new shuttleSearch();
    System.out.println("Puzzle 1 solution = " + obj.getBus());
    System.out.println("Puzzle 2 solution = " + obj.part2());
  }
}
