package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement heading = $("[data-test-id='dashboard']");
    private ElementsCollection cards = $$(".list__item div");


    public DashboardPage() {

        heading.shouldBe(visible);
    }

        public int getCardBalance(DataHelper.CardInfo cardInfo) {
            var text = getCard(cardInfo).getText();
            return extractBalance(text);
        }

        public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
            getCard(cardInfo).$("button").click();
            return new TransferPage();
        }

        private SelenideElement getCard(DataHelper.CardInfo cardInfo) {
            return  cards.findBy(Condition.attribute("data-test-id", cardInfo.getId()));
        }


        private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}
