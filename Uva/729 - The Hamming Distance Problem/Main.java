import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


class Main {

	public static void main(String[] args) {
		
		StringBuilder output = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		sc.nextLine(); 
		sc.nextLine();
		
		while (sc.hasNextInt()) {
			int bits = sc.nextInt();
			int distance = sc.nextInt();
			
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < bits; i++) {
				sb.append('0');
			}
			
			ArrayList<String> binaryStrings = hamming(sb.toString(), sb.length() - 1, distance);
			Collections.sort(binaryStrings);
			
			for (String i : binaryStrings) {
				output.append(i + "\n");
			}
			
			output.append("\n");
		}
		
		System.out.println(output.toString().substring(0, output.length() - 2));
	}
	
	private static ArrayList<String> hamming(String binaryString, int start, int distance) {
		ArrayList<String> result = new ArrayList<String>();
		
		if (distance == 0) {
			return new ArrayList<String>(Arrays.asList(binaryString));
		} else {
			for (int i = start; i >= 0; i--) {
				StringBuilder sb = new StringBuilder(binaryString);
				sb.setCharAt(i, '1');
				result.addAll(hamming(sb.toString(), i - 1, distance - 1));
			}
		}
		
		return result;
	}

}
