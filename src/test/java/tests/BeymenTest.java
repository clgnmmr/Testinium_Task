package tests;

import hooks.Hook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GeneralPage;
import utilities.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import static hooks.Hook.random;

@ExtendWith(Hook.class)
@Execution(ExecutionMode.CONCURRENT)
public class BeymenTest {

    GeneralPage generalPage = new GeneralPage();


    @BeforeAll
    public static void setOnce() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ConfigurationReader.getProperty("productInfoPath"), false))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ExcelUtil.loadExcel(ConfigurationReader.getProperty("wordExcelPath"), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void test01() throws InterruptedException {
        // User enters the first search term in the search box
        generalPage.homePage.searchBox.sendKeys(ExcelUtil.getCellData(0, 0));
        Log.info("User enters the first search term: " + ExcelUtil.getCellData(0, 0));

        // User clears the search box
        generalPage.homePage.searchBoxClear();
        Log.info("User clears the search box.");

        // Wait until the search box is visible
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(generalPage.homePage.searchBox));
        Log.info("Waiting for the search box to be visible.");

        // User enters the second search term and hits Enter
        String secondWord = ExcelUtil.getCellData(1, 0);
        generalPage.homePage.searchBox.sendKeys(secondWord, Keys.ENTER);
        Log.info("User enters the second search term: " + secondWord + " and presses Enter.");

        // Randomly select a product from the product list
        int number = random.nextInt(0, generalPage.productPage.productList.size());
        ReusableMethods.scrollIntoView(generalPage.productPage.productList.get(number));
        generalPage.productPage.productList.get(number).click();
        Log.info("User selects a random product from the product list.");

        // Write the product title and price to a text file
        WriteToText.writeToMethod(generalPage.productPage.productTitle.getText(), ConfigurationReader.getProperty("productInfoPath"));
        WriteToText.writeToMethod(generalPage.productPage.productPrice.getText(), ConfigurationReader.getProperty("productInfoPath"));
        Log.info("User writes the product title and price to the product info file.");

        // Scroll into view for product size selection
        ReusableMethods.scrollIntoView(generalPage.productPage.productSize.getFirst());
        int sizeNumber = random.nextInt(0, generalPage.productPage.productSize.size());
        generalPage.productPage.productSize.get(sizeNumber).click();
        Log.info("User selects a random product size.");

        // User adds the product to the cart
        generalPage.productPage.addToCart.click();
        Log.info("User clicks 'Add to Cart'.");

        // User navigates to the cart
        ReusableMethods.scrollIntoView(generalPage.productPage.myCartButton, false);
        generalPage.productPage.myCartButton.click();
        Log.info("User navigates to the cart.");

        // Verify that the price in the cart matches the expected price
        String expectedPrice = ReadToText.readText(ConfigurationReader.getProperty("productInfoPath")).split(" ")[0];
        String actualPrice = generalPage.cartPage.productPrice.getText().split(",")[0];
        Assertions.assertEquals(expectedPrice, actualPrice);
        Log.assertLog("User compares the price in the product list with the price in the cart.");

        // User increases the product quantity in the cart
        String value = "2";
        ReusableMethods.dropdownHandle(generalPage.cartPage.cartDropDown, value);
        Assertions.assertTrue(generalPage.cartPage.cartDropDown.getText().contains(value));
        Log.assertLog("User checks that the quantity of the product has increased.");

        // User deletes the product from the cart
        Thread.sleep(3000);
        ReusableMethods.waitForClickability(generalPage.cartPage.deleteButton, 5);
        generalPage.cartPage.deleteButton.click();
        Log.info("User clicks the delete button to remove the product from the cart.");

        // User verifies that the cart is empty
        ReusableMethods.waitForVisibility(generalPage.cartPage.emptyMessage, 5);
        Assertions.assertTrue(generalPage.cartPage.emptyMessage.isDisplayed());
        Log.assertLog("User verifies that there are no products in the cart.");
    }


    @AfterAll
    public static void tearDownAll() {
        Driver.closeDriver();
    }
}
