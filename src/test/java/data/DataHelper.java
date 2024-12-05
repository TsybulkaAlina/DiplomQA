package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.Math.random;
import static java.lang.String.format;

public class DataHelper {
    public static final Faker faker = new Faker(new Locale("en"));

    // Заполнение всех полей валидными данными
    private DataHelper() {
    }
    // Заполнение поля "Номер карты"
    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }
    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }
    // Заполнение поля "Месяц"
    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return format("%02d", localDate.getMonthValue());}

    // Заполнение поля "Год"
    public static String getValidYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }
    // Заполнение поля "Владелец"
    public static String getValidHolder() {
        return faker.name().firstName();
    }
    // Заполнение поля "Код CVC/CVV"
    public static String getValidCodcvccvv() {
        double x = random()*1000;
        int result = (int)Math.ceil(x);
        return String.valueOf(result);
    }

    // Заполнение поля карты невалидными данными
    public static CardInfo getCardNumberNull() {
        return new CardInfo("0000000000000000", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getCardNumberEmpty() {
        return new CardInfo("", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getCardNumberLess() {
        return new CardInfo("123456789", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getCardNumberWithCyrillic() {
        return new CardInfo("12345678901абв41", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getCardNumberWithLatin() {
        return new CardInfo("12345678901abc41", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getCardNumberWithSymbol() {
        return new CardInfo("@@44444444444441", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }


    // Заполнение поля месяц невалидными данными
    public static CardInfo getMonth13() {
        return new CardInfo(getApprovedCardNumber(),"13", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthNull() {
        return new CardInfo(getApprovedCardNumber(),"0", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthSymbol() {
        return new CardInfo(getApprovedCardNumber(),"4?", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthLetter() {
        return new CardInfo(getApprovedCardNumber(),"5y", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthUnformatted() {
        return new CardInfo(getApprovedCardNumber(),"7", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthNullNull() {
        return new CardInfo(getApprovedCardNumber(),"00", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthEmpty() {
        return new CardInfo(getApprovedCardNumber(),"", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }

    // Заполнение поля года невалидными символами
    public static CardInfo getYearLessThanTheCurrentOne() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"23", getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getTheYearIs10YearsLongerThanTheCurrentOne() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"54", getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getYearSymbol() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"2+", getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getYearLetter() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"2G", getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getYearUnformatted() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"5", getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getYearEmpty() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"", getValidHolder(), getValidCodcvccvv());
    }

    // Заполнение поля "Владелец" невалидными данные
    public static CardInfo getOwnerCyrillic() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"Вася", getValidCodcvccvv());
    }
    public static CardInfo getOwnerSymbol() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"Vas$ia", getValidCodcvccvv());
    }
    public static CardInfo getOwnerFigure() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"Va1sia", getValidCodcvccvv());
    }
    public static CardInfo getOwnerOneLetter() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"D", getValidCodcvccvv());
    }
    public static CardInfo getOwnerMoreThan100() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"Eeeeeeeeeeekkkkkkkkkkaaaaaaaaaaattttttttttteeeeeeeeeeerrrrrrrrrrriiiiiiiiiiiinnnnnnnnnnnaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", getValidCodcvccvv());
    }
    public static CardInfo getOwnerEmpty() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"", getValidCodcvccvv());
    }

    // Заполнение поля "CVC/CVV" невалидными данными
    public static CardInfo getCVCCVVSymbol() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"12%");
    }
    public static CardInfo getCVCCVVLetter() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"12A");
    }
    public static CardInfo getCVCCVVoneDigit() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"7");
    }
    public static CardInfo getCVCCVVtwoDigit() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"79");
    }
    public static CardInfo getCVCCVVEmpty() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"");
    }
    public static CardInfo getCVCCVV000() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"000");
    }

    // Данные карты
    @Value
    public static class CardInfo {
        String cardnumber;
        String month;
        String year;
        String holder;
        String codcvccvv;
    }

}
