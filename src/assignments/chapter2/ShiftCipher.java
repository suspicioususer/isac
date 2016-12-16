package assignments.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShiftCipher {

	public static String Encryptor(String plainText, int shiftCount) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		for (int i = 0; i < plainTextArray.length; i++) {
			char c = plainTextArray[i];
			c = (char) (c + shiftCount);
			if (c > 'z') {
				c = (char) (c - 26);
			} else if (c < 'a') {
				c = (char) (c + 26);
			}
			plainTextArray[i] = c;
		}
		return new String(plainTextArray);
	}

	public static String Decryptor(String encrypted, int shiftCount) {
		char encryptedArray[] = encrypted.toLowerCase().toCharArray();
		for (int i = 0; i < encryptedArray.length; i++) {
			char c = encryptedArray[i];
			c = (char) (c - shiftCount);
			if (c > 'z') {
				c = (char) (c - 26);
			} else if (c < 'a') {
				c = (char) (c + 26);
			}
			encryptedArray[i] = c;
		}
		return new String(encryptedArray);
	}

	public static void main(String[] args) throws IOException {
		int shiftCount = 0;
		String plainText = null, encrypted = null, decrypted = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("For exit enter \'-1\' to shift count.");
		while (shiftCount != -1) {
			System.out.println("Shift Count : ");
			shiftCount = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Plain Text : ");
			plainText = bufferedReader.readLine();
			System.out.println("Original Data : " + plainText);
			encrypted = Encryptor(plainText, shiftCount);
			System.out.println("Encrypted Data : " + encrypted);
			decrypted = Decryptor(encrypted, shiftCount);
			System.out.println("Decrypted Data : " + decrypted);
		}
		bufferedReader.close();
	}
}