package assignments.chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DHKETestClass {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int number = 0, modulo = 0;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Number : ");
		number = Integer.parseInt(bufferedReader.readLine());
		System.out.println("Modulo : ");
		modulo = Integer.parseInt(bufferedReader.readLine());
		if(DiffieHellmanKeyExchange.isPrimitiveRoot(number, modulo)){
			System.out.println("It is primivitve root");
		} else {
			System.out.println("It is not primitive root!");
		}
	}

}
