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
    do {
      char token = expression.charAt(index);
      if(token == '(') {
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
        int endindex = index;
        while(index >= 0 && expression.charAt(index) >= '0' && expression.charAt(index) <= '9') {
          index --;
        }
        index ++;
        operands.push(Long.parseLong(expression.substring(index, endindex + 1)));
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

  long solveExpressionWithPrecedence(String expression) {
    Stack<Long> operands = new Stack<Long>();
    Stack<Character> operators = new Stack<Character>();
    int index = expression.length() - 1;
    do {
      char token = expression.charAt(index);
      if(token == '(') {
        char operator;
        while((operator = operators.pop()) != ')') {
          if(operator == '*') {
            if(!operators.empty() && operators.peek() == '+') {
              long a = operands.pop();
              long b = operands.pop();
              long c = operands.pop();
              operands.push(applyOperation(b, c, operators.pop()));
              operands.push(a);
              operators.push(operator);
              continue;
            }
          }
          operands.push(applyOperation(operands.pop(), operands.pop(), operator));
        }
      }
      else if(token == '+' || token == '*' || token == ')') {
        operators.push(token);
      }
      else if(token >= '0' && token <= '9') {
        int endindex = index;
        while(index >= 0 && expression.charAt(index) >= '0' && expression.charAt(index) <= '9') {
          index --;
        }
        index ++;
        operands.push(Long.parseLong(expression.substring(index, endindex + 1)));
      }
      index --;
    } while(index >= 0);
    long result = 0;
    while(!operators.empty()) {
      char operator = operators.pop();
      if(operator == '*') {
        if(!operators.empty() && operators.peek() == '+') {
          long a = operands.pop();
          long b = operands.pop();
          long c = operands.pop();
          result = applyOperation(b, c, operators.pop());
          operands.push(result);
          operands.push(a);
          operators.push(operator);
          continue;
        }
      }
      result = applyOperation(operands.pop(), operands.pop(), operator);
      operands.push(result);
    }
    return result;
  }

  public long solve(boolean precedence) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("day_18/operations.list"));
    String line;
    long result = 0;
    while((line = br.readLine()) != null) {
      result += (precedence) ? solveExpressionWithPrecedence(line) : solveExpression(line);
    }
    br.close();
    return result;
  }

  public static void main(String[] args) throws IOException{
    OperationOrder obj = new OperationOrder();
    System.out.println("Puzzle 1 solution = " + obj.solve(false));
    System.out.println("Puzzle 2 solution = " + obj.solve(true));
  }
}
