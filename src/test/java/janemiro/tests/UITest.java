package janemiro.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;

public class UITest extends TestBase {
    @Test
    @Description("Проверка добавления в корзину с баннера с Товаром дня")
    @DisplayName("Товар дня - добавление в корзину")
    @Severity(NORMAL)
    void goodOfTheDayPurchaseTest() {
        step("Открыть " + URL, () -> {
            open(URL);
        });
        sleep(5000); //ждём появления баннера с куками
        step("Закрыть баннер с cookie", () -> {
            $(".CookieInformer_button__1M5XF").click();
        });
        step("Кликнуть на кнопку КУПИТЬ на баннере с Товаром дня", () -> {
            $((".j_add-to-cart-simple")).click();
        });
        step("Проверить поп-ап", () -> {
            $(byText("Товар добавлен в корзину")).click();
        });
        sleep(5000);
        step("Добавить ещё товар со страницы корзины", () -> {
            $("[data-testid=QuantityCounter__plus]").doubleClick();
        });

        step("Проверить, что товаров стало 3", () -> {
            $("[data-testid=FullCart__totalQuantity]").shouldHave(text("3"));

            //негативный тест
            // $("[data-testid=FullCart__totalQuantity]").shouldHave(text("2"));
        });
    }
}
