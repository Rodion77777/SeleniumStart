package pages;

import utils.SetupTest;
import org.openqa.selenium.By;
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

    public ResultSearchPage(EventFiringWebDriver eventDriver) {
        LOGGER.info("Class constructor \"ResultSearchPage2\"\n");
        this.eventDriver = eventDriver;
        wait = new WebDriverWait(eventDriver, 10);
    }

    public void clickQuickViewLink () {
        LOGGER.info("Opening of product preview.\n");
        eventDriver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]")).click();
    }

    public void clickQuickViewLink2 () {
        LOGGER.info("Opening of product preview.\n");
        eventDriver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]")).click();
    }

    public boolean productNameIs (String request) {
        LOGGER.info("Verification of product and request correspondence.\n");
        return eventDriver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/h5/a"))
                .getText()
                .toLowerCase()
                .contains(request);
    }

    // Set select sort by -->
    public String getValueSortBy () {
        LOGGER.info("Reading the set method of sorting the results.\n");
        return eventDriver.findElement(By.xpath("//*[@id=\"uniform-selectProductSort\"]/span")).getText();
    }

    public void selectSortbyDefault () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTDEFAULT);
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTDEFAULT);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[1]")));
    }

    public void selectSortbyPriceAsc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTPRICEASC);
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTPRICEASC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[2]")));
    }

    public void selectSortbyPriceDesc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTPRICEDESC);
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTPRICEDESC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[3]")));
    }

    public void selectSortbyNameAsc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTNAMEASC);
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTNAMEASC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[4]")));
    }

    public void selectSortbyNameDesc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTNAMEDESC);
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTNAMEDESC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[5]")));
    }

    public void selectSortbyQuantityDesc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTQUANTITYDESC);
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTQUANTITYDESC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[6]")));
    }

    public void selectSortbyReferenceAsc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTREFERENCEASC);
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTREFERENCEASC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[7]")));
    }

    public void selectSortbyReferenceDesc () {
        LOGGER.log(Level.INFO, "Set how the results are sorted: {0}\n", Info.SORTREFERENCEDESC);
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTREFERENCEDESC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[8]")));
    }
    // Set select sort by <--

    // Set show results per page value -->
    public void setShowResultsPerPage_12 () {
        LOGGER.info("Setting the number of results displayed: 12 per page.\n");
        eventDriver.findElement(By.id("nb_item")).sendKeys(Info.SHOW12);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"nb_item\"]/option[1]")));
    }

    public void setShowResultsPerPage_24 () {
        LOGGER.info("Setting the number of results displayed: 24 per page.\n");
        eventDriver.findElement(By.id("nb_item")).sendKeys(Info.SHOW24);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"nb_item\"]/option[2]")));
    }

    public void setShowResultsPerPage_60 () {
        LOGGER.info("Setting the number of results displayed: 60 per page.\n");
        eventDriver.findElement(By.id("nb_item")).sendKeys(Info.SHOW60);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"nb_item\"]/option[3]")));
    }

    public void showALLResults () {
        LOGGER.info("Display all results found on the page.\n");
        eventDriver.findElement(By.xpath("//*[@id=\"pagination\"]/form/div/button")).submit();
    }
    // Set show results per page value <--

    public int getFoundResultsValue () {
        LOGGER.info("Reading of the number of results found.\n");
        return Integer.parseInt(eventDriver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[2]"))
                .getText().replaceAll("[ \\D]", ""));
    }
}