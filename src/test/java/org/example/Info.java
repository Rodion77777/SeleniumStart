package org.example;

import org.openqa.selenium.WebElement;

public class Info {
    public static final String RU = "Русский";
    public static final String UA = "Українська";
    public static final String EN = "English";

    public static final String UAH = "UAH";
    public static final String USD = "USD";
    public static final String EUR = "EUR";

    public static final char ₴ = '₴';
    public static final char $ = '$';
    public static final char € = '€';

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

    public static int convertPercentage (WebElement percent) {
        return Math.abs (
                Integer.parseInt (
                        percent.getText().replaceAll("[ -+%]", "")
                )
        );
    }

    public static double convertPrice (WebElement price) {
        return Double.parseDouble ( price.getText()
                        .replaceAll("[\\s" + Info.REGEXCURRENCY + "]", "")
                        .replace(",", ".")
        );
    }

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