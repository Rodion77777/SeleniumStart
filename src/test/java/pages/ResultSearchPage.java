package pages;

import maintest.SetupTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Info;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultSearchPage {

    private EventFiringWebDriver eventDriver;
    private WebDriverWait wait;
    private final Logger LOGGER = SetupTest.jlogger;

    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]")
    private WebElement quickViewLink;

    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[1]")
    private WebElement quickViewLink2;

    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/h5/a")
    private WebElement product_name;

    @FindBy(xpath = "//*[@id=\"uniform-selectProductSort\"]/span")
    private WebElement valueSortBy;

    @FindBy(xpath = "//*[@id=\"selectProductSort\"]")
    private WebElement selectProductSort;

    @FindBy(xpath = "//*[@id=\"selectProductSort\"]/option[1]")
    private WebElement selectProductSortOption1;

    @FindBy(xpath = "//*[@id=\"selectProductSort\"]/option[2]")
    private WebElement selectProductSortOption2;

    @FindBy(xpath = "//*[@id=\"selectProductSort\"]/option[3]")
    private WebElement selectProductSortOption3;

    @FindBy(xpath = "//*[@id=\"selectProductSort\"]/option[4]")
    private WebElement selectProductSortOption4;

    @FindBy(xpath = "//*[@id=\"selectProductSort\"]/option[5]")
    private WebElement selectProductSortOption5;

    @FindBy(xpath = "//*[@id=\"selectProductSort\"]/option[6]")
    private WebElement selectProductSortOption6;

    @FindBy(xpath = "//*[@id=\"selectProductSort\"]/option[7]")
    private WebElement selectProductSortOption7;

    @FindBy(xpath = "//*[@id=\"selectProductSort\"]/option[8]")
    private WebElement selectProductSortOption8;

    @FindBy(xpath = "//*[@id=\"nb_item\"]")
    private WebElement showResultsPerPage;

    @FindBy(xpath = "//*[@id=\"nb_item\"]/option[1]")
    private WebElement howResultsPerPageOption1;

    @FindBy(xpath = "//*[@id=\"nb_item\"]/option[2]")
    private WebElement howResultsPerPageOption2;

    @FindBy(xpath = "//*[@id=\"nb_item\"]/option[3]")
    private WebElement howResultsPerPageOption3;

    @FindBy(xpath = "//*[@id=\"center_column\"]/h1/span[2]")
    private WebElement foundResultsValue;

    @FindBy(xpath = "//*[@id=\"pagination\"]/form/div/button")
    private WebElement showAllResultsButton;


    /* ====================================================
     *               Constructor MainPage
     * ================================================= */

    public ResultSearchPage (EventFiringWebDriver eventDriver) {
        LOGGER.info("Class constructor \"ResultSearchPage\"\n");
        PageFactory.initElements(eventDriver, this);
        this.eventDriver = eventDriver;
        wait = new WebDriverWait(eventDriver, 10);
    }


    /* ====================================================
     *                      Methods
     * ================================================= */

    public void clickQuickViewLink () {
        LOGGER.info("Opening of product preview.\n");
        quickViewLink.click();
    }

    public void clickQuickViewLink2 () {
        LOGGER.info("Opening of product preview.\n");
        quickViewLink2.click();
    }

    public boolean productNameIs (String request) {
        LOGGER.info("Verification of product and request correspondence.\n");
        return product_name
                .getText()
                .toLowerCase()
                .contains(request);
    }

    // Set select sort by -->
    public String getValueSortBy () {
        LOGGER.info("Reading the set method of sorting the results.\n");
        return valueSortBy.getText();
    }

    public void selectSortbyDefault () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTDEFAULT);
        selectProductSort.sendKeys(Info.SORTDEFAULT);
        PageFactory.initElements(eventDriver, selectProductSort);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption1));
    }

    public void selectSortbyPriceAsc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTPRICEASC);
        selectProductSort.sendKeys(Info.SORTPRICEASC);
        PageFactory.initElements(eventDriver, selectProductSort);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption2));
    }

    public void selectSortbyPriceDesc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTPRICEDESC);
        selectProductSort.sendKeys(Info.SORTPRICEDESC);
        PageFactory.initElements(eventDriver, selectProductSort);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption3));
    }

    public void selectSortbyNameAsc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTNAMEASC);
        selectProductSort.sendKeys(Info.SORTNAMEASC);
        PageFactory.initElements(eventDriver, selectProductSort);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption4));
    }

    public void selectSortbyNameDesc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTNAMEDESC);
        selectProductSort.sendKeys(Info.SORTNAMEDESC);
        PageFactory.initElements(eventDriver, selectProductSort);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption5));
    }

    public void selectSortbyQuantityDesc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTQUANTITYDESC);
        selectProductSort.sendKeys(Info.SORTQUANTITYDESC);
        PageFactory.initElements(eventDriver, selectProductSort);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption6));
    }

    public void selectSortbyReferenceAsc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTREFERENCEASC);
        selectProductSort.sendKeys(Info.SORTREFERENCEASC);
        PageFactory.initElements(eventDriver, selectProductSort);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption7));
    }

    public void selectSortbyReferenceDesc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTREFERENCEDESC);
        selectProductSort.sendKeys(Info.SORTREFERENCEDESC);
        PageFactory.initElements(eventDriver, selectProductSort);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption8));
    }
    // Set select sort by <--

    // Set show results per page value -->
    public void setShowResultsPerPage_12 () {
        LOGGER.info("Setting the number of results displayed: 12 per page.\n");
        showResultsPerPage.sendKeys(Info.SHOW12);
        wait.until(ExpectedConditions.elementToBeSelected(howResultsPerPageOption1));
    }

    public void setShowResultsPerPage_24 () {
        LOGGER.info("Setting the number of results displayed: 24 per page.\n");
        showResultsPerPage.sendKeys(Info.SHOW24);
        wait.until(ExpectedConditions.elementToBeSelected(howResultsPerPageOption2));
    }

    public void setShowResultsPerPage_60 () {
        LOGGER.info("Setting the number of results displayed: 60 per page.\n");
        showResultsPerPage.sendKeys(Info.SHOW60);
        wait.until(ExpectedConditions.elementToBeSelected(howResultsPerPageOption3));
    }

    public void showALLResults () {
        LOGGER.info("Display all results found on the page.\n");
        showAllResultsButton.submit();
    }
    // Set show results per page value <--

    public int getFoundResultsValue () {
        LOGGER.info("Reading of the number of results found.\n");
        return Integer.parseInt(foundResultsValue.getText().replaceAll("[ \\D]", ""));
    }
}