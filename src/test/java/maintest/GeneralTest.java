package maintest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ConfProperties;
import utils.Info;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class GeneralTest extends SetupTest{
    private static final PrintStream OUT = System.out;

    public void download_wait () {
        // TODO: rebuild this method
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("html")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));// /html/body
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[3]/i")));

        // page load timeout engage.
        jlogger.info("Page update.");
        eventDriver.get(eventDriver.getCurrentUrl());
    }

    public void findRequest(String request) {
        jlogger.log(Level.INFO, "Request search: {0}", request);
        mainPage.setRequest(request);
        mainPage.clickSearchButton();
    }

    public double getNewOrOldPriceIfPresent (WebElement element) {
        return productsObject.priceFinder2(element).stream()
                .map(Info::parseDouble)
                .max(Double::compare)
                .get();
    }

    @Test
    public void generalTest () {
        eventDriver.get(ConfProperties.getProperty("mainpage"));

        jlogger.info("Checking currency in prices.");
        List<WebElement> productList = productsObject.popItemFinder();
        List<WebElement> productPrice = productList.stream()
                .map(x -> productsObject.priceFinder2(x).get(0))
                .collect(Collectors.toList());

        String currency = mainPage.getCurrency();

        for (WebElement x : productPrice) {
            switch (Info.getPriceCurrency(x.getText())) {
                case Info.₴ -> assertEquals(Info.UAH, currency);
                case Info.$ -> assertEquals(Info.USD, currency);
                case Info.€ -> assertEquals(Info.EUR, currency);
            }
        }
        jlogger.fine("Verification of currency in prices completed!");

        currency = null;
        productList = null;
        productPrice = null;

        jlogger.info("Setting the selected currency");
        mainPage.setCurrencyUSD(); // point 3
        // TODO: uncomment line in the end of project
        //checkCurrency(Info.USD);


        jlogger.info("Start of currency compliance checking.");

        String request = "dress";
        jlogger.log(Level.INFO, "Search request: {0}", request);
        download_wait();
        findRequest(request); // point 4
        download_wait();
        resultSearchPage.selectSortbyPriceDesc();
        resultSearchPage.setShowResultsPerPage_60();
        download_wait();

        List<WebElement> products = productsObject.itemFinder();
        List<WebElement> productsName = products.stream()
                .map(productsObject::nameFinder)
                .collect(Collectors.toList());

        for (WebElement x : productsName) {
            // TODO: uncomment line in the end of project
            //assertTrue(x.getText().toLowerCase().contains(request.toLowerCase()));
        }

        productsName = null;

        assertNotNull(String.valueOf(resultSearchPage.getFoundResultsValue()));
        assertEquals(products.size(), resultSearchPage.getFoundResultsValue());

        // point 6
        boolean result = products.stream()
                .map(x -> Info.getPriceCurrency(x.findElement(By.xpath("div/div[2]/div[1]/span")).getText()))
                .anyMatch(x -> !x.equals(Info.$));

        assertFalse(result);
        jlogger.fine("Currency compliance check completed!");

        // point 7
        jlogger.info("Checking the sorting of goods.");
        assertEquals(Info.SORTPRICEDESC, resultSearchPage.getValueSortBy());

        // point 8
        jlogger.info("Start of the merchandise sorting compliance check.");
        List<Double> list3 = products.stream()
                .map(this::getNewOrOldPriceIfPresent)
                .collect(Collectors.toList());

        List<Double> sortedList = list3.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        assertEquals(list3, sortedList);
        jlogger.fine("Checks on the appropriateness of the sorting of goods are completed!");

        // point 9
        jlogger.info("The start of a price and discount check on goods.");
        List<List<WebElement>> pPrice = products.stream()
                .map(productsObject::priceFinder2)
                .filter(x -> x.size() == 3)
                .collect(Collectors.toList());

        products = null;
        list3 = null;

        for (List<WebElement> x : pPrice) {

            double newPrice = Info.parseDouble(x.get(0));
            double oldPrice = Info.parseDouble(x.get(1));
            int percent = Info.parseInteger(x.get(2));
            double assertPrice = oldPrice - (oldPrice * Math.abs(percent) / 100);

            assertEquals(3, x.size());

            assertTrue(newPrice > 0);
            assertTrue(x.get(0).getText().length() > 0);
            OUT.print(newPrice + " | ");

            assertTrue(oldPrice > 0);
            assertTrue(x.get(1).getText().length() > 0);
            OUT.print(oldPrice + " | ");

            assertTrue(percent < 0);
            assertTrue(x.get(2).getText().length() > 0);
            OUT.print(percent + " |\n");

            assertTrue(x.get(2).getText().matches("^-\\d+%$"));

            OUT.println(new DecimalFormat("### ##0.00").format(assertPrice));

            // point 10
            // TODO: uncomment line in the end of project
            //assertEquals(assertPrice, newPrice);
            jlogger.fine("Checking prices and discounts on goods is complete!");
        }
    }
}
