package assignments.chapter3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

public class DataEncryptionStandard {

	public static SecretKey getSecretEncryptionKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey;
	}

	public static byte[] Encryptor(String plainText, SecretKey secretKey) throws Exception {
		Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encrypted = desCipher.doFinal(plainText.getBytes());
		return encrypted;
	}

	public static String Decryptor(byte[] encrypted, SecretKey secretKey) throws Exception {
		Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		desCipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decrypted = desCipher.doFinal(encrypted);
		return new String(decrypted);
	}

	private static String bytesToHex(byte[] text) {
		return DatatypeConverter.printHexBinary(text);
	}

	public static void main(String[] args) throws Exception {
		String plainText = " ", decrypted = null;
		byte[] encrypted;
		SecretKey secretKey = getSecretEncryptionKey();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

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
			System.out.println("DES Key (Hexadecimal) : " + bytesToHex(secretKey.getEncoded()));
			encrypted = Encryptor(plainText, secretKey);
			System.out.println("Encrypted Data (Hexadecimal):" + bytesToHex(encrypted));
			decrypted = Decryptor(encrypted, secretKey);
			System.out.println("Decrypted Data : " + decrypted);
		}
		bufferedReader.close();
	}
}