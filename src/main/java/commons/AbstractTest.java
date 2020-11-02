package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class AbstractTest extends AbstractPage {
	protected final Log log;
	WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	public void deleteAllFilesInReportNGScreenshot() {
		log.info("-------START delete image attachment files in folder---------");
		deleteAllFilesInScreenShotFolder();
		deleteAllFilesInTemp();
		log.info("-------END delete image attachment files in folder---------");

	}

	public void deleteAllFilesInScreenShotFolder() {
		try {
			String pathScreenshotFolder = GlobalConstants.ROOT_FOLDER + "\\ReportNGScreenShots";
			File file = new File(pathScreenshotFolder);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void deleteAllFilesInTemp() {
		try {
			String pathTempFolder = "C:\\Users\\Khoa\\AppData\\Local\\Temp";
			File file = new File(pathTempFolder);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver getBrowserDriver(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();

			// set profile auto download file
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("browser.download.folderList", 2);
			options.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FOLDER);
			options.addPreference("browser.download.useDownloadDir", true);
			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			options.addPreference("pdfjs.disabled", true); // disable the built-in PDF viewer
			options.setCapability("moz:useNonSpecCompliantPointerOrigin", true);//config run repeatedly hover to element on Firefox browser
//			options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

//			options.addArguments("-private");
			driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			// set profile auto download file
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FOLDER);
			prefs.put("download.prompt_for_download", false);
			options.setExperimentalOption("prefs", prefs);
//			options.addArguments("-incognito");
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();

			options.getCapability("no-first-run");
			options.getCapability("force-device-scale-factor=1");
			options.getCapability("safebrowsing.enabled");
			options.setCapability("download.default_directory", GlobalConstants.DOWNLOAD_FOLDER);

			driver = new EdgeDriver(options);
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("Please choose your browser!!");
		}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get(GlobalConstants.LIVE_GURU99_USER_URL);
		return driver;
	}

	public static int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	private boolean checkTrue(boolean condition) {
		boolean status = true;
		try {

			Assert.assertTrue(condition);

		} catch (Throwable e) {
			status = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return status;

	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean status = true;
		try {
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return status;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean status = true;
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return status;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected void closeBrowserAndDriver(WebDriver driver) {
		try {
			// get ra tên của OS và convert qua chữ thường
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			// Khai báo 1 biến command line để thực thi
			String cmd = "";
			if (driver != null) {
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}


}
