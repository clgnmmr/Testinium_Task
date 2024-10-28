package hooks;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pages.GeneralPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.Random;


public class Hook implements  AfterEachCallback,BeforeEachCallback {

    public static Random random=new Random();

    GeneralPage generalPage=new GeneralPage();

    @Override
    public void beforeEach(ExtensionContext context) throws InterruptedException {

        generalPage.homePage.entryHomePage();
    }
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {  // Eğer test başarısızsa
            try {
                ReusableMethods.getScreenshot("test");
                Driver.closeDriver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
