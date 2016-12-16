package assignments.chapter1;

import java.util.Scanner;

public class EuclidAlgorithmWith5Clauses {
	
	private static int GCD(int number1, int number2) {
		if (number2 == 0) {
			return number1;
		}
		return GCD(number2, number1 % number2);
	}

	private static int modInverse(int number1, int number2) {
		number1 = number1 % number2;
		for (int p = 1; p < number2; p++) {
			if ((number1 * p) % number2 == 1) {
				return p;
			}
		}
		return 0;
	}

	public static void main(String args[]) {
		int temp = 0;
		int a, b, c, d, i, k, m, x, a$, b$, m$;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Options are : {1,2,3,4,5} - 0 for exit.");
			temp = scanner.nextInt();

			switch (temp) {
			case 1:
				System.out.println("Expression is : a * c = b * c(modm) && gcd(c, m) = 1 --> a = b(modm)");
				System.out.println("Enter A,B numbers, C coefficient and M value for modular arithmetic");
				System.out.println("A : ");
				a = scanner.nextInt();
				System.out.println("B : ");
				b = scanner.nextInt();
				System.out.println("C : ");
				c = scanner.nextInt();
				System.out.println("M : ");
				m = scanner.nextInt();
				if ((((a * c) % m) == ((b * c) % m)) && GCD(c, m) == 1) {
					b = a % m;
					System.out.println("Simplified Result : " + b);
				} else
					System.out.println("Not compatible.");
				break;
			case 2:
				System.out.println("Expression is : a * c = b * c (modm) && gcd(c, m) = i --> a = b(modk) && k = m / i");
				System.out.println("Enter A,B numbers, C coefficient and M value for modular arithmetic");
				System.out.println("A : ");
				a = scanner.nextInt();
				System.out.println("B : ");
				b = scanner.nextInt();
				System.out.println("C : ");
				c = scanner.nextInt();
				System.out.println("M : ");
				m = scanner.nextInt();
				i = GCD(c, m);
				if ((((a * c) % m) == ((b * c) % m)) && i != 1) {
					k = m / i;
					b = a % k;
					System.out.println("Simplified Result : " + b);
				} else
					System.out.println("Not compatible.");
				break;
			case 3:
				System.out.println("GCD must be equal \'1\' for a ^ -1 existence.");
				System.out.println("Expression is : there exists a ^ -1 <--> gcd(a, m) = 1 then a * a ^ -1 =: 1(modm)");
				System.out.println("Enter A or A ^ -1 and M value for modular arithmetic");
				System.out.println("A or A ^ -1 : ");
				a = scanner.nextInt();
				System.out.println("M : ");
				m = scanner.nextInt();
				if (modInverse(a, m) == 0) {
					System.out.println("Not Found.");
				} else
					System.out.println("A ^ -1 = " + modInverse(a, m));
				break;
			case 4:
				System.out.println("Expression is : a * x = b(modm) && gcd(a, m) = 1 --> x = a ^ -1 * b(modm)");
				System.out.println("Enter A,B numbers and M value for modular arithmetic");
				System.out.println("A : ");
				a = scanner.nextInt();
				System.out.println("B : ");
				b = scanner.nextInt();
				System.out.println("M : ");
				m = scanner.nextInt();
				if (GCD(a, m) == 1) {
					x = (modInverse(a, m) * b) % m;
					System.out.println("Simplified Result (x) : " + x);
				} else
					System.out.println("Not compatible.");

				break;
			case 5:
				System.out
						.println("Expression is : a * x = b(modm) && gcd(a, m) = d && b % d = 0 --> x = (a\') ^ -1 * b\'(modm\')");
				System.out.println("Enter A,B numbers, M value for modular arithmetic");
				System.out.println("A : ");
				a = scanner.nextInt();
				System.out.println("B : ");
				b = scanner.nextInt();
				System.out.println("M : ");
				m = scanner.nextInt();
				d = GCD(a, m);
				if (d >= 1 && b % d == 0) {
					a$ = a / d;
					b$ = b / d;
					m$ = m / d;
					x = (modInverse(a$, m$) * b$) % m$;
					System.out.println("Simplified Result : " + x);
				} else
					System.out.println("Not compatible.");
				break;
			default:
				break;
			}
			if (temp == 0) {
				System.out.println("Terminated.");
				break;
			}
		}
		scanner.close();
	}
}