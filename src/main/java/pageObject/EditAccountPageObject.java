package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUI.EditAccountPageUI;

public class EditAccountPageObject extends AbstractPage {
    WebDriver driver;

    public EditAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEditAccountFormTitleDisplay() {
        return isElementDisplay(driver, EditAccountPageUI.EDIT_ACCOUNT_FORM_PAGE_TITLE);
    }
}
