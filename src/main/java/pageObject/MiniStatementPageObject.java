package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class MiniStatementPageObject extends AbstractPage {
    WebDriver driver;

    public MiniStatementPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
