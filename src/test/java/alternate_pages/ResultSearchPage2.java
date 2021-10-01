package alternate_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Info;

public class ResultSearchPage2 {

    private EventFiringWebDriver eventDriver;
    private WebDriverWait wait;

    public ResultSearchPage2 (EventFiringWebDriver eventDriver) {
        this.eventDriver = eventDriver;
        wait = new WebDriverWait(eventDriver, 10);
    }

    public void clickQuickViewLink () {
        eventDriver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]")).click();
    }

    public void clickQuickViewLink2 () {
        eventDriver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]")).click();
    }

    public boolean productNameIs (String request) {
        return eventDriver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/h5/a"))
                .getText()
                .toLowerCase()
                .contains(request);
    }

    // Set select sort by -->
    public String getValueSortBy () {
        return eventDriver.findElement(By.xpath("//*[@id=\"uniform-selectProductSort\"]/span")).getText();
    }

    public void selectSortbyDefault () {
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTDEFAULT);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[1]")));
    }

    public void selectSortbyPriceAsc () {
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTPRICEASC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[2]")));
    }

    public void selectSortbyPriceDesc () {
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTPRICEDESC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[3]")));
    }

    public void selectSortbyNameAsc () {
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTNAMEASC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[4]")));
    }

    public void selectSortbyNameDesc () {
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTNAMEDESC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[5]")));
    }

    public void selectSortbyQuantityDesc () {
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTQUANTITYDESC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[6]")));
    }

    public void selectSortbyReferenceAsc () {
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTREFERENCEASC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[7]")));
    }

    public void selectSortbyReferenceDesc () {
        eventDriver.findElement(By.id("selectProductSort")).sendKeys(Info.SORTREFERENCEDESC);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"selectProductSort\"]/option[8]")));
    }
    // Set select sort by <--

    // Set show results per page value -->
    public void setShowResultsPerPage_12 () {
        eventDriver.findElement(By.id("nb_item")).sendKeys(Info.SHOW12);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"nb_item\"]/option[1]")));
    }

    public void setShowResultsPerPage_24 () {
        eventDriver.findElement(By.id("nb_item")).sendKeys(Info.SHOW24);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"nb_item\"]/option[2]")));
    }

    public void setShowResultsPerPage_60 () {
        eventDriver.findElement(By.id("nb_item")).sendKeys(Info.SHOW60);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id=\"nb_item\"]/option[3]")));
    }

    public void showALLResults () {
        eventDriver.findElement(By.xpath("//*[@id=\"pagination\"]/form/div/button")).submit();
    }
    // Set show results per page value <--

    public int getFoundResultsValue () {
        return Integer.parseInt(eventDriver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[2]"))
                .getText().replaceAll("[ \\D]", ""));
    }
}