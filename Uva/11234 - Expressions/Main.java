import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Main {

	private static Stack<Character> stack = new Stack<Character>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());

		StringBuilder output = new StringBuilder();

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			for (char c : line.toCharArray()) {
				stack.push(c);
			}

			// handle empty input
			if (stack.isEmpty()) {
				output.append("\n");
				continue;
			}

			Node tree = buildTree();
			output.append(tree.queueExpression() + "\n");
			stack.clear();
		}

		output.deleteCharAt(output.length() - 1);
		System.out.println(output.toString());
	}

	public static Node buildTree() {
		Character c = stack.pop();
		Node leftSubtree, rightSubtree;

		if (Character.isUpperCase(c)) {
			leftSubtree = buildTree();
			rightSubtree = buildTree();
		} else {
			return new Node(c);
		}

		return new Node(c, leftSubtree, rightSubtree);
	}

}

class Node {
	Character value;
	Node leftChild;
	Node rightChild;

	Node(Character value) {
		this(value, null, null);
	}

	Node(Character value, Node leftChild, Node rightChild) {
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public String queueExpression() {
		Queue<Node> frontier = new LinkedList<Node>();
		Stack<Character> visited = new Stack<Character>();
		frontier.add(this);

		while (!frontier.isEmpty()) {
			Node current = frontier.remove();
			visited.push(current.value);

			if (current.rightChild != null) {
				frontier.add(current.rightChild);
			}

			if (current.leftChild != null) {
				frontier.add(current.leftChild);
			}
		}

		StringBuilder sb = new StringBuilder();

		while (!visited.isEmpty()) {
			sb.append(visited.pop());
		}

		return sb.toString();
	}

}
