package example.saucedemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class CheckoutInfoPage implements PageHeader,BasePage {

    public SelenideElement firstName = $("#first-name");
    public SelenideElement lastName = $("#last-name");
    public SelenideElement postalCode = $("#postal-code");
    public SelenideElement continueLink = $("#continue");
    public SelenideElement cancelLink = $("#cancel");
    public SelenideElement errorMessage = $x("//div[@class='error-message-container error']/h3");

    public void getsLoaded() {
        pageTitle.shouldHave(Condition.text("CHECKOUT: YOUR INFORMATION"));
    }
}
