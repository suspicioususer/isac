package assignments.chapter1;

import java.util.Scanner;

public class ChineseRemainderTheorem {

	private static int GCD(int number1, int number2) {
		if (number2 == 0) {
			return number1;
		}
		return GCD(number2, number1 % number2);
	}

	private static int[] modInverse(int resettedValues[], int modulos[]) {

		for (int i = 0; i < modulos.length; i++) {
			if (resettedValues[i] < 0) {
				int t = Math.abs(resettedValues[i]) % modulos[i];
				resettedValues[i] = modulos[i] - t;
			}

			if (GCD(resettedValues[i], modulos[i]) != 1) {
				System.out.println("They are not relatively prime");
				break;
			}

			for (int p = 1; p < modulos[i]; p++) {
				if ((resettedValues[i] * p) % modulos[i] == 1) {
					resettedValues[i] = p;
				}
			}
		}
		return resettedValues;
	}

	public static int modulosMultiplication(int bigM, int modulos[]) {

		for (int i = 0; i < modulos.length; i++) {
			bigM *= modulos[i];
		}
		return bigM;
	}

	public static int[] majors(int bigM, int majors[], int modulos[]) {

		for (int i = 0; i < majors.length; i++) {
			majors[i] = bigM / modulos[i];
		}
		return majors;
	}

	public static int[] newValues(int majors[], int modulos[]) {
		int temp[] = new int[modulos.length];

		for (int i = 0; i < temp.length; i++) {
			temp[i] = majors[i] % modulos[i];
		}
		return temp;
	}

	public static int resultCRT(int values[], int majors[], int resettedValues[], int bigM) {
		int x = 0;
		for (int i = 0; i < values.length; i++) {
			x += values[i] * majors[i] * resettedValues[i];
		}
		x = x % bigM;
		return x;
	}

	public static void main(String[] args) {

		int bigM, equationCount = 0, result;
		int[] values, modulos, majors, resettedValues;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			bigM = 1;
			System.out.println("Equation Count : ");
			equationCount = scanner.nextInt();

			if (equationCount < 2) {
				System.out.println("Minimum equation count is must be \'2\'!");
				break;
			}

			values = new int[equationCount];
			modulos = new int[equationCount];
			majors = new int[equationCount];
			resettedValues = new int[equationCount];
			for (int i = 0; i < equationCount; i++) {
				System.out.println(i + 1 + ". Value : ");
				values[i] = scanner.nextInt();
				System.out.println(i + 1 + ". Modulo : ");
				modulos[i] = scanner.nextInt();
			}
			bigM = modulosMultiplication(bigM, modulos);
			majors = majors(bigM, majors, modulos);
			resettedValues = newValues(majors, modulos);
			resettedValues = modInverse(resettedValues, modulos);
			result = resultCRT(values, majors, resettedValues, bigM);

			System.out.println("X : " + result + "mod(" + bigM + ")");

		}
		scanner.close();
	}
}
