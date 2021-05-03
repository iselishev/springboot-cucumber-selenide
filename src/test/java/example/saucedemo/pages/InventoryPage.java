package example.saucedemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import example.saucedemo.pages.model.InventoryItem;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

@Component
public class InventoryPage implements PageHeader,BasePage{

    private ElementsCollection inventoryList = $$(By.className("inventory_item"));
    private ElementsCollection inventoryLabelsList = $$x("//div[@class='inventory_item']//div[@class='inventory_item_name']");
    public int getItemsCount(){return inventoryList.size();}
    //
    public List<String> getAllItemLabels(){
        return inventoryLabelsList.stream().map(SelenideElement::getText).collect(Collectors.toList());
    }
    public List<InventoryItem> getAllInventoryItems(){
        this.getsLoaded();
        return getAllItemLabels().stream().map(p-> new InventoryItem(p)).collect(Collectors.toList());
    }

    public InventoryItem getInventoryItemByName(String name){
        return new InventoryItem(name);
    }


    public SelenideElement s= inventoryList.findBy(Condition.text("Sauce Labs Bike Light"));


    private String buildProductLink(String productName, String action){
        return String.format("//div[@class='inventory_item']/div[contains(., '%s')]//button[contains(text(),'%s')]",productName,action);
    }


    public void getsLoaded() {
        pageTitle.shouldHave(Condition.text("PRODUCTS"));
    }
}
