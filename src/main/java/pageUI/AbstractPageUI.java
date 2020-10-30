package pageUI;


public class AbstractPageUI {
    public static final String TEXT_BOX_BY_TITLE = "//td[contains(text(),'%s')]/following-sibling::td/input";
    public static final String TEXT_BOX_ERROR_MESSAGES_BY_TITLE = "//td[contains(text(),'%s')]/following-sibling::td/label";
    public static final String TEXTAREA_BY_TITLE = "//td[text()='%s']/following-sibling::td/textarea";
    public static final String BUTTON_BY_NAME = "//input[@value='%s']";
    public static final String PAGE_NAME = "//a[text()='%s']";
}
