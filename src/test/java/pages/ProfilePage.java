package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {

    public WebDriver driver;

    @FindBy(xpath = "//*[contains(@class, 'logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")
    private WebElement userName;


    /* ====================================================
     *               Constructor ProfilePage
     * ================================================= */
    public ProfilePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /* ====================================================
     *                      Methods
     * ================================================= */
    public void userLogout () {
        logoutButton.click();
    }

    public String getUserName () {
        return userName.getText();
    }

}
