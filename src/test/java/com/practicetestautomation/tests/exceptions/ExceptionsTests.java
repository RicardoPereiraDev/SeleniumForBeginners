package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTests {

    private  WebDriver driver;
    private Logger logger;
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser){
        //System.out.println("Running test in " + browser);
        logger= Logger.getLogger(LoginTests.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running test in " + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
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
        //Open page
        //WebDriver driver = new FirefoxDriver();
        //driver = new FirefoxDriver();// Here initialize a session of Chrome and create a object of ChromeDriver that implement the interface Webdriver, is like as we had classe Vehicule car = new Mercedes();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test(groups ={"positive", "regression", "smoke"})
    public void testLoginFunctionality(){

        logger.info("Starting testLoginFunctionality"); //Logger is a tool that writes messages about what your program is doing, making it easier to debug and monitor your application.

        //Open page
        //WebDriver driver = new ChromeDriver(); // Here initialize a session of Chrome and create a object of ChromeDriver that implement the interface Webdriver, is like as we had classe Vehicule car = new Mercedes();
        //WebDriver driver = new FirefoxDriver();
        //driver.get("https://practicetestautomation.com/practice-test-login/"); //here we navigate to practice test login page

        //Type username student into Username field
        WebElement usernameInput = driver.findElement(By.id("username")); //here we have inputs login credentials
        logger.info("Type username");
        usernameInput.sendKeys("student");

        //Type password Password123 into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        logger.info("Type password");
        passwordInput.sendKeys("Password123");

        //Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        //WebElement submitButton = driver.findElement(By.id("//button"));
        logger.info("Click Submit button");
        submitButton.click();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) { //can throw excpetion, so is better tratar essa exceção, Se acontecer algum problema durante a espera, trata esse erro."
            throw new RuntimeException(e); //Se a thread for interrompida, lança um novo erro e termina o programa."
        }

        //Verify new page URL contains practicetestautomation.com/logged-in-successfully/
        logger.info("Verify the login functionality");
        String urlExpected = "https://practicetestautomation.com/logged-in-successfully/";
        String atualUrl = driver.getCurrentUrl();
        Assert.assertEquals(atualUrl, urlExpected);

        //Verify new page contains expected text ('Congratulations' or 'successfully logged in')

        //String pageOFSuccess = "https://practicetestautomation.com/logged-in-successfully/";
        String expectedMessage = "Congratulations student. You successfully logged in!";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedMessage));

        //Verify button Log out is displayed on the new page
        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOutButton.isDisplayed());

       // driver.quit();

    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups ={"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage){

        //Test case 2: Negative username test


        //Open page

        logger.info("Starting negativeLoginTest");
        //Type username incorrectUser into Username field
        WebElement incorrectUser = driver.findElement(By.id("username"));
        logger.info("Typing username: " + username);
        incorrectUser.sendKeys(username);

        //Type password Password123 into Password field
        WebElement inputPassword = driver.findElement(By.id("password"));
        logger.info("Typing password");
        inputPassword.sendKeys(password);

        //Push Submit button
       WebElement elementButton = driver.findElement(By.id("submit"));
        //WebElement elementButton = driver.findElement(By.id("//button"));
        logger.info("Click Submit button");
        elementButton.click();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) { //can throw excpetion, so is better tratar essa exceção, Se acontecer algum problema durante a espera, trata esse erro."
            throw new RuntimeException(e); //Se a thread for interrompida, lança um novo erro e termina o programa."
        }

        logger.info("Verify the expected error message: " + expectedErrorMessage);
        //Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());


        //Verify error message text is Your username is invalid!

        String atualErrorMessage = errorMessage.getText();
        Assert.assertEquals(atualErrorMessage, expectedErrorMessage);

    }

}
