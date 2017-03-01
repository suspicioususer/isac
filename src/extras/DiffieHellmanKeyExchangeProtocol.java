package extras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class DiffieHellmanKeyExchangeProtocol {

	private BigInteger value, secretKey;
	public BigInteger p, g, publicKey;

	public DiffieHellmanKeyExchangeProtocol(int value, int p, int g) {
		this.value = new BigInteger(Integer.toString(value));
		this.p = new BigInteger(Integer.toString(p));
		this.g = new BigInteger(Integer.toString(g));
	}

	public void publicKey(String x) {
		publicKey = g.modPow(value, p);
		System.out.println("Public Key " + "(" + x + ")" + " : " + publicKey.toString());
	}

	public BigInteger getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(BigInteger publicKey) {
		secretKey = publicKey.modPow(value, p);
	}

	public static boolean isPrime(int n) {
		for (int i = 2; 2 * i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static boolean isPrimitiveRoot(int number, int modulo) {
		List<Integer> nonDuplicateRoots = new ArrayList<Integer>();
		for (int i = 0; i < modulo; i++) {
			int temp = (int) (Math.pow(number, i + 1) % modulo);
			if (temp == 0) {
				return false;
			}
			nonDuplicateRoots.add(temp);
		}
		Collections.sort(nonDuplicateRoots);
		LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<Integer>();
		linkedHashSet.addAll(nonDuplicateRoots);
		nonDuplicateRoots.clear();
		nonDuplicateRoots.addAll(linkedHashSet);

		for (int i = 0; i < nonDuplicateRoots.size(); i++) {
			if (i + 1 == nonDuplicateRoots.get(i)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	// public boolean GCD(BigInteger number1, BigInteger number2) {
	// if ((number1.gcd(number2)).compareTo(BigInteger.ONE) == 0) {
	// return true;
	// } else {
	// return false;
	// }
	// }
	//
	// public BigInteger modInverse(BigInteger number, BigInteger modulo) {
	// return number.modInverse(modulo);
	// }

	public static void main(String[] args) throws IOException {
		int value = 0, p = 0, g = 0;
		DiffieHellmanKeyExchangeProtocol alice, bob;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				System.out.println("Prime Number : ");
				p = Integer.parseInt(bufferedReader.readLine());
				boolean prime = DiffieHellmanKeyExchangeProtocol.isPrime(p);

				System.out.println("Primitive Root Value : ");
				g = Integer.parseInt(bufferedReader.readLine());
				boolean primitiveRoot = DiffieHellmanKeyExchangeProtocol.isPrimitiveRoot(g, p);

				if (prime && primitiveRoot) {
					System.out.println("a value for Alice : ");
					value = Integer.parseInt(bufferedReader.readLine());
					if (value >= 0 && value <= (p - 2)) {
						alice = new DiffieHellmanKeyExchangeProtocol(value, p, g);
						alice.publicKey("a");
					} else {
						alice = null;
					}
					System.out.println("b value for Bob : ");
					value = Integer.parseInt(bufferedReader.readLine());
					if (value >= 0 && value <= (p - 2)) {
						bob = new DiffieHellmanKeyExchangeProtocol(value, p, g);
						bob.publicKey("b");
					} else {
						bob = null;
					}
					bob.setSecretKey(alice.publicKey);
					alice.setSecretKey(bob.publicKey);
					if (!bob.getSecretKey().equals(null) && !alice.getSecretKey().equals(null)
							&& bob.getSecretKey().compareTo(alice.getSecretKey()) == 0) {
						System.out.println("");
						System.out.println("Diffie - Hellman Key Exchange is successful");
						System.out.println("");
						System.out.println("Secret Keys : ");
						System.out.println("Alice's secret key (result) : " + alice.getSecretKey());
						System.out.println("Bob's secret key (result) : " + bob.getSecretKey());
						System.out.println("Both of sides have same key.");
					} else {
						System.out.println("Diffie - Hellman Key Exchange is not successful!");
					}
				} else if (!prime && primitiveRoot) {
					System.out.println("P is not prime.");
				} else if (prime && !primitiveRoot) {
					System.out.println("G is not primitive root.");
				} else {
					System.out.println("P and G is not valid.");
				}
				
			} catch (IOException e) {
				System.out.println("Oops! Something went wrong!");
				e.printStackTrace();
				break;
			}
		}
		bufferedReader.close();
	}
}