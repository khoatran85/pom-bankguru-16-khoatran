package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class NewAccountPageObject extends AbstractPage {
    WebDriver driver;

    public NewAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
