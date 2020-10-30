package Account;

import commons.AbstractTest;
import commons.GlobalConstants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.LoginPageObject;
import pageObject.ManagerPageObject;
import pageObject.NewAccountPageObject;
import pageUI.AbstractPageUI;

public class NewAccount extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    NewAccountPageObject newAccountPage;
    ManagerPageObject managerPage;

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        loginPage = openLoginPage(driver);
        loginPage.inputToTextboxByTittle(driver, GlobalConstants.USER_ID, "UserID");
        loginPage.inputToTextboxByTittle(driver, GlobalConstants.PASSWORD, "Password");
        managerPage = loginPage.clickToButtonByName(driver, "LOGIN");
        newAccountPage = (NewAccountPageObject) managerPage.clickToPageByName(driver, "New Account");
    }

    @Test
    public void NewAccount_01_CustomerID_Cannot_Be_Empty() {
        log.info("NewAccount_01_CustomerID_Cannot_Be_Empty - Step 01 - Let 'Customer id' field is empty");
        newAccountPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "Customer id");

        log.info("NewAccount_01_CustomerID_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Customer id"), "Customer ID is required");
    }

    @Test
    public void NewAccount_02_CustomerID_Must_Be_Numeric() {
        log.info("NewAccount_02_CustomerID_Must_Be_Numeric - Step 01 - Input value '1234Acc' to 'Customer id' textbox");
        newAccountPage.inputToTextboxByTittle(driver, "1234Acc", "Customer id");

        log.info("NewAccount_02_CustomerID_Must_Be_Numeric - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Customer id"), "Characters are not allowed");

        log.info("NewAccount_02_CustomerID_Must_Be_Numeric - Step 03 - Input value 'Acc123' to 'Customer id' textbox");
        newAccountPage.inputToTextboxByTittle(driver, "Acc123", "Customer id");

        log.info("NewAccount_02_CustomerID_Must_Be_Numeric - Step 04 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Customer id"), "Characters are not allowed");
    }

    @Test
    public void NewAccount_03_CustomerID_Cannot_Have_Special_Character() {
        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 01 - Input value '123!@#' to 'Customer id' textbox");
        newAccountPage.inputToTextboxByTittle(driver, "123!@#", "Customer id");

        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Customer id"), "Special characters are not allowed");

        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 01 - Input value '!@#' to 'Customer id' textbox");
        newAccountPage.inputToTextboxByTittle(driver, "!@#", "Customer id");

        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Customer id"), "Special characters are not allowed");
    }

    @Test
    public void NewAccount_04_CustomerID_cannot_Have_Blank_Space() {
        log.info("EditCustomer_04_CustomerID_cannot_Have_Blank_Space - Step 01 - Input value '123 45' to 'Customer id' textbox");
        newAccountPage.inputToTextboxByTittle(driver, "12 45", "Customer id");

        log.info("EditCustomer_04_CustomerID_cannot_Have_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Customer id"), "Characters are not allowed");
    }

    @Test
    public void NewAccount_05_CustomerID_Cannot_Have_First_Charactor_As_Bland_Space() {
        log.info("EditCustomer_05_CustomerID_Cannot_Have_First_Charactor_As_Bland_Space - Step 01 - Input value ' 2313' to 'Customer id' textbox");
        newAccountPage.inputToTextboxByTittle(driver, " 2313", "Customer id");

        log.info("EditCustomer_05_CustomerID_Cannot_Have_First_Charactor_As_Bland_Space - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Customer id"), "First character can not have space");
    }

    @Test
    public void NewAccount_06_Initial_Deposit_Cannot_Be_Empty() {
        log.info("NewAccount_06_Initial_Deposit_Cannot_Be_Empty - Step 01 - Let 'Initial deposit' field is empty");
        newAccountPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "Initial deposit");

        log.info("NewAccount_06_Initial_Deposit_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Initial deposit"), "Initial Deposit must not be blank");
    }

    @Test
    public void NewAccount_07_Initial_Deposit_Must_Be_Numeric() {
        log.info("NewAccount_07_Initial_Deposit_Must_Be_Numeric - Step 01 - Input value '1234Acc' to 'Initial deposit' textbox");
        newAccountPage.inputToTextboxByTittle(driver, "1234Acc", "Initial deposit");

        log.info("NewAccount_07_Initial_Deposit_Must_Be_Numeric - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Initial deposit"), "Characters are not allowed");

        log.info("NewAccount_07_Initial_Deposit_Must_Be_Numeric - Step 03 - Input value 'Acc123' to 'Initial deposit' textbox");
        newAccountPage.inputToTextboxByTittle(driver, "Acc123", "Initial deposit");

        log.info("NewAccount_07_Initial_Deposit_Must_Be_Numeric - Step 04 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Initial deposit"), "Characters are not allowed");
    }

    @Test
    public void NewAccount_08_Initial_Deposit_Cannot_Have_Special_Character() {
        log.info("NewAccount_08_Initial_Deposit_Cannot_Have_Special_Character - Step 01 - Input value '123!@#' to 'Initial deposit' textbox");
        newAccountPage.inputToTextboxByTittle(driver, "123!@#", "Initial deposit");

        log.info("NewAccount_08_Initial_Deposit_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Initial deposit"), "Special characters are not allowed");

        log.info("NewAccount_08_Initial_Deposit_Cannot_Have_Special_Character - Step 01 - Input value '!@#' to 'Initial deposit' textbox");
        newAccountPage.inputToTextboxByTittle(driver, "!@#", "Initial deposit");

        log.info("NewAccount_08_Initial_Deposit_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Initial deposit"), "Special characters are not allowed");
    }

    @Test
    public void NewAccount_09_Initial_Deposit_Cannot_Have_Blank_Space() {
        log.info("NewAccount_09_Initial_Deposit_Cannot_Have_Blank_Space - Step 01 - Input value '123 45' to 'Initial deposit' textbox");
        newAccountPage.inputToTextboxByTittle(driver, "12 45", "Initial deposit");

        log.info("NewAccount_09_Initial_Deposit_Cannot_Have_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Initial deposit"), "Characters are not allowed");
    }

    @Test
    public void NewAccount_10_Initial_Deposit_Cannot_Have_First_Charactor_As_Bland_Space() {
        log.info("NewAccount_10_Initial_Deposit_Cannot_Have_First_Charactor_As_Bland_Space - Step 01 - Input value ' 2313' to 'Initial deposit' textbox");
        newAccountPage.inputToTextboxByTittle(driver, " 2313", "Initial deposit");

        log.info("NewAccount_10_Initial_Deposit_Cannot_Have_First_Charactor_As_Bland_Space - Step 02 - Verify error message display");
        verifyEquals(newAccountPage.getErrorMessageByTextboxTitle(driver, "Initial deposit"), "First character can not have space");
    }
}
