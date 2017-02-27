package assignments.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class AutokeyCipher {
	
	public static String createKey(String plainText, int keyCount, HashMap<Character, Integer> hashMap,
			HashMap<Integer, Character> inverseHashMap){
		char keyTextArray[] = plainText.toLowerCase().toCharArray();
		int temp = keyCount % 26;
		for (int i = 0; i < plainText.length(); i++) {
			int a = hashMap.get(plainText.charAt(i));
			if (i == 0) {
				a = (a + temp) % 26;
			} else {
				a = (a + hashMap.get(plainText.charAt(i - 1))) % 26;
			}
			keyTextArray[i] = inverseHashMap.get(a);
		}
		return new String(keyTextArray);
	}

	public static String Encryptor(String plainText, String keyText, HashMap<Character, Integer> hashMap,
			HashMap<Integer, Character> inverseHashMap) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		for (int i = 0; i < plainText.length(); i++) {
			int a = hashMap.get(plainText.charAt(i));
			int b = hashMap.get(keyText.charAt(i));
			a = (a + b) % 26;
			plainTextArray[i] = inverseHashMap.get(a);
		}
		return new String(plainTextArray);
	}

	public static String Decryptor(String encrypted, String keyText, HashMap<Character, Integer> hashMap,
			HashMap<Integer, Character> inverseHashMap) {
		char encryptedArray[] = encrypted.toLowerCase().toCharArray();
		for (int i = 0; i < encrypted.length(); i++) {
			int a = hashMap.get(encrypted.charAt(i));
			int b = hashMap.get(keyText.charAt(i));
			a = a - b;
			if (a < 0) {
				int x;
				x = Math.abs(a) % 26;
				a = 26 - x;
			} else {
				a = a % 26;
			}
			encryptedArray[i] = inverseHashMap.get(a);
		}
		return new String(encryptedArray);
	}

	public static void main(String[] args) throws IOException {
		int keyCount = 0;
		String plainText = null, keyText = null, encrypted = null, decrypted = null;
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		HashMap<Integer, Character> inverseHashMap = new HashMap<Integer, Character>();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 26; i++) {
			hashMap.put((char) (i + 97), i); // fill the hashmap with z26
												// (english alphabet) using
												// ASCII codes
			inverseHashMap.put(i, (char) (i + 97)); // inverse of alphabet
		}

		System.out.println("For exit enter negative value to shift count.");
		while (true) {
			System.out.println("Plain Text : ");
			plainText = bufferedReader.readLine();
			System.out.println("Key Count : ");
			keyCount = Integer.parseInt(bufferedReader.readLine());
			if (keyCount < 0 || plainText.equals("") || plainText.equals(null)) {
				System.out.println("Program terminated.");
				break;
			}
			System.out.println("Original Data : " + plainText);
			keyText = createKey(plainText, keyCount, hashMap, inverseHashMap);
			System.out.println("Key : " + keyText);
			encrypted = Encryptor(plainText, keyText, hashMap, inverseHashMap);
			System.out.println("Encrypted Data : " + encrypted);
			decrypted = Decryptor(encrypted, keyText, hashMap, inverseHashMap);
			System.out.println("Decrypted Data : " + decrypted);
		}
		bufferedReader.close();
	}
}
