package utils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SetupTest {

    public static MainPage mainPage;
    public static LoginForm loginForm;
    public static ProfilePage profilePage;
    public static RegistrationForm registrationForm;
    public static ResultSearchPage resultSearchPage;
    public static ProductsObject productsObject;
    public static WebDriverWait wait;
    public static Logger jlogger;
    public static EventFiringWebDriver eventDriver;

    private static WebDriver setupChromeBrowser () {
        System.setProperty(Info.PROPERTY_CHROME, Info.CHROME_DRIVER);
        jlogger.info("Chrome browser has been selected.\n");
        return new ChromeDriver();
    }
    private static WebDriver setupOperaBrowser () {
        System.setProperty(Info.PROPERTY_OPERA, Info.OPERA_DRIVER);
        jlogger.info("Opera browser has been selected.\n");
        return new OperaDriver();
    }
    private static WebDriver setupFirefoxBrowser () {
        System.setProperty(Info.PROPERTY_GECKO, Info.FIREFOX_DRIVER);

        jlogger.info("Firefox browser has been selected.\n");
        return new FirefoxDriver();
    }
    public static WebDriver setupTorBrowser () {

        System.setProperty(Info.PROPERTY_GECKO, Info.FIREFOX_DRIVER);

        FirefoxBinary binary = new FirefoxBinary(new File(Info.TOR_BROWSER));
        FirefoxProfile torProfile = new FirefoxProfile(new File(Info.TOR_PROFILE));

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
        mainPage = new MainPage(eventDriver);
        loginForm = new LoginForm(eventDriver);
        profilePage = new ProfilePage(eventDriver);
        registrationForm = new RegistrationForm(eventDriver);
        resultSearchPage = new ResultSearchPage(eventDriver);
        productsObject = new ProductsObject(eventDriver);
        wait = new WebDriverWait(eventDriver, 10);

        jlogger.info("Setting implicit time-outs.\n");
        eventDriver.manage().window().maximize();
        eventDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        eventDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        eventDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        jlogger.info("Driver requests the home page of the website.\n");
        eventDriver.get(Info.MAIN_PAGE_URL);
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
        //eventDriver.close();
        eventDriver.quit();
        jlogger = null;
    }

}
