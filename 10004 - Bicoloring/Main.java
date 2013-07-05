import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {

	private static final int EMPTY = 0;
	private static final int COLOR1 = 1;
	private static final int COLOR2 = 2;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line;

		while (sc.hasNextLine()) {
			line = sc.nextLine();
			String[] numbers = line.split(" ");

			if (numbers.length == 1) {
				int nodes = Integer.parseInt(numbers[0]);

				if (nodes == 0)
					break;

				int edges = Integer.parseInt(sc.nextLine());
				int[][] graph = new int[nodes][nodes];
				int[] coloring = new int[nodes];

				for (int i = 0; i < edges; i++) {
					numbers = sc.nextLine().split(" ");
					int head = Integer.parseInt(numbers[0]);
					int tail = Integer.parseInt(numbers[1]);

					graph[head][tail] = 1;
					graph[tail][head] = 1;
				}

				if (bicoloring(graph, coloring)) {
					System.out.println("BICOLORABLE.");
				} else {
					System.out.println("NOT BICOLORABLE.");
				}
			}
		}

	}

	private static boolean bicoloring(int[][] graph, int[] coloring) {
		Queue<Integer> frontier = new LinkedList<Integer>();
		frontier.add(0);

		while (!frontier.isEmpty()) {
			int current = frontier.remove().intValue();

			if (coloring[current] == EMPTY) {
				coloring[current] = COLOR1;
			}

			int currentColor = coloring[current];
			int oppositeColor = (currentColor == COLOR1) ? COLOR2 : COLOR1;

			for (int i = 0; i < graph[current].length; i++) {
				if (graph[current][i] == 1) {
					if (coloring[i] == currentColor) {
						return false;
					} else if (coloring[i] == EMPTY) {
						coloring[i] = oppositeColor;
						frontier.add(i);
					}
				}
			}
		}

		return true;
	}

}
