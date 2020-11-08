import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class ImprovedRPN {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MyList numbers = new MyList();
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String[] split = input.split(" ");
        for (String str : split) {
            if (!isOperator(str)) numbers.addFirst(Integer.parseInt(str));
            else {
                int n2 = numbers.removeFirst();
                int n1 = numbers.removeFirst();
                switch (str) {
                    case "+":
                        numbers.addFirst(n1 + n2);
                        break;
                    case "-":
                        numbers.addFirst(n1 - n2);
                        break;
                    case "x":
                        numbers.addFirst(n1 * n2);
                        break;
                }
            }
        }
        System.out.println(numbers.removeFirst());
        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
    }

    public static boolean isOperator(String str) {
        if (str.equals("+") || str.equals("-") || str.equals("x")) return true;
        return false;
    }

    public static class MyList {

        private Node top;

        /**
         * Constructor for my linked list.
         */
        public MyList() {
            top = null;
        }

        /**
         * Checks if the list is empty;
         *
         * @return true if the list is empty, false otherwise.
         */
        public boolean isEmpty() {
            return top == null;
        }

        /**
         * Adds an integer at the beginning of the list.
         *
         * @param n integer to be added at the beginning.
         */
        public void addFirst(int n) {
            top = new Node(n, top);
        }

        /**
         * Adds an integer at the end of the list.
         *
         * @param n integer to be added at the end.
         */
        public void addLast(int n) {
            if (isEmpty())
                addFirst(n);
            else
                top.push(n);
        }

        /**
         * Removes the first Node in the list.
         *
         * @return the value of the removed Node.
         */
        public int removeFirst() {
            if (!isEmpty()) {
                int value = top.getValue();
                top = top.getNext();
                return value;
            } else {
                throw new IllegalStateException("Empty stack");
            }
        }

        /**
         * Removes the last Node in the list.
         *
         * @return the value of the removed Node.
         */
        public int removeLast() {
            if (!isEmpty()) {
                if (top.getNext() == null) {
                    int value = top.getValue();
                    top = null;
                    return value;
                } else {
                    return top.removeLast();
                }
            } else {
                throw new IllegalStateException("Empty stack");
            }
        }

        private class Node {
            private int value;
            private Node next;

            /**
             * Constructor for inner class Node.
             * A node has an integer value and a pointer to the next Node.
             *
             * @param value integer value of node.
             * @param next  pointer to the next Node.
             */
            public Node(int value, Node next) {
                this.value = value;
                this.next = next;
            }

            /**
             * Get node integer value.
             *
             * @return value.
             */
            public int getValue() {
                return value;
            }

            /**
             * Get pointer to next node.
             *
             * @return next node.
             */
            public Node getNext() {
                return next;
            }

            /**
             * Recursively push a value to the end of the list.
             *
             * @param value integer to be added.
             */
            public void push(int value) {
                if (next == null)
                    next = new Node(value, null);
                else
                    next.push(value);
            }

            /**
             * Removes last Node.
             */
            public int removeLast() {
                int value = 0;
                if (next.getNext() == null) {
                    value = next.getValue();
                    next = null;
                    return value;
                } else {
                    return value + next.removeLast();
                }

            }
        }

    }

}

