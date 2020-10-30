package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class FundTransferPageObject extends AbstractPage {
    WebDriver driver;

    public FundTransferPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
