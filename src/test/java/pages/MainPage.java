package pages;

import maintest.SetupTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Info;

import java.util.logging.Logger;

public class MainPage {

    private EventFiringWebDriver eventDriver;
    private WebDriverWait wait;
    private final Logger LOGGER = SetupTest.jlogger;

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



    /* ====================================================
     *               Constructor MainPage
     * ================================================= */

    public MainPage (EventFiringWebDriver eventDriver) {
        LOGGER.info("Class constructor \"MainPage\"\n");
        PageFactory.initElements(eventDriver, this);
        this.eventDriver = eventDriver;
        wait = new WebDriverWait(eventDriver, 10);
    }


    /* ====================================================
     *                      Methods
     * ================================================= */

    public void setRequest(String request) {
        LOGGER.info("Clearing the input field and entering a query.\n");
        responseField.sendKeys(Keys.CONTROL + "a");
        responseField.sendKeys(Keys.DELETE);
        responseField.sendKeys(request);
    }

    public void clickSearchButton () {
        searchButton.click();
    }

    public void getMainPage () {
        LOGGER.info("Switch to the home page.\n");
        mainPageLink.click();
    }

    public void clickContactLink () {
        LOGGER.info("Switch to the contact link.\n");
        contactLink.click();
    }

    public String getLanguages () {
        LOGGER.info("Reading the set interface language.\n");
        return languages.getText();
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
        currencySelectButton.click();
    }

    public String getCurrency () {
        LOGGER.info("Reading of set currency.\n");
        return currencyText.getText();
    }

    public void setCurrencyUAH () {
        LOGGER.info("Currency setting: UAH.\n");
        currencySelectButton.click();
        setCurrencyUAH.click();
        wait.until(ExpectedConditions.invisibilityOf(setCurrencyUAH));
    }

    // TODO: JScript methods not checked after changes
    public void setCurrencyUAH_JS () {
        LOGGER.info("(executing JavaScript) Currency setting: UAH.\n");
        eventDriver.executeScript("javascript:setCurrency(1);", set_currency_button);
        set_currency_button.click();
    }

    public void setCurrencyUSD () {
        LOGGER.info("Currency setting: USD.\n");
        currencySelectButton.click();
        setCurrencyUSD.click();
        wait.until(ExpectedConditions.invisibilityOf(setCurrencyUSD));
    }

    // TODO: JScript methods not checked after changes
    public void setCurrencyUSD_JS () {
        LOGGER.info("(executing JavaScript) Currency setting: USD.\n");
        eventDriver.executeScript("javascript:setCurrency(3);", set_currency_button);
        set_currency_button.click();
    }

    public void setCurrencyEUR () {
        LOGGER.info("Currency setting: EUR.\n");
        currencySelectButton.click();
        setCurrencyEUR.click();
        wait.until(ExpectedConditions.invisibilityOf(setCurrencyEUR));
    }

    // TODO: JScript methods not checked after changes
    public void setCurrencyEUR_JS () {
        LOGGER.info("(executing JavaScript) Currency setting: EUR.\n");
        eventDriver.executeScript("javascript:setCurrency(2);", set_currency_button);
        set_currency_button.click();
    }

    public void clickSetupButton () {
        LOGGER.info("Calling up the account login window.\n");
        setupButton.click();
    }
}