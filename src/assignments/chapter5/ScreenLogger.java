package assignments.chapter5;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ScreenLogger {

	protected static String count = "";

	public static void main(String[] args) {
		try {
			Robot robot = new Robot();
			String format = "png";
			String fileName = "C:/FullScreenshot" + count + "." + format;

			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, new File(fileName));
			System.out.println("Screenshot saved.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}