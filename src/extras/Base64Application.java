package extras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.io.UnsupportedEncodingException;

public class Base64Application {

	public static String encoders(String text, char type) throws UnsupportedEncodingException {
		type = Character.toUpperCase(type);
		switch (type) {
		case 'B':
			return Base64.getEncoder().encodeToString(text.getBytes("UTF-8"));
		case 'U':
			return Base64.getUrlEncoder().encodeToString(text.getBytes("UTF-8"));
		case 'M':
			return Base64.getMimeEncoder().encodeToString(text.getBytes("UTF-8"));
		default:
			return "Invalid Parameter!";
		}
	}

	public static String decoders(String encoded, char type) throws UnsupportedEncodingException {
		type = Character.toUpperCase(type);
		switch (type) {
		case 'B':
			return new String(Base64.getDecoder().decode(encoded), "UTF-8");
		case 'U':
			return new String(Base64.getUrlDecoder().decode(encoded), "UTF-8");
		case 'M':
			return new String(Base64.getMimeDecoder().decode(encoded), "UTF-8");
		default:
			return "Invalid Parameter!";
		}
	}

	public static void main(String[] args) throws IOException {

		String text = "", encoded = "", decoded = "";
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("For exit pass by null to text.");

		while (true) {
			try {
				System.out.println("Text : ");
				text = bufferedReader.readLine();
				if (text.equals("") || text.equals(null)) {
					System.out.println("Program terminated.");
					break;
				}
				System.out.println("Original Data : " + text);
				encoded = encoders(text, 'B');
				System.out.println("Base64 Encoded (Basic) : " + encoded);
				decoded = decoders(encoded, 'B');
				System.out.println("Base64 Decoded (Basic) : " + decoded);
				encoded = encoders(text, 'U');
				System.out.println("Base64 Encoded (URL) : " + encoded);
				decoded = decoders(encoded, 'U');
				System.out.println("Base64 Decoded (URL) : " + decoded);
				encoded = encoders(text, 'M');
				System.out.println("Base64 Encoded (MIME) : " + encoded);
				decoded = decoders(encoded, 'M');
				System.out.println("Base64 Decoded (MIME) : " + decoded);
			} catch (Exception e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
		bufferedReader.close();
	}

}
