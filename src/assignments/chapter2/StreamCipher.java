package assignments.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class StreamCipher {

	public static String generateRandomKey(int plainTextLength, HashMap<Integer, Character> inverseHashMap) {
		Random random = new Random();
		String key = "";
		for (int i = 0; i < plainTextLength; i++) {
			int k = random.nextInt(26);
			char c = inverseHashMap.get(k);
			key = key + c;
		}
		return key;
	}

	public static String Encryptor(String plainText, String key, HashMap<Character, Integer> hashMap,
			HashMap<Integer, Character> inverseHashMap) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		char keyArray[] = key.toLowerCase().toCharArray();
		for (int i = 0; i < plainTextArray.length; i++) {
			int k = hashMap.get(plainTextArray[i]);
			int m = hashMap.get(keyArray[i]);
			int w = (k + m) % 26;
			plainTextArray[i] = inverseHashMap.get(w);
		}
		return new String(plainTextArray);
	}

	public static String Decryptor(String encrypted, String key, HashMap<Character, Integer> hashMap,
			HashMap<Integer, Character> inverseHashMap) {
		char encryptedArray[] = encrypted.toLowerCase().toCharArray();
		char keyArray[] = key.toLowerCase().toCharArray();
		for (int i = 0; i < encryptedArray.length; i++) {
			int k = hashMap.get(encryptedArray[i]);
			int m = hashMap.get(keyArray[i]);
			int w = k - m;
			if (w < 0) {
				int a = 0;
				a = Math.abs(w) % 26;
				w = 26 - a;
			} else {
				w = w % 26;
			}
			encryptedArray[i] = inverseHashMap.get(w);
		}
		return new String(encryptedArray);
	}

	public static void main(String[] args) throws IOException {
		String plainText = null, key = null, encrypted = null, decrypted = null;
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		HashMap<Integer, Character> inverseHashMap = new HashMap<Integer, Character>();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 26; i++) {
			hashMap.put((char) (i + 97), i);
			inverseHashMap.put(i, (char) (i + 97));
		}

		System.out.println("For exit pass by null to plain text.");
		while (true) {
			System.out.println("Plain Text : ");
			plainText = bufferedReader.readLine();
			if (plainText.equals("*exit*") || plainText.equals("")) {
				System.out.println("Program has been terminated.");
				System.out.println("# Reason[1] : Plain text is null.");
				System.out.println("# Reason[2] : You entered \"*exit*\" key.");
				break;
			}

			System.out.println("Original Data : " + plainText);
			key = generateRandomKey(plainText.length(), inverseHashMap);
			System.out.println("Generated Random Key : " + key);
			encrypted = Encryptor(plainText, key, hashMap, inverseHashMap);
			System.out.println("Encrypted Data : " + encrypted);
			decrypted = Decryptor(encrypted, key, hashMap, inverseHashMap);
			System.out.println("Decrypted Data : " + decrypted);
		}
		bufferedReader.close();
	}
}
