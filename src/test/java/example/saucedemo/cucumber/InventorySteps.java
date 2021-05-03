package example.saucedemo.cucumber;

import example.saucedemo.pages.model.InventoryItem;
import example.saucedemo.pages.model.Product;
import example.saucedemo.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventorySteps {

    @Autowired
    private InventoryPage inventoryPage;

    @Autowired
    private SharedContent shared;

    @Value("${tests.products.total:6}")
    private int totalProductsStored;


    @Given("total amount of products available is known")
    public void totalAmountOfProductsAvailableIsKnown() {
        // set totalProductsStored based on latest data available in DB or via REST API
        // default value is set to 6 ^
    }

    @And("PRODUCTS page is displayed")
    public void productsPageIsDisplayed() {
        inventoryPage.getsLoaded();
    }


    @Then("amount of products displayed is as expected")
    public void amountOfProductsDisplayedIsAsExpected() {
        assertEquals(totalProductsStored, inventoryPage.getAllInventoryItems().size());
    }

    @And("following products have the details as listed below")
    public void followingProductsHaveTheDetailsAsListedBelow(List<Product> expectedProducts) {
        List<Product> availableProducts = new ArrayList<>();
        List<InventoryItem> displayedItems = inventoryPage.getAllInventoryItems();
        displayedItems.forEach(x -> availableProducts.add(x.getProductInfo()));
    }

    @And("User clicks at 'Add to cart' for the following products")
    public void userClicksAtAddToCartForTheFollowingProducts(List<String> productNames) {
        removeALlItemsFromCartAtInventoryPage();
        productNames.forEach(x -> inventoryPage.getInventoryItemByName(x).getAddToCart().click());
    }

    @And("User clicks at 'Remove' for the following products")
    public void userClicksAtARemoveForTheFollowingProducts(List<String> productNames) {
        productNames.forEach(x -> inventoryPage.getInventoryItemByName(x).getRemove().click());
    }

    @And("Cart icon displays {}")
    public void cartIconDisplays(int itemsInCart) {
        assertEquals(itemsInCart, Integer.valueOf(inventoryPage.shoppingCartTotalBadge.getText()));
    }

    @And("User adds {} products into cart")
    public void userAddsProductsIntoCart(int addedProductsAmount) {
        removeALlItemsFromCartAtInventoryPage();
        if (addedProductsAmount > inventoryPage.getItemsCount())
            throw new IllegalArgumentException("Not enough products are available at screen to be added to cart");
        for (int i = 0; i < addedProductsAmount; i++) {
            inventoryPage.getAllInventoryItems().get(i).getAddToCart().click();
        }
    }

    private void removeALlItemsFromCartAtInventoryPage() {
        inventoryPage.getAllInventoryItems().forEach(x -> {
            if (x.getRemove().isDisplayed()) x.getRemove().click();
        });
    }

    @And("added products are stored as ADDED_PRODUCTS")
    public void addedProductsAreStoredAsADDED_PRODUCTS() {
        shared.getAddedProducts().clear();
        inventoryPage.getAllInventoryItems().forEach(x -> {
            if (x.getRemove().exists()) shared.getAddedProducts().add(x.getProductInfo());
        });
    }

    @When("User clicks at Cart icon")
    public void userClicksAtCartIcon() {
        inventoryPage.shoppingCartIcon.click();
    }


}
