package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Info;

public class MainPage {

    private EventFiringWebDriver eventDriver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"search_query_top\"]")
    private WebElement responseField;

    @FindBy(xpath = "//*[@id=\"searchbox\"]/button")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"header_logo\"]/a")
    private WebElement mainPageLink;

    @FindBy(xpath = "//*[@id=\"contact-link\"]")
    private WebElement contactLink;

    @FindBy(xpath = "//*[@id=\"languages-block-top\"]")
    private WebElement languages;

    @FindBy(xpath = "//*[@id=\"setCurrency\"]")
    private WebElement currencySelectButton;

    @FindBy(xpath = "//*[contains(@rel, 'nofollow')]")
    private WebElement set_currency_button;

    @FindBy(xpath = "//*[@id=\"first-currencies\"]")
    private WebElement currencyToggle;

    //==================================>>

    @FindBy(xpath = "/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[1]")
    private WebElement setCurrencyUAH;

    @FindBy(xpath = "/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[2]")
    private WebElement setCurrencyUSD;

    @FindBy(xpath = "/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/ul/li[3]")
    private WebElement setCurrencyEUR;

    //==================================<<

    @FindBy(xpath = "/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/form/div/strong")
    private WebElement currencyText;

    @FindBy(css = "a[title='Войти в учетную запись покупателя']")
    private WebElement setupButton;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]")
    private WebElement popProdLinkView;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[1]/span")
    private WebElement popProdPrice;


    /* ====================================================
     *               Constructor MainPage
     * ================================================= */

    public MainPage (EventFiringWebDriver eventDriver) {
        PageFactory.initElements(eventDriver, this);
        this.eventDriver = eventDriver;
        wait = new WebDriverWait(eventDriver, 10);
    }


    /* ====================================================
     *                      Methods
     * ================================================= */

    public void setRequest(String response) {
        responseField.sendKeys(Keys.CONTROL + "a");
        responseField.sendKeys(Keys.DELETE);
        responseField.sendKeys(response);
    }

    public void clickSearchButton () {
        searchButton.click();
    }

    public void getMainPage () {
        mainPageLink.click();
    }

    public void clickContactLink () {
        contactLink.click();
    }

    public String getLanguages () {
        return languages.getText();
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
        currencySelectButton.click();
    }

    public String getCurrency () {
        return currencyText.getText();
    }

    public void setCurrencyUAH () {
        currencySelectButton.click();
        setCurrencyUAH.click();
        wait.until(ExpectedConditions.invisibilityOf(setCurrencyUAH));
    }

    public void setCurrencyUAH_JS () {
        eventDriver.executeScript("javascript:setCurrency(1);", set_currency_button);
        set_currency_button.click();
    }

    public void setCurrencyUSD () {
        currencySelectButton.click();
        setCurrencyUSD.click();
        wait.until(ExpectedConditions.invisibilityOf(setCurrencyUSD));
    }

    public void setCurrencyUSD_JS () {
        eventDriver.executeScript("javascript:setCurrency(3);", set_currency_button);
        set_currency_button.click();
    }

    public void setCurrencyEUR () {
        currencySelectButton.click();
        setCurrencyEUR.click();
        wait.until(ExpectedConditions.invisibilityOf(setCurrencyEUR));
    }

    public void setCurrencyEUR_JS () {
        eventDriver.executeScript("javascript:setCurrency(2);", set_currency_button);
        set_currency_button.click();
    }

    public void clickSetupButton () {
        setupButton.click();
    }

    public void openPopProdLink () {
        popProdLinkView.click();
    }

    public String getPopProdPrice () {
        return popProdPrice.getText();
    }

}