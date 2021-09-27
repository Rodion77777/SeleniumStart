package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoginForm {

    private EventFiringWebDriver eventDriver;

    public LoginForm(EventFiringWebDriver eventDriver) {
        PageFactory.initElements(eventDriver, this);
        this.eventDriver = eventDriver;
    }

    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement emailfield;

    @FindBy(xpath = "//*[contains(@id, 'passwd')]")
    private WebElement passwordfield;

    @FindBy(xpath = "//*[contains(@id, 'SubmitLogin')]")
    private WebElement passwordButton;


    public void inputLogin (String email) {
        emailfield.sendKeys(email);
    }

    public void inputPassword (String password) {
        passwordfield.sendKeys(password);
    }

    public void clickLoginButton () {
        passwordButton.click();
    }
}
