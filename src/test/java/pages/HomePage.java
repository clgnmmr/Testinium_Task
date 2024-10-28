package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.Log;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "onetrust-reject-all-handler")
    public WebElement allRejectedButton;

    @FindBy(xpath = "//button[contains(@class, 'o-modal__closeButton')]/*[name()='svg']")
    public WebElement genderAlertCloseButton;

    @FindBy(xpath = "//div[@class='container -wide']/div/div/a")
    public WebElement beymenLogo;

    @FindBy(css = "input[class*='search']")
    public WebElement searchBox;

    @FindBy(xpath = "//button[@class='o-header__search--close -hasButton']")
    public WebElement searchBoxClear;

    @FindBy(className = "o-header__form--close")
    public WebElement cancelSearch;






    public void entryHomePage(){
        Log.startTestCase("User searches for a product and adds it to the cart");
        Driver.getDriver().get(ConfigurationReader.getProperty("homeUrl"));
        Log.info("User navigates to " + ConfigurationReader.getProperty("homeUrl") + " page");
        allRejectedButton.click();
        try {
            if (genderAlertCloseButton.isDisplayed()){
                genderAlertCloseButton.click();
            }
        } catch (Exception e) {
            System.out.println("Gender selection was not made");
        }

        Assertions.assertEquals(ConfigurationReader.getProperty("homeUrl"),Driver.getDriver().getCurrentUrl());
        Assertions.assertTrue(beymenLogo.isDisplayed());
        Log.assertLog("User verifies they are on the homepage");
    }

    public void searchBoxClear(){
        searchBoxClear.click();
        cancelSearch.click();
    }
}
