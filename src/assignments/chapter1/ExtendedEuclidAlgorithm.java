package assignments.chapter1;

import java.util.Scanner;

public class ExtendedEuclidAlgorithm {

	public static int gcdext(int n, int m) {

		int u1 = 0, u2 = 1, t1 = 1, t2 = 0, s, k, l;
		k = n;
		l = m;

		while (m != 0) {
			int q = Math.floorDiv(n, m);
			int r = n % m;
			System.out.println(
					"n = " + n + ", m = " + m + ", t1 = " + t1 + ", t2 = " + t2 + ", u1 = " + u1 + ", u2 = " + u2);
			n = m;
			m = r;
			s = u1;
			u1 = t1 - q * u1;
			t1 = s;
			s = u2;
			u2 = t2 - q * u2;
			t2 = s;
		}
		if (t1 < 0)
			System.out.println("GCDEXT(" + k + "," + l + ") = " + t2 + "*" + l + "-" + Math.abs(t1) + "*" + k);
		else if (t1 > 0)
			System.out.println("GCDEXT(" + k + "," + l + ") = " + t2 + "*" + l + "+" + t1 + "*" + k);

		return 0;
	}

	public static void main(String[] args) {

		int n = 0, m = 0;
		Scanner scanner = new Scanner(System.in);

		while (n != -1 || m != -1) {
			System.out.println("First Number : ");
			n = scanner.nextInt();
			System.out.println("Second Number : ");
			m = scanner.nextInt();
			gcdext(n, m);
		}
		scanner.close();
	}

}
