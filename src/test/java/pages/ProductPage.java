package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;
import java.util.Random;

import static hooks.Hook.random;

public class ProductPage {

    public ProductPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBys({
            @FindBy(xpath = "//div[@id='productList']/div")
    })
    public List<WebElement> productList;

    @FindBy(xpath = "//h1")
    public WebElement productTitle;

    @FindBy(className = "m-price__new")
    public WebElement productPrice;

    @FindBy(xpath = "//div[@class='m-variation']/span[@class='m-variation__item']")
    public List<WebElement> productSize;

    @FindBy(xpath = "//button[@id='addBasket']")
    public WebElement addToCart;

    @FindBy(xpath = "//a[@title='My Cart'] | //a[@title='Sepetim']")
    public WebElement myCartButton;





    public WebElement chooseProduct(){
        int number=random.nextInt(0,productList.size());
        return productList.get(number);
    }
}
