package additionaltest;

import maintest.SetupTest;
import org.junit.Test;
import utils.Info;

import static org.junit.Assert.assertEquals;

public class AdditionalTest extends SetupTest {

    public void download_wait () {
        // TODO: rebuild this method
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("html")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));// /html/body
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[3]/i")));

        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajax_running")));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("page")));

        // page load timeout engage.
        eventDriver.get(eventDriver.getCurrentUrl());
    }

    public void checkLanguage (String lang) {
        download_wait();
        assertEquals(lang, mainPage.getLanguages());
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
}
