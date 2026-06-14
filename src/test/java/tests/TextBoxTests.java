package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testdata.TestData;

import static io.qameta.allure.Allure.step;

@Story("TextBox форма")
public class TextBoxTests extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();
    TestData testData = new TestData();

    @Test
    @DisplayName("Успешное заполнение всей формы TextBox")
    void successfullFillFormTest() {
        step("Открыть страницу TextBox", () ->
                textBoxPage.openPage()
        );

        step("Заполнить все поля формы", () -> {
            textBoxPage.typeUserName(testData.userName)
                    .typeUserEmail(testData.userEmail)
                    .typeCurrentAddress(testData.currentAddress)
                    .typePermanentAddress(testData.permanentAddress)
                    .submitButton();
        });

        step("Проверить результаты", () -> {
            textBoxPage.checkField("name", testData.userName)
                    .checkField("email", testData.userEmail)
                    .checkField("currentAddress", testData.currentAddress)
                    .checkField("permanentAddress", testData.permanentAddress);
        });
    }

    @Test
    @DisplayName("Успешное заполнение формы без адресов")
    void successfullFillFormWithoutAddressTest() {
        step("Открыть страницу TextBox", () ->
                textBoxPage.openPage()
        );

        step("Заполнить только имя и email", () -> {
            textBoxPage.typeUserName(testData.userName)
                    .typeUserEmail(testData.userEmail)
                    .submitButton();
        });

        step("Проверить результаты", () -> {
            textBoxPage.checkField("name", testData.userName)
                    .checkField("email", testData.userEmail);
        });
    }

    @Test
    @DisplayName("Успешное заполнение формы без адресов (цепочка вызовов)")
    void successfullFillFormWithoutAddressTest_chaining() {
        step("Открыть страницу TextBox", () ->
                textBoxPage.openPage()
        );

        step("Заполнить только имя и email с проверкой", () -> {
            textBoxPage.typeUserName(testData.userName)
                    .typeUserEmail(testData.userEmail)
                    .submitButton()
                    .checkField("name", testData.userName)
                    .checkField("email", testData.userEmail);
        });
    }

    @Test
    @DisplayName("Заполнение только поля имени")
    void miniFieldTest() {
        step("Открыть страницу TextBox", () ->
                textBoxPage.openPage()
        );

        step("Заполнить только поле имени", () -> {
            textBoxPage.typeUserName(testData.userName)
                    .submitButton();
        });

        step("Проверить результат", () ->
                textBoxPage.checkField("name", testData.userName)
        );
    }
}