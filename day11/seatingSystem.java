package day11;

import java.io.*;
import java.util.*;
public class seatingSystem {
  public int getOccupied() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day11/seats.map"));
    int result = 0;
    String line = "";
    List<String> seatMap = new ArrayList<String>();
    while((line = br.readLine()) != null) {
      seatMap.add(line);
    }
    br.close();
    int rowSize = seatMap.get(0).length();
    int colSize = seatMap.size();
    char[][] seatArr = new char[colSize][rowSize];
    char[][] seatArrCopy = new char[colSize][rowSize];
    for(int i = 0; i < colSize; i++) {
      for(int j = 0; j < rowSize; j++) {
        seatArr[i][j] = seatMap.get(i).charAt(j);
      }
    }

    // for(int i = 0; i < colSize; i++) {
    //   for(int j = 0; j < rowSize; j++) {
    //     System.out.print(seatArr[i][j] + " ");
    //   }
    //   System.out.println();
    // }
    int occupiedSeats = 0;
    int previousOccupiedSeats = Integer.MAX_VALUE;
    while(occupiedSeats == previousOccupiedSeats) {
      for(int i = 0; i < colSize; i++) {
        for(int j = 0; j < rowSize; j++) {
          seatArrCopy[i][j] = seatArr[i][j];
        }
      }
      for(int i = 0; i < colSize; i++) {
        for(int j = 0; j < rowSize; j++) {
          if(seatArrCopy[i][j] == 'L' && 
          ((i - 1 < 0) || (seatArrCopy[i - 1][j] != '#')) && 
          ((i + 1 >= colSize) || (seatArrCopy[i + 1][j] != '#')) && 
          ((j - 1 < 0) || (seatArrCopy[i][j - 1] != '#')) && 
          ((j + 1 >= rowSize) || (seatArrCopy[i - 1][j] != '#')) &&
          ((j + 1 >= rowSize) || (j - 1 < 0) || (seatArrCopy[i - 1][j - 1] != '#'))) {
            seatArr[i][j] = '#';
            occupiedSeats += 1;
          }
          else if(seatArrCopy[i][j] == '#') {
            int seatCount = 0;
            if((i - 1 >= 0) && (seatArrCopy[i - 1][j] == '#')) {
              seatCount += 1;
            }
            if((i + 1 < colSize) && (seatArrCopy[i + 1][j] == '#')) {
              seatCount += 1;
            }
            if((j - 1 >= 0) && (seatArrCopy[i][j - 1] == '#')) {
              seatCount += 1;
            }
            if((j + 1 < rowSize) && (seatArrCopy[i][j + 1] == '#')) {
              seatCount += 1;
            }
            if((i - 1 >= 0) && (j - 1 >= 0) && (seatArrCopy[i - 1][j - 1] == '#')) {
              seatCount += 1;
            }
            if((i - 1 >= 0) && (j + 1 < rowSize) && (seatArrCopy[i - 1][j + 1] == '#')) {
              seatCount += 1;
            }
            if((i + 1 < colSize) && (j + 1 < rowSize) && (seatArrCopy[i + 1][j + 1] == '#')) {
              seatCount += 1;
            }
            if((i + 1 < colSize) && (j - 1 >= 0) && (seatArrCopy[i + 1][j - 1] == '#')) {
              seatCount += 1;
            }
            if(seatCount >= 4) {
              seatArr[i][j] = 'L';
            }
          }
        }
      }
    }
    return result;
  }
  public static void main(String[] args) throws IOException{
    seatingSystem obj = new seatingSystem();
    System.out.println("Puzzle 1 solution = " + obj.getOccupied());
  }
}
