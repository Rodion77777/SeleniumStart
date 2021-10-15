package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.SetupTest;

public class RegistrationForm {

    private EventFiringWebDriver eventDriver;

    public RegistrationForm(EventFiringWebDriver eventDriver) {
        SetupTest.jlogger.info("Class constructor \"RegistrationForm\"\n");
        this.eventDriver = eventDriver;
    }

    // Submit email
    public void inputEmail (String email) {
        eventDriver.findElement(By.xpath("//*[contains(@id, 'email_create')]")).sendKeys(email);
    }

    public void clickCreateButton () {
        eventDriver.findElement(By.xpath("//*[contains(@id, 'SubmitCreate')]")).click();
    }

    // Methods of field completion
    public void genderChoice (String gender) {
        WebElement genderMan = eventDriver.findElement(By.xpath("//*[contains(@id, 'id_gender1')]"));
        WebElement genderWoman = eventDriver.findElement(By.xpath("//*[contains(@id, 'id_gender2')]"));

        if (gender.equals("MAN") && !genderMan.isSelected()) genderMan.click();
        else genderWoman.click();
    }

    public void inputFirstName (String firstname) {
        eventDriver.findElement(By.xpath("//*[contains(@id, 'customer_firstname')]")).sendKeys(firstname);
    }

    public void inputLastName (String lastname) {
        eventDriver.findElement(By.xpath("//*[contains(@id, 'customer_lastname')]")).sendKeys(lastname);
    }

    public void inputPassword (String password) {
        eventDriver.findElement(By.xpath("//*[contains(@id, 'passwd')]")).sendKeys(password);
    }

    // Chose date of Birth
    public void setDaysOfBirth (String birthDays) {
        eventDriver.findElement(By.xpath("//*[contains(@id, 'days')]")).sendKeys(birthDays);
    }

    public void setMonthOfBirth (String birthMonth) {
        eventDriver.findElement(By.xpath("//*[contains(@id, 'months')]")).sendKeys(birthMonth);
    }

    public void setYearsOfBirth (String birthYears) {
        eventDriver.findElement(By.xpath("//*[contains(@id, 'cuselFrame-years')]")).sendKeys(birthYears);
    }

    // Subscriptions
    public void setNewsletter (boolean choseNewsLetter) {
        WebElement newsletter = eventDriver.findElement(By.xpath("//*[contains(@id, 'newsletter')]"));
        if (choseNewsLetter && !newsletter.isSelected()) newsletter.click();
    }

    public void setSpecialOffers (boolean choseSpecialOffers) {
        WebElement specialOffers = eventDriver.findElement(By.xpath("//*[contains(@id, 'optin')]"));
        if (choseSpecialOffers && !specialOffers.isSelected()) specialOffers.click();
    }

    // Submit account with data
    public void clickSubmitAccount () {
        eventDriver.findElement(By.xpath("//*[contains(@id, 'submitAccount')]")).click();
    }
}