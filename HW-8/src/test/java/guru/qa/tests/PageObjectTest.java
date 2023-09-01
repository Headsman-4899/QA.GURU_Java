package guru.qa.tests;

import com.github.javafaker.Faker;
import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

import static guru.qa.tests.TestData.*;
import static guru.qa.utils.RandomUtils.getRandomItemFromArray;

public class PageObjectTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    @Test
    public void fillForm() {
        String userName = faker.name().firstName();
        String userLastName = faker.name().lastName();
        String userEmail = faker.internet().emailAddress();
        String userGender = getRandomItemFromArray(TestData.gender);
        String userNumber = 8 + faker.phoneNumber().subscriberNumber(9);
        String userBirthDay_day = String.format("%02d", faker.number().numberBetween(1, 28));
        String userBirthDay_month = getRandomItemFromArray(months);
        String userBirthDay_year = String.valueOf(faker.number().numberBetween(1950, 2005));
        String userSubjects = getRandomItemFromArray(subjects);
        String userHobbies = getRandomItemFromArray(hobbies);
        String userPictureLocation = "ds.jpg";
        String userAddress = faker.address().streetAddress();
        String userState = getRandomItemFromArray(states);
        String userCity = getRandomItemFromArray(TestData.cities);

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
}
