package alternate_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Info;

public class MainPage2 {

    private EventFiringWebDriver eventDriver;
    private WebDriverWait wait;

    public MainPage2 (EventFiringWebDriver eventDriver) {
        this.eventDriver = eventDriver;
        wait = new WebDriverWait(eventDriver, 10);
    }

    public void setRequest(String response) {
        WebElement element = eventDriver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(response);
    }

    public void clickSearchButton () {
        eventDriver.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click();
    }

    public void getMainPage () {
        eventDriver.findElement(By.xpath("//*[@id=\"header_logo\"]/a")).click();
    }

    public void clickContactLink () {
        eventDriver.findElement(By.xpath("//*[@id=\"contact-link\"]")).click();
    }

    public String getLanguages () {
        return eventDriver.findElement(By.xpath("//*[@id=\"languages-block-top\"]")).getText();
    }

    public void setLangEnglish () {
        setLang(Info.LINK_EN);
    }

    public void setLangRussian () {
        setLang(Info.LINK_RU);
    }

    public void setLangUkrainian () {
        setLang(Info.LINK_UK);
    }

    private void setLang (String link) {
        eventDriver.get(
                eventDriver.getCurrentUrl()
                        .replaceFirst(Info.BASE_LINK + "\\w{2}/", link)
        );
    }

    public void clickCurrencyButton () {
        eventDriver.findElement(By.xpath("//*[@id=\"setCurrency\"]")).click();
    }

    public String getCurrency () {
        return eventDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/div/strong")).getText();
    }

    public void setCurrencyUAH () {
        eventDriver.findElement(By.xpath("//*[@id=\"setCurrency\"]")).click();
        eventDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[1]")).click();
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[1]")));
    }

    public void setCurrencyUAH_JS () {
        WebElement button = eventDriver.findElement(By.xpath("//*[contains(@rel, 'nofollow')]"));
        eventDriver.executeScript("javascript:setCurrency(1);", button);
        button.click();
    }

    public void setCurrencyUSD () {
        eventDriver.findElement(By.xpath("//*[@id=\"setCurrency\"]")).click();
        eventDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[2]")).click();
        wait.until(ExpectedConditions.invisibilityOf(eventDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[2]"))));
    }

    public void setCurrencyUSD_JS () {
        WebElement button = eventDriver.findElement(By.xpath("//*[contains(@rel, 'nofollow')]"));
        eventDriver.executeScript("javascript:setCurrency(3);", button);
        button.click();
    }

    public void setCurrencyEUR () {
        eventDriver.findElement(By.xpath("//*[@id=\"setCurrency\"]")).click();
        eventDriver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[3]")).click();
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[3]")));
    }

    public void setCurrencyEUR_JS () {
        WebElement button = eventDriver.findElement(By.xpath("//*[contains(@rel, 'nofollow')]"));
        eventDriver.executeScript("javascript:setCurrency(2);", button);
        button.click();
    }

    public void clickSetupButton () {
        eventDriver.findElement(By.xpath("a[title='Войти в учетную запись покупателя']")).click();
    }

    public void openPopProdLink () {
        eventDriver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]")).click();
    }

    public String getPopProdPrice () {
        return eventDriver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[1]/span")).getText();
    }
}