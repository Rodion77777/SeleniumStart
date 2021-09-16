package org.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public abstract class SetupTest {

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
