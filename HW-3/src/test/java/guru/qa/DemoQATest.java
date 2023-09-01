package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DemoQATest {
    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x10180";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    public void fillFormTest() {
        Faker faker = new Faker();
        String firstName =  faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String mobilePhone = faker.phoneNumber().subscriberNumber(10);
        String state = "Uttar Pradesh";
        String hobby = "Sports";
        String address = faker.address().fullAddress();
        String favoriteSubject = "Maths";
        LocalDate randomDate = LocalDate.now().minusMonths(5).minusDays(20).minusYears(20);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMMM,yyyy");
        String formattedDate = randomDate.format(myFormatObj);

        open("/automation-practice-form");
        executeJavaScript("document.querySelector('#fixedban').setAttribute('style', 'display: none');");

        $("#firstName").setValue(firstName);
        $("[placeholder='Last Name']").setValue(lastName);
        $("#userEmail-wrapper input").setValue(email);
        $(byClassName("custom-control-label")).click();
        $(byId("userNumber")).setValue(mobilePhone);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionByValue(String.valueOf(randomDate.getYear()));
        $(".react-datepicker__month-select").selectOptionByValue(String.valueOf(randomDate.getMonthValue() - 1));
        String datepickerDayXpath = String.format("//div[not(contains(@class,'day--outside-month'))][text()='%s']", randomDate.getDayOfMonth());
        $x(datepickerDayXpath).click();

        $("#subjectsInput").sendKeys(favoriteSubject);
        $("#subjectsWrapper .subjects-auto-complete__option").click();

        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#currentAddress").setValue(address);
        $(byAttribute("type","file")).uploadFromClasspath("ds.jpg");

        SelenideElement stateDropdownList = $("#state");
        stateDropdownList.click();
        stateDropdownList.$(byText(state)).click();

        SelenideElement cityDropdownList = $("#city");
        cityDropdownList.click();
        cityDropdownList.$(byXpath(".//*[contains(@class, 'option')]")).click();

        $("#submit").click();

        $("table").shouldHave(
                text(firstName),
                text(lastName),
                text(email),
                text(mobilePhone),
                text(state),
                text(hobby),
                text(favoriteSubject),
                text(address),
                text(formattedDate)
        );
    }
}
