package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExceptionsPage extends BasePage {

    private By addButtonLocator = By.id("add_btn");

    private By row2InputField = By.xpath("//div[@id='row2']/input");
    private By row1InputField = By.xpath("//div[@id='row1']/input");

    private By errorMessageLocator = By.id("error");
    private By saveButton = By.xpath("//div[@id='row2']/button[@name= 'Save']");
    private By saveButton1 = By.xpath("//div[@id='row1']/button[@name= 'Save']");
   // private By verifyTextSaveInButton = By.xpath("//div[@id='row2']/button[@name= 'Save']");

    private By confirmationSavedRows = By.id("confirmation");
    private By clearInputField1 = By.xpath("//div[@id='row1']/input");

    private By editButtonLocator = By.id("edit_btn");

    private By instructionsTextLocator = By.id("instructions");


    public ExceptionsPage(WebDriver driver){

        super(driver);
    }


    public void url(){

        super.visitUrl("https://practicetestautomation.com/practice-test-exceptions/");
    }

        //Click Add button
    public void addButton(){

        driver.findElement(addButtonLocator).click();
    }

    //Tempo de espera explicito
    public void load(){
        waitForElement(row2InputField); // this load method waits specifically for the logout button to become visible on the page
    }

    //if Element Is There After Wait return True, else, return false
    //Verify Row 2 input field is displayed
    public boolean isRowTwoDisplayedAfterWait(){

        return ifElemIsThereAfterWait(row2InputField);
    }


    /*
    public boolean isRow2InputFieldDisplayed(){
        return isDisplayed(row2InputField);
    }
     */

    public String getErrorMessage(){
        WebElement errorMessageElement = waitForElement( errorMessageLocator);

        return errorMessageElement.getText();

    }

    //Method of enter text in 2row input field
    public void enterFood(String textFood){

        driver.findElement(row2InputField).sendKeys(textFood);
    }

    public void saveButton(){

        driver.findElement(saveButton).click();
    }

    public String verifyTextSavedInRows(){
        return waitForElement(confirmationSavedRows).getText();
    }

    public void clearRow1(){
        WebElement clearText = driver.findElement(clearInputField1);
         clearText.clear();

    }

    //Method of enter text in 1row input field
    public void enterTextRow1(String text){

        driver.findElement(row1InputField).sendKeys(text);
    }

    public void saveButtonRow1(){

        driver.findElement(saveButton1).click();
    }

    public void editButton(){
        driver.findElement(editButtonLocator).click();
    }
    //Verify instruction text element is no longer displayed


    public boolean instructionTextElemHiddenAfterWait(){
        return waitForHidden(instructionsTextLocator);
    }

}
