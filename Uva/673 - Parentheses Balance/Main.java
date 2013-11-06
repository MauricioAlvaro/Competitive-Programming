import java.util.Scanner;
import java.util.Stack;

public class Main {

	static final Integer PAREN = new Integer(0);
	static final Integer BRACKET = new Integer(1);

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine().trim());

		StringBuilder out = new StringBuilder(n);

		while (sc.hasNextLine()) {
			String line = sc.nextLine();

			if (balance(line)) {
				out.append("Yes\n");
			} else {
				out.append("No\n");
			}
		}

		// delete the last '\n'
		out.deleteCharAt(out.length() - 1);
		System.out.println(out.toString());
	}

	private static boolean balance(String line) {
		char[] chars = line.toCharArray();
		Stack<Integer> stack = new Stack<Integer>();

		for (char c : chars) {
			if (c == '(') {
				stack.push(PAREN);
			} else if (c == '[') {
				stack.push(BRACKET);
			} else if (c == ')') {
				if (stack.isEmpty())
					return false;
				if (stack.pop() != PAREN)
					return false;
			} else if (c == ']') {
				if (stack.isEmpty())
					return false;
				if (stack.pop() != BRACKET)
					return false;
			}
		}

		return stack.isEmpty();
	}
}
