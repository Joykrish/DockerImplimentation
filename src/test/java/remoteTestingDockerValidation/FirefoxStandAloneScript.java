package remoteTestingDockerValidation;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxStandAloneScript {
	
	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities ds=DesiredCapabilities.firefox();
		
		URL url=new URL("http://localhost:4444/wd/hub");
		
		RemoteWebDriver driver=new RemoteWebDriver(url,ds);
		driver.get("https://www.google.co.in/");
		System.out.println(driver.getTitle());
		
	}

}
