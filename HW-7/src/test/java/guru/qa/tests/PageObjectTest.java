package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PageObjectTest {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void fillForm() {
        String userName = "Daulet";
        String userLastName = "Kareneyev";
        String userEmail = "daulet@gmail.com";
        String userGender = "Male";
        String userNumber = "1234567890";
        String userBirthDay_day = "14";
        String userBirthDay_month = "June";
        String userBirthDay_year = "2001";
        String userSubjects = "English";
        String userHobbies = "Music";
        String userPictureLocation = "ds.jpg";
        String userAddress = "Victory cq 5";
        String userState = "Uttar Pradesh";
        String userCity = "Agra";

        registrationPage.openPage();

        registrationPage.setFirstName(userName);
        registrationPage.setLastName(userLastName);
        registrationPage.setUserEmail(userEmail);
        registrationPage.clickUserGender(userGender);
        registrationPage.setUserNumber(userNumber);
        registrationPage.setBirthDay(userBirthDay_day, userBirthDay_month, userBirthDay_year);
        registrationPage.setSubjects(userSubjects);
        registrationPage.clickHobbies(userHobbies);
        registrationPage.setPictures(userPictureLocation);
        registrationPage.setAddress(userAddress);
        registrationPage.setState(userState);
        registrationPage.setCity(userCity);

        registrationPage.clickSubmit();

        registrationPage.verifyResultsModal();
        registrationPage.verifyResults("Student Name", userName + " " + userLastName);
        registrationPage.verifyResults("Student Email", userEmail);
        registrationPage.verifyResults("Gender", userGender);
        registrationPage.verifyResults("Mobile", userNumber);
        registrationPage.verifyResults("Date of Birth", userBirthDay_day + " " + userBirthDay_month + "," + userBirthDay_year);
        registrationPage.verifyResults("Subjects", userSubjects);
        registrationPage.verifyResults("Hobbies", userHobbies);
        registrationPage.verifyResults("Address", userAddress);
        registrationPage.verifyResults("State and City", userState + " " + userCity);

        registrationPage.clickClose();
    }

    @AfterAll
    public static void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }
}
