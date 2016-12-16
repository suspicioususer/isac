package assignments.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VigenereCipher {

	public static String Encryptor(String plainText, String cipherKey) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		for (int i = 0, j = 0; i < plainTextArray.length; i++) {
			char c = plainTextArray[i];
			if (c < 'a' || c > 'z') {
				continue;
			}
			plainTextArray[i] = (char) ((c + cipherKey.charAt(j) - 2 * 'a') % 26 + 'a');
			j = ++j % cipherKey.length();
		}
		return new String(plainTextArray);
	}

	public static String Decryptor(String encrypted, String cipherKey) {
		char encryptedArray[] = encrypted.toLowerCase().toCharArray();
		for (int i = 0, j = 0; i < encryptedArray.length; i++) {
			char c = encryptedArray[i];
			if (c < 'a' || c > 'z') {
				continue;
			}
			encryptedArray[i] = (char) ((c - cipherKey.charAt(j) + 26) % 26 + 'a');
			j = ++j % cipherKey.length();
		}
		return new String(encryptedArray);
	}

	public static void main(String[] args) throws IOException {
		String plainText = null, cipherKey = null, encrypted = null, decrypted = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("For exit pass by null to plain text and cipher key.");
		do {
			System.out.println("Plain Text : ");
			plainText = bufferedReader.readLine();
			System.out.println("Cipher Key : ");
			cipherKey = bufferedReader.readLine();
			System.out.println("Original Data : " + plainText);
			System.out.println("Cipher Key : " + cipherKey);
			encrypted = Encryptor(plainText, cipherKey);
			System.out.println("Encrypted Data : " + encrypted);
			decrypted = Decryptor(encrypted, cipherKey);
			System.out.println("Decrypted Data : " + decrypted);
		} while (!plainText.equals("") && !cipherKey.equals(""));
		bufferedReader.close();
	}

}
