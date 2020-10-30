package customer;

import commons.AbstractTest;
import commons.GlobalConstants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.EditCustomerPageObject;
import pageObject.LoginPageObject;
import pageObject.ManagerPageObject;
import pageUI.AbstractPageUI;

public class EditCustomer extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    ManagerPageObject managerPage;
    EditCustomerPageObject editCustomerPage;

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        loginPage = openLoginPage(driver);
        loginPage.inputToTextboxByTittle(driver, GlobalConstants.USER_ID, "UserID");
        loginPage.inputToTextboxByTittle(driver, GlobalConstants.PASSWORD, "Password");
        managerPage = loginPage.clickToButtonByName(driver, "LOGIN");
        editCustomerPage = (EditCustomerPageObject) managerPage.clickToPageByName(driver, "Edit Customer");
    }

    @Test
    public void EditCustomer_01_CustomerID_Cannot_Be_Empty() {
        log.info("EditCustomer_01_CustomerID_Cannot_Be_Empty - Step 01 - Let 'Customer ID' field is empty");
        editCustomerPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "Customer ID");

        log.info("EditCustomer_01_CustomerID_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Customer ID is required");
    }

    @Test
    public void EditCustomer_02_CustomerID_Must_Be_Numeric() {
        log.info("EditCustomer_02_CustomerID_Must_Be_Numeric - Step 01 - Input value '1234Acc' to 'Customer ID' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "1234Acc", "Customer ID");

        log.info("EditCustomer_02_CustomerID_Must_Be_Numeric - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Characters are not allowed");

        log.info("EditCustomer_02_CustomerID_Must_Be_Numeric - Step 03 - Input value 'Acc123' to 'Customer ID' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "Acc123", "Customer ID");

        log.info("EditCustomer_02_CustomerID_Must_Be_Numeric - Step 04 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Characters are not allowed");
    }

    @Test
    public void EditCustomer_03_CustomerID_Cannot_Have_Special_Character() {
        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 01 - Input value '123!@#' to 'Customer ID' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "123!@#", "Customer ID");

        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Special characters are not allowed");

        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 01 - Input value '!@#' to 'Customer ID' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "!@#", "Customer ID");

        log.info("EditCustomer_03_CustomerID_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer ID"), "Special characters are not allowed");
    }

    @Test
    public void EditCustomer_04_Valid_CustomerID() {
        log.info("EditCustomer_04_Valid_CustomerID - Step 01 - Input value '123!@#' to 'Customer ID' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, NewCustomer.customerID, "Customer ID");

        log.info("EditCustomer_04_Valid_CustomerID - Step 02 - Verify error message display");
        editCustomerPage.clickToButtonByName(driver, "Submit");

        log.info("EditCustomer_04_Valid_CustomerID - Step 03 - Verify login correct CustomerID");
        verifyTrue(editCustomerPage.isEditCustomerTitleDisplay());
    }

    @Test
    public void EditCustomer_05_Address_Cannot_Be_Empty() {
        log.info("EditCustomer_05_Address_Cannot_Be_Empty - Step 01 - Let 'Customer ID' textbox empty");
        editCustomerPage.inputToTextAreaByTittle(driver, "", "Address");
        log.info("EditCustomer_05_Address_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "Address"), "Address Field must not be blank");
    }

    @Test
    public void EditCustomer_06_City_Cannot_Be_Empty() {
        log.info("EditCustomer_06_City_Cannot_Be_Empty - Step 01 - Let 'City' textbox empty");
        editCustomerPage.inputToTextboxByTittle(driver, "", "City");
        log.info("EditCustomer_06_City_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "City Field must not be blank");
    }

    @Test
    public void EditCustomer_07_City_Cannot_Be_Numeric() {
        log.info("EditCustomer_07_City_Cannot_Be_Numeric - Step 01 - Input value '1234' to 'City' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "1234", "City");

        log.info("EditCustomer_07_City_Cannot_Be_Numeric - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "Numbers are not allowed");

        log.info("EditCustomer_07_City_Cannot_Be_Numeric - Step 03 - Input value 'city1234' to 'City' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "city1234", "City");

        log.info("EditCustomer_07_City_Cannot_Be_Numeric - Step 04 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "Numbers are not allowed");
    }

    @Test
    public void EditCustomer_08_City_Cannot_Have_Special_Character() {
        log.info("EditCustomer_08_City_Have_Special_Character - Step 01 - Input value 'City!@#' to 'City' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "City!@#", "City");

        log.info("EditCustomer_08_City_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "Special characters are not allowed");

        log.info("EditCustomer_08_City_Have_Special_Character - Step 03 - Input value '!@#' to 'City' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "!@#", "City");

        log.info("EditCustomer_08_City_Have_Special_Character - Step 04 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "Special characters are not allowed");
    }

    @Test
    public void EditCustomer_09_State_Cannot_Be_Empty() {
        log.info("EditCustomer_09_State_Cannot_Be_Empty - Step 01 - Let 'State' textbox empty");
        editCustomerPage.inputToTextboxByTittle(driver, "", "State");
        log.info("EditCustomer_09_State_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "State must not be blank");
    }

    @Test
    public void EditCustomer_10_State_Cannot_Be_Numeric() {
        log.info("EditCustomer_10_State_Cannot_Be_Numeric - Step 01 - Input value '1234' to 'State' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "1234", "State");

        log.info("EditCustomer_10_State_Cannot_Be_Numeric - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "Numbers are not allowed");

        log.info("EditCustomer_10_State_Cannot_Be_Numeric - Step 03 - Input value 'state1234' to 'State' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "State1234", "State");

        log.info("EditCustomer_10_State_Cannot_Be_Numeric - Step 04 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "Numbers are not allowed");
    }

    @Test
    public void EditCustomer_11_State_Cannot_Have_Special_Character() {
        log.info("EditCustomer_11_State_Cannot_Have_Special_Character - Step 01 - Input value 'State!@#' to 'State' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "State!@#", "State");

        log.info("EditCustomer_11_State_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "Special characters are not allowed");

        log.info("EditCustomer_11_State_Cannot_Have_Special_Character - Step 03 - Input value '!@#' to 'State' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "!@#", "State");

        log.info("EditCustomer_11_State_Cannot_Have_Special_Character - Step 04 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "Special characters are not allowed");
    }

    @Test
    public void EditCustomer_12_PIN_Must_Be_Numeric() {
        log.info("EditCustomer_12_PIN_Must_Be_Numeric - Step 01 - Input value 'PIN123' to 'PIN' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "PIN123", "PIN");

        log.info("EditCustomer_12_PIN_Must_Be_Numeric - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "Characters are not allowed");
    }

    @Test
    public void EditCustomer_13_PIN_Cannot_Be_Empty() {

        log.info("EditCustomer_13_PIN_Cannot_Be_Empty - Step 01 - Let 'PIN' field is empty");
        editCustomerPage.inputToTextboxByTittle(driver, "", "PIN");

        log.info("EditCustomer_13_PIN_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "PIN Code must not be blank");
    }

    @Test
    public void EditCustomer_14_PIN_Must_Have_6_digits() {
        log.info("EditCustomer_14_PIN_Must_Have_6_digits - Step 01 - Input value '12' to 'PIN' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "12", "PIN");

        log.info("EditCustomer_14_PIN_Must_Have_6_digits - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "PIN Code must have 6 Digits");

        log.info("EditCustomer_14_PIN_Must_Have_6_digits - Step 03 - Input value '12345' to 'PIN' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "12345", "PIN");

        log.info("EditCustomer_14_PIN_Must_Have_6_digits - Step 04 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "PIN Code must have 6 Digits");
    }

    @Test
    public void EditCustomer_15_PIN_Cannot_Have_Special_Characters() {
        log.info("EditCustomer_15_PIN_Cannot_Have_Special_Characters - Step 01 - Input value '!@#' to 'PIN' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "!@#", "PIN");

        log.info("EditCustomer_15_PIN_Cannot_Have_Special_Characters - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "Special characters are not allowed");

        log.info("EditCustomer_15_PIN_Cannot_Have_Special_Characters - Step 03 - Input value 'PIN!@#' to 'PIN' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "PIN!@#", "PIN");

        log.info("EditCustomer_15_PIN_Cannot_Have_Special_Characters - Step 04 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "Special characters are not allowed");
    }

    @Test
    public void EditCustomer_16_Telephone_Cannot_Be_Empty() {

        log.info("EditCustomer_16_Telephone_Cannot_Be_Empty - Step 01 - Let 'Mobile Number' field is empty");
        editCustomerPage.inputToTextboxByTittle(driver, "", "Mobile Number");

        log.info("EditCustomer_16_Telephone_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "Mobile Number"), "Mobile no must not be blank");
    }

    @Test
    public void EditCustomer_17_Telephone_Cannot_Have_Special_Characters() {
        log.info("EditCustomer_17_Telephone_Cannot_Have_Special_Characters - Step 01 - Input value '4564!@45' to 'Mobile Number' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "4564!@45", "Mobile Number");

        log.info("EditCustomer_17_Telephone_Cannot_Have_Special_Characters - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "Mobile Number"), "Special characters are not allowed");

        log.info("EditCustomer_17_Telephone_Cannot_Have_Special_Characters - Step 03 - Input value '@#4498484' to 'Mobile Number' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "@#4498484", "Mobile Number");

        log.info("EditCustomer_17_Telephone_Cannot_Have_Special_Characters - Step 04 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "Mobile Number"), "Special characters are not allowed");

        log.info("EditCustomer_17_Telephone_Cannot_Have_Special_Characters - Step 05 - Input value '4498484@#' to 'Mobile Number' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "4498484@#", "Mobile Number");

        log.info("EditCustomer_17_Telephone_Cannot_Have_Special_Characters - Step 06 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "Mobile Number"), "Special characters are not allowed");
    }

    @Test
    public void EditCustomer_18_Email_Cannot_Be_Empty() {

        log.info("EditCustomer_18_Email_Cannot_Be_Empty - Step 01 - Let 'E-mail' field is empty");
        editCustomerPage.inputToTextboxByTittle(driver, "", "E-mail");

        log.info("EditCustomer_18_Email_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID must not be blank");
    }

    @Test
    public void EditCustomer_19_Email_Must_Be_In_Correct_Format() {

        log.info("EditCustomer_19_Email_Must_Be_In_Correct_Format - Step 01 - Input value 'guru99@gmail' to 'E-mail' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "guru99@gmail", "E-mail");

        log.info("EditCustomer_19_Email_Must_Be_In_Correct_Format - Step 02 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID is not valid");

        log.info("EditCustomer_19_Email_Must_Be_In_Correct_Format - Step 03 - Input value 'guru99' to 'E-mail' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "guru99", "E-mail");

        log.info("EditCustomer_19_Email_Must_Be_In_Correct_Format - Step 04 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID is not valid");

        log.info("EditCustomer_19_Email_Must_Be_In_Correct_Format - Step 05 - Input value 'Guru99@' to 'E-mail' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "Guru99@", "E-mail");

        log.info("EditCustomer_19_Email_Must_Be_In_Correct_Format - Step 06 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID is not valid");

        log.info("EditCustomer_19_Email_Must_Be_In_Correct_Format - Step 07 - Input value 'guru99@gmail.' to 'E-mail' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "guru99@gmail.", "E-mail");

        log.info("EditCustomer_19_Email_Must_Be_In_Correct_Format - Step 08 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID is not valid");

        log.info("EditCustomer_19_Email_Must_Be_In_Correct_Format - Step 09 - Input value 'guru99gmail.com' to 'E-mail' textbox");
        editCustomerPage.inputToTextboxByTittle(driver, "guru99gmail.com", "E-mail");

        log.info("EditCustomer_19_Email_Must_Be_In_Correct_Format - Step 10 - Verify error message display");
        verifyEquals(editCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID is not valid");
    }

    @Test
    public void EditCustomer_20_Input_Correct_Info() {
        String editAddress = "1883 Cursus Avenue";
        String editCity = "Houston";
        String editState = "Texas";
        String editPIN = "166455";
        String editMobile = "4779728081";
        String editEmail = "testing" + randomNumber() + "@gmail.com";


        log.info("NewCustomer_30_Create_New_Customer_Success - Step 01 - Input valid value into textbox");
        editCustomerPage.inputToTextAreaByTittle(driver, editAddress, "Address");
        editCustomerPage.inputToTextboxByTittle(driver, editCity, "City");
        editCustomerPage.inputToTextboxByTittle(driver, editState, "State");
        editCustomerPage.inputToTextboxByTittle(driver, editPIN, "PIN");
        editCustomerPage.inputToTextboxByTittle(driver, editMobile, "Mobile Number");
        editCustomerPage.inputToTextboxByTittle(driver, editEmail, "E-mail");

        log.info("NewCustomer_21_PIN_cannot_Have_Blank_Space - Step 01 - Click to Submit button");
        editCustomerPage.clickToButtonByName(driver, "Submit");

        log.info("NewCustomer_21_PIN_cannot_Have_Blank_Space - Step 02 - Verify all info correct");
        verifyTrue(editCustomerPage.isEditCustomerSuccessMessageDisplay());
        verifyEquals(editCustomerPage.getValueByTitle(driver, "Address"), editAddress);
        verifyEquals(editCustomerPage.getValueByTitle(driver, "City"), editCity);
        verifyEquals(editCustomerPage.getValueByTitle(driver, "State"), editState);
        verifyEquals(editCustomerPage.getValueByTitle(driver, "Pin"), editPIN);
        verifyEquals(editCustomerPage.getValueByTitle(driver, "Mobile No."), editMobile);
        verifyEquals(editCustomerPage.getValueByTitle(driver, "Email"), editEmail);
    }

    @AfterClass
    public void AfterClass() {
        closeBrowserAndDriver(driver);
    }
}
