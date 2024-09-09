package remoteTestingDockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StartDocker {

	public void startDocker() throws IOException, InterruptedException {
		boolean flag = false;
		BufferedReader reader = null;
		String currentLine = null;
		Runtime runTime = Runtime.getRuntime();

		runTime.exec("cmd /c start docker_up.bat");

		System.out.println("executed docker-up file");
		// String f = "output.txt";

		File file = new File("output.txt");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 90);
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
				if (currentLine.contains("The node is registered to the hub and ready to use")) {

					System.out.println("found my text:-The node is registered to the hub and ready to use");
					flag = true;
					break;
				}

				currentLine = reader.readLine();

			}
			reader.close();
		}

		Assert.assertTrue(flag);
		runTime.exec("cmd /c start scale.bat");
		Thread.sleep(15000);
	}
}