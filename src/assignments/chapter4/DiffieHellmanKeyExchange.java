package assignments.chapter4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class DiffieHellmanKeyExchange {

	private BigInteger value, secretKey;
	public BigInteger p, g, publicKey;

	public DiffieHellmanKeyExchange(int value, int p, int g) {
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
			if(temp == 0){
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

	public boolean GCD(BigInteger number1, BigInteger number2) {
		if ((number1.gcd(number2)).compareTo(BigInteger.ONE) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public BigInteger modInverse(BigInteger number, BigInteger modulo) {
		return number.modInverse(modulo);
	}
}