import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int testCases = sc.nextInt();
		sc.nextLine();
		sc.nextLine();

		StringBuilder sb = new StringBuilder();
		HashSet<SimpleEntry<Integer, Integer>> combinations;

		for (int i = 0; i < testCases; i++) {
			ArrayList<String> strings = new ArrayList<String>();
			combinations = new HashSet<SimpleEntry<Integer, Integer>>();

			while (sc.hasNextLine()) {
				String line = sc.nextLine();

				if (line.isEmpty()) {
					break;
				}

				strings.add(line);
			}

			Collections.sort(strings);

			for (int j = 0; j < strings.size(); j++) {
				String a = strings.get(j);

				for (int k = 0; k < strings.size(); k++) {
					String b = strings.get(k);
					SimpleEntry<Integer, Integer> jk = new SimpleEntry<Integer, Integer>(j, k);
					SimpleEntry<Integer, Integer> kj = new SimpleEntry<Integer, Integer>(k, j);

					if (j != k) {
						if (!combinations.contains(jk) && !combinations.contains(kj)) {
							if (isAnagram(a, b)) {
								if (a.compareTo(b) < 0) {
									sb.append(a + " = " + b + "\n");
								} else {
									sb.append(b + " = " + a + "\n");
								}
								combinations.add(jk);
							}
						}
					}
				}
			}
			sb.append("\n");
		}

		// Having spaces at the end makes the submission fail
		sb.deleteCharAt(sb.lastIndexOf("\n"));
		sb.deleteCharAt(sb.lastIndexOf("\n"));
		System.out.println(sb.toString());
	}

	public static HashMap<Character, Integer> getCharacterCount(String s) {
		HashMap<Character, Integer> counter = new HashMap<Character, Integer>();

		for (char i : s.toCharArray()) {
			if (!Character.toString(i).matches("\\s")) {
				if (counter.containsKey(i)) {
					int count = counter.get(i) + 1;
					counter.put(i, count);
				} else {
					counter.put(i, 1);
				}
			}
		}

		return counter;
	}

	public static boolean isAnagram(String a, String b) {
		HashMap<Character, Integer> counterA = getCharacterCount(a);
		HashMap<Character, Integer> counterB = getCharacterCount(b);
		return counterA.equals(counterB);
	}
}
