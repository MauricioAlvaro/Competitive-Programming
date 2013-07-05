import java.util.Scanner;

class Main {

	private static int m, n, t;
	private static int[][] memo = new int[10000][2];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder output = new StringBuilder();

		while (sc.hasNextInt()) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			t = sc.nextInt();

			m = Math.min(a, b);
			n = Math.max(a, b);

			clearMemo();
			maxBurgers(t);

			output.append(memo[t][0] + memo[t][1]);

			if (t - (m * memo[t][0] + n * memo[t][1]) > 0) {
				output.append(" " + (t - (m * memo[t][0] + n * memo[t][1])));
			}

			output.append("\n");
		}

		output.deleteCharAt(output.length() - 1);
		System.out.println(output.toString());
	}

	private static void clearMemo() {
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < 2; j++) {
				memo[i][j] = 0;
			}
		}
	}

	private static void maxBurgers(int number) {
		for (int i = m; i <= number; i++) {
			if (i % m == i % n) {
				int a = memo[i - m][0] + memo[i - m][1];
				int b = memo[i - n][0] + memo[i - n][1];

				if (a > b) {
					memo[i][0] = memo[i - m][0] + 1;
					memo[i][1] = memo[i - m][1];
				} else {
					memo[i][0] = memo[i - n][0];
					memo[i][1] = memo[i - n][1] + 1;
				}
			} else if (i % m < i % n) {
				memo[i][0] = memo[i - m][0] + 1;
				memo[i][1] = memo[i - m][1];
			} else {
				memo[i][0] = memo[i - n][0];
				memo[i][1] = memo[i - n][1] + 1;
			}
		}
	}

}
