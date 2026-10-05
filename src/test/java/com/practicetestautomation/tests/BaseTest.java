package com.practicetestautomation.tests;

import com.practicetestautomation.tests.exceptions.ExceptionsTests;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {

    protected WebDriver driver;
    protected Logger logger= Logger.getLogger(BaseTest.class.getName());

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")



    /*
    Sem o selenium grid era feito desta maneira infra:

    LOCAL
            ChromeDriver
    ↓
    Chrome local
    */

    /*
    public void setUp(@Optional("chrome") String browser){
        //System.out.println("Running test in " + browser);
        logger= Logger.getLogger(ExceptionsTests.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running test in " + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                //driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                logger.warning("Configuration for " + browser + "is missing, so running tests in Chrome by default ");
                //System.out.println("Configuration for " + browser + "Is missing, so running tests in Chrome by default ");
                driver = new ChromeDriver();
                break;
        }

        // tipo de espera implícita
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //  espera implícita

        //OPEN PAGE
        //WebDriver driver = new FirefoxDriver();
        //driver = new FirefoxDriver();// Here initialize a session of Chrome and create a object of ChromeDriver that implement the interface Webdriver, is like as we had classe Vehicule car = new Mercedes();
        //driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }
*/

            /*

   Com GRID
   RemoteWebDriver
    ↓
    localhost:4444
            ↓
    Selenium Grid
    ↓
    Chrome
    */
    public void setup(String browser) throws MalformedURLException {


        /*ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable");

        driver = new RemoteWebDriver(
                new URL("http://localhost:4444"),
                options
        );

        driver.manage().window().maximize();

         */

        MutableCapabilities options;

        if (browser.equalsIgnoreCase("chrome")){

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setBrowserVersion("stable");
            options=chromeOptions;

        } else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBrowserVersion("stable");
            options= firefoxOptions;

        } else if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setBrowserVersion("stable");
            options= edgeOptions;

        }else {

            throw new IllegalArgumentException(
                    "Browser not supported: " + browser
            );
        }
        driver = new RemoteWebDriver(
                new URL("http://localhost:4444"),
                options
                );

            driver.manage().window().maximize();
            logger.info("Starting browser: " + browser);

    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){

        if(driver != null){
            driver.quit();
        }

        logger.info("Browser is closed");
    }
}
