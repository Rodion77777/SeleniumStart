package maintest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ConfProperties;
import utils.Info;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AllMiniTests extends SetupTest {
    private static final PrintStream OUT = System.out;

    public void download_wait () {
        // TODO: rebuild this method
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("html")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));// /html/body
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[3]/i")));

        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajax_running")));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("page")));

        // page load timeout engage?
        driver.get(driver.getCurrentUrl());
    }

    public void findResponse (String response) {
        mainPage.setResponse(response);
        mainPage.clickSearchButton();
    }

    public double getNewOrOldPriceIfPresent (WebElement element) {
        return productsObject.priceFinder2(element).stream()
                .map(Info::parseDouble)
                .max(Double::compare)
                .get();
    }

    public void checkCurrency (String curr) {
        download_wait();
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

        driver.get(ConfProperties.getProperty("mainpage"));

        //mainPage.setCurrencyEUR();
        //mainPage.setCurrencyUSD();

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
    }

    @Test // point 3
    public void changeCurrencyTest () {

        /*
         *      Test of change currency on website.
         *      This test meets the requirement in point "3" in test task.
         *      Uncomment lines for full test with all currencies.
         */

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
        mainPage.setResponse("dress");
        mainPage.clickSearchButton();
    }

    @Test // point 5-6
    public void findItemTest () {

        /*
         *      Test of currency matching in all found products: "dress".
         *      This test meets the requirement in point "3" in test task.
         */

        driver.get(ConfProperties.getProperty("mainpage"));

        findResponse("dress");

        mainPage.setCurrencyUSD();
        download_wait();
        resultSearchPage.selectSortbyPriceDesc();
        resultSearchPage.setShowResultsPerPage_60();
        download_wait();

        List<WebElement> myItems = productsObject.itemFinder();
        assertNotNull(String.valueOf(resultSearchPage.getFoundResultsValue()));
        assertEquals(myItems.size(), resultSearchPage.getFoundResultsValue());

        boolean result = myItems.stream()
                        .map(x -> Info.getPriceCurrency(x.findElement(By.xpath("div/div[2]/div[1]/span")).getText()))
                        .anyMatch(x -> !x.equals(Info.$));

        assertFalse(result);
    }

    @Test // point 7
    public void sortButtonTest () {
        /*
         *      point 7
         */
        resultSearchPage.selectSortbyPriceDesc();
        assertEquals(Info.SORTPRICEDESC, resultSearchPage.getValueSortBy());
    }

    @Test // point 8
    public void priceSortMatch () {
        driver.get(ConfProperties.getProperty("mainpage"));

        findResponse("dress");

        resultSearchPage.selectSortbyPriceDesc();
        resultSearchPage.setShowResultsPerPage_60();
        download_wait();

        List<WebElement> myItems = productsObject.itemFinder();
        assertEquals(myItems.size(), resultSearchPage.getFoundResultsValue());

        List<Double> list3 = myItems.stream()
                .map(this::getNewOrOldPriceIfPresent)
                .collect(Collectors.toList());

        List<Double> sortedList = list3.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        assertEquals(list3, sortedList);
    }

    @Test // point 9-10
    public void findDiscount () {
        driver.get(ConfProperties.getProperty("mainpage"));

        findResponse("dress");

        resultSearchPage.selectSortbyPriceDesc();
        resultSearchPage.setShowResultsPerPage_60();
        download_wait();

        List<WebElement> pProducts = productsObject.itemFinder();
        List<List<WebElement>> pPrice = pProducts.stream()
                .map(productsObject::priceFinder2)
                .filter(x -> x.size() == 3)
                .collect(Collectors.toList());

        // TODO: rebuild or delete
        OUT.printf("\npProduct.size : %d", pProducts.size());
        OUT.printf("\npPrice.size : %d", pPrice.size());
        OUT.printf("\npPrice.get(0).size() : %d", pPrice.get(0).size());
        OUT.println(pPrice.get(0).get(0).getText());
        OUT.println(pPrice.get(0).get(1).getText());
        OUT.println(pPrice.get(0).get(2).getText());

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
            // TODO: uncomment line in the end of project
            //assertEquals(assertPrice, newPrice);
        }
    }

}