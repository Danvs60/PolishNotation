import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class ReversePolishNotation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        NPNBinaryTree tree = new NPNBinaryTree();
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String[] split = input.split(" ");
        for (int i = split.length - 1; i >= 0; i--) {
            tree.push(split[i]);
        }
        for(String str : split)
            tree.push(str);
        System.out.println(tree.runNPN());
        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
    }
    public static class NPNBinaryTree {
        private Node top;

        public NPNBinaryTree() {
            top = null;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public int runNPN() {
            return top.runNPN();
        }

        public void push(String value) {
            if (isEmpty()) top = new Node(value);
            else top.push(value);
        }

        private class Node {
            private String value;
            private Node left;
            private Node right;
            private int id = 0;

            public Node(String value) {
                this.value = value;
                this.left = null;
                this.right = null;
                if (isOperator(value)) this.id = 2;
                else this.id = 1;
            }

            public boolean isOperator(String op) {
                if (op.equals("+") || op.equals("-") || op.equals("x") || op.equals("/")) return true;
                return false;
            }

            public boolean isDone() {
                if (this.id == 1) return true;
                else if (left != null && right != null && left.isDone() && right.isDone()) return true;
                else return false;
            }

            public void push(String value) {
                if (left != null && (!left.isDone())) left.push(value);
                else if (right != null && (!right.isDone())) right.push(value);
                else if (left == null) left = new Node(value);
                else if (right == null) right = new Node(value);
            }

            public int runNPN() {
                switch (value) {
                    case "+":
                        return (right.runNPN() + left.runNPN());
                    case "-":
                        return (right.runNPN() - left.runNPN());
                    case "x":
                        return (right.runNPN() * left.runNPN());
                }
                return Integer.parseInt(value);
            }
        }
    }
}
