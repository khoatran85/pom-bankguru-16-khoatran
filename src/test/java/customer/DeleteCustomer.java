package customer;

import commons.AbstractTest;
import commons.GlobalConstants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.DeleteCustomerPageObject;
import pageObject.EditCustomerPageObject;
import pageObject.LoginPageObject;
import pageObject.ManagerPageObject;
import pageUI.AbstractPageUI;

public class DeleteCustomer extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    ManagerPageObject managerPage;
    DeleteCustomerPageObject deleteCustomerPage;
    EditCustomerPageObject editCustomerPage;

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        loginPage = openLoginPage(driver);
        loginPage.inputToTextboxByTittle(driver, GlobalConstants.USER_ID, "UserID");
        loginPage.inputToTextboxByTittle(driver, GlobalConstants.PASSWORD, "Password");
        managerPage = loginPage.clickToButtonByName(driver, "LOGIN");
        deleteCustomerPage = (DeleteCustomerPageObject) managerPage.clickToPageByName(driver, "Delete Customer");
    }

    @Test
    public void DeleteCustomer_01_CustomerID_Cannot_Be_Empty() {
        log.info("DeleteCustomer_01_CustomerID_Cannot_Be_Empty - Step 01 - Let 'Customer ID' field is empty");
        deleteCustomerPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "Customer ID");

        log.info("DeleteCustomer_01_CustomerID_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(deleteCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Customer ID is required");
    }

    @Test
    public void DeleteCustomer_02_CustomerID_Must_Be_Numeric() {
        log.info("DeleteCustomer_02_CustomerID_Must_Be_Numeric - Step 01 - Input value '1234Acc' to 'Customer ID' textbox");
        deleteCustomerPage.inputToTextboxByTittle(driver, "1234Acc", "Customer ID");

        log.info("DeleteCustomer_02_CustomerID_Must_Be_Numeric - Step 02 - Verify error message display");
        verifyEquals(deleteCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Characters are not allowed");

        log.info("DeleteCustomer_02_CustomerID_Must_Be_Numeric - Step 03 - Input value 'Acc123' to 'Customer ID' textbox");
        deleteCustomerPage.inputToTextboxByTittle(driver, "Acc123", "Customer ID");

        log.info("DeleteCustomer_02_CustomerID_Must_Be_Numeric - Step 04 - Verify error message display");
        verifyEquals(deleteCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Characters are not allowed");
    }

    @Test
    public void DeleteCustomer_03_CustomerID_Cannot_Have_Special_Character() {
        log.info("DeleteCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 01 - Input value '123!@#' to 'Customer ID' textbox");
        deleteCustomerPage.inputToTextboxByTittle(driver, "123!@#", "Customer ID");

        log.info("DeleteCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(deleteCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Special characters are not allowed");

        log.info("DeleteCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 01 - Input value '!@#' to 'Customer ID' textbox");
        deleteCustomerPage.inputToTextboxByTittle(driver, "!@#", "Customer ID");

        log.info("DeleteCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(deleteCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Special characters are not allowed");
    }

    @Test
    public void DeleteCustomer_04_CustomerID_Cannot_Have_Blank_Space() {
        log.info("DeleteCustomer_04_CustomerID_Cannot_Have_Blank_Space - Step 01 - Input value ' ' to 'Customer ID' textbox");
        deleteCustomerPage.inputToTextboxByTittle(driver, "23 22", "Customer ID");

        log.info("DeleteCustomer_04_CustomerID_Cannot_Have_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(deleteCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Characters are not allowed");
    }

    @Test
    public void DeleteCustomer_05_First_Character_Cannot_Be_Space() {
        log.info("DeleteCustomer_05_First_Character_Cannot_Be_Space - Step 01 - Input value ' ' to 'Customer ID' textbox");
        deleteCustomerPage.inputToTextboxByTittle(driver, " 2322", "Customer ID");

        log.info("DeleteCustomer_05_First_Character_Cannot_Be_Space - Step 02 - Verify error message display");
        verifyEquals(deleteCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "First character can not have space");

    }

    // @Test
    public void DeleteCustomer_06_Delete_Exist_Customer_And_Check_Deleted_Successfully() {
        log.info("DeleteCustomer_06_Delete_Exist_Customer_And_Check_Deleted_Successfully - Step 01 - Input correct Customer ID to 'Customer ID' textbox");
        deleteCustomerPage.inputToTextboxByTittle(driver, NewCustomer.customerID, "Customer ID");

        log.info("DeleteCustomer_06_Delete_Exist_Customer_And_Check_Deleted_Successfully - Step 02 - Click to Submit button");
        deleteCustomerPage.clickToButtonByName(driver, "Submit");

        log.info("DeleteCustomer_06_Delete_Exist_Customer_And_Check_Deleted_Successfully - Step 03 - Confirm delete");
        deleteCustomerPage.acceptAlert(driver);

        log.info("DeleteCustomer_06_Delete_Exist_Customer_And_Check_Deleted_Successfully - Step 04 - Verify Account delete successfully");
        verifyEquals(getTextInAlert(driver), "Customer deleted Successfully");
        deleteCustomerPage.acceptAlert(driver);

        log.info("DeleteCustomer_06_Delete_Exist_Customer_And_Check_Deleted_Successfully - Step 03 - Verify account does not exist");
        editCustomerPage = (EditCustomerPageObject) deleteCustomerPage.clickToPageByName(driver, "Edit Customer");
        editCustomerPage.inputToTextboxByTittle(driver, NewCustomer.customerID, "Customer ID");
        editCustomerPage.clickToButtonByName(driver, "Submit");
        verifyEquals(editCustomerPage.getTextInAlert(driver), "Customer does not exist!!");
        deleteCustomerPage.acceptAlert(driver);
    }

    @AfterClass
    public void AfterClass() {
        closeBrowserAndDriver(driver);
    }
}
