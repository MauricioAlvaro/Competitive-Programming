import java.util.Scanner;

class Main {
	
	private static final int MAX = 10000;
	private static long[] ways = new long[MAX];

	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		
		ways[0] = 1;
		change();
		
		while (sc.hasNextInt()) {
			n = sc.nextInt();
			System.out.println(ways[n]);
		}
	}
	
	private static void change() {
		for (int i = 1; i < 22; i++) {
			int cube = (int) Math.pow(i, 3);
			
			for (int j = cube; j < MAX; j++) {
				ways[j] += ways[j - cube];
			}
		}
	}

}
