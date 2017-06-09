package assignments.chapter4;

public class SSCTestClass {

	public static void main(String[] args) {
		ShiftedSupplementaryCipher supplementaryCipher = new ShiftedSupplementaryCipher();
		int shiftCount = 3, count = 5;
		String plainText = "thisisatest";
		System.out.println("Original Data : " + plainText);
		String encrypted = supplementaryCipher.Encryptor(plainText, shiftCount, count);
		System.out.println("Encrypted Data : " + encrypted);
		String decrypted = supplementaryCipher.Decryptor(encrypted, shiftCount, count);
		System.out.println("Decrypted Data : " + decrypted);
	}

}
