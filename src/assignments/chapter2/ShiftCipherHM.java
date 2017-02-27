
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ShiftCipherHM {

	public static String Encryptor(String plainText, int shiftCount, HashMap<Character, Integer> hashMap,
			HashMap<Integer, Character> inverseHashMap) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		int temp = shiftCount % 26;
		for (int i = 0; i < plainText.length(); i++) {
			int a = hashMap.get(plainText.charAt(i));
			a = (a + temp) % 26;
			plainTextArray[i] = inverseHashMap.get(a);
		}
		return new String(plainTextArray);
	}

	public static String Decryptor(String encrypted, int shiftCount, HashMap<Character, Integer> hashMap,
			HashMap<Integer, Character> inverseHashMap) {
		char encryptedArray[] = encrypted.toLowerCase().toCharArray();
		int temp = shiftCount % 26;
		for (int i = 0; i < encrypted.length(); i++) {
			int a = hashMap.get(encrypted.charAt(i));
			a = a - temp;
			if(a < 0 ){
				int x;
				x = Math.abs(a) % 26;
				a = 26 - x;
			}else{
				a = a % 26;
			}
			encryptedArray[i] = inverseHashMap.get(a);
		}
		return new String(encryptedArray);
	}

	public static void main(String[] args) throws IOException {
		int shiftCount = 0;
		String plainText = null, encrypted = null, decrypted = null;
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
			System.out.println("Shift Count : ");
			shiftCount = Integer.parseInt(bufferedReader.readLine());
			if (shiftCount < 0) {
				System.out.println("Program terminated.");
				break;
			}
			System.out.println("Plain Text : ");
			plainText = bufferedReader.readLine();
			System.out.println("Original Data : " + plainText);
			encrypted = Encryptor(plainText, shiftCount, hashMap, inverseHashMap);
			System.out.println("Encrypted Data : " + encrypted);
			decrypted = Decryptor(encrypted, shiftCount, hashMap, inverseHashMap);
			System.out.println("Decrypted Data : " + decrypted);
		}
		bufferedReader.close();
	}
}