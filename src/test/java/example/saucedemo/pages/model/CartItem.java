package example.saucedemo.pages.model;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class CartItem extends BaseItem {
    SelenideElement remove,quantity;


    public CartItem(String productName) {
        super(typeEnum.cart_item,productName);
        remove = itemContainer.$x(".//button[contains(text(),'Remove')]");
        quantity = itemContainer.$(By.className("cart_quantity"));
    }

}
