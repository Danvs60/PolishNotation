import java.util.*;

public class PolishNotation {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        ArrayList<String> solver = new ArrayList<>();
	
	for(String str : input.split(" ")){
		solver.add(str);
	}
	
        for (int j = solver.size() - 1; j >= 0; j--) {
            if (whichOperator(solver.get(j)) > 0) {
                float temp1 = Float.parseFloat(solver.remove(j + 2));
                float temp2 = Float.parseFloat(solver.remove(j + 1));
                solver.add(String.valueOf(solve(solver.remove(j), temp2, temp1)));
            }
        }
        System.out.println(solver.get(0));
    }

    public static int whichOperator(String op) {
        if (op.equals("+")) return 1;
        if (op.equals("-")) return 2;
        if (op.equals("x")) return 3;
        if (op.equals("/")) return 4;
        return 0;
    }

    public static float solve(String op, float a, float b) {
        if (whichOperator(op) == 1) {
            return a + b;
        } else if (whichOperator(op) == 2) {
            return a - b;
        } else if (whichOperator(op) == 3) {
            return a * b;
        } else if (whichOperator(op) == 4) {
            return a / b;
        }
        return 0;
    }

}

