package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class PositiveLoginTests {

    @Test
    public void testLoginFunctionality(){

        //Open page
        //WebDriver driver = new ChromeDriver(); // Here initialize a session of Chrome and create a object of ChromeDriver that implement the interface Webdriver, is like as we had classe Vehicule car = new Mercedes();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/"); //here we navigate to practice test login page

        //Type username student into Username field
        WebElement usernameInput = driver.findElement(By.id("username")); //here we have inputs login credentials
        usernameInput.sendKeys("student");

        //Type password Password123 into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Password123");

        //Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        //submitButton.click();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) { //can throw excpetion, so is better tratar essa exceção, Se acontecer algum problema durante a espera, trata esse erro."
            throw new RuntimeException(e); //Se a thread for interrompida, lança um novo erro e termina o programa."
        }

        //Verify new page URL contains practicetestautomation.com/logged-in-successfully/
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

        driver.quit();

    }

    @Test
    public void incorrectUsernameTest(){

        //Test case 2: Negative username test


        //Open page
        WebDriver driver = new ChromeDriver();

        driver.get("https://practicetestautomation.com/practice-test-login/");

        //Type username incorrectUser into Username field
        WebElement incorrectUser = driver.findElement(By.id("username"));
        incorrectUser.sendKeys("incorrectUsername");

        //Type password Password123 into Password field
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("Password123");

        //Push Submit button
        WebElement elementButton = driver.findElement(By.id("submit"));
        elementButton.click();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) { //can throw excpetion, so is better tratar essa exceção, Se acontecer algum problema durante a espera, trata esse erro."
            throw new RuntimeException(e); //Se a thread for interrompida, lança um novo erro e termina o programa."
        }

        //Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());


        //Verify error message text is Your username is invalid!
        String expectedMessageErrorDisplay = "Your username is invalid!";
        String atualErrorMessage = errorMessage.getText();
        Assert.assertEquals(atualErrorMessage, expectedMessageErrorDisplay);

        driver.quit();
    }
    @Test
    public void incorrectPasswordTest(){

        //Test case 3: Negative password test


        //Open page
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        //Type username student into Username field
        WebElement inputUsername = driver.findElement(By.id("username"));
        inputUsername.sendKeys("student");

        //Type password incorrectPassword into Password field
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("incorrectPassword");

        //Push Submit button
        WebElement elementButton = driver.findElement(By.id("submit"));
        elementButton.click();

        //Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());

        //Verify error message text is Your password is invalid!
        String errorMessageExpected = "Your password is invalid!";
        String atualErrorMessage = errorMessage.getText();
        Assert.assertEquals(atualErrorMessage, errorMessageExpected);

        driver.quit();
    }
}
