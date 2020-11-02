package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.LoginPageObject;
import pageObject.ManagerPageObject;
import pageUI.AbstractPageUI;
import pageUI.NewCustomerPageUI;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {
    private Select select;
    private JavascriptExecutor js;
    private WebDriverWait explicitWait;
    private WebElement element;
    private List<WebElement> elements;
    private Actions action;

    public void openUrl(WebDriver driver, String urlValue) {
        driver.get(urlValue);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void back(WebDriver driver) {
        driver.navigate().back();
    }

    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void forward(WebDriver driver) {
        driver.navigate().forward();

    }

    public boolean isAlertPresent(WebDriver driver) {
        try {
            waitAlertPresent(driver);
            driver.switchTo().alert();
            return true;
        } catch (Exception Ex) {
            return false;
        }
    }

    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void cancelAler(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public void senkeyToAlear(WebDriver driver, String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    public String getTextInAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    public void waitAlertPresent(WebDriver driver) {
        WebDriverWait explicitWait;
        explicitWait = new WebDriverWait(driver, GlobalConstants.SUPPER_SHORT_TIMEOUT);
        try {
            explicitWait.until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
        }

    }

    public void switchWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String runWindows : allWindowIDs) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
            }
        }
    }

    public void switchWindowByTitle(WebDriver driver, String targetTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String runWindows : allWindowIDs) {
            driver.switchTo().window(runWindows);
            String currentWindowTitle = driver.getTitle();
            if (currentWindowTitle.equals(targetTitle)) {
                break;
            }
        }
    }

    public boolean areAllWindowsCloseWithoutParent(WebDriver driver, String parentTitle) {

        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String runWindow : allWindowIDs) {
            driver.switchTo().window(runWindow);
            if (!driver.switchTo().window(runWindow).getTitle().equals(parentTitle)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        switchWindowByTitle(driver, parentTitle);
        return driver.getWindowHandles().size() == 1;
    }

    public By byXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement findElementByXpath(WebDriver driver, String locator) {
        return driver.findElement(byXpath(locator));
    }

    public WebElement findElementByXpath(WebDriver driver, String locator, String... values) {
        return driver.findElement(byXpath(castToObject(locator, values)));
    }

    public String castToObject(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
        return driver.findElements(byXpath(locator));
    }

    public List<WebElement> findElementsByXpath(WebDriver driver, String locator, String... values) {
        return driver.findElements(byXpath(castToObject(locator, values)));
    }

    public void sleepInSeconds(long timeOut) {
        try {
            Thread.sleep(timeOut * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleepInMiliseconds(long timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickToElement(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        findElementByXpath(driver, locator).click();

    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
        highlightElement(driver, locator, values);
        findElementByXpath(driver, castToObject(locator, values)).click();

    }

    public void sendkeyToElement(WebDriver driver, String locator, String value) {
        element = findElementByXpath(driver, locator);
        element.clear();
        element.sendKeys(value);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value, String... values) {
        highlightElement(driver, locator, values);
        element = findElementByXpath(driver, castToObject(locator, values));
        element.clear();
        element.sendKeys(value);
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
        js.executeScript("arguments[0].setAttribute('value', '')", element);

    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value, String... values) {
        element = driver.findElement(By.xpath(castToObject(locator, values)));
        js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
        js.executeScript("arguments[0].setAttribute('value', '')", element);

    }


    public String getElementText(WebDriver driver, String locator) {
        return findElementByXpath(driver, locator).getText().trim();
    }

    public String getElementText(WebDriver driver, String locator, String... values) {
        return findElementByXpath(driver, castToObject(locator, values)).getText().trim();
    }

    public String getElementAttribute(WebDriver driver, String locator, String AttributeName) {
        return findElementByXpath(driver, locator).getAttribute(AttributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String AttributeName, String... values) {
        return findElementByXpath(driver, castToObject(locator, values)).getAttribute(AttributeName);
    }

    public void selectValueInDropdown(WebDriver driver, String locator, String value) {
        select = new Select(findElementByXpath(driver, locator));
        select.selectByVisibleText(value);

    }

    public void selectValueInDropdown(WebDriver driver, String locator, String value, String... values) {
        select = new Select(findElementByXpath(driver, castToObject(locator, values)));
        select.selectByVisibleText(value);
    }

    public String getSelectedIteminDropdown(WebDriver driver, String locator) {
        select = new Select(findElementByXpath(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedIteminDropdown(WebDriver driver, String locator, String... values) {
        select = new Select(findElementByXpath(driver, castToObject(locator, values)));
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String allItemsLocator,
                                           String targetValue) {
        js = (JavascriptExecutor) driver;
//        waitForElementClickable(driver, parentLocator);
        clickToElement(driver, parentLocator);
        waitForElementsPresent(driver, allItemsLocator);
        elements = findElementsByXpath(driver, allItemsLocator);
        for (WebElement item : elements) {
            if (item.getText().contains(targetValue)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String allItemsLocator,
                                           String targetValue, String... values) {
        js = (JavascriptExecutor) driver;
        waitForElementClickable(driver, parentLocator, values);
        clickToElementByJS(driver, parentLocator, values);
        waitForElementsPresent(driver, allItemsLocator, values);
        elements = findElementsByXpath(driver, allItemsLocator, values);
        for (WebElement item : elements) {
            if (item.getText().contains(targetValue)) {
                if (item.isDisplayed()) {
                    sleepInSeconds(1);
                    js.executeScript("arguments[0].click();", item);
                } else {
                    js.executeScript("arguments[0].scrollIntoView(true);", item);
                    sleepInSeconds(1);
                    js.executeScript("arguments[0].click();", item);
                }
                sleepInSeconds(1);
                break;
            }
        }
    }

    public int countElementNumber(WebDriver driver, String locator) {
        return findElementsByXpath(driver, locator).size();
    }

    public int countElementNumber(WebDriver driver, String locator, String... values) {
        overrideGlobalTimeout(driver, 1);
        elements = findElementsByXpath(driver, castToObject(locator, values));
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        return elements.size();
    }


    public void checkToCheckbox(WebDriver driver, String locator) {
        element = findElementByXpath(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkToCheckbox(WebDriver driver, String locator, String... values) {
        element = findElementByXpath(driver, castToObject(locator, values));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        element = findElementByXpath(driver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator, String... values) {
        element = findElementByXpath(driver, castToObject(locator, values));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplay(WebDriver driver, String locator) {
        try {
            return findElementByXpath(driver, locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementUndisplay(WebDriver driver, String locator) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, locator);
        if (elements.size() == 0) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return false;
        }
    }

    public void overrideGlobalTimeout(WebDriver driver, int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public boolean isElementDisplay(WebDriver driver, String locator, String... values) {
        overrideGlobalTimeout(driver, GlobalConstants.SUPPER_SHORT_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, castToObject(locator, values));
        if (elements.size() > 0 && elements.get(0).isDisplayed()) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else if (elements.size() == 0) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return false;
        }
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        return false;
    }

    public boolean isElementUnDisplay(WebDriver driver, String locator, String... values) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, castToObject(locator, values));
        if (elements.size() == 0) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return false;
        }
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return findElementByXpath(driver, locator).isEnabled();

    }

    public boolean isElementselected(WebDriver driver, String locator) {
        return findElementByXpath(driver, locator).isSelected();
    }

    public boolean isElementselected(WebDriver driver, String locator, String... values) {
        return findElementByXpath(driver, castToObject(locator, values)).isSelected();
    }

    public void switchToFrameorIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(findElementByXpath(driver, locator));
    }

    public void switchToDefault(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(findElementByXpath(driver, locator)).perform();
    }

    public void hoverMouseToElement(WebDriver driver, String locator, String... values) {
        action = new Actions(driver);
        action.moveToElement(findElementByXpath(driver, castToObject(locator, values))).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(findElementByXpath(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(findElementByXpath(driver, locator)).perform();
    }

    public void DragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        action = new Actions(driver);
        action.dragAndDrop(findElementByXpath(driver, sourceLocator), findElementByXpath(driver, targetLocator))
                .perform();
    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
        highlightElement(driver, locator);
        action = new Actions(driver);
        action.sendKeys(findElementByXpath(driver, locator), key).perform();
    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... values) {
        highlightElement(driver, castToObject(locator, values));
        action = new Actions(driver);
        action.sendKeys(findElementByXpath(driver, castToObject(locator, values)), key).perform();
    }

    public void upload1FileBySenkey(WebDriver driver, String locator, String imagepath) {
        findElementByXpath(driver, locator).sendKeys(imagepath);

    }

    public void upload3FilesBySenkey(WebDriver driver, String locator, String imagepath1, String imagepath2,
                                     String imagepath3) {
        findElementByXpath(driver, locator).sendKeys(imagepath1 + "\n" + imagepath2 + "\n" + imagepath3);
    }

    public Object executeForBrowser(WebDriver driver, String javaSript) {
        js = (JavascriptExecutor) driver;
        return js.executeScript(javaSript);
    }

    public boolean verifyTextInInnerText(WebDriver driver, String expectedText) {
        js = (JavascriptExecutor) driver;
        String textActual = (String) js
                .executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
        return textActual.equals(expectedText);
    }

    public void scrollToBottomPage(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollToTopPage(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public void highlightElement(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                "border: 5px solid red; border-style: dashed;");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

    }

    public void highlightElement(WebDriver driver, String locator, String... values) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, castToObject(locator, values));
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                "border: 5px solid red; border-style: dashed;");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        js.executeScript("arguments[0].click();", element);
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... values) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, castToObject(locator, values));
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToElement(WebDriver driver, String locator, String... values) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, castToObject(locator, values));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... values) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, castToObject(locator, values));
        js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        boolean status = (boolean) js.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                element);
        return status;
    }

    public void waitForPageLoadComplete(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        explicitWait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
    }

    public void waitForElementsVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, locator);
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, values))));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
    }


    public void waitForElementsInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, locator);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.SUPPER_SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(locator, values))));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, values))));
    }

    public void waitForElementsPresent(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(castToObject(locator, values))));
    }

    public void waitForElementsPresent(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(locator)));
    }

//================================================================================================================//

    public void inputToTextboxByTittle(WebDriver driver, String value, String textboxTitle) {
        if (textboxTitle.contains("Date of Birth")) {
            removeAttributeInDOM(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, "type", textboxTitle);
        }

        waitForElementVisible(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, textboxTitle);
        sendkeyToElement(driver, AbstractPageUI.TEXT_BOX_BY_TITLE, value, textboxTitle);
    }

    public void inputToTextAreaByTittle(WebDriver driver, String value, String textboxTitle) {
        waitForElementVisible(driver, AbstractPageUI.TEXTAREA_BY_TITLE, textboxTitle);
        sendkeyToElement(driver, AbstractPageUI.TEXTAREA_BY_TITLE, value, textboxTitle);
    }

    public LoginPageObject openLoginPage(WebDriver driver) {
        openUrl(driver, GlobalConstants.HOMEPAGE);
        return PageGeneratorManager.getLoginPage(driver);
    }


    public ManagerPageObject clickToButtonByName(WebDriver driver, String buttonName) {
        sleepInMiliseconds(300);
        waitForElementClickable(driver, AbstractPageUI.BUTTON_BY_NAME, buttonName);
        clickToElement(driver, AbstractPageUI.BUTTON_BY_NAME, buttonName);
        return PageGeneratorManager.getManagerPage(driver);
    }

    public AbstractPage clickToPageByName(WebDriver driver, String pageName) {
        waitForElementClickable(driver, AbstractPageUI.PAGE_NAME, pageName);
        clickToElement(driver, AbstractPageUI.PAGE_NAME, pageName);

        switch (pageName) {
            case "New Customer":
                return PageGeneratorManager.getNewCustomerPage(driver);
            case "Edit Customer":
                return PageGeneratorManager.getEditCustomerPage(driver);
            case "Delete Customer":
                return PageGeneratorManager.getDeleteCustomerPage(driver);
            case "New Account":
                return PageGeneratorManager.getNewAccountPage(driver);
            case "Fund Transfer":
                return PageGeneratorManager.getFundTransferPage(driver);
            case "Change Password":
                return PageGeneratorManager.getChangePasswordPage(driver);
            case "Mini Statement":
                return PageGeneratorManager.getMiniStatementPage(driver);
            case "Customised Statement":
                return PageGeneratorManager.getCustomisedStatementPage(driver);
            default:
                return PageGeneratorManager.getManagerPage(driver);

        }
    }

    public String getErrorMessageByTextboxTitle(WebDriver driver, String textboxTitle) {
        System.out.println(getElementText(driver, AbstractPageUI.TEXT_BOX_ERROR_MESSAGES_BY_TITLE, textboxTitle));
        return getElementText(driver, AbstractPageUI.TEXT_BOX_ERROR_MESSAGES_BY_TITLE, textboxTitle);
    }

    public String getValueByTitle(WebDriver driver, String titleName) {
        return getElementText(driver, NewCustomerPageUI.CUSTOMER_REGISTER_SUCCESS_VALUE_BY_TITLE_IN_TABLE, titleName);
    }


    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String currentDate = dateFormat.format(now);
        return currentDate;
    }
}