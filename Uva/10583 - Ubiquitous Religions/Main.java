import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) /* throws FileNotFoundException */{
		// System.setIn(new FileInputStream(new File("test.txt")));

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		int testCase = 0;

		long start = System.currentTimeMillis();
		while (!(m == 0 && n == 0)) {
			UnionFind uf = new UnionFind(n);

			for (int i = 0; i < m; i++) {
				int student1 = sc.nextInt();
				int student2 = sc.nextInt();
				uf.union(student1 - 1, student2 - 1);
			}

			System.out.printf("Case %d: %d\n", ++testCase, uf.getNumberOfSets());
			n = sc.nextInt();
			m = sc.nextInt();
		}
		// System.out.println(System.currentTimeMillis() - start);
	}

}

class UnionFind {
	/*
	 * Weighted Quick Union with path compression implementation of the
	 * UnionFind data structure (also known as Disjoint Set).
	 */

	private int[] parents;
	private int[] sizes;
	private int count;

	public UnionFind(int n) {
		parents = new int[n];
		sizes = new int[n];
		count = n;

		for (int i = 0; i < n; i++) {
			parents[i] = i;
			sizes[i] = 1;
		}
	}

	public int getNumberOfSets() {
		return count;
	}

	public int find(int x) {
		ArrayList<Integer> nodes = new ArrayList<Integer>();

		while (x != parents[x]) {
			nodes.add(x);
			x = parents[x];
		}

		// path compression
		for (Integer node : nodes) {
			parents[node] = x;
			sizes[x]--;
		}

		return x;
	}

	public void union(int x, int y) {
		int px = find(x);
		int py = find(y);

		if (px == py)
			return;

		count--;

		if (sizes[px] < sizes[py]) {
			parents[px] = py;
			sizes[py] += sizes[px];
		} else {
			parents[py] = px;
			sizes[px] += sizes[py];
		}

	}
}
