package additionaltest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import users.TestUser;
import utils.Info;
import utils.SetupTest;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Log_In_Out_Test extends SetupTest {

    public static TestUser user;

    @BeforeClass
    public static void setup2 () { user = new TestUser(); }

    @AfterClass
    public static void tearDown2 () { user = null; }

    public static void checkUserName (String uName) {
        String user = profilePage.getUserName();
        System.out.println(user);
        assertEquals(uName, user);
    }

    @Test
    public void test1_registration() {
        /*
         *      Website registration test.
         */
        jlogger.info("Start of registration.\n");

        eventDriver.get(Info.LOGIN_PAGE_URL);

        registrationForm.inputEmail(user.getEmail());
        registrationForm.clickCreateButton();
        registrationForm.genderChoice(user.getGender());
        registrationForm.inputFirstName(user.getFirstName());
        registrationForm.inputLastName(user.getLastName());
        registrationForm.inputPassword(user.getPassword());
        registrationForm.setDaysOfBirth(user.getBirthDays());
        registrationForm.setMonthOfBirth(user.getMonth());
        registrationForm.setYearsOfBirth(user.getBirthYear());
        registrationForm.setNewsletter(user.isChoseNewsLetter());
        registrationForm.setSpecialOffers(user.isChoseSpecialOffers());
        registrationForm.clickSubmitAccount();
        checkUserName(user.getUsername());
        profilePage.userLogout();
        //loginForm.clickSetupButton();
        jlogger.fine("Registration process completed!\n");
    }

    @Test
    public void test2_login() {
        /*
         *      Website login test.
         */
        jlogger.info("Start logging in to the account.\n");

        eventDriver.get(Info.LOGIN_PAGE_URL);

        loginForm.inputLogin(user.getEmail());
        loginForm.inputPassword(user.getPassword());
        loginForm.clickLoginButton();
        checkUserName(user.getUsername());
        profilePage.userLogout();

        jlogger.fine("The account login process is complete!\n");
    }
}