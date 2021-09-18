package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginForm {

    public WebDriver driver;

    public LoginForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
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
