package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private static WebDriver driver;
    private static final int timeout = 10;

    private Driver() {

    }



    public static <IOException> WebDriver getDriver() {
        String browser = ConfigurationReader.getProperty("browser");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory",
                System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                        "resources" + File.separator + "downloads");
        prefs.put("plugins.always_open_pdf_externally", true);
        prefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
        prefs.put("behavior", "allow");

        if (driver == null) {

            if ("chrome".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--start-maximized");
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
            } else if ("chrome-headless".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--log-level=1");
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-gpu"); // GPU devre dışı bırakıldı (bazı sistemlerde soruna neden olabilir)
                options.addArguments("--disable-extensions"); // Gereksiz uzantılar devre dışı
                options.addArguments("--disable-dev-shm-usage"); // Bellek paylaşımı sorunlarını önler
                options.addArguments("force-device-scale-factor=0.5");
                options.addArguments("user-agent=User-Agent: Mozilla/109.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.5414.75 Safari/537.36 Edg/109.0.1518.61");
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);

            } else if ("firefox".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addPreference("browser.download.dir", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                        "resources" + File.separator + "downloads");
                options.addPreference("browser.download.folderList", 2);
                options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/octet-stream, application/x-winzip, application/x-pdf, application/x-gzip");
                options.addPreference("pdfjs.disabled", true);
                driver = new FirefoxDriver(options);
                driver.manage().window().maximize();
            } else if ("firefox-headless".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addPreference("browser.download.dir", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                        "resources" + File.separator + "downloads");
                options.addPreference("browser.download.folderList", 2);
                options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/octet-stream, application/x-winzip, application/x-pdf, application/x-gzip");
                options.addPreference("pdfjs.disabled", true);
                options.addArguments("user-agent=User-Agent: Mozilla/109.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.5414.75 Safari/537.36 Edg/109.0.1518.61");
                options.addArguments("-width=1920");
                options.addArguments("-height=1080");
                options.addArguments("--headless");
                driver = new FirefoxDriver(options);
            } else if ("edge".equals(browser)) {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("download.prompt_for_download=false");
                options.setExperimentalOption("prefs", prefs);
                driver = new EdgeDriver(options);
            } else if ("edge-headless".equals(browser)) {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.addArguments("disable-gpu");
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("user-agent=User-Agent: Mozilla/109.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.5414.75 Safari/537.36 Edg/109.0.1518.61");
                options.addArguments("force-device-scale-factor=0.5");
                options.addArguments("download.prompt_for_download=false");
                options.setExperimentalOption("prefs", prefs);
                driver = new EdgeDriver(options);
            }
        }
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;

    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}