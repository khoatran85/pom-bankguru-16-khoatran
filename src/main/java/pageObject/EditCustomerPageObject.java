package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUI.EditCustomerPageUI;

public class EditCustomerPageObject extends AbstractPage {
    WebDriver driver;

    public EditCustomerPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEditCustomerTitleDisplay() {
        return isElementDisplay(driver, EditCustomerPageUI.EDIT_CUSTOMER_PAGE_TITLE);
    }

    public boolean isEditCustomerSuccessMessageDisplay() {
        return isElementDisplay(driver, EditCustomerPageUI.EDIT_CUSTOMER_SUCCESS_MESSAGE);
    }
}
