package example.saucedemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import example.saucedemo.pages.model.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$x;

@Component
public class CheckoutOverviewPage implements PageHeader,BasePage{

    public SelenideElement itemTotalAmountLabel = $x("//div[@class='summary_subtotal_label']");
    public SelenideElement taxAmountLabel = $x("//div[@class='summary_tax_label']");
    public SelenideElement totalAmountLabel = $x("//div[@class='summary_total_label']");

    ElementsCollection cartItemsList = $$x("//div[@class='cart_item']");
    private ElementsCollection cartLabelsList = $$x("//div[@class='cart_item']//div[@class='inventory_item_name']");

    public List<String> getAllItemLabels(){
        return cartLabelsList.stream().map(SelenideElement::getText).collect(Collectors.toList());
    }

    public List<CartItem> getAllInventoryItems(){
        pageTitle.shouldHave(Condition.text("CHECKOUT: OVERVIEW"));
        return getAllItemLabels().stream().map(p-> new CartItem(p)).collect(Collectors.toList());
    }

    public int getItemsCount(){return cartItemsList.size();}

    public SelenideElement cancelLink = $("#cancel");
    public SelenideElement finishLink = $("#finish");



    public void getsLoaded() {
        pageTitle.shouldHave(Condition.text("CHECKOUT: OVERVIEW"));
    }

}
