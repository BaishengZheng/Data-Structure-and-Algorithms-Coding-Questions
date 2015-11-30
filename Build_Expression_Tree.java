import java.io.*;
import java.util.*;

/*
 * A simplied solution to build expression tree. In order to build the tree: we should have restrictions:
 *    Every number will be surrounded by parentheses.
 *    Every operator with its operands will be enclosed in parentheses.
 *    Of course, parsing can be done with theses restrictions 
 *       => But the code is more complicated
 *    Thus, the expression (35-3*(3+2))/4 becomes (((35)-((3)*((3)+(2))))/(4))
 *
 */

class Solution {
    public static void main(String[] args) {
       // A test expression:
        String s = "(((35)-((3)*((3)+(2))))/(4))";

        // Create a parser instance.
        ExpressionTree expTree = new ExpressionTree();

        // Parse the expression (which creates the tree).
        expTree.parseExpression(s);

        // Compute the value and print.
        int v = expTree.computeValue();
        System.out.println(s + " = " + v);
    }  
}

class ExprTreeNode {
    ExprTreeNode left, right;   // The left/right child pointers.
    boolean isLeaf;             // Is this a leaf node?
    int value;                  // If so, we'll store the number here.
    char op;                    // If not, we need to know which operator.
}

class ExpressionTree {

    private ExprTreeNode root;

    public void parseExpression(String expr) {
        root = parse(expr);
    }
    
    // This is the recursive method that does the parsing.
    
    private ExprTreeNode parse(String expr) {
        ExprTreeNode node = new ExprTreeNode();
        
        // Note: expr.charAt(0) is a left paren
        // First, find the corresponding matching right paren.
        int m = findMatchingRightParen(expr, 1); // start from index 1
        String leftExpr = expr.substring(1, m+1);

        // Recursive stop condition:
        if (m == expr.length()-1) {
            // It's at the other end => this is a leaf. For example, when evaluate "(36)"
            String operandStr = expr.substring(1, expr.length()-1);
            node.isLeaf = true;
            node.value = getValue(operandStr);
            return node;
        }

        // Otherwise, there's a second operand and an operator.

        // Find the left paren to match the rightmost right paren.
        int n = findMatchingLeftParen(expr, expr.length()-2);// start from index expr.length()-2
        String rightExpr = expr.substring(n, expr.length()-1);

        // Recursively parse the left and right substrings.
        node.left = parse(leftExpr);
        node.right = parse(rightExpr);
        node.op = expr.charAt(m+1);

        return node;
    }
    

    private int findMatchingRightParen(String s, int leftPos) {
        // Given the position of a left-paren in String s,
        // find the matching right paren.
        // Recognize the code?

        Stack<Character> stack = new Stack<Character>();
        stack.push(s.charAt(leftPos));
        for (int i=leftPos+1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    // This is the one.
                    return i;
                }
            }
        }
        // If we reach here, there's an error.
        System.out.println("ERROR: findRight: s=" + s + " left=" + leftPos);
        return -1;
    }


    private int findMatchingLeftParen(String s, int rightPos) {
        // ... similar ...
        Stack<Character> stack = new Stack<Character>();
        stack.push(s.charAt(rightPos));
        for (int i=rightPos-1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == ')') {
                stack.push(ch);
            }
            else if (ch == '(') {
                stack.pop();
                if (stack.isEmpty()) {
                    // This is the one.
                    return i;
                }
            }
        }
        // If we reach here, there's an error.
        System.out.println("ERROR: findLeft: s=" + s + " right=" + rightPos);
        return -1;
    }

    private int getValue(String s) {
        try {
            int k = Integer.parseInt(s);
            return k;
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public int computeValue() {
        return compute(root);
    }

    private int compute(ExprTreeNode node) {
        if (node.isLeaf) {
            return node.value;
        }

        // Otherwise do left and right, and add.
        int leftValue = compute(node.left);
        int rightValue = compute(node.right);

        if (node.op == '+') {
            return leftValue + rightValue;
        }
        else if (node.op == '-') {
            return leftValue - rightValue;
        }
        else if (node.op == '*') {
            return leftValue * rightValue;
        }
        else {
            return leftValue / rightValue;
        }
    }

} // end-ExpressionTree
