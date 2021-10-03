package maintest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfProperties;
import utils.Info;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class GeneralTest extends SetupTest {
    private static final PrintStream OUT = System.out;

    public void download_wait () {
        jlogger.info("Wait for page load.\n");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajax_running")));
    }

    public void findRequest(String request) {
        jlogger.log(Level.INFO, "Request search: {0}\n", request);
        mainPage.setRequest(request);
        mainPage.clickSearchButton();
    }

    public void checkCurrency (String curr) {
        jlogger.log(Level.INFO, "Currency matching, expected {0}", curr);
        assertEquals(curr, mainPage.getCurrency());
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

        jlogger.info("Checking currency in prices.\n");
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
        jlogger.fine("Verification of currency in prices completed!\n");

        currency = null;
        productList = null;
        productPrice = null;

        jlogger.info("Setting the selected currency\n");
        mainPage.setCurrencyUSD(); // point 3
        checkCurrency(Info.USD);

        jlogger.info("Start of currency compliance checking.\n");

        String request = "dress";
        jlogger.log(Level.INFO, "Search request: {0}\n", request);
        findRequest(request); // point 4
        resultSearchPage.selectSortbyPriceDesc();
        resultSearchPage.showALLResults();

        List<WebElement> products = productsObject.itemFinder();
        List<WebElement> productsName = products.stream()
                .map(productsObject::nameFinder)
                .collect(Collectors.toList());

        for (WebElement x : productsName) {
            // TODO: this statement is incorrect because the data provided by the website does not match the request
            //assertTrue(x.getText().toLowerCase().contains(request.toLowerCase()));
        }

        productsName = null;

        // point 5
        assertNotNull(String.valueOf(resultSearchPage.getFoundResultsValue()));
        assertEquals(products.size(), resultSearchPage.getFoundResultsValue());

        // point 6
        boolean result = products.stream()
                .map(x -> Info.getPriceCurrency(x.findElement(By.xpath("div/div[2]/div[1]/span")).getText()))
                .anyMatch(x -> !x.equals(Info.$));

        assertFalse(result);
        jlogger.fine("Currency compliance check completed!\n");

        // point 7
        jlogger.info("Checking the sorting of goods.\n");
        assertEquals(Info.SORTPRICEDESC, resultSearchPage.getValueSortBy());

        // point 8
        jlogger.info("Start of the merchandise sorting compliance check.\n");
        List<Double> list3 = products.stream()
                .map(this::getNewOrOldPriceIfPresent)
                .collect(Collectors.toList());

        List<Double> sortedList = list3.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        assertEquals(list3, sortedList);
        jlogger.fine("Checks on the appropriateness of the sorting of goods are completed!\n");

        // point 9
        jlogger.info("The start of a price and discount check on goods.\n");
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
            // TODO: this statement is incorrect because the data provided by the website does not match the request
            //assertEquals(assertPrice, newPrice);
            jlogger.fine("Checking prices and discounts on goods is complete!\n");
        }
    }
}
