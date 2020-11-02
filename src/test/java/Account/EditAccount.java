package Account;

import commons.AbstractTest;
import commons.GlobalConstants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.EditAccountPageObject;
import pageObject.LoginPageObject;
import pageObject.ManagerPageObject;
import pageObject.NewAccountPageObject;
import pageUI.AbstractPageUI;

public class EditAccount extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    EditAccountPageObject editAccountPage;
    ManagerPageObject managerPage;

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        loginPage = openLoginPage(driver);
        loginPage.inputToTextboxByTittle(driver, GlobalConstants.USER_ID, "UserID");
        loginPage.inputToTextboxByTittle(driver, GlobalConstants.PASSWORD, "Password");
        managerPage = loginPage.clickToButtonByName(driver, "LOGIN");
        editAccountPage = (EditAccountPageObject) managerPage.clickToPageByName(driver, "Edit Account");
    }

    @Test
    public void EditAccount_01_Account_Number_Cannot_Be_Empty() {
        log.info("NewAccount_01_CustomerID_Cannot_Be_Empty - Step 01 - Let 'Account No' field is empty");
        editAccountPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "Account No");

        log.info("NewAccount_01_CustomerID_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(editAccountPage.getErrorMessageByTextboxTitle(driver, "Account No"), "Account Number is required");
    }

    @Test
    public void EditAccount_02_Account_Number_Must_Be_Numeric() {
        log.info("NewAccount_02_CustomerID_Must_Be_Numeric - Step 01 - Input value '1234Acc' to 'Account No' textbox");
        editAccountPage.inputToTextboxByTittle(driver, "1234Acc", "Account No");

        log.info("NewAccount_02_CustomerID_Must_Be_Numeric - Step 02 - Verify error message display");
        verifyEquals(editAccountPage.getErrorMessageByTextboxTitle(driver, "Account No"), "Characters are not allowed");

        log.info("NewAccount_02_CustomerID_Must_Be_Numeric - Step 03 - Input value 'Acc123' to 'Account No' textbox");
        editAccountPage.inputToTextboxByTittle(driver, "Acc123", "Account No");

        log.info("NewAccount_02_CustomerID_Must_Be_Numeric - Step 04 - Verify error message display");
        verifyEquals(editAccountPage.getErrorMessageByTextboxTitle(driver, "Account No"), "Characters are not allowed");
    }

    @Test
    public void EditAccount_03_Account_Number_Cannot_Have_Special_Character() {
        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 01 - Input value '123!@#' to 'Account No' textbox");
        editAccountPage.inputToTextboxByTittle(driver, "123!@#", "Customer id");

        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(editAccountPage.getErrorMessageByTextboxTitle(driver, "Account No"), "Special characters are not allowed");

        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 01 - Input value '!@#' to 'Account No' textbox");
        editAccountPage.inputToTextboxByTittle(driver, "!@#", "Account No");

        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(editAccountPage.getErrorMessageByTextboxTitle(driver, "Account No"), "Special characters are not allowed");
    }

    @Test
    public void EditAccount_04_Account_Number_cannot_Have_Blank_Space() {
        log.info("EditCustomer_04_CustomerID_cannot_Have_Blank_Space - Step 01 - Input value '123 45' to 'Account No' textbox");
        editAccountPage.inputToTextboxByTittle(driver, "12 45", "Account No");

        log.info("EditCustomer_04_CustomerID_cannot_Have_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(editAccountPage.getErrorMessageByTextboxTitle(driver, "Account No"), "Characters are not allowed");
    }

    @Test
    public void EditAccount_05_Account_Number_Cannot_Have_First_Charactor_As_Bland_Space() {
        log.info("EditCustomer_05_CustomerID_Cannot_Have_First_Charactor_As_Bland_Space - Step 01 - Input value ' 2313' to 'Account No' textbox");
        editAccountPage.inputToTextboxByTittle(driver, " 2313", "Account No");

        log.info("EditCustomer_05_CustomerID_Cannot_Have_First_Charactor_As_Bland_Space - Step 02 - Verify error message display");
        verifyEquals(editAccountPage.getErrorMessageByTextboxTitle(driver, "Account No"), "First character can not have space");
    }

    @Test
    public void EditAccount_06_Input_valid_Account_Number() {
        log.info("EditAccount_06_Input_valid_Account_Number - Step 01 - Input to 'Account No' textbox with value: " + NewAccount.accountID);
        editAccountPage.inputToTextboxByTittle(driver, NewAccount.accountID, "Account No");

        log.info("EditAccount_06_Input_valid_Account_Number - Step 02 - Click to Submit button");
        editAccountPage.clickToButtonByName(driver, "Submit");

        log.info("EditAccount_06_Input_valid_Account_Number - Step 03 - Verify error message display");
        verifyTrue(editAccountPage.isEditAccountFormTitleDisplay());
    }

}
