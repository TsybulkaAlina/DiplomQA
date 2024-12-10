package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BuyPage {
    private final SelenideElement Buy = $(byText("Купить"));
    private final SelenideElement Buyaloan = $(byText("Купить в кредит"));
    private final SelenideElement Paymentcard = $(byText("Оплата по карте"));
    private final SelenideElement Creditcard = $(byText("Кредит по данным карты"));

    public HomePage BuyCard() {
        Buy.click(); //нажимаем кнопку купить
        Paymentcard.shouldBe(visible); // видим оплата по карте
        return new HomePage();
    }

    public HomePage BuyCreditCard() {
        Buyaloan.click(); //нажимаем кнопку Купить в кредит
        Creditcard.shouldBe(visible); // видим Кредит по данным карты
        return new HomePage();
    }
}