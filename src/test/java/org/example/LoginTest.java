package org.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class LoginTest {

    private static final PrintStream OUT = System.out;
    private static final PrintStream ERR = System.err;

    public static MainPage mainPage;
    public static LoginForm loginForm;
    public static ProfilePage profilePage;
    public static RegistrationForm registrationForm;
    public static ResultSearchPage resultSearchPage;
    public static ProductsObject productsObject;
    public static WebDriver driver;
    public static WebDriverWait wait;

    private static WebDriver setupChromeBrowser () {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        return new ChromeDriver();
    }

    private static WebDriver setupOperaBrowser () {
        System.setProperty("webdriver.opera.driver", ConfProperties.getProperty("operadriver"));
        return new OperaDriver();
    }

    private static WebDriver setupFirefoxBrowser () {
        final String propertyGecko = "webdriver.gecko.driver";
        final String propertyMarionette = "webdriver.firefox.marionette";

        final String fireFoxDriver_v29_1 = ConfProperties.getProperty("firefoxdriver_v29_1");
        final String fireFoxDriver_v29_0 = ConfProperties.getProperty("firefoxdriver_v29_0");
        final String fireFoxDriver_v28_0 = ConfProperties.getProperty("firefoxdriver_v28_0");

        System.setProperty(propertyGecko, fireFoxDriver_v29_1);

        return new FirefoxDriver();
    }

    public static WebDriver setupTorBrowser () {
        final String propertyGecko = "webdriver.gecko.driver";
        final String propertyMarionette = "webdriver.firefox.marionette";

        final String fireFoxDriver_v29_1 = ConfProperties.getProperty("firefoxdriver_v29_1");
        final String fireFoxDriver_v29_0 = ConfProperties.getProperty("firefoxdriver_v29_0");
        final String fireFoxDriver_v28_0 = ConfProperties.getProperty("firefoxdriver_v28_0");

        System.setProperty(propertyGecko, fireFoxDriver_v28_0);

        final String torPath = ConfProperties.getProperty("torbrowser");
        final String profilePath = ConfProperties.getProperty("torProfilePath");

        File torProfileDir = new File(profilePath);
        FirefoxBinary binary = new FirefoxBinary(new File(torPath));
        FirefoxProfile torProfile = new FirefoxProfile(torProfileDir);

        FirefoxOptions options = new FirefoxOptions();
            options.setBinary(binary);
            options.setProfile(torProfile);
            options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);

        return new FirefoxDriver(options);
    }

    @BeforeClass
    public static void setup () {

        driver = setupChromeBrowser();

        mainPage = new MainPage(driver);
        loginForm = new LoginForm(driver);
        profilePage = new ProfilePage(driver);
        registrationForm = new RegistrationForm(driver);
        resultSearchPage = new ResultSearchPage(driver);
        productsObject = new ProductsObject(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10);
        driver.get(ConfProperties.getProperty("mainpage"));

    }

    public void download_wait () {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("html")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));// /html/body
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[3]/i")));

        // page load timeout engage.
        driver.get(driver.getCurrentUrl());
    }

    public void checkUserName (String uName) {
        String user = profilePage.getUserName();
        System.out.println(user);
        assertEquals(uName, user);
    }

    public void findResponse (String response) {
        mainPage.setResponse(response);
        mainPage.clickSearchButton();
    }

    @Test
    public void registrationTest () {
        /*
         *      Website registration test.
         *      To run the test, the registration data in the "conf.properties" file must be filled in!
         */
        registrationForm.inputemail(ConfProperties.getProperty("email"));
        registrationForm.clickCreateButton();
        registrationForm.genderChoice(ConfProperties.getProperty("gender"));
        registrationForm.inputFirstName(ConfProperties.getProperty("firstname"));
        registrationForm.inputLastName(ConfProperties.getProperty("lastname"));
        registrationForm.checkEmail(ConfProperties.getProperty("email"));
        registrationForm.inputPassword(ConfProperties.getProperty("password"));
        registrationForm.setDaysOfBirth(ConfProperties.getProperty("birthDays"));
        registrationForm.setMonthOfBirth(ConfProperties.getProperty("birthMonth"));
        registrationForm.setYearsOfBirth(ConfProperties.getProperty("birthYear"));
        registrationForm.setNewsletter(ConfProperties.getChose("choseNewsLetter"));
        registrationForm.setSpecialOffers(ConfProperties.getChose("choseSpecialOffers"));
        registrationForm.clickSubmitAccount();
        checkUserName(ConfProperties.getProperty("username"));
        profilePage.userLogout();
        //loginForm.clickSetupButton();
    }

    @Test
    public void loginTest () {
        /*
         *      Website login test.
         *      To run the test, the registration data in the "conf.properties" file must be filled in!
         */
        loginForm.inputLogin(ConfProperties.getProperty("email"));
        loginForm.inputPassword(ConfProperties.getProperty("password"));
        loginForm.clickLoginButton();
        checkUserName(ConfProperties.getProperty("username"));
        profilePage.userLogout();
    }

    @Test
    public void changeLanguageTest () {
        mainPage.setLangEnglish();
        checkLanguage(Info.EN);

        mainPage.setLangUkrainian();
        checkLanguage(Info.UA);

        mainPage.setLangRussian();
        checkLanguage(Info.RU);
    }

    public void checkLanguage (String lang) {
        download_wait();
        assertEquals(lang, mainPage.getLanguages());
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
                case "" + Info.₴ -> assertEquals(Info.UAH, currency);
                case "" + Info.$ -> assertEquals(Info.USD, currency);
                case "" + Info.€ -> assertEquals(Info.EUR, currency);
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

    public void checkCurrency (String curr) {
        download_wait();
        System.out.printf("\nExpected: %s -> %s\n", curr, mainPage.getCurrency());
        assertEquals(curr, mainPage.getCurrency());
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
                        .anyMatch(x -> !x.equals(Info.$+""));

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

    public double getNewOrOldPriceIfPresent (WebElement element) {
        return productsObject.priceFinder2(element).stream()
                .map(Info::parseDouble)
                .max(Double::compare)
                .get();
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

        OUT.println("pProduct.size : " + pProducts.size());
        OUT.println("pPrice.size : " + pPrice.size());
        OUT.println("pPrice.get(0).size() : " + pPrice.get(0).size());
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
            //assertEquals(assertPrice, newPrice);
        }
    }

    @Test
    public void generalTest () {
        driver.get(ConfProperties.getProperty("mainpage"));

        List<WebElement> productList = productsObject.popItemFinder();
        List<WebElement> productPrice = productList.stream()
                .map(x -> productsObject.priceFinder2(x).get(0))
                .collect(Collectors.toList());

        String currency = mainPage.getCurrency();

        for (WebElement x : productPrice) {
            switch (Info.getPriceCurrency(x.getText())) {
                case "" + Info.₴ -> assertEquals(Info.UAH, currency);
                case "" + Info.$ -> assertEquals(Info.USD, currency);
                case "" + Info.€ -> assertEquals(Info.EUR, currency);
            }
        }

        currency = null;
        productList = null;
        productPrice = null;

        mainPage.setCurrencyUSD(); // point 3
        //checkCurrency(Info.USD);

        String response = "dress";
        download_wait();
        findResponse(response); // point 4
        download_wait();
        resultSearchPage.selectSortbyPriceDesc();
        resultSearchPage.setShowResultsPerPage_60();
        download_wait();

        List<WebElement> products = productsObject.itemFinder();
        List<WebElement> productsName = products.stream()
                .map(productsObject::nameFinder)
                .collect(Collectors.toList());

        for (WebElement x : productsName) {
            //assertTrue(x.getText().toLowerCase().contains(response.toLowerCase()));
        }

        assertNotNull(String.valueOf(resultSearchPage.getFoundResultsValue()));
        assertEquals(products.size(), resultSearchPage.getFoundResultsValue());

        // point 6
        boolean result = products.stream()
                .map(x -> Info.getPriceCurrency(x.findElement(By.xpath("div/div[2]/div[1]/span")).getText()))
                .anyMatch(x -> !x.equals(Info.$ + ""));

        assertFalse(result);

        // point 7
        assertEquals(Info.SORTPRICEDESC, resultSearchPage.getValueSortBy());

        // point 8
        List<Double> list3 = products.stream()
                .map(this::getNewOrOldPriceIfPresent)
                .collect(Collectors.toList());

        List<Double> sortedList = list3.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        assertEquals(list3, sortedList);

        // point 9
        List<List<WebElement>> pPrice = products.stream()
                .map(productsObject::priceFinder2)
                .filter(x -> x.size() == 3)
                .collect(Collectors.toList());

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
            //assertEquals(assertPrice, newPrice);
        }
    }

    @AfterClass
    public static void tearDown () {
        mainPage = null;
        loginForm = null;
        profilePage = null;
        registrationForm = null;
        resultSearchPage = null;
        productsObject = null;
        wait = null;
        driver.close();
        driver.quit();
    }
}