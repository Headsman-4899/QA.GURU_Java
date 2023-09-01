package guru.qa.pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {
    public void submittingForm () {
        $("#example-modal-sizes-title-lg")
                .shouldHave(text("Thanks for submitting the form"));
    }

    public void formResoults (String key, String value) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
    }
}
