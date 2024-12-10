package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.CardDataHelper;
import data.DatabaseHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.PaymentPage;
import page.BuyPage;

import static com.codeborne.selenide.Selenide.open;
import static data.CardDataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        DatabaseHelper.deleteTable();
    }

    @Test
    @DisplayName("Successful card payment must be approved")
    void theCardPaymentMustBeApproved() {
        var cardinfo = new CardDataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
        var buyPage = new BuyPage();
        buyPage.BuyCard();// купить
        var form = new PaymentPage();
        form.fillingOutTheForm(cardinfo);
        form.paymentSuccessfull();
        assertEquals("APPROVED", DatabaseHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Card payment must be declined")
    void theCardPaymentMustBeDeclined() {
        var cardinfo = new CardDataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
        var buyPage = new BuyPage();
        buyPage.BuyCreditCard();// купить в кредит
        var form = new PaymentPage();
        form.fillingOutTheForm(cardinfo);
        form.declinedPayment();
        assertEquals("DECLINED", DatabaseHelper.getPaymentStatus());
    }

    // номер карты 0
    @Test
    public void theCardIsNull() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberNull());
        form.declinedPayment();
    }

    // номер карты знаков меньше 16
    @Test
    public void theCardLess16() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberLess());
        form.invalidCardFormat();
    }

    // в номере карты символы
    @Test
    public void theCardSymbol() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberNoValid());
        form.invalidCardFormat();
    }

    // в номере карты буквы кириллицы
    @Test
    public void theCardCyrillic() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberCyrillic());
        form.invalidCardFormat();
    }

    // в номере карты буквы кириллицы
    @Test
    public void theCardLatinalphabet() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberLatin());
        form.invalidCardFormat();
    }

    // Номер карты не заполнен
    @Test
    public void theCardEmpty() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberEmpty());
        form.invalidCardFormat();
    }

    // месяц больше 12
    @Test
    public void monthMoreThan12() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonth13());
        form.invalidCardExpirationDate();
    }

    // месяц 0
    @Test
    public void zeroMonth() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthNull());
        form.monthNotValid();
    }

    // в месяце символ
    @Test
    public void SymbolMonth() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthSymbol());
        form.monthNotValid();
    }

    // в месяце буква
    @Test
    public void letterMonth() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthLetter());
        form.monthNotValid();
    }

    // в месяце одна цифра
    @Test
    public void unformattedMonth() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthUnformatted());
        form.monthNotValid();
    }

    // ввод в поле месяц два нуля
    @Test
    public void MonthTwoZeros() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthNullNull());
        form.monthNotValid();
    }

    // месяц не заполнен
    @Test
    public void emptyMonth() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthempty());
        form.monthNotValid();
    }

    // год меньше текущего
    @Test
    public void lessThanCurrentOneYear() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getYearLessThanTheCurrentOne());
        form.theСardExpired();
    }

    // год больше текущего на 10 лет
    @Test
    public void yearLongerThanTheCurrentObn() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getTheYearIs10YearsLongerThanTheCurrentOne());
        form.invalidCardExpirationDate();
    }

    // в поле года символ
    @Test
    public void yearNotValid() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getYearSymbol());
        form.yearNotValid();
    }

    // в поле года буква
    @Test
    public void yearNotValidSymbol() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getYearLetter());
        form.yearNotValid();
    }

    // в поле года один символ
    @Test
    public void yearNotValidOne() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getYearOne());
        form.yearNotValid();
    }

    // поле года не заполнено
    @Test
    public void yearNotValidEmpty() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getYearEmpty());
        form.yearNotValid();
    }

    // заполнение поля владелец кириллицей
    @Test
    public void thereMustBeAnErrorWhenEnteringTheOwnerInCyrillic() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerCyrillic());
        form.ownerNotValid();
    }

    // ввод в поле владелец символы
    @Test
    public void InTheOwnerFieldTheCharacters() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerSymbol());
        form.ownerNotValid();
    }

    // ввод в поле владелец цифры
    @Test
    public void InTheOwnerFieldDigit() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerFigure());
        form.ownerNotValid();
    }

    // Ввод в поле только одну букву
    @Test
    public void EnteringOnlyOneLetterInTheOwnerField() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerOneLetter());
        form.ownerNotValid();
    }

    // Ввод в поле более ста букв
    @Test
    public void EnteringMoreThan100CharactersInTheOwnerField() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerMoreThan100());
        form.ownerNotValid();
    }

    // Незаполненное поле владелец
    @Test
    public void TheOwnerFieldIsEmpty() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerEmpty());
        form.ownerNotValid();
    }

    // Ввод в поле CVCCVV символы
    @Test
    public void InTheCVCCVVFieldTheCharacters() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVVSymbol());
        form.cvcNotValid();
    }

    // Ввод в поле CVCCVV буквы
    @Test
    public void InTheCVCCVVFieldTheLetters() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVVLetter());
        form.cvcNotValid();
    }

    // Ввод в поле CVCCVV только одну цифру
    @Test
    public void ThereIsOnlyOneDigitInTheCVCCVVField() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVVoneDigit());
        form.cvcNotValid();
    }

    // Ввод в поле CVCCVV только двух цифр
    @Test
    public void ThereIsOnlyTwoDigitInTheCVCCVVField() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVVtwoDigit());
        form.cvcNotValid();
    }

    // Незаполненное поле CVCCVV
    @Test
    public void TheCVCCVVFieldIsEmpty() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVVtwoDigit());
        form.cvcNotValid();
    }

    // Незаполненное поле CVCCVV
    @Test
    public void TheCVCCVVEqual000() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVV000());
        form.cvcNotValid();
    }

    // незаполненная форма
    @Test
    @DisplayName("The form must be filled in")
    void theCardPaymentEmpty() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();// купить
        var form = new PaymentPage();
        form.emptyForm();
    }
}