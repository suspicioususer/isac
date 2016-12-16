package assignments.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import Jama.Matrix;

public class HillCipher {

	static Matrix EncryptedMatrix;
	static Matrix DecryptedMatrix;

	private static int modInverse(int number1, int number2) {
		number1 = number1 % number2;
		for (int p = 1; p < number2; p++) {
			if ((number1 * p) % number2 == 1) {
				return p;
			}
		}
		return 0;
	}

	public static String Encryptor(Matrix PlainTextMatrix, Matrix KeyMatrix, HashMap<Character, Integer> hashMap,
			HashMap<Integer, Character> inverseHashMap) {
		String encrypted = "";

		EncryptedMatrix = PlainTextMatrix.times(KeyMatrix);

		for (int i = 0; i < EncryptedMatrix.getRowDimension(); i++) {
			for (int j = 0; j < EncryptedMatrix.getColumnDimension(); j++) {
				int w = (int) EncryptedMatrix.get(i, j) % 26;
				char c = inverseHashMap.get(w);
				if (EncryptedMatrix.get(i, j) < 0) {
					int t = (int) Math.abs(EncryptedMatrix.get(i, j)) % 26;
					t = 26 - t;
					EncryptedMatrix.set(i, j, t);
				} else {
					int t = (int) EncryptedMatrix.get(i, j) % 26;
					EncryptedMatrix.set(i, j, t);
				}
				encrypted = encrypted + c;
			}
		}
		return encrypted;
	}

	public static String Decryptor(Matrix EncryptedMatrix, Matrix KeyMatrix, double inverseDeterminant,
			HashMap<Character, Integer> hashMap, HashMap<Integer, Character> inverseHashMap) {
		String decrypted = "";
		KeyMatrix = KeyMatrix.inverse().times(KeyMatrix.det());
		KeyMatrix = KeyMatrix.times(inverseDeterminant);

		for (int i = 0; i < KeyMatrix.getRowDimension(); i++) {
			for (int j = 0; j < KeyMatrix.getColumnDimension(); j++) {
				if (KeyMatrix.get(i, j) < 0) {
					double t = Math.abs(KeyMatrix.get(i, j)) % 26;
					t = 26 - Math.round(t);
					KeyMatrix.set(i, j, t);
				} else {
					double t = KeyMatrix.get(i, j) % 26;
					KeyMatrix.set(i, j, Math.round(t));
				}
			}
		}

		DecryptedMatrix = EncryptedMatrix.times(KeyMatrix);

		for (int i = 0; i < DecryptedMatrix.getRowDimension(); i++) {
			for (int j = 0; j < DecryptedMatrix.getColumnDimension(); j++) {
				int w = (int) DecryptedMatrix.get(i, j) % 26;
				char c = inverseHashMap.get(w);
				if (DecryptedMatrix.get(i, j) < 0) {
					int t = (int) Math.abs(DecryptedMatrix.get(i, j)) % 26;
					t = 26 - t;
					DecryptedMatrix.set(i, j, t);
				} else {
					int t = (int) DecryptedMatrix.get(i, j) % 26;
					DecryptedMatrix.set(i, j, t);
				}
				decrypted = decrypted + c;
			}
		}
		return decrypted;
	}

	public static void main(String[] args) throws IOException {
		String key = "";
		double[][] dKeyMatrix, dPlainTextMatrix;
		double inverseDeterminant;
		int temp;
		String plainText = null, encrypted = null, decrypted = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		HashMap<Integer, Character> inverseHashMap = new HashMap<Integer, Character>();
		Matrix KeyMatrix, PlainTextMatrix;

		for (int i = 0; i < 26; i++) {
			hashMap.put((char) (i + 97), i);
			inverseHashMap.put(i, (char) (i + 97));
		}

		while (true) {
			System.out.println("Plain Text : ");
			plainText = bufferedReader.readLine();
			System.out.println("Key : ");
			key = bufferedReader.readLine();
			int keyLength = key.length();
			int squareRootKeyLength = (int) Math.sqrt(keyLength);
			int blockSize = plainText.length() / squareRootKeyLength;
			if (Math.sqrt(keyLength) != squareRootKeyLength || plainText.equals("*exit*") || key.equals("*exit")
					|| plainText.equals("") || key.equals("") || plainText.length() % squareRootKeyLength != 0
					|| plainText.length() < 2 || squareRootKeyLength < 2) {
				System.out.println("Program has been terminated.");
				System.out.println("# Reason[1] : Plain text or key is null.");
				System.out.println("# Reason[2] : Key is not valid. Square root of key's length must be integer.");
				System.out.println("# Reason[3] : Plain text is not valid. "
						+ "Plain text must be divide to square root of key's length perfectly. No remainder allowed.");
				System.out.println("# Reason[4] : Minimum length of plain text is \'2\', key is \'4\' characters.");
				System.out.println("# Reason[5] : You entered \"*exit*\" plain text or key.");
				break;
			}

			dKeyMatrix = new double[squareRootKeyLength][squareRootKeyLength];
			dPlainTextMatrix = new double[blockSize][squareRootKeyLength];
			key = key.toLowerCase();
			plainText = plainText.toLowerCase();

			temp = 0;
			for (int i = 0; i < squareRootKeyLength; i++) {
				for (int j = 0; j < squareRootKeyLength; j++) {
					dKeyMatrix[i][j] = hashMap.get(key.charAt(temp));
					temp++;
				}
			}

			temp = 0;
			for (int i = 0; i < blockSize; i++) {
				for (int j = 0; j < squareRootKeyLength; j++) {
					dPlainTextMatrix[i][j] = hashMap.get(plainText.charAt(temp));
					temp++;
				}
			}

			KeyMatrix = new Matrix(dKeyMatrix);
			PlainTextMatrix = new Matrix(dPlainTextMatrix);

			inverseDeterminant = KeyMatrix.det();
			if (inverseDeterminant < 0) {
				int t = (int) Math.abs(inverseDeterminant) % 26;
				inverseDeterminant = 26 - t;
				inverseDeterminant = modInverse((int) inverseDeterminant, 26);
			} else {
				inverseDeterminant = (Math.round(inverseDeterminant) % 26);
				inverseDeterminant = modInverse((int) inverseDeterminant, 26);
			}

			System.out.println("Original Data : " + plainText);
			encrypted = Encryptor(PlainTextMatrix, KeyMatrix, hashMap, inverseHashMap);
			System.out.println("Encrypted Data : " + encrypted);
			decrypted = Decryptor(EncryptedMatrix, KeyMatrix, inverseDeterminant, hashMap, inverseHashMap);
			System.out.println("Decrypted Data : " + decrypted);
		}
		bufferedReader.close();
	}
}
