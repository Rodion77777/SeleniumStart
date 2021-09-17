package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Log_In_Out extends SetupTest {

    public void checkUserName (String uName) {
        String user = profilePage.getUserName();
        System.out.println(user);
        assertEquals(uName, user);
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
        registrationForm.setNewsletter(ConfProperties.getChoose("choseNewsLetter"));
        registrationForm.setSpecialOffers(ConfProperties.getChoose("choseSpecialOffers"));
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

}