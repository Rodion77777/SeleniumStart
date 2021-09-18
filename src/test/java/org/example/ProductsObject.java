package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsObject {

    public WebDriver driver;


    /* ====================================================
     *             Constructor ProductsObject
     * ================================================= */

    public ProductsObject (WebDriver driver) {
        this.driver = driver;
    }


    /* ====================================================
     *                      Methods
     * ================================================= */

    public List<WebElement> itemFinder () {
        return driver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li"));
    }

    public List<WebElement> priceFinder (List<WebElement> itemFinder, int numItem) {
        return itemFinder.get(numItem).findElements(By.xpath("div/div[2]/div[1]/span"));
    }

    public List<WebElement> priceFinder2 (WebElement element) {
        return element.findElements(By.xpath("div/div[2]/div[1]/span"));
    }

    public List<WebElement> popItemFinder () {
        return driver.findElements(By.xpath("//*[@id=\"homefeatured\"]/li"));
    }

    public WebElement nameFinder (WebElement element) {
        return element.findElement(By.xpath("div/div[2]/h5/a"));
    }
}