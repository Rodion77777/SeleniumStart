package maintest;

import alternate_pages.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfProperties;
import utils.EventHandler;
import utils.JULogger;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SetupTest2 {

    public static MainPage2 mainPage;
    public static LoginForm2 loginForm;
    public static ProfilePage2 profilePage;
    public static RegistrationForm2 registrationForm;
    public static ResultSearchPage2 resultSearchPage;
    public static ProductsObject2 productsObject;
    public static WebDriverWait wait;
    public static Logger jlogger;
    public static EventFiringWebDriver eventDriver;

    private static WebDriver setupChromeBrowser () {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        jlogger.info("Chrome browser has been selected.\n");
        return new ChromeDriver();
    }
    private static WebDriver setupOperaBrowser () {
        System.setProperty("webdriver.opera.driver", ConfProperties.getProperty("operadriver"));
        jlogger.info("Opera browser has been selected.\n");
        return new OperaDriver();
    }
    private static WebDriver setupFirefoxBrowser () {
        final String propertyGecko = "webdriver.gecko.driver";
        final String propertyMarionette = "webdriver.firefox.marionette";

        final String fireFoxDriver_v29_1 = ConfProperties.getProperty("firefoxdriver_v29_1");
        final String fireFoxDriver_v29_0 = ConfProperties.getProperty("firefoxdriver_v29_0");
        final String fireFoxDriver_v28_0 = ConfProperties.getProperty("firefoxdriver_v28_0");

        System.setProperty(propertyGecko, fireFoxDriver_v29_1);

        jlogger.info("Firefox browser has been selected.\n");
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

        jlogger.info("Tor browser has been selected.\n");
        return new FirefoxDriver(options);
    }

    @BeforeClass
    public static void setup () {

        jlogger = new JULogger(SetupTest.class.getName()).myLogger;

        jlogger.info("First initialisation driver.\n");
        eventDriver = new EventFiringWebDriver(setupChromeBrowser()).register(new EventHandler(jlogger));

        jlogger.info("First initialisation of page objects.\n");
        mainPage = new MainPage2(eventDriver);
        loginForm = new LoginForm2(eventDriver);
        profilePage = new ProfilePage2(eventDriver);
        registrationForm = new RegistrationForm2(eventDriver);
        resultSearchPage = new ResultSearchPage2(eventDriver);
        productsObject = new ProductsObject2(eventDriver);
        wait = new WebDriverWait(eventDriver, 30);

        jlogger.info("Setting implicit time-outs.\n");
        eventDriver.manage().window().maximize();
        eventDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        eventDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        eventDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        jlogger.info("Driver requests the home page of the website.\n");
        eventDriver.get(ConfProperties.getProperty("mainpage"));
    }

    @AfterClass
    public static void tearDown () {
        jlogger.info("Performing the \"tearDown\" method.\n");

        mainPage = null;
        loginForm = null;
        profilePage = null;
        registrationForm = null;
        resultSearchPage = null;
        productsObject = null;
        wait = null;
        eventDriver.close();
        eventDriver.quit();
        jlogger = null;
    }

}
