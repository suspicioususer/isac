package extras.experiments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class DateBasedSSC extends ShiftedSupplementaryCipher {

	public DateBasedSSC() {

	}

	public static void main(String[] args) {
		DateBasedSSC dbssc = new DateBasedSSC();
		String plainText = null, encrypted = null, decrypted = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Plain Text : ");
			try {
				plainText = bufferedReader.readLine();
				if (plainText.equals("") || plainText.equals(null)) {
					System.out.println("Program terminated.");
					break;
				}
				System.out.println("Original Data : " + plainText);
				encrypted = dbssc.Encryptor(plainText, LocalDateTime.now().getDayOfMonth(),
						LocalDateTime.now().getMonthValue());
				System.out.println("Encrypted Data : " + encrypted);
				decrypted = dbssc.Decryptor(encrypted, LocalDateTime.now().getDayOfMonth(),
						LocalDateTime.now().getMonthValue());
				System.out.println("Decrypted Data : " + decrypted);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
