package extras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class RSA {

	private static final String ALGORITHM = "RSA";
	private static final String PATH = System.getProperty("user.dir");
	private static final String PRIVATE_KEY_FILE = PATH + "/RSA_private.key";
	private static final String PUBLIC_KEY_FILE = PATH + "/RSA_public.key";

	public static void generateKeys() {

		try {
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
			keyGen.initialize(1024);
			final KeyPair key = keyGen.generateKeyPair();

			File privateKeyFile = new File(PRIVATE_KEY_FILE);
			File publicKeyFile = new File(PUBLIC_KEY_FILE);

			if (privateKeyFile.getParentFile() != null) {
				privateKeyFile.getParentFile().mkdirs();
			}
			privateKeyFile.createNewFile();

			if (publicKeyFile.getParentFile() != null) {
				publicKeyFile.getParentFile().mkdirs();
			}
			publicKeyFile.createNewFile();

			ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
			publicKeyOS.writeObject(key.getPublic());
			publicKeyOS.close();

			ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
			privateKeyOS.writeObject(key.getPrivate());
			privateKeyOS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean areKeysExists() {
		File privateKey = new File(PRIVATE_KEY_FILE);
		File publicKey = new File(PUBLIC_KEY_FILE);

		if (privateKey.exists() && publicKey.exists()) {
			return true;
		}
		return false;
	}

	public static byte[] Encryptor(String plainText, PublicKey publicKey) {
		byte[] encryptedTextArray = null;
		try {
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			encryptedTextArray = cipher.doFinal(plainText.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedTextArray;
	}

	public static String Decryptor(byte[] encryptedTextArray, PrivateKey privateKey) {
		byte[] decryptedTextArray = null;
		try {
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			decryptedTextArray = cipher.doFinal(encryptedTextArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(decryptedTextArray);
	}

	public static void main(String[] args) {

		String plainText = null, encrypted = null, decrypted = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		ObjectInputStream objectInputStream = null;

		System.out.println("For exit pass null to plain text.");
		while (true) {
			try {
				
				if (!areKeysExists()) {
					generateKeys();
				}
				
				System.out.println("Plain Text : ");
				plainText = bufferedReader.readLine();
				
				if (plainText.equals("") || plainText.equals(null) || plainText.trim().isEmpty()) {
					System.out.println("Program terminated.");
					break;
				}

				System.out.println("Original Data : " + plainText);

				objectInputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
				final PublicKey publicKey = (PublicKey) objectInputStream.readObject();
				final byte[] encryptedTextArray = Encryptor(plainText, publicKey);
				encrypted = new String(encryptedTextArray);
				objectInputStream.close();
				System.out.println("Encrypted Data : " + encrypted);

				objectInputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
				final PrivateKey privateKey = (PrivateKey) objectInputStream.readObject();
				decrypted = Decryptor(encryptedTextArray, privateKey);
				objectInputStream.close();
				System.out.println("Decrypted Data : " + decrypted);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}