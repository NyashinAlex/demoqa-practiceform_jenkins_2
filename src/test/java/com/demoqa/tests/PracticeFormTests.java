package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.helper.Attach;
import com.demoqa.pages.PracticeFormPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormTests {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("browserName", "chrome");
//        capabilities.setCapability("browserVersion", "100.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
    @Test
    void fillPracticeForm() {

        practiceFormPage.openPage()
                .setFirstName("Alex")
                .setLastName("Nyashin")
                .setUserEmail("nyashin@test.com")
                .setGender("Male")
                .setUserNumber("8905478547")
                .setBirthDate("1996","July","07")
                .setSubjects("Maths")
                .setHobbies("Sports")
                .uploadPicture("user.jpg")
                .setCurrentAddress("Moscow")
                .setState("NCR")
                .setCity("Delhi")
                .clickSubmitButton();

        practiceFormPage.checkVisible()
                .checkTableElement("Student Name","Alex Nyashin")
                .checkTableElement("Student Email","nyashin@test.com")
                .checkTableElement("Gender", "Male")
                .checkTableElement("Mobile", "8905478547")
                .checkTableElement("Date of Birth", "07 July,1996")
                .checkTableElement("Subjects", "Maths")
                .checkTableElement("Hobbies", "Sports")
                .checkTableElement("Picture", "user.jpg")
                .checkTableElement("Address", "Moscow")
                .checkTableElement("State and City", "NCR Delhi");

    }
    @Test
    void fillMinPracticeForm() {

        practiceFormPage.openPage()
                .setFirstName("Alex")
                .setLastName("Nyashin")
                .setGender("Male")
                .setUserNumber("8905478547")
                .setBirthDate("1996","July","07")
                .clickSubmitButton();

        practiceFormPage.checkVisible()
                .checkTableElement("Student Name","Alex Nyashin")
                .checkTableElement("Gender", "Male")
                .checkTableElement("Mobile", "8905478547")
                .checkTableElement("Date of Birth", "07 July,1996");
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}