package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class CustomisedStatementPageObject extends AbstractPage {
    WebDriver driver;

    public CustomisedStatementPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
