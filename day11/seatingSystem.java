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
    int occupiedSeats = 0;
    int previousOccupiedSeats = Integer.MAX_VALUE;
    boolean flag = true;
    while(flag) {
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
          ((j + 1 >= rowSize) || (seatArrCopy[i][j + 1] != '#')) &&
          ((i - 1 < 0) || (j - 1 < 0) || (seatArrCopy[i - 1][j - 1] != '#')) &&
          ((i - 1 < 0) || (j + 1 >= rowSize) || (seatArrCopy[i - 1][j + 1] != '#')) &&
          ((i + 1 >= colSize) || (j + 1 >= rowSize) || (seatArrCopy[i + 1][j + 1] != '#')) &&
          ((i + 1 >= colSize) || (j - 1 < 0) || (seatArrCopy[i + 1][j - 1] != '#'))) {
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
              occupiedSeats -= 1;
            }
          }
        }
      }
      if(occupiedSeats == previousOccupiedSeats) {
        flag = false;
      }
      previousOccupiedSeats = occupiedSeats;
    }
    result = previousOccupiedSeats;
    return result;
  }
  public int getNewOccupied() throws IOException {
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
    boolean flag = true;
    while(flag) {
      for(int i = 0; i < colSize; i++) {
        for(int j = 0; j < rowSize; j++) {
          seatArrCopy[i][j] = seatArr[i][j];
        }
      }

      // for(int i = 0; i < colSize; i++) {
        // for(int j = 0; j < rowSize; j++) {
          // System.out.print(seatArr[i][j] + " ");
        // }
        // System.out.println();
      // }

      for(int i = 0; i < colSize; i++) {
        for(int j = 0; j < rowSize; j++) {
          if(seatArrCopy[i][j] == 'L') {
            boolean L = true, R = true, T = true, B = true, TL = true, TR = true, BL = true, BR = true;
            int a = i - 1;
            int b = j;
            while(a >= 0 && seatArrCopy[a][b] == '.') {
              a -= 1;
            }
            if(a >= 0 && seatArrCopy[a][b] == '#') {
              T = false;
            }
            
            a = i + 1; 
            b = j;
            while(a < colSize && seatArrCopy[a][b] == '.') {
              a += 1;
            }
            if(a < colSize && seatArrCopy[a][b] == '#') {
              B = false;
            }

            a = i; 
            b = j - 1;
            while(b >= 0 && seatArrCopy[a][b] == '.') {
              b -= 1;
            }
            if(b >= 0 && seatArrCopy[a][b] == '#') {
              L = false;
            }

            a = i; 
            b = j + 1;
            while(b < rowSize && seatArrCopy[a][b] == '.') {
              b += 1;
            }
            if(b < rowSize && seatArrCopy[a][b] == '#') {
              R = false;
            }

            a = i - 1;
            b = j - 1;
            while(a >= 0 && b >= 0 && seatArrCopy[a][b] == '.') {
              a -= 1;
              b -= 1;
            }
            if(a >= 0 && b >= 0 && seatArrCopy[a][b] == '#') {
              TL = false;
            }

            a = i - 1;
            b = j + 1;
            while(a >= 0 && b < rowSize && seatArrCopy[a][b] == '.') {
              a -= 1;
              b += 1;
            }
            if(a >= 0 && b < rowSize && seatArrCopy[a][b] == '#') {
              TR = false;
            }

            a = i + 1;
            b = j + 1;
            while(a < colSize && b < rowSize && seatArrCopy[a][b] == '.') {
              a += 1;
              b += 1;
            }
            if(a < colSize && b < rowSize && seatArrCopy[a][b] == '#') {
              BR = false;
            }

            a = i + 1;
            b = j - 1;
            while(a < colSize && b >= 0 && seatArrCopy[a][b] == '.') {
              a += 1;
              b += 1;
            }
            if(a < colSize && b >= 0 && seatArrCopy[a][b] == '#') {
              BL = false;
            }

            if(T && B && L && R && TL && TR && BR && BL) {
              seatArr[i][j] = '#';
              occupiedSeats += 1;
            }
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
              // System.out.println("occupied seat to be changed = " + i + " " + j);
              seatArr[i][j] = 'L';
              occupiedSeats -= 1;
            }
          }
        }
      }
      // System.out.println(occupiedSeats);
      if(occupiedSeats == previousOccupiedSeats) {
        flag = false;
      }
      previousOccupiedSeats = occupiedSeats;
    }
    result = previousOccupiedSeats;
    return result;
  }
  public static void main(String[] args) throws IOException{
    seatingSystem obj = new seatingSystem();
    System.out.println("Puzzle 1 solution = " + obj.getOccupied());
    // System.out.println("Puzzle 2 solution = " + obj.getNewOccupied());
  }
}
