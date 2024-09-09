package remoteTestingDockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;



public class StopDocker {

	public void stopDocker() throws IOException, InterruptedException {
		boolean flag = false;
		BufferedReader reader = null;
		String currentLine = null;
		Runtime runTime = Runtime.getRuntime();

		runTime.exec("cmd /c start docker_down.bat");

		// String f = "output.txt";

		File file = new File("output.txt");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 300);
		long stopnow = cal.getTimeInMillis();

		while (System.currentTimeMillis() < stopnow) {

			if (flag == true) {
				break;
			}
			while (!file.exists()) {
				Thread.sleep(3000);
			}
			reader = new BufferedReader(new FileReader(file));
			currentLine = reader.readLine();

			while (currentLine != null && !flag) {
				if (currentLine.contains("selenium-hub exited with code")) {

					System.out.println("found the text:- selenium-hub exited with code");
					flag = true;
					break;
				}

				currentLine = reader.readLine();

			}
			reader.close();
		}
		Thread.sleep(3000);

		Assert.assertTrue(flag);

	}
}