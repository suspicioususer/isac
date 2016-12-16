package assignments.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class SubstitutionCipher {

	public static String Encryptor(String plainText, String PI, String inversePI) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		HashMap<Character, Character> hashMap = new HashMap<Character, Character>();
		for (int i = 0; i < PI.length(); ++i) {
			hashMap.put(PI.charAt(i), inversePI.charAt(i));
		}

		for (int i = 0; i < plainTextArray.length; i++) {
			char c = plainTextArray[i];
			c = hashMap.get(c);
			plainTextArray[i] = c;
		}
		return new String(plainTextArray);
	}

	public static String Decryptor(String encrypted, String PI, String inversePI) {
		char encryptedArray[] = encrypted.toLowerCase().toCharArray();
		HashMap<Character, Character> hashMap = new HashMap<Character, Character>();
		for (int i = 0; i < PI.length(); i++) {
			hashMap.put(inversePI.charAt(i), PI.charAt(i));
		}

		for (int i = 0; i < encryptedArray.length; i++) {
			char c = encryptedArray[i];
			c = hashMap.get(c);
			encryptedArray[i] = c;
		}
		return new String(encryptedArray);
	}

	public static void main(String[] args) throws IOException {
		String PI = "abcdefghijklmnopqrstuvwxyz";
		String inversePI = "xnyahpogzqwbtsflrcvmuekjdi";
		String plainText = " ", encrypted = null, decrypted = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("For exit enter \"*exit*\"");

		while (!plainText.equals("*exit*")) {
			System.out.println("Plain Text : ");
			plainText = bufferedReader.readLine();
			System.out.println("Original Data : " + plainText);
			encrypted = Encryptor(plainText, PI, inversePI);
			System.out.println("Encrypted Data : " + encrypted);
			decrypted = Decryptor(encrypted, PI, inversePI);
			System.out.println("Decrypted Data : " + decrypted);
		}
		bufferedReader.close();
	}
}
