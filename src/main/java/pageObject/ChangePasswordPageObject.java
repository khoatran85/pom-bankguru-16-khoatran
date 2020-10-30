package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class ChangePasswordPageObject extends AbstractPage {
    WebDriver driver;

    public ChangePasswordPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
