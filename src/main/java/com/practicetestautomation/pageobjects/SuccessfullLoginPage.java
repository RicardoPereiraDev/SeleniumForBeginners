package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessfullLoginPage extends BasePage {

    private By logOutButtonLocator = By.linkText("Log out");

    public SuccessfullLoginPage(WebDriver driver){
       super(driver);
    }


  public boolean isLogOutButtonDisplayed(){
        return isDisplayed(logOutButtonLocator);
  }

  public void load(){
        waitForElement(logOutButtonLocator); // this load method waits specifically for the logout button to become visible on the page
  }
}
