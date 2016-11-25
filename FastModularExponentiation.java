import java.util.Arrays;
import java.util.Scanner;

public class FastModularExponentiation {

	public static void main(String[] args) {
		int a, b, c = 0, d = 1, m, i, j;
		String bStr = null;
		Scanner scanner = new Scanner(System.in);

		System.out.println("For exit enter \'0\' to number.");
		while (true) {
			System.out.println("Number : ");
			a = scanner.nextInt();
			if (a == 0) {
				System.out.println("Program terminated.");
				break;
			}
			System.out.println("Power : ");
			b = scanner.nextInt();
			System.out.println("Modulo : ");
			m = scanner.nextInt();

			bStr = Integer.toBinaryString(b);
			System.out.println("Binary power : " + bStr);
			j = bStr.length() - 1;

			int[] ci = new int[bStr.length()];
			int[] di = new int[bStr.length()];

			for (i = 0; i < bStr.length(); i++) {
				c = 2 * c;
				d = (int) ((Math.pow(d, 2)) % m);
				if (bStr.charAt(i) == '1') {
					c += 1;
					d = (d * a) % m;
				}
				ci[j - i] = c;
				di[j - i] = d;
			}
			System.out.println("C Array : " + Arrays.toString(ci));
			System.out.println("D Array : " + Arrays.toString(di));
			System.out.println("Result : " + di[0]);
		}
		scanner.close();
	}
}
