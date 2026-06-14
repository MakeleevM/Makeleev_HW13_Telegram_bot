package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

    private final SelenideElement userNameInput = $("#userName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement outputResults = $("#output");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");


    public TextBoxPage openPage() {
        open("/one-page-form/text-box.html");

        return this;
    }

    public TextBoxPage typeUserName(String value) {
        userNameInput.setValue(value);

        return this;
    }

    public TextBoxPage typeUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public TextBoxPage submitButton() {
        submitButton.click();

        return this;
    }

    public TextBoxPage typeCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage typePermanentAddress(String value) {
        permanentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage checkField(String key, String value) {
        outputResults.$(byId(key)).shouldHave(text(value));

        return this;
    }
}
