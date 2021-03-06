package assignments.chapter4;

import java.util.HashMap;

public class ShiftedSupplementaryCipher {

	private static HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
	private static HashMap<Integer, Character> inverseHashMap = new HashMap<Integer, Character>();

	public ShiftedSupplementaryCipher() {
		for (int i = 0; i < 26; i++) {
			hashMap.put((char) (i + 97), i);
			inverseHashMap.put(i, (char) (i + 97));
		}
	}

	public String Shifter(String plainText, int count) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		int length = plainText.length();
		char tempArray[] = new char[length];

		if (count > 0) {
			count = count % length;
		} else {
			count = Math.abs(count) % length;
			count = length - count;
		}

		for (int i = 0; i < length; i++) {
			if ((i + count) < length) {
				tempArray[i + count] = plainTextArray[i];
			} else {
				int temp = (i + count) % length;
				tempArray[temp] = plainTextArray[i];
			}
		}
		return new String(tempArray);
	}

	public String Encryptor(String plainText, int shiftCount, int count) {
		char plainTextArray[] = plainText.toLowerCase().toCharArray();
		String supplemented = "", shifted = "";

		if (shiftCount > 0) {
			shiftCount = shiftCount % 26;
		} else {
			shiftCount = Math.abs(shiftCount) % 26;
			shiftCount = 26 - shiftCount;
		}

		for (int i = 0; i < plainText.length(); i++) {
			int a = hashMap.get(plainText.charAt(i));
			a = (26 - a) % 26;
			supplemented += inverseHashMap.get(a);
			a = (a + shiftCount) % 26;
			plainTextArray[i] = inverseHashMap.get(a);
			shifted += plainTextArray[i];
		}

		System.out.println("Supplemented String : " + supplemented);
		System.out.println("Shifted String : " + shifted);
		plainTextArray = Shifter(new String(plainTextArray), count).toCharArray();
		return new String(plainTextArray);
	}

	public String Decryptor(String encrypted, int shiftCount, int count) {
		char encryptedArray[] = encrypted.toLowerCase().toCharArray();
		count = -count;
		String supplemented = "", shifted = "";
		encryptedArray = Shifter(new String(encryptedArray), count).toCharArray();
		encrypted = String.valueOf(encryptedArray);
		shifted = encrypted;
		System.out.println("ReShifted String : " + shifted);
		if (shiftCount > 0) {
			shiftCount = shiftCount % 26;
		} else {
			shiftCount = Math.abs(shiftCount) % 26;
			shiftCount = 26 - shiftCount;
		}

		for (int i = 0; i < encrypted.length(); i++) {
			int a = hashMap.get(encrypted.charAt(i));
			a = a - shiftCount;
			if (a < 0) {
				int x;
				x = Math.abs(a) % 26;
				a = 26 - x;
			} else {
				a = a % 26;
			}
			supplemented += inverseHashMap.get(a);
			a = (26 - a) % 26;
			encryptedArray[i] = inverseHashMap.get(a);

		}
		System.out.println("ReSupplemented String : " + supplemented);
		return new String(encryptedArray);
	}

}