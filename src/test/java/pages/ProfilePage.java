package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ProfilePage {

    private EventFiringWebDriver eventDriver;

    @FindBy(xpath = "//*[contains(@class, 'logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")
    private WebElement userName;


    /* ====================================================
     *               Constructor ProfilePage
     * ================================================= */
    public ProfilePage (EventFiringWebDriver eventDriver) {
        PageFactory.initElements(eventDriver, this);
        this.eventDriver = eventDriver;
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
