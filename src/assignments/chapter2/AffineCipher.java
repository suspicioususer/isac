package assignments.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class AffineCipher {

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

	public static String Encryptor(String plainText, int shiftCount, int multiplier,
			HashMap<Integer, Character> hashMap) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		for (int i = 0; i < plainTextArray.length; i++) {
			int k = plainTextArray[i];
			k = ((((multiplier * (k - 97)) + shiftCount) % 26));
			plainTextArray[i] = hashMap.get(k);
		}
		return new String(plainTextArray);
	}

	public static String Decryptor(String encrypted, int shiftCount, int multiplier,
			HashMap<Integer, Character> hashMap) {
		char encryptedArray[] = encrypted.toLowerCase().toCharArray();
		int temp = modInverse(multiplier, 26);
		for (int i = 0; i < encryptedArray.length; i++) {
			int k = (int) encryptedArray[i];
			int t = (k - 97) - shiftCount;
			if (t < 0) {
				int a = 0;
				t = temp * t;
				a = Math.abs(t) % 26;
				t = 26 - a;
				encryptedArray[i] = hashMap.get(t);
			} else {
				k = ((temp * ((k - 97) - shiftCount)) % 26);
				encryptedArray[i] = hashMap.get(k);
			}
		}
		return new String(encryptedArray);
	}

	public static void main(String[] args) throws IOException {

		int shiftCount = 0, multiplier = 0;
		String plainText = null, encrypted = null, decrypted = null;
		String z26 = "abcdefghijklmnopqrstuvwxyz";
		HashMap<Integer, Character> hashMap = new HashMap<Integer, Character>();
		for (int i = 0; i < z26.length(); i++) {
			hashMap.put(i, z26.charAt(i));
		}

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("For exit enter \'-1\' to shift count and multiplier.");
		while (true) {
			System.out.println("Shift Count : ");
			shiftCount = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Multiplier : ");
			multiplier = Integer.parseInt(bufferedReader.readLine());
			if (shiftCount == -1 && multiplier == -1 && GCD(multiplier, 26) != 1) {
				break;
			}
			System.out.println("Plain Text : ");
			plainText = bufferedReader.readLine();
			System.out.println("Original Data : " + plainText);
			encrypted = Encryptor(plainText, shiftCount, multiplier, hashMap);
			System.out.println("Encrypted Data : " + encrypted);
			decrypted = Decryptor(encrypted, shiftCount, multiplier, hashMap);
			System.out.println("Decrypted Data : " + decrypted);
		}
		bufferedReader.close();
	}
}