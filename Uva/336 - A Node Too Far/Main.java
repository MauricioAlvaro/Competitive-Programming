import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {

	static HashMap<Integer, HashMap<Integer, Integer>> adjacencyList = new HashMap<Integer, HashMap<Integer, Integer>>();

	public static void main(String[] args) /* throws FileNotFoundException */{
		// System.setIn(new FileInputStream("test2.txt"));

		Scanner sc = new Scanner(System.in);
		int j = 0;

		while (sc.hasNextLine()) {
			int nc = sc.nextInt();
			if (nc == 0)
				break;

			for (int i = 0; i < nc; i++) {
				int head = sc.nextInt();
				int tail = sc.nextInt();
				addOrUpdateEdge(head, tail, 1);
				addOrUpdateEdge(tail, head, 1);
			}

			int initialNode = sc.nextInt();
			int ttl = sc.nextInt();

			while (!(initialNode == 0 && ttl == 0)) {
				int notReachable = adjacencyList.size() - reachable(initialNode, ttl);
				System.out.println("Case " + ++j + ": " + notReachable + " nodes not reachable from node "
						+ initialNode + " with TTL = " + ttl + ".");
				initialNode = sc.nextInt();
				ttl = sc.nextInt();
			}

			adjacencyList = new HashMap<Integer, HashMap<Integer, Integer>>();
		}
	}

	static void addOrUpdateEdge(int from, int to, int weight) {
		if (adjacencyList.containsKey(from)) {
			adjacencyList.get(from).put(to, weight);
		} else {
			HashMap<Integer, Integer> edges = new HashMap<Integer, Integer>();
			edges.put(to, weight);
			adjacencyList.put(from, edges);
		}
	}

	static Set<Entry<Integer, Integer>> neighborsAndWeight(int node) {
		Set<Entry<Integer, Integer>> neighborsAndWeight = new HashSet<Entry<Integer, Integer>>();
		if (adjacencyList.containsKey(node)) {
			neighborsAndWeight = adjacencyList.get(node).entrySet();
		}
		return neighborsAndWeight;
	}

	static int reachable(int start, int ttl) {
		Queue<NodeWeight> frontier = new LinkedList<NodeWeight>();
		Set<Integer> visited = new HashSet<Integer>();
		frontier.add(new NodeWeight(start, 0));

		while (!frontier.isEmpty()) {
			NodeWeight current = frontier.poll();
			visited.add(current.node);

			for (Entry<Integer, Integer> nw : neighborsAndWeight(current.node)) {
				int node = nw.getKey();
				int cost = nw.getValue() + current.weight;
				if (!visited.contains(node) && cost <= ttl) {
					frontier.add(new NodeWeight(node, cost));
				}
			}
		}

		// System.out.println(visited.size());
		return visited.size();
	}

	static class NodeWeight {
		public int node;
		public int weight;

		public NodeWeight(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}

}
