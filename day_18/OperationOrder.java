// https://adventofcode.com/2020/day/18
package day_18;

import java.io.*;
import java.util.*;
public class OperationOrder {
  long applyOperation(long a, long b, char operator) {
    switch(operator){
      case '+':
        return a + b;
      case '*':
        return a * b;
    }
    return 0;
  }

  long solveExpression(String expression) {
    Stack<Long> operands = new Stack<Long>();
    Stack<Character> operators = new Stack<Character>();
    int index = expression.length() - 1;
    String number = "";
    boolean pushFlag = false;
    do {
      char token = expression.charAt(index);
      if(token == ' ') {
        if(pushFlag) {
          operands.push(Long.parseLong(number));
          pushFlag = false;
          number = "";
        }
      }
      else if(token == '(') {
        if(pushFlag) {
          operands.push(Long.parseLong(number));
          pushFlag = false;
          number = "";
        }
        char operator;
        while((operator = operators.pop()) != ')') {
          long a = operands.pop();
          long b = operands.pop();
          operands.push(applyOperation(a, b, operator));
        }
      }
      else if(token == '+' || token == '*' || token == ')') {
        operators.push(token);
      }
      else if(token >= '0' && token <= '9') {
        number = token + number;
        pushFlag = true;
        if(index == 0) {
          operands.push(Long.parseLong(number));
        }
      }
      index --;
    } while(index >= 0);
    long result = 0;
    while(!operators.empty()) {
      long a = operands.pop();
      long b = operands.pop();
      char operator = operators.pop();
      result = applyOperation(a, b, operator);
      operands.push(result);
    }
    return result;
  }

  public long solve(boolean precedence) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_18/operations.list"));
    String line;
    long result = 0;
    while((line = br.readLine()) != null) {
      result += (precedence) ? solveExpression(line) : solveExpression(line);
    }
    br.close();
    return result;
  }

  public static void main(String[] args) throws IOException{
    OperationOrder obj = new OperationOrder();
    System.out.println("Puzzle 1 solution = " + obj.solve(false));
    // System.out.println("Puzzle 2 solution = " + obj.solve(true));
  }
}
