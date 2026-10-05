package com.practicetestautomation.tests.exceptions;

import com.practicetestautomation.pageobjects.ExceptionsPage;
import com.practicetestautomation.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTests extends BaseTest {


    @Test
    public void noSuchElementExceptionTest(){

        logger.info("Starting noSuchElementExceptionTest"); //Logger is a tool that writes messages about what your program is doing, making it easier to debug and monitor your application.
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.url();
        exceptionsPage.addButton();
        //exceptionsPage.load();
        Assert.assertTrue(exceptionsPage.isRowTwoDisplayedAfterWait(),"Row 2 input field is not displayed");




        /*

        //Without POM - Model page object

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        //Click Add button
        WebElement elementButton = driver.findElement(By.id("add_btn"));
        logger.info("Click Add button");
        elementButton.click();

        //Tempo de espera explicito
        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));

        //Verify Row 2 input field is displayed
        logger.info("Verify Row 2 input is displayed");
       // WebElement row2InputField = driver.findElement(By.xpath("//div[@id='row2']/input"));
        Assert.assertTrue(row2InputField.isDisplayed(),"Row 2 input field is not displayed");


         */
    }

    @Test
    public void timeoutExceptionTest(){
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.url();
        exceptionsPage.addButton();
        Assert.assertTrue(exceptionsPage.isRowTwoDisplayedAfterWait(),"Row 2 input field is not displayed");

        /*

        //Click Add button
        WebElement elementButton = driver.findElement(By.id("add_btn"));
        logger.info("Click Add button");
        elementButton.click();

        //Tempo de espera explicito



        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        boolean isDisplayed = row2InputField.isDisplayed();
        logger.info("Row 2 displayed: " + isDisplayed);
        Assert.assertTrue(isDisplayed, "Row 2 input field is not displayed");

        //Verify validações
        Assert.assertTrue(row2InputField.isDisplayed(),"Row 2 input field is not displayed" );
       // Assert.assertTrue(logger.info("Row 2 displayed: "+ row2InputField.isDisplayed()));


         */
    }



    //Test case 2: ElementNotInteractableException
    //Open page


    @Test
    public void ElementNotInteractableException(){

        ExceptionsPage exceptionsPage = new ExceptionsPage( driver);
        exceptionsPage.url();
        exceptionsPage.addButton();
        exceptionsPage.isRowTwoDisplayedAfterWait();
        exceptionsPage.enterFood("Orange");
        exceptionsPage.saveButton();
        String expectedMessage = "Row 2 was saved";

        Assert.assertEquals(exceptionsPage.verifyTextSavedInRows(), expectedMessage,"ATTENTION-Row 2 was not saved!!");


        /*
         Without POM

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));

        //2-Click Add button
        WebElement elementButton = driver.findElement(By.id("add_btn"));
        logger.info("Click Add button");
        elementButton.click();

        //3-Wait for the second row to load and Verify validações
        WebElement waitFor2RowInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));

        //4-Type text into the second input field
        waitFor2RowInput.sendKeys("natural juice");

        //5-Click Save button using locator By.name(“Save”)
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row2']/button[@name= 'Save']"));
        saveButton.click();

        //6-Verify text saved
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String atualMessage = successMessage.getText();
        logger.info("Confirmation message: " + atualMessage);
        String expectedMessage = "Row 2 was saved";

        //verifies that the website confirms the save
        Assert.assertEquals(atualMessage, expectedMessage, "Message is not expected"); //if run fail show the message "Message is not expected"

        //If i wanted to verify the input value, i would use below code:
        String value = waitFor2RowInput.getAttribute("value");
        logger.info("Input value: " + value);
        Assert.assertEquals(value, "natural juice"); //verifies that the input field still contains the expected text.


         */

    }

    @Test
    public void InvalidElementStateException(){

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.url();
        exceptionsPage.editButton();

        exceptionsPage.clearRow1();

        //Type text into the input field
        exceptionsPage.enterTextRow1("banana");

        //Click Save button using locator By.name(“Save”)
        exceptionsPage.saveButtonRow1();

        //Verify text saved = Row 1 was saved
        Assert.assertEquals(exceptionsPage.verifyTextSavedInRows(),"Row 1 was saved", "Row 1 was not saved" );

        /*

        //Test case 3: InvalidElementStateException

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));

        WebElement inputFieldRow1 = driver.findElement(By.xpath("//div[@id='row1']/input"));

        //Click in button Edit
        WebElement editButtonWait = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit_btn")));
        editButtonWait.click();

        //Clear input field
        inputFieldRow1.clear();
        logger.info("Clear input field of row1");

        //Type text into the input field
        inputFieldRow1.sendKeys("aaa");

        //Click Save button using locator By.name(“Save”)
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row1']/button[@name= 'Save']"));
        saveButton.click();


        //Verify text saved
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String atualMessage = successMessage.getText();
        logger.info("Confirmation message: " + atualMessage);
        String expectedMessage = "Row 1 was saved";

        //verifies that the website confirms the save
        Assert.assertEquals(atualMessage, expectedMessage, "Message is not expected"); //if run fail show the message "Message is not expected"


         */
    }

    @Test
    public void StaleElementReferenceException(){

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.url();
        exceptionsPage.addButton();
        //Verify instruction text element is no longer displayed
        Assert.assertTrue(exceptionsPage.instructionTextElemHiddenAfterWait(),"in other hand, the message of Instructions is still displayed");

        //Test case 4: StaleElementReferenceException

        /*
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));

        //Test case 4: StaleElementReferenceException
        //Open page
        //Find the instructions text element
       // WebElement instructionsText = driver.findElement(By.id("instructions"));



        //Click in button Add
        WebElement addButton=driver.findElement(By.id("add_btn"));
        addButton.click();
        logger.info("Click in add button");

        //Verify instruction text element is no longer displayed
       Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))));

         */

    }


}
