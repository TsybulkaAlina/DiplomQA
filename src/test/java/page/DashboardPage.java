package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private final SelenideElement Buy = $(byText("Купить"));
    private final SelenideElement Buyaloan = $(byText("Купить в кредит"));
    private final SelenideElement Paymentcard = $(byText("Оплата по карте"));
    private final SelenideElement Creditcard = $(byText("Кредит по данным карты"));

    public DashboardPage BuyByCard() {
        Buy.click(); // Кликаем на кнопку "Купить"
        Paymentcard.shouldBe(visible); // Видим "Оплата по карте"
        return new DashboardPage();
    }

    public DashboardPage BuyByCreditCard() {
        Buyaloan.click(); // Кликаем на кнопку "Купить в кредит"
        Creditcard.shouldBe(visible); // Видим "Кредит по данным карты"
        return new DashboardPage();
    }
}

