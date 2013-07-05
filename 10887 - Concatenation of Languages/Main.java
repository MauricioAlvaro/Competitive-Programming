import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


class Main {

	public static void main(String[] args) {
		int k = 0;
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		
		StringBuilder out = new StringBuilder();
		
		while (sc.hasNextLine()) {
			String[] numbers = sc.nextLine().split(" ");
			int m = Integer.parseInt(numbers[0]);
			int n = Integer.parseInt(numbers[1]);
			
			ArrayList<String> mlang = new ArrayList<String>(m);
			ArrayList<String> nlang = new ArrayList<String>(n);
			
			for (int i = 0; i < m; i++) {
				mlang.add(sc.nextLine());
			}
			
			for (int i = 0; i < n; i++) {
				nlang.add(sc.nextLine());
			}
			
			Set<String> c = new HashSet<String>();
			
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					c.add(mlang.get(i) + nlang.get(j));
				}
			}
			
			out.append("Case " + ++k + ": " + c.size() + "\n");
		}
		
		System.out.println(out.deleteCharAt(out.length() - 1));
	}

}
