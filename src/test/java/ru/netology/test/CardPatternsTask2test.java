package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardPatternsTask2test {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldRegisteredActiveUser() {
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        $("span[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("span[data-test-id=password] input").setValue(registeredUser.getPassword());
        $("div [data-test-id=action-login]").click();
        $("h2").shouldHave(Condition.text("Личный кабинет")).shouldBe(Condition.visible);

    }

    @Test
    void shouldRegisteredBlockedUser() {
        var blockRegisteredUser = DataGenerator.Registration.getRegisteredUser("blocked");
        $("span[data-test-id=login] input").setValue(blockRegisteredUser.getLogin());
        $("span[data-test-id=password] input").setValue(blockRegisteredUser.getPassword());
        $("div [data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Ошибка! Пользователь заблокирован")).shouldBe(Condition.visible);

    }

    @Test
    void shouldNotRegisteredActiveUser() {
        var notRegisteredUser = DataGenerator.Registration.getUser("active");
        $("span[data-test-id=login] input").setValue(notRegisteredUser.getLogin());
        $("span[data-test-id=password] input").setValue(notRegisteredUser.getPassword());
        $("div [data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);

    }

    @Test
    void shouldRegisteredActiveUserInvalidLogin() {
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        var wrongLogin = DataGenerator.getRandomLogin();
        $("span[data-test-id=login] input").setValue(wrongLogin);
        $("span[data-test-id=password] input").setValue(registeredUser.getPassword());
        $("div [data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);

    }

    @Test
    void shouldRegisteredActiveUserInvalidPassword() {
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        var wrongPassword = DataGenerator.getRandomPassword();
        $("span[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("span[data-test-id=password] input").setValue(wrongPassword);
        $("div [data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);

    }
}
