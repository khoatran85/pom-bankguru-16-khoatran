package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DeleteCustomerPageObject extends AbstractPage {
    WebDriver driver;

    public DeleteCustomerPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
