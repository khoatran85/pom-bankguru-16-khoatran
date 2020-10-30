package commons;

import org.openqa.selenium.WebDriver;
import pageObject.*;

public class PageGeneratorManager {

    public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
        return new NewCustomerPageObject(driver);
    }

    public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
        return new EditCustomerPageObject(driver);
    }

    public static DeleteCustomerPageObject getDeleteCustomerPage(WebDriver driver) {
        return new DeleteCustomerPageObject(driver);
    }

    public static CustomisedStatementPageObject getCustomisedStatementPage(WebDriver driver) {
        return new CustomisedStatementPageObject(driver);
    }

    public static FundTransferPageObject getFundTransferPage(WebDriver driver) {
        return new FundTransferPageObject(driver);
    }

    public static MiniStatementPageObject getMiniStatementPage(WebDriver driver) {
        return new MiniStatementPageObject(driver);
    }

    public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
        return new NewAccountPageObject(driver);
    }

    public static EditAccountPageObject getEditAccountPage(WebDriver driver) {
        return new EditAccountPageObject(driver);
    }

    public static DeleteAccountPageObject getDeleteAccountPage(WebDriver driver) {
        return new DeleteAccountPageObject(driver);
    }

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static ManagerPageObject getManagerPage(WebDriver driver) {
        return new ManagerPageObject(driver);
    }

    public static ChangePasswordPageObject getChangePasswordPage(WebDriver driver) {
        return new ChangePasswordPageObject(driver);
    }


}

	

	
	
