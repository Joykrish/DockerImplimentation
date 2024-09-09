package remoteTestingDockerValidation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeTest1 {

	@BeforeTest
	public void startDocketScale() throws IOException, InterruptedException {
		File fi = new File("output.txt");
		if (fi.delete()) {
			System.out.println("File deleted Successfully");
		} else {
			System.out.println("File is not deleted Successfully");
		}
		StartDocker st = new StartDocker();
		st.startDocker();

	}
@AfterTest
	public void stopDockerDeleteFile() throws IOException, InterruptedException {
	
		StopDocker sp = new StopDocker();
		sp.stopDocker();
	}

	@Test
	public void Chrome1() throws MalformedURLException {
		DesiredCapabilities ds = DesiredCapabilities.chrome();

		URL url = new URL("http://localhost:4444/wd/hub");

		RemoteWebDriver driver = new RemoteWebDriver(url, ds);
		driver.get("https://gmail.com");
		System.out.println(driver.getTitle());

	}

}
