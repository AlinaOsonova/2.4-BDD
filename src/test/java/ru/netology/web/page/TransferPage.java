package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement headTransfer = $(byText("Пополнение карты"));
    private final SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");


    public TransferPage() {
        headTransfer.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amount, DataHelper.CardInfo cardInfo) {
        makeTransfer(amount, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amount, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amount);
        fromInput.setValue(cardInfo.getNumber());
        transferButton.click();
    }


    public void findErrorNotification(String expectedText) {
        errorNotification.should(Condition.and("Проверка сообщения об ошибке", Condition.text(expectedText), Condition.visible));
    }
}

