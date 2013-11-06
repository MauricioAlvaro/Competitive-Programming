import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static HashMap<Integer, HashMap<Integer, Integer>> adjacencyList = new HashMap<Integer, HashMap<Integer, Integer>>();
	static int numberOfBrokers;

	public static void main(String[] args) /* throws FileNotFoundException */{
		// System.setIn(new FileInputStream(new File("test.txt")));

		Scanner sc = new Scanner(System.in);

		while (sc.hasNextLine()) {
			numberOfBrokers = sc.nextInt();

			if (numberOfBrokers == 0) {
				break;
			}

			// goto next line
			sc.nextLine();

			for (int i = 0; i < numberOfBrokers; i++) {
				String line = sc.nextLine();
				String[] elements = line.split(" ");
				int pairs = Integer.parseInt(elements[0]);

				for (int j = 1; j < 2 * pairs; j += 2) {
					int node = Integer.parseInt(elements[j]);
					int cost = Integer.parseInt(elements[j + 1]);

					addOrUpdateEdge(i, node - 1, cost);
				}
			}

			printResult();
			adjacencyList = new HashMap<Integer, HashMap<Integer, Integer>>();
		}
	}

	static void printResult() {
		double[][] shortestPaths = floydWarshall(numberOfBrokers);
		int lastNode = 0;
		double lastCost = Double.POSITIVE_INFINITY;

		for (int i = 0; i < shortestPaths.length; i++) {
			double lastCostInRow = shortestPaths[i][0];

			for (int j = 0; j < shortestPaths[i].length; j++) {
				if (shortestPaths[i][j] > lastCostInRow) {
					lastCostInRow = shortestPaths[i][j];
				}
			}

			if (lastCostInRow < lastCost) {
				lastCost = lastCostInRow;
				lastNode = i;
			}
		}

		if (lastCost == Double.POSITIVE_INFINITY) {
			System.out.println("disjoint");
		} else {
			System.out.println((lastNode + 1) + " " + (int) lastCost);
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

	static double[][] floydWarshall(int n) {
		double[][] distances = new double[n][n];

		// initialize the distances matrix from the adjacencyList
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					distances[i][j] = 0;
				} else {
					if (adjacencyList.containsKey(i)) {
						if (adjacencyList.get(i).containsKey(j)) {
							distances[i][j] = adjacencyList.get(i).get(j);
						} else {
							distances[i][j] = Double.POSITIVE_INFINITY;
						}
					} else {
						distances[i][j] = Double.POSITIVE_INFINITY;
					}
				}
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
				}
			}
		}

		return distances;
	}

}
