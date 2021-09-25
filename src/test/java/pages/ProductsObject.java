package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

public class ProductsObject {

    public EventFiringWebDriver eventDriver;


    /* ====================================================
     *             Constructor ProductsObject
     * ================================================= */

    public ProductsObject (EventFiringWebDriver eventDriver) { this.eventDriver = eventDriver;}


    /* ====================================================
     *                      Methods
     * ================================================= */

    public List<WebElement> itemFinder () {
        return eventDriver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li"));
    }

    public List<WebElement> priceFinder2 (WebElement element) {
        return element.findElements(By.xpath("div/div[2]/div[1]/span"));
    }

    public List<WebElement> popItemFinder () {
        return eventDriver.findElements(By.xpath("//*[@id=\"homefeatured\"]/li"));
    }

    public WebElement nameFinder (WebElement element) {
        return element.findElement(By.xpath("div/div[2]/h5/a"));
    }
}