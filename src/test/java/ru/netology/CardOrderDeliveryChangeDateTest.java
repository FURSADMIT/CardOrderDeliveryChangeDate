package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderDeliveryChangeDateTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:7777/");
    }

    @Test
    void positiveRegistrationTest() {

        DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        int daysToAddForFirstMeeting = 4;
        String firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        int daysToAddForSecondMeeting = 7;
        String secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $(".checkbox__box").click();
        $x("//span[contains(text(),'Запланировать')]").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate));

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $x("//span[contains(text(),'Запланировать')]").click();
        $x(".//div [contains(text(), 'У вас уже запланирована встреча на другую дату. Перепланировать?')]")
                .shouldBe(visible);
        $x(".//span[contains(text(), 'Перепланировать')]").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate))
                .shouldBe(visible, Duration.ofSeconds(15));
    }
}




