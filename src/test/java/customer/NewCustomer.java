package customer;

import commons.AbstractTest;
import commons.GlobalConstants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.LoginPageObject;
import pageObject.ManagerPageObject;
import pageObject.NewCustomerPageObject;
import pageUI.AbstractPageUI;


public class NewCustomer extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    ManagerPageObject managerPage;
    NewCustomerPageObject newCustomerPage;

    public static String customerName = "AUTOMATION TESTING";
    String dateOfBirth = "1989-01-01";
    String address = "PO Box 911 8331 Duis Avenue";
    String city = "Tampa";
    String state = "FL";
    String PIN = "466250";
    String mobile = "4555442476";
    public static String email = "automation" + randomNumber() + "@gmail.com";
    String password = "automation";
    String gender = "female";
    public static String customerID;

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        loginPage = openLoginPage(driver);
        loginPage.inputToTextboxByTittle(driver, GlobalConstants.USER_ID, "UserID");
        loginPage.inputToTextboxByTittle(driver, GlobalConstants.PASSWORD, "Password");
        managerPage = loginPage.clickToButtonByName(driver, "LOGIN");
        newCustomerPage = (NewCustomerPageObject) managerPage.clickToPageByName(driver, "New Customer");
    }

    @Test
    public void NewCustomer_01_Name_Cannot_Be_Empty() {
        log.info("NewCustomer_01_Name_Cannot_Be_Empty - Step 01 - Let 'Customer Name' field is empty");
        newCustomerPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "Customer Name");

        log.info("NewCustomer_01_Name_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer Name"), "Customer name must not be blank");
    }

    @Test
    public void NewCustomer_02_Name_Cannot_Be_Numeric() {
        log.info("NewCustomer_02_Name_Cannot_Be_Numberic - Step 01 - Input value '1234' to 'Customer Name' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "1234", "Customer Name");

        log.info("NewCustomer_02_Name_Cannot_Be_Numberic - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer Name"), "Numbers are not allowed");

        log.info("NewCustomer_02_Name_Cannot_Be_Numberic - Step 03 - Input value 'name1234' to 'Customer Name' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "name1234", "Customer Name");

        log.info("NewCustomer_02_Name_Cannot_Be_Numberic - Step 04 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer Name"), "Numbers are not allowed");
    }

    @Test
    public void NewCustomer_03_Name_Cannot_Have_Special_Character() {
        log.info("NewCustomer_03_Name_Cannot_Have_Special_Character - Step 01 - Input value 'name!@#' to 'Customer Name' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "name!@#", "Customer Name");

        log.info("NewCustomer_03_Name_Cannot_Have_Special_Character - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer Name"), "Special characters are not allowed");

        log.info("NewCustomer_03_Name_Cannot_Have_Special_Character - Step 03 - Input value '!@#' to 'Customer Name' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "!@#", "Customer Name");

        log.info("NewCustomer_03_Name_Cannot_Have_Special_Character - Step 04 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer Name"), "Special characters are not allowed");
    }

    @Test
    public void NewCustomer_04_Name_Cannot_Have_First_Charactor_As_Bland_Space() {
        log.info("NewCustomer_04_Name_Cannot_Have_First_Charactor_As_Bland_Space - Step 01 - Input value ' ' to 'Customer Name' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, " ", "Customer Name");

        log.info("NewCustomer_04_Name_Cannot_Have_First_Charactor_As_Bland_Space - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Customer Name"), "First character can not have space");
    }

    @Test
    public void NewCustomer_05_Address_cannot_Be_Empty() {
        log.info("NewCustomer_05_Address_cannot_Be_Empty - Step 01 - Input value ' ' to 'Address' textbox");
        newCustomerPage.sendKeyboardToElement(driver, AbstractPageUI.TEXTAREA_BY_TITLE, Keys.TAB, "Address");

        log.info("NewCustomer_05_Address_cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Address"), "Address Field must not be blank");
    }

    @Test
    public void NewCustomer_06_Address_cannot_Have_First_Blank_Space() {

        log.info("NewCustomer_06_Address_cannot_Have_First_Blank_Space - Step 01 - Input value ' ' to 'Address' textbox");
        newCustomerPage.inputToTextAreaByTittle(driver, " ", "Address");

        log.info("NewCustomer_06_Address_cannot_Have_First_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Address"), "First character can not have space");
    }

    @Test
    public void NewCustomer_08_City_Cannot_Be_Empty() {

        log.info("NewCustomer_08_City_Cannot_Be_Empty - Step 01 - Let 'City' field is empty");
        newCustomerPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "City");

        log.info("NewCustomer_08_City_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "City Field must not be blank");
    }

    @Test
    public void NewCustomer_09_City_Cannot_Be_Numeric() {
        log.info("NewCustomer_09_City_Cannot_Be_Numberic - Step 01 - Input value '1234' to 'City' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "1234", "City");

        log.info("NewCustomer_09_City_Cannot_Be_Numberic - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "Numbers are not allowed");

        log.info("NewCustomer_09_City_Cannot_Be_Numberic - Step 03 - Input value 'city1234' to 'City' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "city1234", "City");

        log.info("NewCustomer_09_City_Cannot_Be_Numberic - Step 04 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "Numbers are not allowed");
    }

    @Test
    public void NewCustomer_10_City_Cannot_Have_Special_Characters() {
        log.info("NewCustomer_10_City_Cannot_Have_Special_Characters - Step 01 - Input value '!@#' to 'City' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "!@#", "City");

        log.info("NewCustomer_10_City_Cannot_Have_Special_Characters - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "Special characters are not allowed");

        log.info("NewCustomer_10_City_Cannot_Have_Special_Characters - Step 03 - Input value 'city!@#' to 'City' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "city!@#", "City");

        log.info("NewCustomer_10_City_Cannot_Have_Special_Characters - Step 04 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "Special characters are not allowed");
    }

    @Test
    public void NewCustomer_11_City_cannot_Have_First_Blank_Space() {
        log.info("NewCustomer_11_City_cannot_Have_First_Blank_Space - Step 01 - Input value ' city' to 'City' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, " city", "City");

        log.info("NewCustomer_11_City_cannot_Have_First_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "City"), "First character can not have space");
    }

    @Test
    public void NewCustomer_12_State_Cannot_Be_Empty() {

        log.info("NewCustomer_12_State_Cannot_Be_Empty - Step 01 - Let 'City' field is empty");
        newCustomerPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "State");

        log.info("NewCustomer_12_State_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "State must not be blank");
    }

    @Test
    public void NewCustomer_13_State_Cannot_Be_Numeric() {
        log.info("NewCustomer_13_State_Cannot_Be_Numberic - Step 01 - Input value '1234' to 'State' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "1234", "State");

        log.info("NewCustomer_13_State_Cannot_Be_Numberic - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "Numbers are not allowed");

        log.info("NewCustomer_13_State_Cannot_Be_Numberic - Step 03 - Input value 'State1234' to 'State' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "city1234", "State");

        log.info("NewCustomer_13_State_Cannot_Be_Numberic - Step 04 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "Numbers are not allowed");
    }

    @Test
    public void NewCustomer_14_State_Cannot_Have_Special_Characters() {
        log.info("NewCustomer_14_State_Cannot_Have_Special_Characters - Step 01 - Input value '!@#' to 'State' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "!@#", "State");

        log.info("NewCustomer_14_State_Cannot_Have_Special_Characters - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "Special characters are not allowed");

        log.info("NewCustomer_14_State_Cannot_Have_Special_Characters - Step 03 - Input value 'State!@#' to 'State' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "city!@#", "State");

        log.info("NewCustomer_14_State_Cannot_Have_Special_Characters - Step 04 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "Special characters are not allowed");
    }

    @Test
    public void NewCustomer_15_State_cannot_Have_First_Blank_Space() {
        log.info("NewCustomer_15_State_cannot_Have_First_Blank_Space - Step 01 - Input value ' auto' to 'State' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, " auto", "State");

        log.info("NewCustomer_15_State_cannot_Have_First_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "State"), "First character can not have space");
    }


    @Test
    public void NewCustomer_16_PIN_Cannot_Be_Empty() {

        log.info("NewCustomer_16_PIN_Cannot_Be_Empty - Step 01 - Let 'PIN' field is empty");
        newCustomerPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "PIN");

        log.info("NewCustomer_16_PIN_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "PIN Code must not be blank");
    }

    @Test
    public void NewCustomer_17_PIN_Must_Be_Numeric() {
        log.info("NewCustomer_17_PIN_Must_Be_Numeric - Step 01 - Input value 'PIN123' to 'PIN' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "PIN123", "PIN");

        log.info("NewCustomer_17_PIN_Must_Be_Numeric - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "Characters are not allowed");
    }

    @Test
    public void NewCustomer_18_PIN_Must_Have_6_digits() {
        log.info("NewCustomer_18_PIN_Must_Have_6_digits - Step 01 - Input value '12' to 'PIN' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "12", "PIN");

        log.info("NewCustomer_18_PIN_Must_Have_6_digits - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "PIN Code must have 6 Digits");

        log.info("NewCustomer_18_PIN_Must_Have_6_digits - Step 03 - Input value '12345' to 'PIN' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "12345", "PIN");

        log.info("NewCustomer_18_PIN_Must_Have_6_digits - Step 04 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "PIN Code must have 6 Digits");
    }

    @Test
    public void NewCustomer_19_PIN_Cannot_Have_Special_Characters() {
        log.info("NewCustomer_19_PIN_Cannot_Have_Special_Characters - Step 01 - Input value '!@#' to 'PIN' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "!@#", "PIN");

        log.info("NewCustomer_19_PIN_Cannot_Have_Special_Characters - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "Special characters are not allowed");

        log.info("NewCustomer_19_PIN_Cannot_Have_Special_Characters - Step 03 - Input value 'PIN!@#' to 'PIN' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "PIN!@#", "PIN");

        log.info("NewCustomer_19_PIN_Cannot_Have_Special_Characters - Step 04 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "Special characters are not allowed");
    }

    @Test
    public void NewCustomer_20_PIN_cannot_Have_First_Blank_Space() {
        log.info("NewCustomer_20_PIN_cannot_Have_First_Blank_Space - Step 01 - Input value ' 12345' to 'PIN' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, " 12345", "PIN");

        log.info("NewCustomer_20_PIN_cannot_Have_First_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "First character can not have space");
    }

    @Test
    public void NewCustomer_21_PIN_cannot_Have_Blank_Space() {
        log.info("NewCustomer_21_PIN_cannot_Have_Blank_Space - Step 01 - Input value '123 45' to 'PIN' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "123 45", "PIN");

        log.info("NewCustomer_21_PIN_cannot_Have_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "PIN"), "Characters are not allowed");
    }

    @Test
    public void NewCustomer_22_Telephone_Cannot_Be_Empty() {

        log.info("NewCustomer_22_Telephone_Cannot_Be_Empty - Step 01 - Let 'Mobile Number' field is empty");
        newCustomerPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "Mobile Number");

        log.info("NewCustomer_22_Telephone_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Mobile Number"), "Mobile no must not be blank");
    }

    @Test
    public void NewCustomer_23_Telephone_cannot_Have_First_Blank_Space() {
        log.info("NewCustomer_23_Telephone_cannot_Have_First_Blank_Space - Step 01 - Input value ' 123456' to 'Mobile Number' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, " 12345", "Mobile Number");

        log.info("NewCustomer_23_Telephone_cannot_Have_First_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Mobile Number"), "First character can not have space");
    }

    @Test
    public void NewCustomer_24_Telephone_cannot_Have_Blank_Space() {
        log.info("NewCustomer_24_Telephone_cannot_Have_Blank_Space - Step 01 - Input value '123 456' to 'Mobile Number' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "123 456", "Mobile Number");

        log.info("NewCustomer_24_Telephone_cannot_Have_Blank_Space - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Mobile Number"), "Characters are not allowed");
    }

    @Test
    public void NewCustomer_25_Telephone_Cannot_Have_Special_Characters() {
        log.info("NewCustomer_25_Telephone_Cannot_Have_Special_Characters - Step 01 - Input value '4564!@45' to 'Mobile Number' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "4564!@45", "Mobile Number");

        log.info("NewCustomer_25_Telephone_Cannot_Have_Special_Characters - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Mobile Number"), "Special characters are not allowed");

        log.info("NewCustomer_25_Telephone_Cannot_Have_Special_Characters - Step 03 - Input value '@#4498484' to 'Mobile Number' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "@#4498484", "Mobile Number");

        log.info("NewCustomer_25_Telephone_Cannot_Have_Special_Characters - Step 04 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Mobile Number"), "Special characters are not allowed");

        log.info("NewCustomer_25_Telephone_Cannot_Have_Special_Characters - Step 05 - Input value '4498484@#' to 'Mobile Number' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "4498484@#", "Mobile Number");

        log.info("NewCustomer_25_Telephone_Cannot_Have_Special_Characters - Step 06 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "Mobile Number"), "Special characters are not allowed");
    }


    @Test
    public void NewCustomer_26_Email_Cannot_Be_Empty() {

        log.info("NewCustomer_26_Email_Cannot_Be_Empty - Step 01 - Let 'E-mail' field is empty");
        newCustomerPage.sendKeyboardToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, Keys.TAB, "Mobile Number");

        log.info("NewCustomer_26_Email_Cannot_Be_Empty - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID must not be blank");
    }

    @Test
    public void NewCustomer_27_Email_Must_Be_In_Correct_Format() {

        log.info("NewCustomer_27_Email_Must_Be_In_Correct_Format - Step 01 - Input value 'guru99@gmail' to 'E-mail' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "guru99@gmail", "E-mail");

        log.info("NewCustomer_27_Email_Must_Be_In_Correct_Format - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID is not valid");

        log.info("NewCustomer_27_Email_Must_Be_In_Correct_Format - Step 03 - Input value 'guru99' to 'E-mail' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "guru99", "E-mail");

        log.info("NewCustomer_27_Email_Must_Be_In_Correct_Format - Step 04 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID is not valid");

        log.info("NewCustomer_27_Email_Must_Be_In_Correct_Format - Step 05 - Input value 'Guru99@' to 'E-mail' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "Guru99@", "E-mail");

        log.info("NewCustomer_27_Email_Must_Be_In_Correct_Format - Step 06 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID is not valid");

        log.info("NewCustomer_27_Email_Must_Be_In_Correct_Format - Step 07 - Input value 'guru99@gmail.' to 'E-mail' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "guru99@gmail.", "E-mail");

        log.info("NewCustomer_27_Email_Must_Be_In_Correct_Format - Step 08 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID is not valid");

        log.info("NewCustomer_27_Email_Must_Be_In_Correct_Format - Step 09 - Input value 'guru99gmail.com' to 'E-mail' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, "guru99gmail.com", "E-mail");

        log.info("NewCustomer_27_Email_Must_Be_In_Correct_Format - Step 10 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "Email-ID is not valid");
    }

    @Test
    public void NewCustomer_29_Telephone_Cannot_Have_First_Space() {
        log.info("NewCustomer_29_Telephone_Cannot_Have_First_Space - Step 01 - Input value ' guru99@gmail.com' to 'E-mail' textbox");
        newCustomerPage.inputToTextboxByTittle(driver, " guru99@gmail.com", "E-mail");

        log.info("NewCustomer_29_Telephone_Cannot_Have_First_Space - Step 02 - Verify error message display");
        verifyEquals(newCustomerPage.getErrorMessageByTextboxTitle(driver, "E-mail"), "First character can not have space");
    }

    @Test
    public void NewCustomer_30_Create_New_Customer_Success() {
        log.info("NewCustomer_30_Create_New_Customer_Success - Step 01 - Input valid value into textbox");
        newCustomerPage.inputToTextboxByTittle(driver, customerName, "Customer Name");
        newCustomerPage.selectGenderValue(gender);
        newCustomerPage.inputToTextboxByTittle(driver, dateOfBirth, "Date of Birth");
        newCustomerPage.inputToTextAreaByTittle(driver, address, "Address");
        newCustomerPage.inputToTextboxByTittle(driver, city, "City");
        newCustomerPage.inputToTextboxByTittle(driver, state, "State");
        newCustomerPage.inputToTextboxByTittle(driver, PIN, "PIN");
        newCustomerPage.inputToTextboxByTittle(driver, mobile, "Mobile Number");
        newCustomerPage.inputToTextboxByTittle(driver, email, "E-mail");
        newCustomerPage.inputToTextboxByTittle(driver, password, "Password");

        log.info("NewCustomer_21_PIN_cannot_Have_Blank_Space - Step 01 - Click to Submit button");
        newCustomerPage.clickToButtonByName(driver, "Submit");

        log.info("NewCustomer_21_PIN_cannot_Have_Blank_Space - Step 02 - Verify all info correct");
        verifyTrue(newCustomerPage.isCustomerRegisterSuccessMessageDisplay());
        verifyEquals(newCustomerPage.getValueByTitle(driver, "Customer Name"), customerName);
        verifyEquals(newCustomerPage.getValueByTitle(driver, "Gender"), gender);
        verifyEquals(newCustomerPage.getValueByTitle(driver, "Birthdate"), dateOfBirth);
        verifyEquals(newCustomerPage.getValueByTitle(driver, "Address"), address);
        verifyEquals(newCustomerPage.getValueByTitle(driver, "City"), city);
        verifyEquals(newCustomerPage.getValueByTitle(driver, "State"), state);
        verifyEquals(newCustomerPage.getValueByTitle(driver, "Pin"), PIN);
        verifyEquals(newCustomerPage.getValueByTitle(driver, "Mobile No."), mobile);
        verifyEquals(newCustomerPage.getValueByTitle(driver, "Email"), email);

        customerID = newCustomerPage.getValueByTitle(driver, "Customer ID");
    }


    @AfterClass
    public void AfterClass() {
        closeBrowserAndDriver(driver);
    }
}

