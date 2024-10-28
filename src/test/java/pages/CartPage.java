package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CartPage {

    public CartPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='m-basket__optionHeader']")
    public WebElement productTitle;

    @FindBy(xpath = "//span[@class='priceBox__salePrice']")
    public WebElement productPrice;

    @FindBy(id = "quantitySelect0-key-0")
    public WebElement cartDropDown;

    @FindBy(className = "m-basket__remove")
    public  WebElement deleteButton;

    @FindBy(xpath = "(//strong[@class='m-empty__messageTitle'])[1]")
    public  WebElement emptyMessage;


}
