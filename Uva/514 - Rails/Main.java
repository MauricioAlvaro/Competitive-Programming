import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		while (sc.hasNextInt()) {
			int coaches = sc.nextInt();

			if (coaches == 0) {
				continue;
			}

			ArrayDeque<Integer> station;
			ArrayDeque<Integer> outgoing;
			boolean hasTestCases = true;

			while (hasTestCases) {
				station = new ArrayDeque<Integer>();
				outgoing = new ArrayDeque<Integer>();
				int n = 0;

				for (int i = 1; i <= coaches; i++) {
					n = sc.nextInt();

					if (n == 0) {
						hasTestCases = false;
						break;
					}

					outgoing.add(n);
					station.push(i);

					while (!outgoing.isEmpty() && !station.isEmpty() && outgoing.peek().equals(station.peek())) {
						outgoing.remove();
						station.pop();
					}
				}

				if (n != 0) {
					sb.append(station.isEmpty() ? "Yes\n" : "No\n");
				}
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
}
