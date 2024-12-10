package test;

import data.DatabaseHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.BuyPage;

import static com.codeborne.selenide.Selenide.open;

public class SuccessfulPaymentTest {
    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        DatabaseHelper.deleteTable();
    }

    @Test
    @DisplayName("the Buy form should open")
    public void ShouldFormBuy() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
    }

    @Test
    @DisplayName("a credit purchase form should open")
    public void ShouldFormBuyCredit() {
        var buyPage = new BuyPage();
        buyPage.BuyCreditCard();
    }
}