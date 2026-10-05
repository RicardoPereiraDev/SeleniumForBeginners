package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccessfullLoginPage;
import com.practicetestautomation.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTests extends BaseTest {


    @Test(groups ={"positive", "regression", "smoke"})
    public void testLoginFunctionality(){

        logger.info("Starting testLoginFunctionality"); //Logger is a tool that writes messages about what your program is doing, making it easier to debug and monitor your application.
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visitUrl();
        SuccessfullLoginPage successfullLoginPage = loginPage.executeLogin("student","Password123");
        successfullLoginPage.load();
        //Open page
        //WebDriver driver = new ChromeDriver(); // Here initialize a session of Chrome and create a object of ChromeDriver that implement the interface Webdriver, is like as we had classe Vehicule car = new Mercedes();
        //WebDriver driver = new FirefoxDriver();
        //driver.get("https://practicetestautomation.com/practice-test-login/"); //here we navigate to practice test login page


        /*

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

      */


        //Verify new page URL contains practicetestautomation.com/logged-in-successfully/
        logger.info("Verify the login functionality");
        //String urlExpected = "https://practicetestautomation.com/logged-in-successfully/";
        //String atualUrl = successfullLoginPage.getCurrentUrl();
        Assert.assertEquals(successfullLoginPage.getCurrentUrl(),"https://practicetestautomation.com/logged-in-successfully/");

        //Verify new page contains expected text ('Congratulations' or 'successfully logged in')

        //String pageOFSuccess = "https://practicetestautomation.com/logged-in-successfully/";
        //String expectedMessage = "Congratulations student. You successfully logged in!";
        //String pageSource = successfullLoginPage.getPageSource();
        Assert.assertTrue(successfullLoginPage.getPageSource().contains("Congratulations student. You successfully logged in!"));

        //Verify button Log out is displayed on the new page
       // WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(successfullLoginPage.isLogOutButtonDisplayed());

       // driver.quit();

    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups ={"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage){

        //Test case 2: Negative username test
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visitUrl();
        loginPage.executeLogin(username, password);
        //Verify error message text is Your username is invalid!
        Assert.assertEquals(loginPage.getErrorMessage(),expectedErrorMessage);

        /*
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

        */

    }

}
