package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUI.NewAccountPageUI;

public class NewAccountPageObject extends AbstractPage {
    WebDriver driver;

    public NewAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentOpenDate() {
        return getCurrentDate();
    }

    public boolean isAccountCreateSuccessMessageDisplay() {
        return isElementDisplay(driver, NewAccountPageUI.ACCOUNT_CREATE_SUCCESS_MESSAGE);
    }
}
