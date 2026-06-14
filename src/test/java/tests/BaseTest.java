package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }

    @BeforeAll
    static void setupSelenideConfig() {

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-guru.github.io";
        Configuration.headless = true;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
//
//        Configuration.browser = System.getProperty("browser", "chrome");
//        Configuration.browserVersion = System.getProperty("browserVersion", "128.0");
//        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
//        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
//        Configuration.baseUrl = System.getProperty("baseUrl", "https://qa-guru.github.io");
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true));
//        Configuration.browserCapabilities = capabilities;
//        Configuration.remote = "https://" +
//                System.getProperty("remoteBrowserUrlLogin") +
//                ":" +
//                System.getProperty("remoteBrowserUrlPassword") +
//                "@" +
//                System.getProperty("remoteBrowserUrl", "selenoid.autotests.cloud/wd/hub");
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
