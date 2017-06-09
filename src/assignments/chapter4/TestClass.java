package assignments.chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {

	static int value = 0, p = 0, g = 0, shiftCount = 0, count = 0;
	static DiffieHellmanKeyExchange alice, bob;
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static boolean getValues() throws NumberFormatException, IOException {
		System.out.println("Prime Number : ");
		p = Integer.parseInt(bufferedReader.readLine());
		System.out.println("Primitive Root Value : ");
		g = Integer.parseInt(bufferedReader.readLine());
		boolean prime = DiffieHellmanKeyExchange.isPrime(p);
		if (prime) {
			System.out.println("a value for Alice : ");
			value = Integer.parseInt(bufferedReader.readLine());
			if (value >= 0 && value <= (p - 2)) {
				alice = new DiffieHellmanKeyExchange(value, p, g);
				alice.publicKey("a");
			} else {
				alice = null;
			}
			System.out.println("b value for Bob : ");
			value = Integer.parseInt(bufferedReader.readLine());
			if (value >= 0 && value <= (p - 2)) {
				bob = new DiffieHellmanKeyExchange(value, p, g);
				bob.publicKey("b");
			} else {
				bob = null;
			}
			bob.setSecretKey(alice.publicKey);
			alice.setSecretKey(bob.publicKey);
			if (!bob.getSecretKey().equals(null) && !alice.getSecretKey().equals(null)
					&& bob.getSecretKey().compareTo(alice.getSecretKey()) == 0) {
				System.out.println("Diffie - Hellman Key Exchange is successful");
				return true;
			} else {
				System.out.println("Diffie - Hellman Key Exchange is not successful!");
				return false;
			}
		} else {
			System.out.println("P is not prime.");
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		String plainText = null, encrypted = null, decrypted = null;
		ShiftedSupplementaryCipher supplementaryCipher = new ShiftedSupplementaryCipher();

		while (true) {
			try {
				System.out.println("Plain Text : ");
				plainText = bufferedReader.readLine();
				if (plainText.equals("") || plainText.equals(null)) {
					System.out.println("Program terminated.");
					break;
				}
				boolean status = getValues();
				if (status) {
					shiftCount = alice.getSecretKey().intValue();
				} else {
					System.out.println("Shift Count key have not created.");
					break;
				}
				status = getValues();
				if (status) {
					count = alice.getSecretKey().intValue();
				} else {
					System.out.println("Rearrangement Count key have not created.");
					break;
				}

				/*
				 * System.out.println("Shift Count : "); shiftCount =
				 * Integer.parseInt(bufferedReader.readLine());
				 * System.out.println("Rearrangement Count : "); count =
				 * Integer.parseInt(bufferedReader.readLine());
				 */
				System.out.println("Original Data : " + plainText);
				encrypted = supplementaryCipher.Encryptor(plainText, shiftCount, count);
				System.out.println("Encrypted Data : " + encrypted);
				decrypted = supplementaryCipher.Decryptor(encrypted, shiftCount, count);
				System.out.println("Decrypted Data : " + decrypted);

			} catch (IOException e) {
				System.out.println("Oops! Something went wrong!");
				e.printStackTrace();
				break;
			}
		}
		bufferedReader.close();
	}
}