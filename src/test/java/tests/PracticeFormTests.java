package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testdata.TestData;

import static io.qameta.allure.Allure.step;
import static testdata.TestData.messageAfterSubmitting;
import static testdata.TestData.messagePracticeForm;

@Story("Регистрационная форма")
public class PracticeFormTests extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
    @DisplayName("Успешное заполнение всей формы регистрации")
    void registrationFormTest() {
        step("Открыть страницу регистрации", () ->
                registrationPage.openPage()
        );

        step("Заполнить все поля формы", () -> {
            registrationPage.removeBanners()
                    .practiceForm(messagePracticeForm)
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .typeUserEmail(testData.userEmail)
                    .setGender(testData.genderWrapper)
                    .typeUserNumber(testData.userNumber)
                    .setDateOfBirth(testData.day, testData.month, testData.year)
                    .setSubjects(testData.subjects)
                    .setHobbiesWrapper(testData.hobbies)
                    .setUploadPicture(testData.uploadfile)
                    .setCurrentAddress(testData.currentAddress)
                    .setStateAndCity(testData.state, testData.city)
                    .submitButton();
        });

        step("Проверить результаты в модальном окне", () -> {
            registrationPage.setModalWindow(messageAfterSubmitting)
                    .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Student Email", testData.userEmail)
                    .checkResult("Gender", testData.genderWrapper)
                    .checkResult("Mobile", testData.userNumber)
                    .checkResult("Date of Birth", testData.day + " " + testData.month.substring(0, 3) +
                            " " + testData.year)
                    .checkResult("Subjects", testData.subjects)
                    .checkResult("Hobbies", testData.hobbies)
                    .checkResult("Picture", testData.uploadfile)
                    .checkResult("Address", testData.currentAddress)
                    .checkResult("State and City", testData.state + " " + testData.city);
        });
    }

    @Test
    @DisplayName("Заполнение только обязательных полей формы")
    void requiredTestFields() {
        step("Открыть страницу регистрации", () ->
                registrationPage.openPage()
        );

        step("Заполнить только обязательные поля", () -> {
            registrationPage.removeBanners()
                    .practiceForm(messagePracticeForm)
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .setGender(testData.genderWrapper)
                    .typeUserNumber(testData.userNumber)
                    .submitButton();
        });

        step("Проверить успешное отправление формы", () -> {
            registrationPage.setModalWindow(messageAfterSubmitting)
                    .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Gender", testData.genderWrapper)
                    .checkResult("Mobile", testData.userNumber);
        });
    }

    @Test
    @DisplayName("Негативный тест: неверный формат email")
    void negativeWrongEmailTest() {
        step("Открыть страницу регистрации", () ->
                registrationPage.openPage()
        );

        step("Ввести некорректный email и отправить форму", () -> {
            registrationPage.removeBanners()
                    .practiceForm(messagePracticeForm)
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .typeUserNumber(testData.userNumber)
                    .typeUserEmail(testData.wrongEmail)
                    .setGender(testData.genderWrapper)
                    .submitButton();
        });

        step("Проверить сообщение об ошибке", () ->
                registrationPage.checkFieldResultError()
        );
    }

    @Test
    @DisplayName("Негативный тест: неверный формат номера телефона")
    void negativeWrongNumberTest() {
        step("Открыть страницу регистрации", () ->
                registrationPage.openPage()
        );

        step("Ввести некорректный номер телефона и отправить форму", () -> {
            registrationPage.removeBanners()
                    .practiceForm(messagePracticeForm)
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .typeUserNumber(testData.wrongNumber)
                    .typeUserEmail(testData.userEmail)
                    .setGender(testData.genderWrapper)
                    .submitButton();
        });

        step("Проверить сообщение об ошибке", () ->
                registrationPage.checkFieldResultError()
        );
    }
}