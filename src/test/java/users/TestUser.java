package users;

public class TestUser {
    private String firstName = "Firstname";
    private String lastName = "lastName";
    private String username = firstName + " " + lastName;
    private String gender = "MAN";
    private String birthDays = "1";
    private String month = "January";
    private String birthYear = "2000";
    private boolean choseNewsLetter = false;
    private boolean choseSpecialOffers = false;
    private String email = "example4@gmail.com";
    private String password = "Password";

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDays() {
        return birthDays;
    }

    public String getMonth() {
        return month;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public boolean isChoseNewsLetter() {
        return choseNewsLetter;
    }

    public boolean isChoseSpecialOffers() {
        return choseSpecialOffers;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
