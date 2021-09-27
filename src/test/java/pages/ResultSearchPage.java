package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.Info;

import java.util.logging.Logger;

public class ResultSearchPage {

    private EventFiringWebDriver eventDriver;

    // TODO: remove unused variables and methods
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

    @FindBy(xpath = "//*[@id=\"nb_item\"]")
    private WebElement showResultsPerPage;

    @FindBy(xpath = "//*[@id=\"center_column\"]/h1/span[2]")
    private WebElement foundResultsValue;


    /* ====================================================
     *               Constructor MainPage
     * ================================================= */

    public ResultSearchPage (EventFiringWebDriver eventDriver) {
        PageFactory.initElements(eventDriver, this);
        this.eventDriver = eventDriver;
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
    }

    public void selectSortbyPriceAsc () {
        selectProductSort.sendKeys(Info.SORTPRICEASC);
    }

    public void selectSortbyPriceDesc () {
        selectProductSort.sendKeys(Info.SORTPRICEDESC);
    }

    public void selectSortbyNameAsc () {
        selectProductSort.sendKeys(Info.SORTNAMEASC);
    }

    public void selectSortbyNameDesc () {
        selectProductSort.sendKeys(Info.SORTNAMEDESC);
    }

    public void selectSortbyQuantityDesc () {
        selectProductSort.sendKeys(Info.SORTQUANTITYDESC);
    }

    public void selectSortbyReferenceAsc () {
        selectProductSort.sendKeys(Info.SORTREFERENCEASC);
    }

    public void selectSortbyReferenceDesc () {
        selectProductSort.sendKeys(Info.SORTREFERENCEDESC);
    }
    // Set select sort by <--


    // Set show results per page value -->
    public void setShowResultsPerPage_12 () {
        showResultsPerPage.sendKeys(Info.SHOW12);
    }

    public void setShowResultsPerPage_24 () {
        showResultsPerPage.sendKeys(Info.SHOW24);
    }

    public void setShowResultsPerPage_60 () {
        showResultsPerPage.sendKeys(Info.SHOW60);
    }
    // Set show results per page value <--

    public int getFoundResultsValue () {
        return Integer.parseInt(foundResultsValue.getText().replaceAll("[ \\D]", ""));
    }
}