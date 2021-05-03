package example.saucedemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class CheckoutPage implements PageHeader,BasePage{

    public SelenideElement thankYouNote = $x("//h2[@class='complete-header']");
    public SelenideElement backHomeLink = $("#back-to-products");

    @Override
    public void getsLoaded() {
        pageTitle.shouldHave(Condition.text("CHECKOUT: COMPLETE!"));
    }

}
