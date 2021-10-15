package utils;

import org.openqa.selenium.WebElement;

public class Info {

    public static final String MAIN_PAGE_URL = "http://prestashop.qatestlab.com.ua/ru/";
    public static final String LOGIN_PAGE_URL = "http://prestashop.qatestlab.com.ua/ru/authentication?back=my-account";

    public static final String RU = "Русский";
    public static final String UA = "Українська";
    public static final String EN = "English";

    public static final String UAH = "UAH";
    public static final String USD = "USD";
    public static final String EUR = "EUR";

    public static final String ₴ = "₴";
    public static final String $ = "$";
    public static final String € = "€";

    public static final String REGEXCURRENCY = "[" + $ + ₴ + € + "]";

    public static final String BASE_LINK = "http://prestashop.qatestlab.com.ua/";
    public static final String LINK_RU = BASE_LINK + "ru/";
    public static final String LINK_UK = BASE_LINK + "uk/";
    public static final String LINK_EN = BASE_LINK + "en/";

    public static final String SORTDEFAULT = "--";
    public static final String SORTPRICEASC = "Price: Lowest first";
    public static final String SORTPRICEDESC = "Price: Highest first";
    public static final String SORTNAMEASC = "Product Name: A to Z";
    public static final String SORTNAMEDESC = "Product Name: Z to A";
    public static final String SORTQUANTITYDESC = "In stock";
    public static final String SORTREFERENCEASC = "Reference: Lowest first";
    public static final String SORTREFERENCEDESC = "Reference: Highest first";

    public static final String SHOW12 = "12";
    public static final String SHOW24 = "24";
    public static final String SHOW60 = "60";

    public static final String OPERA_DRIVER = "src/test/java/resources/operadriver_win64/operadriver.exe";
    public static final String CHROME_DRIVER = "src/test/java/resources/chromedriver_win32/chromedriver.exe";
    public static final String FIREFOX_DRIVER = "src/test/java/resources/geckodriver-v0.29.1-win64/geckodriver.exe";
    public static final String TOR_BROWSER = "C:/Program Files/Tor Browser/Browser/firefox.exe";
    public static final String TOR_PROFILE = "C:/Program Files/Tor Browser/Browser/TorBrowser/Data/Browser/profile.default";

    public static final String PROPERTY_CHROME = "webdriver.chrome.driver";
    public static final String PROPERTY_OPERA = "webdriver.opera.driver";
    public static final String PROPERTY_GECKO = "webdriver.gecko.driver";
    public static final String PROPERTY_MARIONETTE = "webdriver.firefox.marionette";

    public static double parseDouble (WebElement price) {
        return Double.parseDouble (price.getText()
                        .replaceAll("[\\s" + Info.REGEXCURRENCY + "%]", "")
                        .replace(",", "."));
    }

    public static int parseInteger (WebElement price) {
        return Integer.parseInt (price.getText()
                .replaceAll("[\\s" + Info.REGEXCURRENCY + "%]", "")
                .replace(",", "."));
    }

    public static String getPriceCurrency (String price) {
        return price.replaceAll("[ ,.\\w]", "");
    }
}