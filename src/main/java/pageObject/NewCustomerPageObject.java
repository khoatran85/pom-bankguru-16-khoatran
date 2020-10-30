package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUI.NewCustomerPageUI;

public class NewCustomerPageObject extends AbstractPage {
    WebDriver driver;

    public NewCustomerPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public boolean isCustomerRegisterSuccessMessageDisplay() {
        return isElementDisplay(driver, NewCustomerPageUI.CUSTOMER_REGISTER_SUCCESS_MESSAGER);
    }


    public void selectGenderValue(String gender) {
        if (gender.contains("male")) {
            clickToElement(driver, NewCustomerPageUI.GENDER_RADIO, "m");
        }
        clickToElement(driver, NewCustomerPageUI.GENDER_RADIO, "f");
        ;
    }
}
