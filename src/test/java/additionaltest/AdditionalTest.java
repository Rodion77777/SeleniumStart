package additionaltest;

import maintest.SetupTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Info;

import static org.junit.Assert.assertEquals;

public class AdditionalTest extends SetupTest {

    public void download_wait () {
        jlogger.info("Wait for page load.\n");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajax_running")));
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
