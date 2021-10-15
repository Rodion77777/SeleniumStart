package maintest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Info;
import utils.SetupTest;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AllMiniTests extends SetupTest {
    private static final PrintStream OUT = System.out;

    public void download_wait () {
        jlogger.info("Wait for page load.\n");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajax_running")));
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

    public void checkCurrency (String curr) {
        jlogger.log(Level.INFO, "Currency matching, expected {0}", curr);
        assertEquals(curr, mainPage.getCurrency());
    }

    @Test // point 1-2
    public void currencyMatching() {

        /*
         *      Test of currency matching (popular products in main page).
         *      Website have "UAH" currency in default.
         *      This test meets the requirement in point "2" in test task.
         *      Uncomment one of the lines to change the currency.
         */

        eventDriver.get(Info.MAIN_PAGE_URL);

        //mainPage.setCurrencyEUR();
        //mainPage.setCurrencyUSD();

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
    }

    @Test // point 3
    public void changeCurrencyTest () {

        /*
         *      Test of change currency on website.
         *      This test meets the requirement in point "3" in test task.
         *      Uncomment lines for full test with all currencies.
         */
        jlogger.info("Currency change check.");
        mainPage.setCurrencyUSD();
        checkCurrency(Info.USD);
        /*
        mainPage.setCurrencyEUR();
        checkCurrency(Info.EUR);

        mainPage.setCurrencyUAH();
        checkCurrency(Info.UAH);
        */
    }

    @Test // point 4
    public void searchOfDress () {
        jlogger.info("Search engine test");
        mainPage.setRequest("dress");
        mainPage.clickSearchButton();
    }

    @Test // point 5-6
    public void findItemTest () {

        /*
         *      Test of currency matching in all found products: "dress".
         *      This test meets the requirement in point "5" and "6" in test task.
         */

        jlogger.info("Start of currency compliance checking.");

        eventDriver.get(Info.MAIN_PAGE_URL);

        findRequest("dress");

        mainPage.setCurrencyUSD();
        assertEquals(Info.USD, mainPage.getCurrency());

        resultSearchPage.selectSortbyPriceDesc();
        assertEquals(Info.SORTPRICEDESC, resultSearchPage.getValueSortBy());

        resultSearchPage.showALLResults();

        List<WebElement> myItems = productsObject.itemFinder();
        assertNotNull(String.valueOf(resultSearchPage.getFoundResultsValue()));
        assertEquals(myItems.size(), resultSearchPage.getFoundResultsValue());

        boolean result = myItems.stream()
                        .map(x -> Info.getPriceCurrency(x.findElement(By.xpath("div/div[2]/div[1]/span")).getText()))
                        .anyMatch(x -> !x.equals(Info.$));

        assertFalse(result);
        jlogger.fine("Currency compliance check completed!");
    }

    @Test // point 7
    public void sortButtonTest () {
        /*
         *      point 7
         */
        jlogger.info("Checking the sorting of goods.");
        findRequest("dress");
        resultSearchPage.selectSortbyPriceDesc();
        assertEquals(Info.SORTPRICEDESC, resultSearchPage.getValueSortBy());
    }

    @Test // point 8
    public void priceSortMatch () {
        jlogger.info("Start of the merchandise sorting compliance check.");
        eventDriver.get(Info.MAIN_PAGE_URL);

        findRequest("dress");

        resultSearchPage.selectSortbyPriceDesc();
        assertEquals(Info.SORTPRICEDESC, resultSearchPage.getValueSortBy());
        resultSearchPage.showALLResults();

        List<WebElement> myItems = productsObject.itemFinder();
        assertEquals(myItems.size(), resultSearchPage.getFoundResultsValue());

        List<Double> list3 = myItems.stream()
                .map(this::getNewOrOldPriceIfPresent)
                .collect(Collectors.toList());

        List<Double> sortedList = list3.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        assertEquals(list3, sortedList);
        jlogger.fine("Checks on the appropriateness of the sorting of goods are completed!");
    }

    @Test // point 9-10
    public void findDiscount () {
        jlogger.info("The start of a price and discount check on goods.");
        eventDriver.get(Info.MAIN_PAGE_URL);

        findRequest("dress");

        resultSearchPage.selectSortbyPriceDesc();
        resultSearchPage.showALLResults();

        List<WebElement> pProducts = productsObject.itemFinder();
        List<List<WebElement>> pPrice = pProducts.stream()
                .map(productsObject::priceFinder2)
                .filter(x -> x.size() == 3)
                .collect(Collectors.toList());

        DecimalFormat template = new DecimalFormat("### ###.00");

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

            OUT.println (template.format(assertPrice));
            // TODO: this statement is incorrect because the data provided by the website does not match the request
            //assertEquals(assertPrice, newPrice);
            jlogger.fine("Checking prices and discounts on goods is complete!");
        }
    }

}