package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class ManagerPageObject extends AbstractPage {
    WebDriver driver;

    public ManagerPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
