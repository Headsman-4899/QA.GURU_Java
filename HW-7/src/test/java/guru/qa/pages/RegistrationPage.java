package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.CalendarComponent;
import guru.qa.pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();
    private final String titleText = "Student Registration Form";
    private SelenideElement firstNameSelector = $("#firstName");
    private SelenideElement lastNameSelector = $("#lastName");
    private SelenideElement userEmailSelector = $("#userEmail");
    private SelenideElement userGenterSelector = $("#genterWrapper");
    private SelenideElement userNumberSelector = $("#userNumber");
    private SelenideElement userSubjectsSelector = $("#subjectsInput");
    private SelenideElement userHobbiesSelector = $("#hobbiesWrapper");
    private SelenideElement userPictureSelector = $("#uploadPicture");
    private SelenideElement userCurrentAddress = $("#currentAddress");
    private SelenideElement userState = $("#state");
    private SelenideElement userStateCityWrapper = $("#stateCity-wrapper");
    private SelenideElement userCity = $("#city");
    private SelenideElement submitButton = $("#submit");
    private SelenideElement closeButton = $("#closeLargeModal");

    public void openPage () {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(titleText));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    public void setFirstName (String value) {
        firstNameSelector.setValue(value);
    }

    public void setLastName (String value) {
        lastNameSelector.setValue(value);
    }

    public void setUserEmail (String value) {
        userEmailSelector.setValue(value);
    }

    public void clickUserGender (String gender) {
        userGenterSelector.$(byText(gender)).click();
    }

    public void setUserNumber (String value) {
        userNumberSelector.setValue(value);
    }

    public void setBirthDay (String day, String month, String year) {
        calendarComponent.setDate(day, month, year);
    }

    public void setSubjects (String value) {
        userSubjectsSelector.setValue(value).pressEnter();
    }

    public void clickHobbies (String hobbies) {
        userHobbiesSelector.$(byText(hobbies)).click();
    }

    public void setPictures (String location) {
        userPictureSelector.uploadFromClasspath(location);
    }

    public void setAddress (String value) {
        userCurrentAddress.setValue(value);
    }

    public void setState (String value) {
        userState.click();
        userStateCityWrapper.$(byText(value)).click();
    }

    public void setCity (String value) {
        userCity.click();
        userStateCityWrapper.$(byText(value)).click();
    }

    public void clickSubmit () {
        submitButton.click();
    }

    public void verifyResultsModal () {
        registrationResultsModal.submittingForm();
    }

    public void verifyResults (String key, String value) {
        registrationResultsModal.formResoults(key, value);
    }

    public void clickClose () {
        closeButton.click();
    }
}
