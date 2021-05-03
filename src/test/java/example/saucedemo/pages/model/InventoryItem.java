package example.saucedemo.pages.model;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class InventoryItem extends BaseItem {
    SelenideElement remove,addToCart;

    public InventoryItem(String productName) {
        super(typeEnum.inventory_item,productName);
        addToCart = itemContainer.$x(".//button[contains(text(),'Add to cart')]");
        remove = itemContainer.$x(".//button[contains(text(),'Remove')]");
    }

}
