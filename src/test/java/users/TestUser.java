package users;

import lombok.Getter;

@Getter
public class TestUser {
    private String firstName = "Firstname";
    private String lastName = "lastName";
    private String userName = firstName + " " + lastName;
    private String gender = "MAN";
    private String birthDays = "1";
    private String month = "January";
    private String birthYear = "2000";
    private boolean choseNewsLetter = false;
    private boolean choseSpecialOffers = false;
    private String email = "example6@gmail.com";
    private String password = "Password";
}
