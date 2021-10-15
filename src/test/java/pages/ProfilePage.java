package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ProfilePage {

    private EventFiringWebDriver eventDriver;

    public ProfilePage(EventFiringWebDriver eventDriver) {
        PageFactory.initElements(eventDriver, this);
        this.eventDriver = eventDriver;
    }

    public void userLogout () {
        eventDriver.findElement(By.xpath("//*[contains(@class, 'logout')]")).click();
    }

    public String getUserName () {
        return eventDriver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText();
    }
}
