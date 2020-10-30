package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class EditAccountPageObject extends AbstractPage {
    WebDriver driver;

    public EditAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
