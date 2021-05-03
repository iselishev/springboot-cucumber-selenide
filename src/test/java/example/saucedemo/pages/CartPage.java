package example.saucedemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import example.saucedemo.pages.model.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class CartPage implements PageHeader,BasePage{

    public SelenideElement continueShoppingBtn = $x("//button[@name='continue-shopping']");
    public SelenideElement checkoutBtn = $x("//button[@name='checkout']");

    public ElementsCollection cartItemsList = $$x("//div[@class='cart_item']");
    private ElementsCollection cartLabelsList = $$x("//div[@class='cart_item']//div[@class='inventory_item_name']");

    public int getItemsCount(){return cartItemsList.size();}

    public List<String> getAllItemLabels(){
        return cartLabelsList.stream().map(SelenideElement::getText).collect(Collectors.toList());
    }

    public List<CartItem> getAllInventoryItems(){
        this.getsLoaded();
        return getAllItemLabels().stream().map(p-> new CartItem(p)).collect(Collectors.toList());
    }

    public void getsLoaded() {
        pageTitle.shouldHave(Condition.text("YOUR CART"));
    }

}
