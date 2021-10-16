package pages;

import utils.SetupTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Info;

import java.util.logging.Logger;

public class MainPage {

    private EventFiringWebDriver eventDriver;
    private WebDriverWait wait;
    private final Logger LOGGER = SetupTest.jlogger;

    public MainPage(EventFiringWebDriver eventDriver) {
        LOGGER.info("Class constructor \"MainPage\"\n");
        this.eventDriver = eventDriver;
        wait = new WebDriverWait(eventDriver, 10);
    }

    public void setRequest(String response) {
        LOGGER.info("Clearing the input field and entering a query.\n");
        WebElement element = eventDriver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(response);
    }

    public void clickSearchButton () {
        eventDriver.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click();
    }

    public void getMainPage () {
        LOGGER.info("Switch to the home page.\n");
        eventDriver.findElement(By.xpath("//*[@id=\"header_logo\"]/a")).click();
    }

    public void clickContactLink () {
        LOGGER.info("Switch to the contact link.\n");
        eventDriver.findElement(By.xpath("//*[@id=\"contact-link\"]")).click();
    }

    public String getLanguages () {
        LOGGER.info("Reading the set interface language.\n");
        return eventDriver.findElement(By.xpath("//*[@id=\"languages-block-top\"]")).getText();
    }

    public void setLangEnglish () {
        LOGGER.info("Interface language setting: English.\n");
        setLang(Info.LINK_EN);
    }

    public void setLangRussian () {
        LOGGER.info("Interface language setting: Russian.\n");
        setLang(Info.LINK_RU);
    }

    public void setLangUkrainian () {
        LOGGER.info("Interface language setting: Ukrainian.\n");
        setLang(Info.LINK_UK);
    }

    private void setLang (String link) {
        eventDriver.get(
                eventDriver.getCurrentUrl()
                        .replaceFirst(Info.BASE_LINK + "\\w{2}/", link)
        );
    }

    public void clickCurrencyButton () {
        LOGGER.info("Сall up the currency selection menu.\n");
        eventDriver.findElement(By.xpath("//*[@id=\"setCurrency\"]")).click();
    }

    public String getCurrency () {
        LOGGER.info("Reading of set currency.\n");
        return eventDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/div/strong")).getText();
    }

    public void setCurrencyUAH () {
        LOGGER.info("Currency setting: UAH.\n");
        eventDriver.findElement(By.xpath("//*[@id=\"setCurrency\"]")).click();
        eventDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[1]")).click();
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[1]")));
    }

    public void setCurrencyUAH_JS () {
        LOGGER.info("(executing JavaScript) Currency setting: UAH.\n");
        WebElement button = eventDriver.findElement(By.xpath("//*[contains(@rel, 'nofollow')]"));
        eventDriver.executeScript("javascript:setCurrency(1);", button);
        button.click();
    }

    public void setCurrencyUSD () {
        LOGGER.info("Currency setting: USD.\n");
        eventDriver.findElement(By.xpath("//*[@id=\"setCurrency\"]")).click();
        eventDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[2]")).click();
        wait.until(ExpectedConditions.invisibilityOf(eventDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[2]"))));
    }

    public void setCurrencyUSD_JS () {
        LOGGER.info("(executing JavaScript) Currency setting: USD.\n");
        WebElement button = eventDriver.findElement(By.xpath("//*[contains(@rel, 'nofollow')]"));
        eventDriver.executeScript("javascript:setCurrency(3);", button);
        button.click();
    }

    public void setCurrencyEUR () {
        LOGGER.info("Currency setting: EUR.\n");
        eventDriver.findElement(By.xpath("//*[@id=\"setCurrency\"]")).click();
        eventDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[3]")).click();
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[3]")));
    }

    public void setCurrencyEUR_JS () {
        LOGGER.info("(executing JavaScript) Currency setting: EUR.\n");
        WebElement button = eventDriver.findElement(By.xpath("//*[contains(@rel, 'nofollow')]"));
        eventDriver.executeScript("javascript:setCurrency(2);", button);
        button.click();
    }

    public void clickSetupButton () {
        LOGGER.info("Calling up the account login window.\n");
        eventDriver.findElement(By.xpath("a[title='Войти в учетную запись покупателя']")).click();
    }
}