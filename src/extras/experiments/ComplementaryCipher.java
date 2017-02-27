package extras.experiments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ComplementaryCipher {

	public static String Encryptor(String plainText, HashMap<Character, Integer> hashMap,
			HashMap<Integer, Character> inverseHashMap) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		for (int i = 0; i < plainText.length(); i++) {
			int a = hashMap.get(plainText.charAt(i));
			a = 25 - a;
			plainTextArray[i] = inverseHashMap.get(a);
		}
		return new String(plainTextArray);
	}

	public static String Decryptor(String encrypted, HashMap<Character, Integer> hashMap,
			HashMap<Integer, Character> inverseHashMap) {
		char encryptedArray[] = encrypted.toLowerCase().toCharArray();
		for (int i = 0; i < encrypted.length(); i++) {
			int a = hashMap.get(encrypted.charAt(i));
			a = 25 - a;
			encryptedArray[i] = inverseHashMap.get(a);
		}
		return new String(encryptedArray);
	}

	public static void main(String[] args) throws IOException {
		String plainText = null, encrypted = null, decrypted = null;
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		HashMap<Integer, Character> inverseHashMap = new HashMap<Integer, Character>();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 26; i++) {
			hashMap.put((char) (i + 97), i);
			inverseHashMap.put(i, (char) (i + 97));
		}

		while (true) {
			System.out.println("Plain Text : ");
			plainText = bufferedReader.readLine();
			if (plainText.equals("") || plainText.equals(null)) {
				System.out.println("Program terminated.");
				break;
			}
			System.out.println("Original Data : " + plainText);
			encrypted = Encryptor(plainText, hashMap, inverseHashMap);
			System.out.println("Encrypted Data : " + encrypted);
			decrypted = Decryptor(encrypted, hashMap, inverseHashMap);
			System.out.println("Decrypted Data : " + decrypted);
		}
		bufferedReader.close();
	}
	//test

}
