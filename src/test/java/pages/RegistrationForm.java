package pages;

import maintest.SetupTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class RegistrationForm {

    private EventFiringWebDriver eventDriver;

    public RegistrationForm(EventFiringWebDriver eventDriver) {
        SetupTest.jlogger.info("Class constructor \"RegistrationForm\"\n");
        PageFactory.initElements(eventDriver, this);
        this.eventDriver = eventDriver;
    }

    @FindBy(xpath = "//*[contains(@id, 'email_create')]")
    private WebElement emailCreate;

    // Submit button for create account
    @FindBy(xpath = "//*[contains(@id, 'SubmitCreate')]")
    private WebElement submitCreate;

    /*
     * Next elements will be visible after click "submitCreate" button
     */

    @FindBy(xpath = "//*[contains(@id, 'id_gender1')]")
    private WebElement genderMAN;

    @FindBy(xpath = "//*[contains(@id, 'id_gender2')]")
    private WebElement genderWOMAN;

    @FindBy(xpath = "//*[contains(@id, 'customer_firstname')]")
    private WebElement firstName;

    @FindBy(xpath = "//*[contains(@id, 'customer_lastname')]")
    private WebElement lastName;

    @FindBy(xpath = "//*[contains(@id, 'email')]")
    private WebElement proofOfEmail;

    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement proofOfEmail1;

    @FindBy(xpath = "//*[contains(@class, 'is_required validate form-control')]")
    private WebElement proofOfEmail2;

    @FindBy(xpath = "//*[contains(@id, 'passwd')]")
    private WebElement createPassword;

    /*
     * Entering of Birthday
     */

    @FindBy(xpath = "//*[contains(@id, 'days')]")
    private WebElement daysOfBirth;

    @FindBy(xpath = "//*[contains(@id, 'months')]")
    private WebElement monthsOfBirth;

    @FindBy(xpath = "//*[contains(@id, 'cuselFrame-years')]")
    private WebElement yearsOfBirth;

    // CHECKBOXES:
    // Checkbox 1: "Sign up for our newsletter!"
    @FindBy(xpath = "//*[contains(@id, 'newsletter')]")
    private WebElement newsletter;

    // Checkbox 2: "Receive special offers from our partners!"
    @FindBy(xpath = "//*[contains(@id, 'optin')]")
    private WebElement specialOffers;

    @FindBy(xpath = "//*[contains(@id, 'submitAccount')]")
    private WebElement submitAccount;

    /*
     * INTERACTION METHODS:
     */

    // Submit email
    public void inputEmail (String email) {
        emailCreate.sendKeys(email);
    }

    public void clickCreateButton () {
        submitCreate.click();
        //eventDriver.findElement(By.xpath("//*[contains(@id, 'SubmitCreate')]")).click();
    }

    // Methods of field completion
    public void genderChoice (String gender) {
        if (gender.equals("MAN") && !genderMAN.isSelected()) genderMAN.click();
        else genderWOMAN.click();
    }

    public void inputFirstName (String firstname) {
        firstName.sendKeys(firstname);
    }

    public void inputLastName (String lastname) {
        lastName.sendKeys(lastname);
    }

    public void inputPassword (String password) {
        createPassword.sendKeys(password);
    }

    // Chose date of Birth
    public void setDaysOfBirth (String birthDays) {
        daysOfBirth.sendKeys(birthDays);
    }

    public void setMonthOfBirth (String birthMonth) {
        monthsOfBirth.sendKeys(birthMonth);
    }

    public void setYearsOfBirth (String birthYears) {
        yearsOfBirth.sendKeys(birthYears);
    }

    // Subscriptions
    public void setNewsletter (boolean choseNewsLetter) {
        if (choseNewsLetter && !newsletter.isSelected()) newsletter.click();
    }

    public void setSpecialOffers (boolean choseSpecialOffers) {
        if (choseSpecialOffers && !specialOffers.isSelected()) specialOffers.click();
    }

    // Submit account with data
    public void clickSubmitAccount () {
        submitAccount.click();
    }

}