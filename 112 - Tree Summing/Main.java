import java.util.AbstractMap.SimpleEntry;
import java.util.Scanner;
import java.util.Stack;

class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		StringBuilder sb = new StringBuilder();
		StringBuilder out = new StringBuilder();

		while (sc.hasNextLine()) {
			sb.append(sc.nextLine());
			String tree = sb.toString();

			int treeBeginIndex = tree.indexOf('(');

			if (treeBeginIndex == -1 || treeBoundary(tree) == -1) {
				continue;
			}

			int sum = Integer
					.parseInt(tree.substring(0, treeBeginIndex).trim());
			tree = tree.substring(treeBeginIndex);

			if (treeSum(tree.replaceAll("\\s", ""), sum)) {
				out.append("yes\n");
			} else {
				out.append("no\n");
			}

			sb.setLength(0);
		}
		
		out.deleteCharAt(out.length() - 1);
		System.out.println(out.toString());
	}

	public static boolean treeSum(String tree, int sum) {
		boolean retval = false;
		Stack<SimpleEntry<Integer, String>> frontier = new Stack<SimpleEntry<Integer, String>>();
		frontier.push(new SimpleEntry<Integer, String>(sum, tree));

		SimpleEntry<Integer, String> current;
		String currentTree, newTree;
		int currentSum, newSum;

		// Empty tree = ()
		if (tree.length() == 2) {
			return false;
		}

		while (!frontier.isEmpty()) {
			current = frontier.pop();
			currentSum = current.getKey();
			currentTree = current.getValue();

			// Empty tree = ()
			if (currentTree.length() == 2) {
				continue;
			}

			newSum = Integer.parseInt(currentTree.substring(1,
					currentTree.indexOf('(', 1)));
			newSum = currentSum - newSum;

			newTree = currentTree.substring(currentTree.indexOf('(', 1),
					currentTree.length() - 1);

			// leaf node = ()()
			if (newTree.equals("()()")) {
				if (newSum == 0) {
					retval = true;
					break;
				} else {
					continue;
				}
			}

			int boundary = treeBoundary(newTree);

			String leftSubtree = newTree.substring(0, boundary + 1);
			String rightSubtree = newTree.substring(boundary + 1);

			frontier.add(new SimpleEntry<Integer, String>(new Integer(newSum),
					rightSubtree));
			frontier.add(new SimpleEntry<Integer, String>(new Integer(newSum),
					leftSubtree));
		}

		return retval;
	}

	public static int treeBoundary(String tree) {
		char[] treeArray = tree.toCharArray();

		int i;
		int counter = 0;

		for (i = 0; i < treeArray.length; i++) {
			if (treeArray[i] == '(') {
				counter++;
			} else if (treeArray[i] == ')') {
				counter--;
			} else {
				continue;
			}

			if (counter == 0) {
				break;
			}
		}

		// return -1 when the string is not a valid tree
		if (counter != 0) {
			i = -1;
		}

		return i;
	}
}
