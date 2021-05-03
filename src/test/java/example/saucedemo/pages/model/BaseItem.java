package example.saucedemo.pages.model;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class BaseItem {

    SelenideElement label,description,price;
    SelenideElement itemContainer;
    public enum typeEnum {cart_item, inventory_item}

    public BaseItem(typeEnum type,String productName){
        itemContainer = $x("//div[@class='"+type.toString().toLowerCase()+"']/div[contains(., '"+productName+"')]");

        label = itemContainer.$(By.className("inventory_item_name"));
        description = itemContainer.$(By.className("inventory_item_desc"));
        price = itemContainer.$(By.className("inventory_item_price"));

    }
    @Override
    public String toString(){
        return label.getText() + price.getText();
    }

    public Product getProductInfo(){
        return Product.builder()
                .name(this.getLabel().getText())
                .price(this.getPrice().getText())
                .description(this.getDescription().getText())
                .build();
    }

}
