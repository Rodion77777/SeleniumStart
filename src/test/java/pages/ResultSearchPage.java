package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Info;

public class ResultSearchPage {

    private EventFiringWebDriver eventDriver;
    private WebDriverWait wait;

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
        PageFactory.initElements(eventDriver, this);
        this.eventDriver = eventDriver;
        wait = new WebDriverWait(eventDriver, 10);
    }


    /* ====================================================
     *                      Methods
     * ================================================= */

    public void clickQuickViewLink () {
        quickViewLink.click();
    }

    public void clickQuickViewLink2 () {
        quickViewLink2.click();
    }

    public boolean productNameIs (String response) {
        return product_name
                .getText()
                .toLowerCase()
                .contains(response);
    }

    // Set select sort by -->
    public String getValueSortBy () {
        return valueSortBy.getText();
    }

    public void selectSortbyDefault () {
        selectProductSort.sendKeys(Info.SORTDEFAULT);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption1));
    }

    public void selectSortbyPriceAsc () {
        selectProductSort.sendKeys(Info.SORTPRICEASC);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption2));
    }

    public void selectSortbyPriceDesc () {
        selectProductSort.sendKeys(Info.SORTPRICEDESC);
        PageFactory.initElements(eventDriver, selectProductSort);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption3));
    }

    public void selectSortbyNameAsc () {
        selectProductSort.sendKeys(Info.SORTNAMEASC);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption4));
    }

    public void selectSortbyNameDesc () {
        selectProductSort.sendKeys(Info.SORTNAMEDESC);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption5));
    }

    public void selectSortbyQuantityDesc () {
        selectProductSort.sendKeys(Info.SORTQUANTITYDESC);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption6));
    }

    public void selectSortbyReferenceAsc () {
        selectProductSort.sendKeys(Info.SORTREFERENCEASC);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption7));
    }

    public void selectSortbyReferenceDesc () {
        selectProductSort.sendKeys(Info.SORTREFERENCEDESC);
        wait.until(ExpectedConditions.elementToBeSelected(selectProductSortOption8));
    }
    // Set select sort by <--

    // Set show results per page value -->
    public void setShowResultsPerPage_12 () {
        showResultsPerPage.sendKeys(Info.SHOW12);
        wait.until(ExpectedConditions.elementToBeSelected(howResultsPerPageOption1));
    }

    public void setShowResultsPerPage_24 () {
        showResultsPerPage.sendKeys(Info.SHOW24);
        wait.until(ExpectedConditions.elementToBeSelected(howResultsPerPageOption2));
    }

    public void setShowResultsPerPage_60 () {
        showResultsPerPage.sendKeys(Info.SHOW60);
        wait.until(ExpectedConditions.elementToBeSelected(howResultsPerPageOption3));
    }

    public void showALLResults () {
        showAllResultsButton.submit();
    }
    // Set show results per page value <--

    public int getFoundResultsValue () {
        return Integer.parseInt(foundResultsValue.getText().replaceAll("[ \\D]", ""));
    }
}