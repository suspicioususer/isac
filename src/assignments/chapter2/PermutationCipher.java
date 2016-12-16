package assignments.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PermutationCipher {

	public static String Encryptor(String plainText) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		char tempArray[] = plainText.toCharArray();
		for (int i = 0; i < plainTextArray.length; i++) {
			switch (i % 6) {
			case 0:
				tempArray[i + 2] = plainTextArray[i];
				break;
			case 1:
				tempArray[i + 4] = plainTextArray[i];
				break;
			case 2:
				tempArray[i - 2] = plainTextArray[i];
				break;
			case 3:
				tempArray[i + 1] = plainTextArray[i];
				break;
			case 4:
				tempArray[i - 3] = plainTextArray[i];
				break;
			case 5:
				tempArray[i - 2] = plainTextArray[i];
				break;
			default:
				System.out.println("Oops! Error occured.");
				break;
			}
		}
		return new String(tempArray);
	}

	public static String Decryptor(String encrypted) {
		char encryptedArray[] = encrypted.toLowerCase().toCharArray();
		char tempArray[] = encrypted.toCharArray();
		for (int i = 0; i < encryptedArray.length; i++) {
			switch (i % 6) {
			case 0:
				tempArray[i] = encryptedArray[i + 2];
				break;
			case 1:
				tempArray[i] = encryptedArray[i + 4];
				break;
			case 2:
				tempArray[i] = encryptedArray[i - 2];
				break;
			case 3:
				tempArray[i] = encryptedArray[i + 1];
				break;
			case 4:
				tempArray[i] = encryptedArray[i - 3];
				break;
			case 5:
				tempArray[i] = encryptedArray[i - 2];
				break;
			default:
				System.out.println("Oops! Error occured.");
				break;
			}
		}
		return new String(tempArray);
	}

	public static void main(String[] args) throws IOException {
		String plainText = " ", encrypted = null, decrypted = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("Plain Text : ");
			plainText = bufferedReader.readLine();
			if (plainText.length() % 6 != 0 || plainText.equals("*exit*") || plainText.equals("")) {
				System.out.println("Program has been terminated.");
				System.out.println("# Reason[1] : Plain text is null.");
				System.out.println("# Reason[2] : Plain text is not valid. It must be 6 * N+ characters.");
				System.out.println("# Reason[3] : You entered \"*exit*\" key.");
				break;
			}
			System.out.println("Original Data : " + plainText);
			encrypted = Encryptor(plainText);
			System.out.println("Encrypted Data : " + encrypted);
			decrypted = Decryptor(encrypted);
			System.out.println("Decrypted Data : " + decrypted);
		}
		bufferedReader.close();
	}
}